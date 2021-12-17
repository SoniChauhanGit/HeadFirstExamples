package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSyncTest {
  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(2);
    Balance balance = new Balance();
    pool.execute(() -> incrementBalance50Times(balance));
    pool.execute(() -> incrementBalance50Times(balance));
    pool.shutdown();
  }

  private static void incrementBalance50Times(Balance balance) {
    for (int i = 0; i < 50; i++) {
      balance.increment();
      System.out.printf("Balance updated by %s. Balance is %d%n", Thread.currentThread().getName(), balance.balance);
    }
  }
}

class Balance {
  int balance;

  public void increment() {
    balance++;
  }
}

