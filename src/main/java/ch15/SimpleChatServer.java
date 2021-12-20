package ch15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {
  private final ExecutorService executorService = Executors.newCachedThreadPool();

  public static void main(String[] args) {
    new SimpleChatServer().go();
  }

  public void go() {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress("localhost", 5000));

      Broadcaster broadcaster = new Broadcaster();
      while (serverSocketChannel.isOpen()) {
        SocketChannel clientSocket = serverSocketChannel.accept();
        broadcaster.addClient(clientSocket);
        executorService.execute(new ClientHandler(clientSocket, broadcaster));
        System.out.println("got a connection");
      }
      serverSocketChannel.close();
      executorService.shutdown();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}

class ClientHandler implements Runnable {
  private final SocketChannel clientSocket;
  private final Broadcaster broadcaster;

  public ClientHandler(SocketChannel clientSocket, Broadcaster broadcaster) {
    this.clientSocket = clientSocket;
    this.broadcaster = broadcaster;
  }

  public void run() {
    ByteBuffer buffer = ByteBuffer.allocate(80);
    System.out.println("ClientHandler.run");
    try {
      int length;
      while ((length = clientSocket.read(buffer)) != -1) {
        System.out.println(length);
        System.out.println("buffer = " + buffer);
//        System.out.println("received: " + new String(buffer.array()).trim());
        buffer.flip();
        System.out.println("buffer = " + buffer);
        while (buffer.hasRemaining()) {
          clientSocket.write(buffer);
        }
        buffer.compact();
      }
      clientSocket.close();
      System.out.println("Closed: " + clientSocket);
      broadcaster.remove(clientSocket);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

class Broadcaster {
  private final ArrayList<SocketChannel> clientOutputStreams = new ArrayList<>();

  void addClient(SocketChannel clientSocket) {
    clientOutputStreams.add(clientSocket);
  }

  public void tellEveryone(ByteBuffer message) {
    for (SocketChannel channel : clientOutputStreams) {
      try {
        channel.write(message);
        message.rewind();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
    message.flip();
  }

  public void remove(SocketChannel clientSocket) {
    clientOutputStreams.remove(clientSocket);
  }
}