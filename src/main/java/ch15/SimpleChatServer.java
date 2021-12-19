package ch15;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
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
    } catch (Exception ex) {
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
    ByteBuffer buffer = ByteBuffer.allocate(32);
    buffer.clear();
    System.out.println("running");
    try {
      while (clientSocket.isOpen()) {
        System.out.println("clientSocket = " + clientSocket.isOpen());
        clientSocket.read(buffer);
        String message = new String(buffer.array()).trim();

        System.out.println("read " + message);
        broadcaster.tellEveryone(message);
        buffer.clear();
      }
      clientSocket.close();
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

  public void tellEveryone(String message) {
    System.out.println("message = " + message);
    byte[] byteArray = message.getBytes();
    ByteBuffer buffer = ByteBuffer.wrap(byteArray);

    Iterator<SocketChannel> it = clientOutputStreams.iterator();
    while (it.hasNext()) {
      SocketChannel next = it.next();
      System.out.println("Telling: " + next);
      try {
        next.write(buffer);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}