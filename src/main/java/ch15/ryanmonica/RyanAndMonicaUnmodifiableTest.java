package ch15.ryanmonica;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaUnmodifiableTest {
  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    BankAccountCollection account = new BankAccountCollection();
    RyanAndMonicaAccountsJob ryan = new RyanAndMonicaAccountsJob("Ryan", account);
    RyanAndMonicaAccountsJob monica = new RyanAndMonicaAccountsJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
    long end = System.currentTimeMillis();
    System.out.println(end - start);
    System.out.println(account.accountBalances);
  }
}

class RyanAndMonicaAccountsJob implements Runnable {
  private final String name;
  private final BankAccountCollection account;
  private final String accountName = "checking";

  RyanAndMonicaAccountsJob(String name, BankAccountCollection account) {
    this.name = name;
    this.account = account;
  }

  public void run() {
    for (int i = 0; i < 10; i++) {
      account.makeWithdrawal(10, accountName, name);
      if (account.getBalance(accountName) < 0) {
        System.out.println("Overdrawn!");
      }
    }
  }
}


class BankAccountCollection {
  Map<String, Integer> accountBalances = new HashMap<>();
  {
    accountBalances.put("checking", 100);
    accountBalances.put("savings", 500);
    accountBalances.put("credit card", 1200);
  }

  public int getBalance(String accountName) {
    return accountBalances.get(accountName);
  }

  public void makeWithdrawal(int amount, String accountName, String withdrawnBy) {
    Integer balance = accountBalances.get(accountName);
    if (balance >= amount) {
      System.out.println(withdrawnBy + " is about to withdraw. balance: "+balance);
      try {
        System.out.println(withdrawnBy + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      System.out.println(withdrawnBy + " woke up.");
      accountBalances.replace(accountName, balance, balance - amount);
      System.out.println(withdrawnBy + " completes the withdrawal");
    } else {
      System.out.println("Sorry, not enough for " + withdrawnBy);
    }
  }
}


