package ch15.ryanmonica;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaStatementTest {
  public static void main(String[] args) throws InterruptedException {
    BankAccountStatement account = new BankAccountStatement();
    RyanAndMonicaStatementJob ryan = new RyanAndMonicaStatementJob("Ryan", account);
    RyanAndMonicaStatementJob monica = new RyanAndMonicaStatementJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(2);
    executor.execute(ryan);
    executor.execute(monica);
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
  }
}

class RyanAndMonicaStatementJob implements Runnable {
  private final String name;
  private final BankAccountStatement account;

  RyanAndMonicaStatementJob(String name, BankAccountStatement account) {
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

class BankAccountStatement {
  private final List<Withdrawal> statement = new ArrayList<>();

  public BankAccountStatement() {
    statement.add(new Withdrawal("Initial Balance", 0, 100));
  }

  public int getBalance() {
    int lastEntry = statement.size() - 1;
    return statement.get(lastEntry).getCurrentBalance();
  }

  public void makeWithdrawal(int amount, String name) {
    if (getBalance() >= amount) {
      System.out.println(name + " is about to withdraw");
      try {
        System.out.println(name + " is going to sleep");
        Thread.sleep(500);
      } catch (InterruptedException ex) {ex.printStackTrace();}
      System.out.println(name + " woke up.");
      withdraw(name, amount);
      System.out.println(name + " completes the withdrawal");
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }

  private void withdraw(String name, int amount) {
    int newBalance = getBalance() - amount;
    Withdrawal withdrawal = new Withdrawal(name, amount, newBalance);
    statement.add(withdrawal);
  }

}

class Withdrawal {
  private final String name;
  private final int amount;
  private final int currentBalance;

  Withdrawal(String name, int amount, int currentBalance) {
    this.name = name;
    this.amount = amount;
    this.currentBalance = currentBalance;
  }

  public String getName() {
    return name;
  }

  public int getAmount() {
    return amount;
  }

  public int getCurrentBalance() {
    return currentBalance;
  }
}


