package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunThreads {

  public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(() -> run("Job 1"));
    threadPool.execute(() -> run("Job 2"));
    threadPool.shutdown();
  }

  public static void run(String jobName) {
    for (int i = 0; i < 25; i++) {
      String threadName = Thread.currentThread().getName();
      System.out.println(jobName + " is running on thread " + threadName);
    }
  }
}