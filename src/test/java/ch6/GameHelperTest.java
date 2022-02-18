package ch6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

  @Test
  void shouldConvertIndexLocationToAlphaCoords() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertEquals("f3", gameHelper.getAlphaCoordsFromIndex(26));
    assertEquals("f6", gameHelper.getAlphaCoordsFromIndex(47));
  }

  @Test
  void shouldNotLetStartupGoOverRightEdge() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertFalse(gameHelper.horizontalStartupFits(26, 3));
  }

  @Test
  void shouldAllowVerticalStartups() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertTrue(gameHelper.verticalStartupFits(26, 3));
  }

  @Test
  void shouldNotLetStartupGoOverBottom() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertFalse(gameHelper.verticalStartupFits(47, 3));
  }

  @Test
  void shouldTurnIndexIntoAlphaCoords() {
    GameHelper gameHelper = new GameHelper();
    assertEquals("f3", gameHelper.getAlphaCoordsFromIndex(26));
  }
}