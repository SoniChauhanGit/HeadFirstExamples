package ch10;

import java.util.ArrayList;

public class Snippets {
  void rounding() {
    int x = Math.round(-24.8f);  // returns -25
    int y = Math.round(24.45f);  // returns 24
    long z = Math.round(24.45);  // returns 24L
  }

  void wrapInt() {
    int x = 32;
    ArrayList<Integer> list = new ArrayList<Integer>();
    list.add(x);
    int num = list.get(0);
  }

  void wrappers() {
    boolean b = true;
    Boolean bool = b;
    char c = 'a';
    Character chr = c;
    byte bt = 0;
    Byte bte = bt;
    short s = 1;
    Short sht = s;
    int i = 2;
    Integer intr = i;
    long l = 3;
    Long lng = l;
    float f = 4;
    Float flt = f;
    double d = 5;
    Double dbl = d;
  }

  static void parse() {
    String s = "2";
    int x = Integer.parseInt(s);
    double d = Double.parseDouble("420.24");

    boolean b = Boolean.parseBoolean("True");
  }

  static void badParse() {
    String t = "two";
    int y = Integer.parseInt(t);

    System.out.println(y);
  }

  void doubleToString1() {
    double d = 42.5;
    String doubleString = "" + d;
  }

  void doubleToString2() {
    double d = 42.5;
    String doubleString = Double.toString(d);
  }

  void doubleToString3() {
    double d = 42.5;
    String doubleString = String.valueOf(d);
  }

  static void format() {
    String.format("I have %.2f, bugs to fix.", 476578.09876);
  }

  public static void main(String[] args) {
    format();
  }
}
