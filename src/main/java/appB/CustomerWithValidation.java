package appB;

public record CustomerWithValidation(int id, String name) {
  public CustomerWithValidation {
    if (id < 0) {
      throw new ValidationException();
    }
  }

  public int id() {
    return id;
  }

  private void checkEverythingIsOk() {

  }
}
