package ch15a.magnet;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.TimeUnit;

import static java.nio.charset.StandardCharsets.UTF_8;

public class PingingClient {

  public static void main(String[] args) {
    new PingingClient().go();
  }

  public void go() {
    InetSocketAddress serverAddress = new InetSocketAddress("127.0.0.1", 5000);
    try (SocketChannel socketChannel = SocketChannel.open(serverAddress)) {
      PrintWriter writer = new PrintWriter(Channels.newWriter(socketChannel, UTF_8));
      System.out.println("Networking established");

      for (int i = 0; i < 10; i++) {
        String message = "ping " + i;
        writer.println(message);
        writer.flush();

        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)) + " Sent " + message);
        TimeUnit.SECONDS.sleep(1);
      }
    } catch (IOException | InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}