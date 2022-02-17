package ch6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameHelperTest {
  @Test
  void shouldGetCharAsIntValue() {
    char a = 'a';
    assertEquals(97, a);
  }

  @Test
  void shouldConvertIntToCharA() {
    // given
    int columnIndex = 0;

    // expect
    assertEquals('a', columnIndex + 97);
  }

  @Test
  void shouldConvertIntToCharG() {
    // given
    int columnIndex = 6;

    // expect
    assertEquals('g', columnIndex + 97);
  }
}