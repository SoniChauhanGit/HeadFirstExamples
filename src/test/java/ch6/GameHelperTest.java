package ch6;

import org.junit.jupiter.api.Test;

import static ch6.GameHelper.Alignment.HORIZONTAL;
import static ch6.GameHelper.Alignment.VERTICAL;
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
    assertFalse(gameHelper.horizontalStartupFits(26, 26 + (HORIZONTAL.getIncrement() * 3)));
  }

  @Test
  void shouldAllowVerticalStartups() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertTrue(gameHelper.verticalStartupFits(26 + (VERTICAL.getIncrement() * 3)));
  }

  @Test
  void shouldNotLetStartupGoOverBottom() {
    // given
    GameHelper gameHelper = new GameHelper();

    // expect
    assertFalse(gameHelper.verticalStartupFits(47 + (VERTICAL.getIncrement() * 3)));
  }

  @Test
  void shouldBeAbleToPlaceHorizontalIntoEmptyGridStartingFromValidLocation() {
    // given
    GameHelper gameHelper = new GameHelper();

    // when
    int[] startupCoords = new int[]{0, 1, 2};
    boolean success = gameHelper.allPositionsAvailable(startupCoords);

    // then
    assertTrue(success);
    assertArrayEquals(new int[]{0, 1, 2}, startupCoords);
  }

  @Test
  void shouldBeAbleToPlaceVerticalIntoEmptyGridStartingFromValidLocation() {
    // given
    GameHelper gameHelper = new GameHelper();

    // when
    boolean success = gameHelper.allPositionsAvailable(new int[]{0, 7, 14});

    // then
    assertTrue(success);
  }

  @Test
  void shouldNotBeAbleToPlaceHorizontalIntoGridAtSameLocationAsExisting() {
    // given
    GameHelper gameHelper = new GameHelper();
    gameHelper.savePositionToGrid(new int[]{8, 9, 10}); // "B1"
    System.out.println(gameHelper);

    // when
    boolean success = gameHelper.allPositionsAvailable(new int[]{9, 10, 11}); // "C1" - horizontal
    System.out.println(gameHelper);

    // then
    assertFalse(success);
  }

  @Test
  void shouldNotBeAbleToPlaceVerticalIntoGridAtSameLocationAsExisting() {
    // given
    GameHelper gameHelper = new GameHelper();
    gameHelper.savePositionToGrid(new int[]{8, 9, 10}); // "B1"
    System.out.println(gameHelper);

    // when
    int[] startupCoords = new int[]{1, 9, 16};
    boolean success = gameHelper.allPositionsAvailable(startupCoords); // "A1" - vertical
    System.out.println(gameHelper);

    // then
    assertFalse(success);
  }

  @Test
  void shouldTurnIndexIntoAlphaCoords() {
    GameHelper gameHelper = new GameHelper();
    assertEquals("f3", gameHelper.getAlphaCoordsFromIndex(26));
  }
}