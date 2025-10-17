import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Program that allows the user to search for a number in a list of numbers.
 * The search is done using Binary Search.
 *
 * @author Atri Sarker
 * @version 1.0
 * @since 2025-10-17
 */
public final class BinarySearch {
  /**
   * Constant for the size of the array.
   */
  public static final int ARRAY_SIZE = 10;
  /**
   * Constant for the minimum random integer.
   */
  public static final int MIN_NUM = 0;
  /**
   * Constant for the maximum random integer.
   */
  public static final int MAX_NUM = 100;

  /**
   * Private constructor to satisfy style checker.
   *
   * @exception IllegalStateException for the utility class.
   * @see IllegalStateException
   */
  private BinarySearch() {
    // Prevents illegal states.
    throw new IllegalStateException("Utility class.");
  }

  /**
   * Function that generates an array filled with random integers.
   *
   * @param minNum    The minimum number [INCLUSIVE] for the random integers.
   * @param maxNum    The maximum number [INCLUSIVE] for the random integers.
   * @param arraySize The size of the array that's going to be generated.
   * @return An array of size {arraySize} filled up with
   *         random integers between {minNum} and {maxNum}.
   */
  public static int[] populateArray(final int minNum,
      final int maxNum, final int arraySize) {
    // Create an array of size {arraySize}
    final int[] arr = new int[arraySize];
    // Create instance of Random class
    final Random rand = new Random();
    // Go through every index of the array
    // And populate it with a random integer between {minNum} and {maxNum}
    for (int index = 0; index < arraySize; index++) {
      // Generate a random integer between {minNum} and {maxNum} [INCLUSIVE]
      final int randNum = rand.nextInt(1 + maxNum - minNum) + minNum;
      // Assign the random integer to the current index of the array
      arr[index] = randNum;
    }
    // Return the populated array
    return arr;
  }

  /**
   * Function that searches for an int in an int[].
   * Using Binary Search.
   *
   * @param arr    The array that is going to be searched.
   * @param target The int to be searched for in the array.
   * @return The index of the target in the array. -1 if not found.
   */
  public static int binarySearch(final int[] arr, final int target) {
    // Initialize low and high search interval bounds
    int low = 0;
    int high = arr.length - 1;
    // Initialize index
    // It's set to -1 in case the target isn't found
    int index = -1;
    // Loop
    while (low <= high) {
      // Get the midpoint of the current search interval
      int midpoint = (low + high) / 2;
      // Get the value found at the midpoint
      int value = arr[midpoint];
      // Compare the midpoint to the target
      if (value < target) {
        // If the value is lesser than the target,
        // set the search interval to contain everything after the midpoint.
        low = midpoint + 1;
      } else if (value > target) {
        // If the value is greater than the target,
        // set the search interval to contain everything before the midpoint.
        high = midpoint - 1;
      } else {
        // If the value is neither greater or lesser than the target,
        // the value must be the target.
        // The target has been found.
        // Set index to the midpoint and break out of the loop.
        index = midpoint;
        break;
      }
    }
    // Return the index
    return index;
  }

  /**
   * Entrypoint of the program.
   *
   * @param args UNUSED.
   */
  public static void main(final String[] args) {
    // Initialize Scanner for user input.
    final Scanner scanner = new Scanner(System.in);
    // Create variable for user input
    String userInput = "";
    // LOOP
    do {
      // Create an array filled with random integers
      int[] arr = populateArray(MIN_NUM, MAX_NUM, ARRAY_SIZE);
      // Sort the array
      Arrays.sort(arr);
      // Prompt message
      System.out.print("What number do you want to search ");
      System.out.println("for in the list below? Enter 'q' to quit.");
      // Print the array
      System.out.print(Arrays.toString(arr) + " ");
      // Input prompt
      System.out.print("Number: ");
      // Get user input as a string
      userInput = scanner.nextLine();
      // Check if the user wants to quit
      if (userInput.equalsIgnoreCase("q")) {
        // If so, break the loop
        break;
      }
      // Try to convert the user input into an integer
      try {
        final int userTarget = Integer.parseInt(userInput);
        // Search for the target in the array
        final int targetIndex = binarySearch(arr, userTarget);
        // Check if the target was found
        if (targetIndex != -1) {
          // If so, print the index of the target
          System.out.printf("The number %d was found at index %d.",
              userTarget, targetIndex);
          // Newline
          System.out.println();
        } else {
          // If not, print that the target wasn't found [IN RED]
          System.out.print("\u001B[31m"); // Red color code
          System.out.printf("ERROR: The number %d ", userTarget);
          System.out.print("was not found in the list of numbers.");
          System.out.print("\u001B[0m"); // Reset color code
          // Newline
          System.out.println();
        }
      } catch (NumberFormatException error) {
        // Error message for non-numeric input. [IN RED]
        System.out.print("\u001B[31m"); // Red color code
        System.out.println("ERROR: INPUT MUST BE AN INTEGER!");
        System.out.print("\u001B[0m"); // Reset color code
      }
    } while (!userInput.equalsIgnoreCase("q"));
    // Loop condition is redundant
    // because the same condition is within the loop.
    // But I still included it because
    // I don't know if I am allowed to use a while (True) loop

    // Close the scanner
    scanner.close();
    // Goodbye message
    System.out.println("Thanks for playing!");
  }
}
