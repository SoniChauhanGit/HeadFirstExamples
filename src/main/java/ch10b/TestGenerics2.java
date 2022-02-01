package ch10b;

import java.util.*;

public class TestGenerics2 {
  public static void main(String[] args) {
    new TestGenerics1().go();
  }

  public void go() {
    List<Animal> animals = new ArrayList<>();
    animals.add(new Dog());
    animals.add(new Cat());
    animals.add(new Dog());

    takeAnimals(animals);
  }

  public void takeAnimals(List<Animal> animals) {
    for (Animal a : animals) {
      a.eat();
    }
  }
}

	