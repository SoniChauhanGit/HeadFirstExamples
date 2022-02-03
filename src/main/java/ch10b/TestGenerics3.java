package ch10b;

import java.util.*;

public class TestGenerics3 {
  public static void main(String[] args) {
    new TestGenerics3().go();
  }

  public void go() {
    List<Animal> animals = List.of(new Dog(), new Cat(), new Dog());
    takeAnimals(animals);

    List<Dog> dogs = List.of(new Dog(), new Dog());
    takeAnimals(dogs);
  }

  public void takeAnimals(List<? extends Animal> animals) {
    for (Animal a : animals) {
      a.eat();
    }
  }

  public <T extends Animal> void takeAnimals2(List<T> animals) {
    for (Animal a : animals) {
      a.eat();
    }
  }
}



