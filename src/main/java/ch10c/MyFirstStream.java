package ch10c;

import java.util.List;
import java.util.stream.Stream;

public class MyFirstStream {

  public static void main(String[] args) {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
    System.out.println("strings = " + strings);

    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);
    System.out.println("limit = " + limit);
  }

  void streamExamples() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);

  }
}
