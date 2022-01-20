package ch15.ryanmonica;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RyanAndMonicaSynchronizedTest {
  public static void main(String[] args) throws InterruptedException {
    BankAccountSynchronized account = new BankAccountSynchronized();
    RyanAndMonicaSynchronizedJob ryan = new RyanAndMonicaSynchronizedJob("Ryan", account);
    RyanAndMonicaSynchronizedJob monica = new RyanAndMonicaSynchronizedJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
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
    for (int i = 0; i < 5; i++) {
      goShopping(10);
    }
  }

  private void goShopping(int amount) {
    synchronized (account) {
      if (account.getBalance() >= amount) {
        System.out.println(name + " is about to spend");
        account.spend(amount);
        System.out.println(name + " finishes spending");
      } else {
        System.out.println("Sorry, not enough for " + name);
      }
    }
  }
}

class BankAccountSynchronized {
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
