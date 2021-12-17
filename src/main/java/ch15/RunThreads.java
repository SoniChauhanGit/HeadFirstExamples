package ch15;

public class RunThreads {
  public static void main(String[] args) {
    Thread alpha = new Thread(RunThreads::printThreadName);
    Thread beta = new Thread(RunThreads::printThreadName);
    alpha.setName("Alpha thread");
    beta.setName("Beta thread");
    alpha.start();
    beta.start();
  }

  public static void printThreadName() {
    for (int i = 0; i < 25; i++) {
      String threadName = Thread.currentThread().getName();
      System.out.println(threadName + " is running");
    }
  }
}