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
//    Thread thread = new Thread(() -> sleepThenPrint());
//    thread.start();
//    Thread.sleep(1000L);
//    System.out.println(thread.getState());
    System.out.println("back in main");
//    executor.awaitTermination(1100L, TimeUnit.MILLISECONDS);
//    executor.shutdown();
  }

  private static void sleepThenPrint() {
    try {
      Thread.sleep(10_000L);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("top o’ the stack");
  }
}
