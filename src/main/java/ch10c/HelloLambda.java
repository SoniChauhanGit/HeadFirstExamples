package ch10c;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloLambda {
  public static void main(String[] args) {
    lambdaAsBehaviour();
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

  static void lambdaAsBehaviour() {
    List<String> strings = new ArrayList<>(List.of("I", "am", "a", "list", "of", "Strings"));
    String str = strings.get(2);
    strings.add("new String");
    strings.forEach(s -> System.out.println(s));

    List<String> result = strings.stream()
                                 .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                                 .limit(4)
                                 .collect(Collectors.toList());
  }
}
