package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnpredictableRunOrder {

}

class RunWithThread {
  public static void main (String[] args) {
    for (int i = 0; i < 20; i++) {
      runThread();
    }
  }

  private static void runThread() {
    Thread myThread = new Thread(() -> System.out.println("top o’ the stack"));
    myThread.start();
    System.out.println("back in main");
  }
}

class RunWithExecutor {
  public static void main (String[] args) {
    ExecutorService executor = Executors.newSingleThreadExecutor();
    for (int i = 0; i < 100; i++) {
      executeRunnable(executor);
    }

    executor.shutdown();
  }

  private static void executeRunnable(ExecutorService executor) {
    executor.execute(() -> System.out.println("top o’ the stack"));

    System.out.println("back in main");
  }
}

