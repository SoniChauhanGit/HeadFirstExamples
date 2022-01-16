package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPools {

  public static void main(String[] args) {
    createCachedThreadPool();
  }

  private static void createCachedThreadPool() {
    ExecutorService threadPool = Executors.newCachedThreadPool();
    threadPool.execute(() -> run("Job 1"));
    threadPool.execute(() -> run("Job 2"));
    threadPool.execute(() -> run("Job 3"));
    threadPool.execute(() -> run("Job 4"));
    threadPool.shutdown();
  }

  private static void createFixedThreadPoolWithMoreJobsThanThreads() {
    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    threadPool.execute(() -> run("Job 1"));
    threadPool.execute(() -> run("Job 2"));
    threadPool.execute(() -> run("Job 3"));
    threadPool.execute(() -> run("Job 4"));
    threadPool.shutdown();
  }

  public static void run(String jobName) {
    for (int i = 0; i < 25; i++) {
      String threadName = Thread.currentThread().getName();
      System.out.println(jobName + " is running on thread " + threadName);
    }
  }
}