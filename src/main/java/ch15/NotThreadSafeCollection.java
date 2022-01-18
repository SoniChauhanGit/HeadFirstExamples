package ch15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotThreadSafeCollection {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(new JobOne());
    threadPool.execute(new JobTwo());
    threadPool.shutdown();
  }
}

class Data {
  private final List<String> letters = new ArrayList<>();
  private final static Data instance = new Data();

  private Data() {
  }

  public static Data getInstance() {
    return instance;
  }

  public List<String> getLetters() {
    return letters;
  }

  public void addLetter(char letter) {
    letters.add(String.valueOf(letter));
  }
}

class JobOne implements Runnable {
  Data a = Data.getInstance();
  char letter = 'A';

  public void run() {
    for (int x = 0; x < 26; x++) {
      a.addLetter(letter++);
      try {
        Thread.sleep(50);
      } catch (InterruptedException ignored) {
      }
    }
    System.out.println("one " + a.getLetters());
    System.out.println("a.getLetters().size() = " + a.getLetters().size());
  }
}

class JobTwo implements Runnable {
  Data a = Data.getInstance();
  char letter = 'a';

  public void run() {
    for (int x = 0; x < 26; x++) {
      a.addLetter(letter++);
      try {
        Thread.sleep(50);
      } catch (InterruptedException ignored) {
      }
    }
    System.out.println("two " + a.getLetters());
    System.out.println("a.getLetters().size() = " + a.getLetters().size());
  }
}
