package ch15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class DailyAdviceServer {

  String[] adviceList = {"Take smaller bites",
          "Go for the tight jeans. No they do NOT make you look fat.",
          "One word: inappropriate",
          "Just for today, be honest. Tell your boss what you *really* think",
          "You might want to rethink that haircut."};

  public void go() {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      InetSocketAddress address = new InetSocketAddress("localhost", 4242);
      serverSocketChannel.bind(address);

      while (serverSocketChannel.isOpen()) {
        SocketChannel socketChannel = serverSocketChannel.accept();

        String advice = getAdvice();
        byte[] message = advice.getBytes();
        ByteBuffer buffer = ByteBuffer.wrap(message);

        socketChannel.write(buffer);

        socketChannel.close();
        System.out.println(advice);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private String getAdvice() {
    return adviceList[(int) (Math.random() * adviceList.length)];
  }

  public static void main(String[] args) {
    DailyAdviceServer server = new DailyAdviceServer();
    server.go();
  }
}