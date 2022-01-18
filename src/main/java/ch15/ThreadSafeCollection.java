package ch15;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeCollection {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(new AddUpperCaseJob());
    threadPool.execute(new AddLowerCaseJob());
    threadPool.shutdown();
  }
}

class DataSafe {
  private final List<String> letters = new CopyOnWriteArrayList<>();
  private final static DataSafe instance = new DataSafe();

  private DataSafe() {
  }

  public static DataSafe getInstance() {
    return instance;
  }

  public List<String> getLetters() {
    return letters;
  }

  public void addLetter(char letter) {
    letters.add(String.valueOf(letter));
  }
}

class AddUpperCaseJob implements Runnable {
  DataSafe a = DataSafe.getInstance();
  char letter = 'A';

  public void run() {
    for (int i = 0; i < 26; i++) {
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

class AddLowerCaseJob implements Runnable {
  DataSafe a = DataSafe.getInstance();
  char letter = 'a';

  public void run() {
    for (int i = 0; i < 26; i++) {
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
