package ch15.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SynchronizedCollection {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    Data data = new DataSynchronized();
    threadPool.execute(new AddLowerCaseJob(data));
    threadPool.execute(new AddUpperCaseJob(data));
    threadPool.shutdown();
  }
}

class DataSynchronized implements Data {
  private final List<String> letters = new ArrayList<>();

  public List<String> getLetters() {
    return letters;
  }

  public synchronized void addLetter(char letter) {
    letters.add(String.valueOf(letter));
  }
}
