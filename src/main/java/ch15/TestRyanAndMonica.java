package ch15;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestRyanAndMonica {
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
  private final Random randomGenerator = new Random();

  RyanAndMonicaJob(String name, BankAccount account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    while (account.getBalance() > 0) {
      makeWithdrawal(10);
      if (account.getBalance() < 0) {
        System.out.println("Overdrawn!");
      }
    }
  }

  private void makeWithdrawal(int amount) {
    if (account.getBalance() >= amount) {
      System.out.printf("%s is about to withdraw $%d. Current balance: $%d%n", name, amount, account.getBalance());
      try {
        System.out.println(name + " is going to sleep");
        sleepForRandomTime();
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      System.out.println(name + " woke up.");
      account.withdraw(amount);
      System.out.printf("%s completes the withdrawal of $%d. New balance: $%d%n", name, amount, account.getBalance());
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }

  private void sleepForRandomTime() throws InterruptedException {
    Thread.sleep(randomGenerator.nextInt(500));
  }
}

class BankAccount {
  private int balance = 50;

  public int getBalance() {
    return balance;
  }

  public void withdraw(int amount) {
    balance = balance - amount;
  }
}

