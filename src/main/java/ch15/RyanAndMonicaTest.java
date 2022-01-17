package ch15;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class BankAccount {
  private int balance = 100;

  public int getBalance() {
    return balance;
  }

  public void withdraw(int amount) {
    balance = balance - amount;
  }
}

public class RyanAndMonicaTest {
  public static void main(String[] args) {
    BankAccount account = new BankAccount();
    RyanAndMonicaJob ryan = new RyanAndMonicaJob("Ryan", account);
    RyanAndMonicaJob monica = new RyanAndMonicaJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
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
    for (int i = 0; i < 10; i++) {
      makeWithdrawal(10);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
    }
  }

  private void makeWithdrawal(int amount) {
    if (account.getBalance() >= amount) {
      System.out.println(name + " is about to withdraw");
      try {
        System.out.println(name + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {ex.printStackTrace();}
      System.out.println(name + " woke up.");
      account.withdraw(amount);
      System.out.println(name + " completes the withdrawal");
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}

