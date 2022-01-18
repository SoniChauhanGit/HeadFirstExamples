package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaSynchronizedTest {
  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    BankAccountSynchronized account = new BankAccountSynchronized();
    RyanAndMonicaSynchronizedJob ryan = new RyanAndMonicaSynchronizedJob("Ryan", account);
    RyanAndMonicaSynchronizedJob monica = new RyanAndMonicaSynchronizedJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
}

class RyanAndMonicaSynchronizedJob implements Runnable {
  private final String name;
  private final BankAccountSynchronized account;

  RyanAndMonicaSynchronizedJob(String name, BankAccountSynchronized account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      account.makeWithdrawal(10, name);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
    }
  }
}

class BankAccountSynchronized {
  private int balance = 100;

  public int getBalance() {
    return balance;
  }

  private void withdraw(int amount) {
    balance = balance - amount;
  }

  public synchronized void makeWithdrawal(int amount, String name) {
    if (balance >= amount) {
      System.out.println(name + " is about to withdraw");
      try {
        System.out.println(name + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {ex.printStackTrace();}
      System.out.println(name + " woke up.");
      withdraw(amount);
      System.out.println(name + " completes the withdrawal");
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}