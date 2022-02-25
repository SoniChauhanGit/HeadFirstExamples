package ch13;

public class Snippets {
  void testTryCatch() {
    try {
      Risky x = new Risky();
      Foo f = x.doRiskyThing();
      int b = f.getNum();

    } catch (Exception e) {
      System.out.println("failed");
    }
    System.out.println("We made it!");
  }
}

class Risky {
  public Foo doRiskyThing() {
    return null;
  }
}

class Foo {
  public int getNum() {
    return 0;
  }
}
