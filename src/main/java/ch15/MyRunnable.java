package ch15;

public class MyRunnable implements Runnable {

   public void run() {
      go();
   }

   public void go() {
      doMore();
   }

   public void doMore() {
      System.out.println(Thread.currentThread().getName() + ": top o’ the stack");
      Thread.dumpStack();
   }

}

class RunTester {
   public static void main(String[] args) {
      MyRunnable runnable = new MyRunnable();
      runnable.run();

      System.out.println(Thread.currentThread().getName() + ": back in main");
      Thread.dumpStack();
   }
}

class ThreadTester {
   public static void main (String[] args) {
      Runnable threadJob = new MyRunnable();
      Thread myThread = new Thread(threadJob);

      myThread.start();

      System.out.println(Thread.currentThread().getName() + ": back in main");
      Thread.dumpStack();
   }
}

class StackUtils {
   static String getCurrentStack() {
      StringBuffer stack = new StringBuffer();
      StackWalker.getInstance()
                 .forEach(stackFrame -> stack.append(stackFrame.toString()).append("\n"));
      return stack.toString();
   }
}
