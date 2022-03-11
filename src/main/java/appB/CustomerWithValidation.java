package appB;

public record CustomerWithValidation(int id, String name) {
  public CustomerWithValidation(int id, String name) {
    if (id < 0) {
      throw new ValidationException();
    }
    this.id = id;
    this.name = name;
  }

  public int id() {
    checkEverythingIsOk();
    return id;
  }

  private void checkEverythingIsOk() {

  }
}
