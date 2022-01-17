package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestSyncTest {
  public static void main(String[] args) throws InterruptedException {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    Balance balance = new Balance();
    pool.execute(() -> incrementBalance50Times(balance));
    pool.execute(() -> incrementBalance50Times(balance));
    pool.shutdown();
    pool.awaitTermination(10, TimeUnit.SECONDS);
  }

  private static void incrementBalance50Times(Balance balance) {
    for (int i = 0; i < 50; i++) {
      balance.increment();
      System.out.printf("Balance updated by %s. Balance is %d%n", Thread.currentThread().getName(), balance.balance);
    }
  }
}

class Balance {
  int balance = 0;

  public void increment() {
    int i = balance;
    try {
      Thread.sleep(1);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    balance = i + 1;
  }
}

