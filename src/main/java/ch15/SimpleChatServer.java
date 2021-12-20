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
    try {
      while (clientSocket.read(buffer) != -1) {
        buffer.flip();
        broadcaster.tellEveryone(buffer);
        buffer.clear();
      }
      disconnectClient();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void disconnectClient() throws IOException {
    clientSocket.close();
    System.out.println("Closed: " + clientSocket);
    broadcaster.remove(clientSocket);
  }
}

class Broadcaster {
  private final ArrayList<SocketChannel> clientOutputStreams = new ArrayList<>();

  void addClient(SocketChannel clientSocket) {
    clientOutputStreams.add(clientSocket);
  }

  public void tellEveryone(ByteBuffer buffer) {
    for (SocketChannel channel : clientOutputStreams) {
      try {
        while (buffer.hasRemaining()) {
          channel.write(buffer);
        }
        buffer.rewind();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public void remove(SocketChannel clientSocket) {
    clientOutputStreams.remove(clientSocket);
  }
}