package ch15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LotsOfReaders {
  public static void main(String[] args) {
    ChatHistory chatHistory = new ChatHistory();
    ExecutorService executor = Executors.newFixedThreadPool(4);
    for (int i = 0; i < 5; i++) {
      final int iteration = i;
      executor.execute(() -> chatHistory.add("Hi there! " + iteration));
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

  public Chat(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }
}
