package ch10;

public class Duck {
  private int size;

  public static void main(String[] args) {
    // doesn't compile
//    System.out.println("Size is " + getSize());
  }

  public void setSize(int s) {
    size = s;
  }

  public int getSize() {
    return size;
  }

}
  