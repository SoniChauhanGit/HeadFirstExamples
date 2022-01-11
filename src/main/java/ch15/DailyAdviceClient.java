package ch15;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class DailyAdviceClient {
  public static void main(String[] args) {
    DailyAdviceClient client = new DailyAdviceClient();
    client.go();
  }

  public void go() {
    try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 4242))) {
      BufferedReader reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));
      String advice = reader.readLine();
      System.out.println("Today you should: " + advice);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}