package ch15b;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.time.format.DateTimeFormatter.ofLocalizedTime;

public class LotsOfReaders {
  public static void main(String[] args) throws InterruptedException {
    List<Chat> chatHistory = new CopyOnWriteArrayList<>();
    ExecutorService executor = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      executor.execute(() -> chatHistory.add(new Chat("Hi there!")));
      executor.execute(() -> printChatHistory("Reader 1", chatHistory));
      executor.execute(() -> printChatHistory("Reader 2", chatHistory));
    }
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
  }

  private static void printChatHistory(String name, List<Chat> chatHistory) {
    for (Chat chat : chatHistory) {
      System.out.println(name + " read: " + chat);
    }
  }
}

class Chat {
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
