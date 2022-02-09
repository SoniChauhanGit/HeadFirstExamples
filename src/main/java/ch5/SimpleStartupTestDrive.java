package ch5;

public class SimpleStartupTestDrive {
  public static void main(String[] args) {
    SimpleStartup dot = new SimpleStartup();
    int[] locations = {2, 3, 4};
    dot.setLocationCells(locations);
    String userGuess = "2";
    String result = dot.checkYourself(userGuess);
    System.out.println(result);
  }
}


class SimpleStartup {
  private int[] locationCells;
  private int numOfHits = 0;

  public void setLocationCells(int[] locs) {
    locationCells = locs;
  }

  public String checkYourself(String stringGuess) {
    int guess = Integer.parseInt(stringGuess);
    String result = "miss";
    for (int cell : locationCells) {
      if (guess == cell) {
        result = "hit";
        numOfHits++;
        break;
      }
    } // out of the loop

    if (numOfHits ==
        locationCells.length) {
      result = "kill";
    }
    return result;
  } // close method
} // close class