package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreads {
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.execute(new ThreadOne());
    pool.execute(new ThreadTwo());
    pool.shutdown();
  }
}

class Accum {
  private static final Accum a = new Accum();
  private int counter = 0;

  private Accum() {
  }

  // singleton
  public static Accum getAccum() {
    return a;
  }

  public synchronized void updateCounter(int add) {
    counter += add;
  }

  public int getCount() {
    return counter;
  }
}

class ThreadOne implements Runnable {
  Accum a = Accum.getAccum();

  public void run() {
    for (int i = 0; i < 98; i++) {
      a.updateCounter(1000);
      try {
        Thread.sleep(50);
      } catch (InterruptedException ex) {
        System.out.println("ThreadOne was interrupted!");
      }
    }
    System.out.println("one " + a.getCount());
  }
}

class ThreadTwo implements Runnable {
  Accum a = Accum.getAccum();

  public void run() {
    for (int i = 0; i < 99; i++) {
      a.updateCounter(1);
      try {
        Thread.sleep(50);
      } catch (InterruptedException ex) {
        System.out.println("ThreadTwo was interrupted!");
      }
    }
    System.out.println("two " + a.getCount());
  }
}