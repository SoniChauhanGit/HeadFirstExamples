package ch15.ryanmonica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RyanAndMonicaBankChecksTest {
  public static void main(String[] args) throws InterruptedException {
    BankAccountChecks account = new BankAccountChecks();
    RyanAndMonicaBankChecksJob ryan = new RyanAndMonicaBankChecksJob("Ryan", account);
    RyanAndMonicaBankChecksJob monica = new RyanAndMonicaBankChecksJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
  }
}

class RyanAndMonicaBankChecksJob implements Runnable {
  private final String name;
  private final BankAccountChecks account;

  RyanAndMonicaBankChecksJob(String name, BankAccountChecks account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    while (account.getBalance() > 0) {
      goShopping(10);
    }
  }

  private void goShopping(int amount) {
    System.out.println(name + " is about to spend");
    account.spend(name, amount);
    System.out.println(name + " finishes spending");
  }
}

class BankAccountChecks {
  private int balance = 50;

  public int getBalance() {
    return balance;
  }

  public synchronized void spend(String name, int amount) {
    if (getBalance() >= amount) {
      balance = balance - amount;
      if (balance < 0) {
        System.out.println("Overdrawn!");
      }
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}
