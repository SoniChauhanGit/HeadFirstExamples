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
      executor.execute(new Chatty(chatHistory));
      executor.execute(new ChatReader(chatHistory));
      executor.execute(new ChatReader(chatHistory));
    }
    executor.shutdown();
  }

}

class ChatReader implements Runnable {
  private ChatHistory chatHistory;

  public ChatReader(ChatHistory chatHistory) {
    this.chatHistory = chatHistory;
  }

  @Override
  public void run() {
    for (Chat chat : chatHistory.getHistory()) {
      System.out.println(chat);
    }
  }
}


class Chatty implements Runnable {
  private ChatHistory chatHistory;

  public Chatty(ChatHistory chatHistory) {
    this.chatHistory = chatHistory;
  }

  @Override
  public void run() {
    chatHistory.add("Hi there!");
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
  private String message;

  public Chat(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return message;
  }
}
