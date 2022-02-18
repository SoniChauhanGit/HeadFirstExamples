package ch6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static ch6.GameHelper.Alignment.HORIZONTAL;
import static ch6.GameHelper.Alignment.VERTICAL;

public class GameHelper {
  private static final String ALPHABET = "abcdefg";
  private static final int GRID_LENGTH = 7;
  private static final int GRID_SIZE = 49;
  private static final int MAX_ATTEMPTS = 200;

  private final int[] grid = new int[GRID_SIZE];
  private final Random random = new Random();

  private int startupCount = 0;

  public String getUserInput(String prompt) {
    System.out.print(prompt + ": ");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine().toLowerCase();
  }

  public ArrayList<String> placeStartup(int startupSize) {
    ArrayList<String> alphaCells = new ArrayList<String>();
    // holds index to grid (0 - 48)
    int[] startupCoords = new int[startupSize];        // current candidate co-ordinates
    int attempts = 0;                                  // current attempts counter
    boolean success = false;                           // flag = found a good location ?

    startupCount++;                                        // nth Startup to place
    Alignment alignment = calculateIncrement();

    while (!success & attempts++ < MAX_ATTEMPTS) {             // main search loop  (32)
      int location = random.nextInt(GRID_SIZE);     // get random starting point
      if (alignment == HORIZONTAL) {
        success = horizontalStartupFits(location, startupSize);
      } else{
        success = verticalStartupFits(location, startupSize);

      }

      if (success) {
        success = bruteForcePlace(startupSize, startupCoords, alignment.getIncrement(), location);
      }
    }                                                   // end while
    System.out.println(this);

    for (int index : startupCoords) {                 // turn location into 'a0'-style startupCoords
      grid[index] = 1;                         // mark master grid position as ‘used'
      String alphaCoords = getAlphaCoordsFromIndex(index);
      alphaCells.add(alphaCoords);
    }
    System.out.println(alphaCells);
    System.out.println(this);
    return alphaCells;
  }

  String getAlphaCoordsFromIndex(int index) {
    int row = calculateRowFromIndex(index);           // get row value
    int column = index % GRID_LENGTH;        // get numeric column value

    String letter = ALPHABET.substring(column, column + 1); // convert column index to letter
    String alphaCoords = letter + row;
    return alphaCoords;
  }

  private boolean bruteForcePlace(int startupSize, int[] startupCoords, int increment, int location) {
    System.out.println("startupSize = " + startupSize + ", startupCoords = " + Arrays.toString(startupCoords) + ", increment = " + increment + ", location = " + location);
    int k = 0;
    for (int j = location; k < startupSize; j += increment) {
      System.out.println("check it's available. k=" + k + ", startupSize:" + startupSize);
      // check all potential positions
      if (grid[j] == 0) {
        System.out.println("yes");
        startupCoords[k++] = j;                    // save location
        System.out.println("startupCoords = " + Arrays.toString(startupCoords));
      } else {
        System.out.println("no");
        return false;
      }
    }
    return true;
  }

  boolean horizontalStartupFits(int location, int startupSize) {
    int finalLocation = location + (HORIZONTAL.getIncrement() * startupSize);
    if (calculateRowFromIndex(location) != calculateRowFromIndex(finalLocation)) { // end position is on a different row to start position
      System.out.println("location = " + location + ", startupSize = " + startupSize);
      return false;
    }
    return true;
  }

  boolean verticalStartupFits(int location, int startupSize) {
    int finalLocation = location + (VERTICAL.getIncrement() * startupSize);
    if (finalLocation > GRID_SIZE) {                              // end position is off the bottom
      System.out.println("location = " + location + ", startupSize = " + startupSize);
      return false;
    }
    return true;
  }

  private Alignment calculateIncrement() {
    if (startupCount % 2 == 0) {                  // if EVEN Startup
      return HORIZONTAL;           // place horizontally
    } else {                                      // else ODD
      return VERTICAL;             // set vertical increment
    }
  }

  private int calculateRowFromIndex(int index) {
    return index / GRID_LENGTH;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (int i = 0; i < grid.length; i++) {
      if (i % 7 == 0) {
        str.append("\n");
      }
      str.append(grid[i]).append(", ");
    }
    return str.toString();
  }

  enum Alignment {
    HORIZONTAL(1), VERTICAL(GRID_LENGTH);

    private final int increment;

    Alignment(int increment) {
      this.increment = increment;
    }

    public int getIncrement() {
      return increment;
    }
  }
}

