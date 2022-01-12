package ch15;

import java.io.BufferedReader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatServer {
  ArrayList<Writer> clientOutputStreams;

  public static void main(String[] args) {
    new SimpleChatServer().go();
  }

  public void go() {
    clientOutputStreams = new ArrayList<>();
    ExecutorService threadPool = Executors.newFixedThreadPool(5);
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress(5000));

      while (serverSocketChannel.isOpen()) {
        SocketChannel clientSocket = serverSocketChannel.accept();
        Writer writer = Channels.newWriter(clientSocket, StandardCharsets.UTF_8);
        clientOutputStreams.add(writer);
        threadPool.submit(new ClientHandler(clientSocket));
        System.out.println("got a connection");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  } // close go

  public void tellEveryone(String message) {
    for (Writer writer : clientOutputStreams) {
      try {
        writer.append(message).write("\n");
        writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } // end while
  } // close tellEveryone

  public class ClientHandler implements Runnable {
    BufferedReader reader;
    SocketChannel socket;

    public ClientHandler(SocketChannel clientSocket) {
      try {
        socket = clientSocket;
        reader = new BufferedReader(Channels.newReader(socket, StandardCharsets.UTF_8));
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } // close constructor

    public void run() {
      String message;
      try {
        while ((message = reader.readLine()) != null) {
          System.out.println("read " + message);
          tellEveryone(message);
        } // close while
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } // close run
  } // close inner class
} // close class