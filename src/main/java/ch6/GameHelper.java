package ch6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {
  private static final String ALPHABET = "abcdefg";
  private static final int GRID_LENGTH = 7;
  private static final int GRID_SIZE = 49;
  private static final int MAX_ATTEMPTS = 200;

  static final int HORIZONTAL_INCREMENT = 1;          // A better way to represent these two things would be an enum
  static final int VERTICAL_INCREMENT = GRID_LENGTH;  // (enumeration), which we cover in Appendix B

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
    int increment = getIncrement();                     // alternates between vertical and horizontal alignment

    while (!success & attempts++ < MAX_ATTEMPTS) {      // main search loop  (32)
      int location = random.nextInt(GRID_SIZE);         // get random starting point

      for (int i = 0; i < startupCoords.length; i++) {  // create an array of proposed co-ordinates for the startup
        startupCoords[i] = location;                    // put current location in the array
        location += increment;                          // calculate the next location
      }
      System.out.println("Trying: " + Arrays.toString(startupCoords));

      if (startupFits(startupCoords, increment)) {      // check the startup would fit on the grid
        success = coordsAvailable(startupCoords);       // if it fits, check those locations aren't already taken
      }                                                 // end loop
    }                                                   // end while

    savePositionToGrid(startupCoords);                  // these coords passed all checks, so update the grid with this startup's position
    ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
    System.out.println(alphaCells);
    return alphaCells;
  }

  boolean startupFits(int[] startupCoords, int increment) {
    int finalLocation = startupCoords[startupCoords.length - 1];
    if (increment == HORIZONTAL_INCREMENT) {
      return calculateRowFromIndex(startupCoords[0]) == calculateRowFromIndex(finalLocation); // check end is on same row as start
    } else {
      return finalLocation < GRID_SIZE;                 // check end position doesn't go off the bottom of the grid
    }
  }

  boolean coordsAvailable(int[] startupCoords) {
    for (int coord : startupCoords) {                   // check all potential positions
      if (grid[coord] != 0) {                           // this co-ordinate already has an entry in the grid
        System.out.println("position: " + coord + " already taken.");
        return false;                                   // NO success
      }
    }
    return true;                                        // if the method got this far, there were no clashes, yay!
  }

  void savePositionToGrid(int[] startupCoords) {
    for (int index : startupCoords) {
      grid[index] = 1;                                  // mark master grid position as 'used'
    }
  }

  private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
    ArrayList<String> alphaCells = new ArrayList<String>();
    for (int index : startupCoords) {                   // for each grid coordinate
      alphaCells.add(getAlphaCoordsFromIndex(index));   // turn it into an "a0" style and add to a list
    }
    return alphaCells;
  }

  String getAlphaCoordsFromIndex(int index) {
    int row = calculateRowFromIndex(index);             // get row value
    int column = index % GRID_LENGTH;                   // get numeric column value

    String letter = ALPHABET.substring(column, column + 1); // convert column index to letter
    return letter + row;
  }

  private int calculateRowFromIndex(int index) {
    return index / GRID_LENGTH;
  }

  private int getIncrement() {
    if (startupCount % 2 == 0) {                        // if EVEN Startup
      return HORIZONTAL_INCREMENT;                      // place horizontally
    } else {                                            // else ODD
      return VERTICAL_INCREMENT;                        // set vertical increment
    }
  }
}

