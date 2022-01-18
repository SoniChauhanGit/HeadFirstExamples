package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RyanAndMonicaAtomicTest {
  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    BankAccountWithAtomic account = new BankAccountWithAtomic();
    RyanAndMonicaAtomicJob ryan = new RyanAndMonicaAtomicJob("Ryan", account);
    RyanAndMonicaAtomicJob monica = new RyanAndMonicaAtomicJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
}

class RyanAndMonicaAtomicJob implements Runnable {
  private final String name;
  private final BankAccountWithAtomic account;

  RyanAndMonicaAtomicJob(String name, BankAccountWithAtomic account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    for (int i = 0; i < 15; i++) {
      account.makeWithdrawal(10, name);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
    }
  }
}

class BankAccountWithAtomic {
  private final AtomicInteger balance = new AtomicInteger(100);

  public int getBalance() {
    return balance.get();
  }

  public void makeWithdrawal(int amount, String name) {
    int initialBalance = balance.get();
    if (initialBalance >= amount) {
      System.out.println(name + " is about to withdraw");
      try {
        System.out.println(name + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {ex.printStackTrace();}
      System.out.println(name + " woke up.");
      boolean success = balance.compareAndSet(initialBalance, initialBalance - amount);
      if (success) {
        System.out.println(name + " completes the withdrawal.");
      } else {
        System.out.println("Sorry " + name + ", try again");
      }
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}


