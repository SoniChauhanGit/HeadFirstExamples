package ch15;

import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.format.DateTimeFormatter.ofLocalizedTime;

public class LotsOfReaders {
  public static void main(String[] args) {
    ChatHistory chatHistory = new ChatHistory();
    ExecutorService executor = Executors.newFixedThreadPool(3);
    for (int i = 0; i < 5; i++) {
      executor.execute(() -> chatHistory.add("Hi there!"));
      executor.execute(() -> printChatHistory("Reader 1", chatHistory));
      executor.execute(() -> printChatHistory("Reader 2", chatHistory));
    }
    executor.shutdown();
  }

  private static void printChatHistory(String name, ChatHistory chatHistory) {
    for (Chat chat : chatHistory.getHistory()) {
      System.out.println(name + " read: " + chat);
    }
  }
}

class ChatHistory {
  private final List<Chat> history = new ArrayList<>();

  public List<Chat> getHistory() {
    return history;
  }

  public void add(String message) {
    history.add(new Chat(message));
  }
}

class Chat {
  private final String message;
  private final LocalDateTime timestamp;

  public Chat(String message) {
    this.message = message;
    timestamp = LocalDateTime.now();
  }

  @Override
  public String toString() {
    String time = timestamp.format(ofLocalizedTime(FormatStyle.MEDIUM));
    return time + " " + message;
  }
}
