package ch6;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

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
    int increment = calculateIncrement();

    while (!success & attempts++ < MAX_ATTEMPTS) {             // main search loop  (32)
      int location = random.nextInt(GRID_SIZE);     // get random starting point
      success = true;                                 // assume success
      int i = 0;
      while (success && i < startupSize) {                // look for adjacent unused spots
        if (grid[location] == 0) {                    // if not already used
          success = startupFits(location, startupSize, increment);
          if (success) {
            startupCoords[i++] = location;                    // save location
            location += increment;                          // try ‘next' adjacent
          }
        } else {                                      // found already used location
          success = false;                          // failure
        }
      }
    }                                                   // end while
    System.out.println(this);

    for (int index : startupCoords) {                 // turn location into 'a0'-style startupCoords
      grid[index] = 1;                         // mark master grid position as ‘used'
      int row = index / GRID_LENGTH;           // get row value
      int column = index % GRID_LENGTH;        // get numeric column value

      String letter = ALPHABET.substring(column, column + 1); // convert column index to letter
      alphaCells.add(letter + row);
    }
    System.out.println(alphaCells);
    System.out.println(this);
    return alphaCells;
  }

  private boolean startupFits(int location, int startupSize, int increment) {
    int finalLocation = location + (increment * startupSize);
    if (finalLocation > GRID_SIZE || finalLocation % GRID_LENGTH == 0) {
      System.out.println("location = " + location + ", startupSize = " + startupSize + ", increment = " + increment);
      return false;
    }
    return true;
  }

  private int calculateIncrement() {
    if (startupCount % 2 == 0) {                // if EVEN Startup
      return 1;                                 // place horizontally
    } else {                                    // else ODD
      return GRID_LENGTH;                       // set vertical increment
    }
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
}