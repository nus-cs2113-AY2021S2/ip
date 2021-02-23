package duke.constants;

/**
 * Container for string constants used during FileIO.
 */

public class FileIOStrings {
    public static final String DELIM = "//";
    public static final String PATH = "duke_data.txt";
    
    /** Error messages for UI. */
    public static final String SAVE_FILE_ERROR_MESSAGE = "Something went wrong when saving...";
    public static final String FILE_FOUND_MESSAGE = "Existing records found! Data loaded..." + '\n';
    public static final String NO_FILE_FOUND_MESSAGE = "No previous records! Starting a new record...";
    public static final String CREATE_FILE_SUCCESS = "New file created!" + '\n';
    public static final String CREATE_FILE_ERROR = "Error creating new file..." + '\n';
}
