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
    // holds index to grid (0 - 48)
    int[] startupCoords = new int[startupSize];         // current candidate co-ordinates
    int attempts = 0;                                   // current attempts counter
    boolean success = false;                            // flag = found a good location ?

    startupCount++;                                     // nth Startup to place
    Alignment alignment = getAlignment();

    while (!success & attempts++ < MAX_ATTEMPTS) {      // main search loop  (32)
      int location = random.nextInt(GRID_SIZE);         // get random starting point
      boolean validLocation = isValidLocation(location, startupSize, alignment);

      if (validLocation) {
        for (int i = 0; i < startupCoords.length; i++) {
          startupCoords[i] = location;
          location += alignment.getIncrement();
        }
        System.out.println("trying: " + Arrays.toString(startupCoords));

        success = allPositionsAvailable(startupCoords);
      }
    }                                                   // end while
    System.out.println(this);

    savePositionToGrid(startupCoords);
    ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
    System.out.println(alphaCells);
    System.out.println(this);
    return alphaCells;
  }

  private boolean isValidLocation(int location, int startupSize, Alignment alignment) {
    int finalLocation = location + (alignment.getIncrement() * startupSize);
    if (alignment == HORIZONTAL) {
      return horizontalStartupFits(location, finalLocation);
    } else {
      return verticalStartupFits(finalLocation);
    }
  }

  private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
    ArrayList<String> alphaCells = new ArrayList<String>();
    for (int index : startupCoords) {                 // turn location into 'a0'-style startupCoords
      String alphaCoords = getAlphaCoordsFromIndex(index);
      alphaCells.add(alphaCoords);
    }
    return alphaCells;
  }

  void savePositionToGrid(int[] startupCoords) {
    for (int index : startupCoords) {                 // turn location into 'a0'-style startupCoords
      grid[index] = 1;                         // mark master grid position as ‘used'
    }
  }

  String getAlphaCoordsFromIndex(int index) {
    int row = calculateRowFromIndex(index);           // get row value
    int column = index % GRID_LENGTH;        // get numeric column value

    String letter = ALPHABET.substring(column, column + 1); // convert column index to letter
    return letter + row;
  }

  boolean allPositionsAvailable(int[] startupCoords) {
    for (int coord : startupCoords) {   // check all potential positions
      if (grid[coord] != 0) {           // this co-ordinate already has an entry in the grid
        return false;                   // NO success
      }
    }
    return true;                        // if the method got this far, there were no clashes, yay!
  }

  boolean horizontalStartupFits(int location, int finalLocation) {
    return calculateRowFromIndex(location) == calculateRowFromIndex(finalLocation); // check end is on same row as start
  }

  boolean verticalStartupFits(int finalLocation) {
    return finalLocation < GRID_SIZE; // check end position doesn't go off the bottom of the grid
  }

  private Alignment getAlignment() {
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

