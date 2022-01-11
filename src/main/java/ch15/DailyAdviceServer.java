package ch15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.Channels;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Random;

public class DailyAdviceServer {
  final private String[] adviceList = {
          "Take smaller bites",
          "Go for the tight jeans. No they do NOT make you look fat.",
          "One word: inappropriate",
          "Just for today, be honest. Tell your boss what you *really* think",
          "You might want to rethink that haircut."};
  private final Random randomGenerator = new Random();

  public static void main(String[] args) {
    DailyAdviceServer server = new DailyAdviceServer();
    server.go();
  }

  public void go() {
    try {
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.bind(new InetSocketAddress("localhost", 4242));

      while (true) {
        SocketChannel sock = serverSocketChannel.accept();

        PrintWriter writer = new PrintWriter(Channels.newOutputStream(sock));
        String advice = getAdvice();
        writer.println(advice);
        writer.close();
        System.out.println(advice);
      }

    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  private String getAdvice() {
    return adviceList[randomGenerator.nextInt(adviceList.length)];
  }
}