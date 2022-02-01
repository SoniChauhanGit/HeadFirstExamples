package ch10b;

import java.util.*;

public class TestGenerics3 {
  public static void main(String[] args) {
    new TestGenerics3().go();
  }

  public void go() {
    List<Animal> animals = new ArrayList<>();
    animals.add(new Dog());
    animals.add(new Cat());
    animals.add(new Dog());
    takeAnimals(animals);

    List<Dog> dogs = new ArrayList<>();
    dogs.add(new Dog());
    dogs.add(new Dog());
//    takeAnimals(dogs);
  }

  public void takeAnimals(List<Animal> animals) {
    for (Animal a : animals) {
      a.eat();
    }
  }
}



