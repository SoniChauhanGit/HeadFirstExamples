package ch10c;

import java.util.List;
import java.util.stream.Collectors;

public class HelloLambda {
  public static void main(String[] args) {
    helloLambda();
  }

  static void helloLambda() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .sorted((s1, s2) -> {
                                   return s1.compareToIgnoreCase(s2);
                                 })
                                 .limit(4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);

  }
}
