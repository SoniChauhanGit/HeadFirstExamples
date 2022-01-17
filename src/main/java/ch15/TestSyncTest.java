package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestSyncTest {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(6);
    Balance balance = new Balance();
    for (int i = 0; i < 1000; i++) {
      pool.execute(() -> incrementBalance(balance));
    }
    pool.shutdown();
    // make sure the pool has finished running all the updates before printing the output
    if (pool.awaitTermination(1, TimeUnit.MINUTES)) {
      System.out.println("balance = " + balance.balance);
    }
  }

  private static void incrementBalance(Balance balance) {
      balance.increment();
  }
}

class Balance {
  int balance = 0;

  public void increment() {
    balance++;
  }
}

