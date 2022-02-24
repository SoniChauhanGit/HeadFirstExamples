package ch10;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.Math.sqrt;
import static java.lang.Math.tan;
import static java.lang.System.out;

class WithStatic {
  public static void main(String[] args) {
    out.println("sqrt " + sqrt(2.0));
    out.println("tan " + tan(60));
  }

  void otherStaticImports() {
    Stream.of("string")
          .filter(Predicate.not(String::isEmpty));
  }
}