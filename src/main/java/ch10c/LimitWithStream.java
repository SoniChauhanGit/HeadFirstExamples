package ch10c;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LimitWithStream {

  public static void main(String[] args) {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .filter(s -> s.length() < 4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);
  }

  void streamExamples() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);
  }

  void limitWithCountTerminal() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);
    long result = limit.count();
    System.out.println("result = " + result);
  }

  void limitWithCollect() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);
    List<String> result = limit.collect(Collectors.toList());
    System.out.println("result = " + result);
  }

  void limitAsStream() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .limit(4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);
  }

  void chainedOperations() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .sorted()
                                 .limit(4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);

    List<String> result2 = strings.stream()
                                 .sorted()
                                 .skip(3)
                                 .limit(4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result2);
  }

  void sortingCaseInsensitive() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .sorted((s1, s2) -> s1.compareToIgnoreCase(s2))
                                 .limit(4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);
  }

  void filtering() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");

    List<String> result = strings.stream()
                                 .filter(s -> s.length() < 4)
                                 .collect(Collectors.toList());
    System.out.println("result = " + result);
  }
}

