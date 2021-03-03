package Duke;

/**
 * Duke class which serves as the Main class of the program
 */
public class Duke {

    /**
     * Name of the txt file that the user wants to use to save data
     * It will be run as a relative file path for the program to access the database
     */
    public static final String FILEPATH = "database.txt";

    /**
     * Main program
     * @param args
     */
    public static void main(String[] args) {
        String storagePath = FILEPATH;
        Logic logic = new Logic(storagePath);
        logic.run();
    }

}