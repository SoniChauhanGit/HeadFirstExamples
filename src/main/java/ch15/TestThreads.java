package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreads {
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.execute(() -> {
      Accum a = Accum.getInstance();
      for (int i = 0; i < 98; i++) {
        a.updateCounter(1000);
      }
    });
    pool.execute(() -> {
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
