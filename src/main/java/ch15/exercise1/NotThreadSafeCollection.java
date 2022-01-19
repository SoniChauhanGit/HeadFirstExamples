package ch15.exercise1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotThreadSafeCollection {
  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    DataImpl data = new DataImpl();
    threadPool.execute(new AddLowerCaseJob(data));
    threadPool.execute(new AddUpperCaseJob(data));
    threadPool.shutdown();
  }
}

class DataImpl implements Data {
  private final List<String> letters = new ArrayList<>();

  public List<String> getLetters() {
    return letters;
  }

  public void addLetter(char letter) {
    letters.add(String.valueOf(letter));
  }
}
