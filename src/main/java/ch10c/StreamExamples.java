package ch10c;

import java.util.List;
import java.util.stream.Stream;

public class StreamExamples {
  void streamExamples() {
    List<String> strings = List.of("I", "am", "a", "list", "of", "Strings");
    Stream<String> stream = strings.stream();
    Stream<String> limit = stream.limit(4);

  }
}
