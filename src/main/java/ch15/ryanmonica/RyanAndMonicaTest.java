package ch15.ryanmonica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaTest {
  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    BankAccount account = new BankAccount();
    RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", account);
    RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
  }
}

class RyanAndMonicaJob implements Runnable {
  private final String name;
  private final BankAccount account;

  RyanAndMonicaJob(String name, BankAccount account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    for (int i = 0; i < 5; i++) {
      goShopping();
    }
  }

  private void goShopping() {
    if (account.getBalance() >= 10) {
      System.out.println(name + " is about to spend");
      account.spend(10);
      System.out.println(name + " finishes spending");
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}

class BankAccount {
  private int balance = 50;
  public int getBalance() {
    return balance;
  }

  public void spend(int amount) {
    balance = balance - amount;
    if (balance < 0) {
      System.out.println("Overdrawn!");
    }
  }
}


