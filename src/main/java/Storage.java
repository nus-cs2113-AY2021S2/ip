import java.io.*;
import java.io.File;

public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage Class
     * @param filePath default filepath to store duke.txt file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Crate a FileWriter object that appends to the file Duke.txt
     * with the specific task description each time a new task is created
     * @param filePath the default filepath for duke.txt
     * @param textToAppend the description of the new task added
     * @throws IOException throw the IOException when the program successfully opened
     * the file but is unable to read the duke.txt file
     */
    protected static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + "\n");
        fw.close();
    }

    /**
     * Read the content of existing file and replace task status of a task from 0 to 1 when it's mark as done.
     * Finally crate a FileWriter object to rewrite the file with replaced text and unchanged texts.
     * @param name The description of the task that is marked as done
     * @throws IOException throw the IOException when the program successfully opened
     * the file, but is unable to read the duke.txt file
     */
    public static void replaceTXT(String name) throws IOException {
        File file = new File("C:/Users/XPS/Desktop/duke.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        String oldtext = "";
        while ((line = reader.readLine()) != null) {
            oldtext += line + "\r\n";
        }
        reader.close();
        String replaced_text = oldtext.replace("0 | " + name , "1 | " + name);
        FileWriter writer = new FileWriter("C:/Users/XPS/Desktop/duke.txt");
        writer.write(replaced_text);
        writer.close();
    }

}
