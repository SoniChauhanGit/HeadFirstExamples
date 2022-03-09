package appB;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestMeTest {
  @Test
  void shouldReturnAMessage() {
    // given
    String expectedMessage = "The message";

    // when
    TestMe testMe = new TestMe(expectedMessage);

    // then
    Assertions.assertEquals(expectedMessage, testMe.getMessage());
  }
}