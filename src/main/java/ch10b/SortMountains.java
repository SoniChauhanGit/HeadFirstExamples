package ch10b;

import java.util.*;

public class SortMountains {

  public static void main(String[] args) {
    new SortMountains().go();
  }

  public void go() {
    List<Mountain> mountains = new LinkedList<>();
    mountains.add(new Mountain("Longs", 14255));
    mountains.add(new Mountain("Elbert", 14433));
    mountains.add(new Mountain("Maroon", 14156));
    mountains.add(new Mountain("Castle", 14265));
    System.out.println("as entered:\n" + mountains);
    NameCompare nc = new NameCompare();
    mountains.sort(nc);
    System.out.println("by name:\n" + mountains);
    HeightCompare hc = new HeightCompare();
    mountains.sort(hc);
    System.out.println("by height:\n" + mountains);
  }
}

class NameCompare implements Comparator<Mountain> {
  public int compare(Mountain one, Mountain two) {
    return one.name.compareTo(two.name);
  }
}

class HeightCompare implements Comparator<Mountain> {
  public int compare(Mountain one, Mountain two) {
    return (two.height - one.height);
  }
}

class Mountain {
  final String name;
  final int height;

  Mountain(String name, int height) {
    this.name = name;
    this.height = height;
  }

  public String toString() {
    return name + " " + height;
  }
}