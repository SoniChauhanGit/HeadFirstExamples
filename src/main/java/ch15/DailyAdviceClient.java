package ch15;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class DailyAdviceClient {

  public void go() {
    try {
      InetSocketAddress address = new InetSocketAddress("localhost", 4242);
      SocketChannel socketChannel = SocketChannel.open(address);

      ByteBuffer buffer = ByteBuffer.allocate(256);
      socketChannel.read(buffer);
      String result = new String(buffer.array()).trim();

      System.out.println("Today you should: " + result);

      socketChannel.close();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public static void main(String[] args) {
    DailyAdviceClient client = new DailyAdviceClient();
    client.go();
  }
}