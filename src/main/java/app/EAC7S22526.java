package app;

import java.util.Locale;

import classes.EventList;
import utils.DataFileUtils;
import utils.UtilsIO;

/**
 * Main class that controls the application's execution flow.
 * <p>
 * This program interacts with users through a menu system to manage
 * event and bet data stored in files. It supports inserting, displaying,
 * importing, and exporting event data along with their associated bets.
 * </p>
 * <p>
 * Data input/output operations are handled by utility classes such as
 * {@link UtilsIO}, {@link DataFileUtils}, and {@link EventList}.
 * </p>
 * 
 * @IOC This class represents the entry point and central controller
 *      of the application.
 */
public class EAC7S22526 {

    /**
     * Entry point of the program.
     * <p>
     * Sets the default locale to US for consistent formatting of numbers,
     * creates an instance of the class, and starts the program loop.
     * </p>
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
        EAC7S22526 program = new EAC7S22526();
        Locale.setDefault(Locale.US);
        program.start();
    }

    /**
     * Starts the main program loop, handling menu options and user interaction.
     * <p>
     * Prompts the user for the data directory and file name, then continuously
     * displays a menu and executes selected operations until the user exits.
     * </p>
     * <p>
     * Corner cases handled:
     * <ul>
     *   <li>Invalid menu option input — displays an error message and re-prompts.</li>
     *   <li>Non-integer input for menu selection — displays an error message and re-prompts.</li>
     * </ul>
     * </p>
     */
    public void start() {
        
    }
}
