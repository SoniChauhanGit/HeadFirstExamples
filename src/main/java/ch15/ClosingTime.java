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
    threadPool.execute(new LongJob("Long Job 1"));
    threadPool.execute(new ShortJob("Short Job"));
    threadPool.execute(new ShortJob("Long Job 2"));
    threadPool.execute(new ShortJob("Shouldn't start job"));
    threadPool.shutdown();
    try {
      System.out.println("Finished? " + threadPool.awaitTermination(1, TimeUnit.SECONDS));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  static void forceShutdown() {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(new LongJob("Long Job 1"));
    threadPool.execute(new ShortJob("Short Job"));
    threadPool.execute(new LongJob("Long Job 2"));
    threadPool.execute(new ShortJob("Shouldn't start job"));
    List<Runnable> unfinished = threadPool.shutdownNow();
    System.out.println("unfinished.size() = " + unfinished.size());
    for (Runnable runnable : unfinished) {
      System.out.println(((NamedJob) runnable).getJobName());
    }
  }

}

abstract class NamedJob implements Runnable {
  protected String jobName;

  NamedJob(String jobName) {
    this.jobName = jobName;
  }

  public String getJobName() {
    return jobName;
  }
}


class ShortJob extends NamedJob {
  ShortJob(String jobName) {
    super(jobName);
  }

  public void run() {
    System.out.println(jobName);
  }
}

class LongJob extends NamedJob {
  LongJob(String jobName) {
    super(jobName);
  }

  public void run() {
    try {
      TimeUnit.SECONDS.sleep(3);
      System.out.println(jobName);
    } catch (InterruptedException e) {
      System.out.println("Job interrupted: " + jobName);
      e.printStackTrace();
    }
  }
}
