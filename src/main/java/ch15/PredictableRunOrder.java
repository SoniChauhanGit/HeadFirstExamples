package ch15;

import java.util.concurrent.*;

public class PredictableRunOrder {
  public static void main(String[] args) throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      PredictableSleep.main(args);
    }
  }
}

class PredictableSleep {
  public static void main (String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> sleepThenPrint());
    System.out.println("back in main");
    executor.shutdown();
  }

  private static void sleepThenPrint() {
    try {
      Thread.sleep(120000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("top o’ the stack");
  }
}

class SleepWithTimeUnit {
  public static void main (String[] args) throws InterruptedException {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(() -> sleepThenPrint());
    System.out.println("back in main");
    executor.shutdown();
  }

  private static void sleepThenPrint() {
    try {
      TimeUnit.MINUTES.sleep(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("top o’ the stack");
  }
}
