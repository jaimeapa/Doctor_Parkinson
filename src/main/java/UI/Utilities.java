package UI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * A utility class providing helper methods for user input from the console,
 * including reading integers, dates, and strings.
 */
public class Utilities {

    /**
     * Prompts the user with a question and reads an integer from the console.
     * Continues to prompt the user until a valid integer is entered.
     *
     * @param question the question to display to the user.
     * @return the integer entered by the user.
     */
    public static int readInteger(String question) {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(input);
        int num;
        String line;
        while (true) {
            try {
                System.out.println(question);
                line = buffer.readLine();
                num = Integer.parseInt(line);
                return num;
            } catch (IOException ioe) {
                System.out.println(" ERROR: Unable to read.");
            } catch (NumberFormatException nfe) {
                System.out.println(" ERROR: Must be a whole number.");
            }
        }
    }

    /**
     * Prompts the user with a question and reads a date (day, month, and year) from the console.
     * Continues to prompt the user until a valid date is entered.
     *
     * @param question the question to display to the user.
     * @return the {@link LocalDate} entered by the user.
     */
    public static LocalDate readDate(String question) {
        while (true) {
            try {
                System.out.println(question);
                int day = readInteger("   Day: ");
                int month = readInteger("   Month: ");
                int year = readInteger("   Year: ");
                return LocalDate.of(year, month, day);
            } catch (DateTimeException e) {
                System.out.println(" ERROR: Introduce a valid date.");
            }
        }
    }

    /**
     * Prompts the user with a question and reads a string from the console.
     * Continues to prompt the user until input is successfully read.
     *
     * @param question the question to display to the user.
     * @return the string entered by the user.
     */
    public static String readString(String question) {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader buffer = new BufferedReader(input);
        String line;
        while (true) {
            try {
                System.out.println(question);
                line = buffer.readLine();
                return line;
            } catch (IOException ioe) {
                System.out.println(" ERROR: Unable to read.");
            }
        }
    }
}
