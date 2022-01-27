package ch15b;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.concurrent.*;

import static java.time.format.DateTimeFormatter.ofLocalizedTime;

public class ConcurrentReaders {
  public static void main(String[] args) throws InterruptedException {
    List<Chat> chatHistory = new ArrayList<>();
    ExecutorService executor = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      executor.execute(() -> chatHistory.add(new Chat("Hi there!")));
      executor.execute(() -> System.out.println(chatHistory));
      executor.execute(() -> System.out.println(chatHistory));
    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
  }

}

final class Chat {
  private final String message;
  private final LocalDateTime timestamp;

  public Chat(String message) {
    this.message = message;
    timestamp = LocalDateTime.now();
  }

  public String toString() {
    String time = timestamp.format(ofLocalizedTime(FormatStyle.MEDIUM));
    return time + " " + message;
  }
}
