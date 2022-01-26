package ch15.ryanmonica;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RyanAndMonicaStatementTest {
  public static void main(String[] args) throws InterruptedException {
    BankAccountStatement account = new BankAccountStatement();
    ExecutorService executor = Executors.newCachedThreadPool();
    executor.execute(new RyanAndMonicaStatementJob("Ryan", account, 20));
    executor.execute(new StatementReaderAccountant("Monica", account));
    executor.execute(new StatementReaderAccountant("Accountant", account));
    executor.execute(new StatementReaderAccountant("Bank Manager", account));
    executor.shutdown();
    executor.awaitTermination(1, TimeUnit.MINUTES);
  }
}

class RyanAndMonicaStatementJob implements Runnable {
  private final String name;
  private final BankAccountStatement account;
  private final int amountToSpend;

  RyanAndMonicaStatementJob(String name, BankAccountStatement account, int amountToSpend) {
    this.name = name;
    this.account = account;
    this.amountToSpend = amountToSpend;
  }

  public void run() {
    for (int i = 0; i < 5; i++) {
      goShopping(amountToSpend);
    }
  }

  private void goShopping(int amount) {
    if (account.getBalance() >= amount) {
      System.out.println(name + " is about to spend");
      account.spend(amount, name);
      System.out.println(name + " finishes spending");
    } else {
      System.out.println("Sorry, not enough for " + name);
    }
  }
}

class StatementReaderAccountant implements Runnable {
  private final String name;
  private final BankAccountStatement account;

  StatementReaderAccountant(String name, BankAccountStatement account) {
    this.name = name;
    this.account = account;
  }

  @Override
  public void run() {
    List<Transaction> statement = account.getStatement();
    for (Transaction transaction : statement) {
      System.out.println(name + " read " + transaction);
    }
  }
}

class BankAccountStatement {
  private final List<Transaction> statement = new CopyOnWriteArrayList<>();

  public BankAccountStatement() {
    statement.add(new Transaction("Initial Balance", 0, 100));
  }

  public int getBalance() {
    int lastEntry = statement.size() - 1;
    return statement.get(lastEntry).getCurrentBalance();
  }

  public void spend(int amount, String name) {
    spend(name, amount);
    if (getBalance() < 0) {
      System.out.println("Overdrawn!");
    }
  }

  private void spend(String name, int amount) {
    int newBalance = getBalance() - amount;
    Transaction transaction = new Transaction(name, amount, newBalance);
    statement.add(transaction);
  }

  public List<Transaction> getStatement() {
    return statement;
  }
}

class Transaction {
  private final String name;
  private final int amount;
  private final int currentBalance;

  Transaction(String name, int amount, int currentBalance) {
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


