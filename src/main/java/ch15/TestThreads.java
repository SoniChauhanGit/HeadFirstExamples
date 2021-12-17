package ch15;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestThreads {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    Future<?> future1 = pool.submit(() -> {
      Accum a = Accum.getInstance();
      for (int i = 0; i < 98; i++) {
        a.updateCounter(1000);
      }
    });
    System.out.println(future1.isDone());
    Future<?> future2 = pool.submit(() -> {
      Accum a = Accum.getInstance();
      for (int i = 0; i < 99; i++) {
        a.updateCounter(1);
      }
    });
    pool.shutdown();
    System.out.println(Accum.getInstance().getCount());
  }
}

class Accum {
  private static final Accum a = new Accum();
  private int counter = 0;

  private Accum() {
  }

  // singleton
  public static Accum getInstance() {
    return a;
  }

  public void updateCounter(int add) {
    counter += add;
  }

  public int getCount() {
    return counter;
  }
}
