package ch10c;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BeTheCompiler {
  public static void main(String[] args) {
    Runnable runnable = () -> System.out.println("Hi!");
    Consumer<String> consumer = s -> System.out.println(s);
    Function<String, Integer> function = s -> s.length();
    Supplier<String> supplier = () -> "Some string";


  }
}
