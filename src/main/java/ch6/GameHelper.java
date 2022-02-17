package ch6;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
  private static final String ALPHABET = "abcdefg";
  private static final int GRID_LENGTH = 7;
  private static final int GRID_SIZE = 49;

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
    int[] coords = new int[startupSize];                  // current candidate coords
    int attempts = 0;                                  // current attempts counter
    boolean success = false;                           // flag = found a good location ?
    int location = 0;                                  // current starting location

    startupCount++;                                        // nth Startup to place
    int incr = 1;                                      // set horizontal increment
    if ((startupCount % 2) == 1) {                         // if odd Startup (place vertically)
      incr = GRID_LENGTH;                               // set vertical increment
    }

    while (!success & attempts++ < 200) {             // main search loop  (32)
      location = random.nextInt(GRID_SIZE);     // get random starting point
      int i = 0;                                       // nth position in Startup to place
      success = true;                                 // assume success
      while (success && i < startupSize) {                // look for adjacent unused spots
        if (grid[location] == 0) {                    // if not already used

          coords[i++] = location;                    // save location
          location += incr;                          // try ‘next' adjacent
          if (location >= GRID_SIZE) {                 // out of bounds - ‘bottom'
            success = false;                         // failure
          }
          if (location % GRID_LENGTH == 0) {  // out of bounds - right edge
            success = false;                         // failure
          }
        } else {                                      // found already used location
          success = false;                          // failure
        }
      }
    }                                                   // end while

    for (int index : coords) {                 // turn location into 'a0'-style coords
      grid[index] = 1;                         // mark master grid pts. as ‘used'
      int row = index / GRID_LENGTH;           // get row value
      int column = index % GRID_LENGTH;        // get numeric column value

      String letter = ALPHABET.substring(column, column + 1); // convert column index to letter
      alphaCells.add(letter + row);
    }
    return alphaCells;
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