package ch15;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClosingTime {
  public static void main(String[] args) {
    forceShutdown();
  }

  static void shutdownGracefully() {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(() -> longJob("Long Job 1"));
    threadPool.execute(() -> shortJob("Short Job"));
    threadPool.execute(() -> shortJob("Long Job 2"));
    threadPool.execute(() -> shortJob("Shouldn't start job"));
    threadPool.shutdown();
    try {
      System.out.println("Finished? " + threadPool.awaitTermination(1, TimeUnit.SECONDS));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void forceShutdown() {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(() -> longJob("Long Job 1"));
    threadPool.execute(() -> shortJob("Short Job"));
    threadPool.execute(() -> longJob("Long Job 2"));
    threadPool.execute(() -> shortJob("Shouldn't start job"));
    List<Runnable> unfinished = threadPool.shutdownNow();
    System.out.println("unfinished.size() = " + unfinished.size());
    for (Runnable runnable : unfinished) {
      System.out.println(runnable);
    }
//    try {
//      System.out.println("Finished? " + threadPool.awaitTermination(1, TimeUnit.SECONDS));
//    } catch (InterruptedException e) {
//      e.printStackTrace();
//    }
  }

  private static void longJob(String jobName) {
    // use sleep() to pretend like this is a long-running job
    try {
      TimeUnit.SECONDS.sleep(3);
      System.out.println(jobName);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void shortJob(String jobName) {
    System.out.println(jobName);
  }


  class ShortJob implements Runnable {
    private String jobName;

    ShortJob(String jobName) {
      this.jobName = jobName;
    }

    public void run() {
      System.out.println(jobName);
    }
  }

  class LongJob implements Runnable {
    private String jobName;

    LongJob(String jobName) {
      this.jobName = jobName;
    }

    public void run() {
      try {
        TimeUnit.SECONDS.sleep(3);
        System.out.println(jobName);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
