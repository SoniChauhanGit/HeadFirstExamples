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
    // holds ‘f6' type coords
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

    int i = 0;                                          // turn location into alpha coords
    int row = 0;
    int column = 0;
    String temp = null;                                // temporary String for concat
    while (i < startupSize) {
      grid[coords[i]] = 1;                              // mark master grid pts. as ‘used'
      row = (int) (coords[i] / GRID_LENGTH);             // get row value
      column = coords[i] % GRID_LENGTH;                  // get numeric column value
      temp = String.valueOf(ALPHABET.charAt(column));   // convert to alpha

      alphaCells.add(temp.concat(Integer.toString(row)));
      i++;
    }
    return alphaCells;
  }
}