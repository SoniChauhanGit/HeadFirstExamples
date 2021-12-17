package ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {
  private ArrayList<SocketChannel> clients;

  public static void main(String[] args) {
    new SimpleChatServer().go();
  }

  public void go() {
    clients = new ArrayList<>();
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      InetSocketAddress address = new InetSocketAddress("127.0.0.1", 5000);
      serverSocketChannel.bind(address);

      while (serverSocketChannel.isOpen()) {
        SocketChannel socketChannel = serverSocketChannel.accept();

        clients.add(socketChannel);
        Thread t = new Thread(new ClientHandler(socketChannel));
        t.start();
        System.out.println("got a connection");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  } // close go

  public class ClientHandler implements Runnable {
    SocketChannel socketChannel;

    public ClientHandler(SocketChannel socketChannel) {
      this.socketChannel = socketChannel;
    }

    public void run() {
      ByteBuffer buffer = ByteBuffer.allocate(1024);
      buffer.clear();
      try {
        int read = socketChannel.read(buffer);
        System.out.println(read);
        ByteBuffer byteBuffer = buffer.flip();
        tellEveryone(byteBuffer);
        System.out.println("told");
      } catch (IOException e) {
        e.printStackTrace();
      }

    } // close run

    public void tellEveryone(ByteBuffer byteBuffer) {
      Iterator<SocketChannel> it = clients.iterator();
      while (it.hasNext()) {
        try {
          SocketChannel channel = it.next();
          while(byteBuffer.hasRemaining()) {
            channel.write(byteBuffer);
          }

        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } // end while
    } // close tellEveryone
  } // close ClientHandler
} // close class