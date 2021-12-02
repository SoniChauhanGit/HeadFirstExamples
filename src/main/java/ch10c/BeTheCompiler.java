package ch10c;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class BeTheCompiler {
  public static void main(String[] args) {
    Runnable runnable = () -> System.out.println("Hi!");
//    Runnable runnable2 = (String str) -> System.out.println(str);

    Consumer<String> consumer = s -> System.out.println(s);
//    Consumer<String> consumer2 = (str1, str2) -> System.out.println(str1 + ", " + str2);
//    Consumer<String> consumer3 = s -> "String" + s;

    Function<String, Integer> function = s -> s.length();
    Function<String, Integer> function2 = (String s) -> s.length();
//    Function<String, Integer> function3 = (int i) -> "i = " + i;
    Supplier<String> supplier = () -> "Some string";
//    Supplier<String> supplier2 = s -> "Some string: " + s;
//    Supplier<String> supplier3 = () -> System.out.println("Some string");


  }
}
