package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunJobs {
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    pool.execute(RunJobs::printThreadName);
    pool.execute(RunJobs::printThreadName);
    pool.shutdownNow();
  }

  public static void printThreadName() {
    for (int i = 0; i < 25; i++) {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + " is running");
    }
  }
}