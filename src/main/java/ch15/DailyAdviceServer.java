package ch15;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
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
      ServerSocket serverSock = new ServerSocket(4242);
      while (true) {
        Socket sock = serverSock.accept();

        PrintWriter writer = new PrintWriter(sock.getOutputStream());
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