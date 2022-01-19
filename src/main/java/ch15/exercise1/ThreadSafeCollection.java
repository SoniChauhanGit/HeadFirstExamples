package ch15.exercise1;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeCollection {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    Data data = new DataSafe();
    threadPool.execute(new AddUpperCaseJob(data));
    threadPool.execute(new AddLowerCaseJob(data));
    threadPool.shutdown();
  }
}

class DataSafe implements Data {
  private final List<String> letters = new CopyOnWriteArrayList<>();

  public List<String> getLetters() {
    return letters;
  }

  public void addLetter(char letter) {
    letters.add(String.valueOf(letter));
  }
}

