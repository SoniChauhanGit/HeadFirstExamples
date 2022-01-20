package ch15.ryanmonica;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaStatementTest {
  public static void main(String[] args) throws InterruptedException {
    BankAccountStatement account = new BankAccountStatement();
    RyanAndMonicaStatementJob ryan = new RyanAndMonicaStatementJob("Ryan", account);
    RyanAndMonicaStatementJob monica = new RyanAndMonicaStatementJob("Monica", account);
    ExecutorService executor = Executors.newFixedThreadPool(4);
    executor.execute(ryan);
    executor.execute(monica);
    executor.execute(new Accountant(account));
    executor.execute(new Accountant(account));
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

class Accountant implements Runnable {
  private final BankAccountStatement account;

  Accountant(BankAccountStatement account) {
    this.account = account;
  }

  @Override
  public void run() {
    for (int i = 0; i < 10; i++) {
      System.out.println(account.getStatement());
    }
  }
}

class BankAccountStatement {
  private final List<Withdrawal> statement = new CopyOnWriteArrayList<>();

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

  public List<Withdrawal> getStatement() {
    return statement;
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

  @Override
  public String toString() {
    return "Withdrawal{" +
            "name='" + name + '\'' +
            ", amount=" + amount +
            ", currentBalance=" + currentBalance +
            '}';
  }
}


