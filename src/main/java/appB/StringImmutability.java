package appB;

public class StringImmutability {
  String stringConcatenation() {
    String s = "0";
    for (int i = 1; i < 10; i++) {
      s = s + i;
    }
    return s;
  }

  String stringBuilder() {
    StringBuilder s = new StringBuilder("0");
    for (int i = 1; i < 10; i++) {
      s.append(i);
    }
    return s.toString();
  }
}
