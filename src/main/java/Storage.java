import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    public Storage() {}

    static void writeToFile(String filePath, String textToAdd) throws IOException {
        /**
         * Clears the content of the current
         * duke.txt file.
         * (also creates one if duke.txt does not exist)
         */
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        /**
         * Adds in tasks from TaskList to duke.txt
         */
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    static void saveTasksToFile(){
        /**
         * Saves the tasks from lists to duke.txt
         */
        try{
            writeToFile("duke.txt", "");
        } catch (IOException e) {
            System.out.println("Something went wrong when attempting to reset duke.txt: " + e.getMessage());
        }
        for (Task t : TaskList.tasks) {
            try {
                String lineToWrite = t.getNatureOfTask() + " ~ " + ((t.getIsDone())?"1":"0") +
                        " ~ " + t.getDescription() + " ~ " + t.getSpecialDescription();
                appendToFile("duke.txt", lineToWrite + System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong when attempting to write duke.txt: " + e.getMessage());
            }
        }
    }
}
