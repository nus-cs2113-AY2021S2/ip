package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class containing the methods necessary to perform file storage in Duke
 * including saving the file and loading the file
 * includes methods to parse the input from file to be loaded into the Duke program
 * where the list of tasks can be displayed exactly the same.
 */
public class Storage {
    public static final int NEW_TASK_INDEX = 6;
    public static String filepath = "data.txt";

    /**
     * Method to load the data from a previously-saved Duke file
     * unless the file is not found, then prints a different welcome message
     *
     * @param filepath the path to load the file from
     * @throws IOException if there is an error reading the file
     */
    static void loadData(String filepath) throws IOException {
        File f = new File(filepath);
        if (f.createNewFile()) {
            System.out.println("Welcome to Duke. Is this your first time using Duke on this machine?");
        } else {
            System.out.println("Your previous Task list from Duke has been loaded! :-)");
            readAndParseFileContents(filepath);
        }
    }

    /**
     * Saves the content of "List" command into a text file
     * that can be be processed later by the loadData method.
     */
    static void saveData() {
        try {
            String data = getAllTaskListData();
            writeToFile(filepath, data);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No items to add to file");
        }
    }

    /**
     * Reads the contents from a loaded Duke file
     * and processes line by line
     *
     * @param filepath the path to load the file from
     * @throws FileNotFoundException if there file does not exist
     */
    private static void readAndParseFileContents(String filepath) throws FileNotFoundException {
        File f = new File(filepath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            addTaskAndMarkIfCompletedFromFile(line);
        }
    }

    /**
     * Parses one line from a Duke saved file
     * and adds the corresponding new Task into the corresponding Duke Task List
     * Will also mark the task as Done if it is already marked as completed inside the file
     *
     * taskType -> The first character ("T"/"E"/"D") as stored in the input file
     * Done -> Whether the Task is Done ("Y"/"N") as stored in the input file
     * @param fileLine one line of input in the file
     */
    private static void addTaskAndMarkIfCompletedFromFile(String fileLine) {
        char taskType = getTaskType(fileLine);
        char isDone = getDone(fileLine);

        TaskList.incrementTasks();
        addNewTaskFromFile(fileLine, taskType);
        markCompletedTaskFromFile(isDone);

    }

    /**
     * Checks if a given Task is marked as Done from a saved file
     * and marks it as Done in the Task List if true.
     *
     * @param isDone character indicating if a task is done
     */
    private static void markCompletedTaskFromFile(char isDone) {
        if (isDone == 'Y') {
            TaskList.tasks.get(TaskList.maxTaskIndex - 1).markAsDone();
        }
    }

    /**
     * Executes commands to identify the new Task that was loaded from the Duke saved file
     * and adds the corresponding new Task into the corresponding Duke Task List
     * Will also directly process the time data for Deadlines and Events
     * Legend: taskTypes 'T' -> Todo, 'D' -> Deadline, 'E' -> Event
     *
     * @param fileLine one line of input in the file
     * @param taskType the character indicating the task type in the file
     */
    private static void addNewTaskFromFile(String fileLine, char taskType) {
        String description;
        switch (taskType) {
        case ('T'):
            TaskList.addNewTodo("todo" + fileLine.substring(NEW_TASK_INDEX));
            break;
        case ('D'):
            int deadlineIndex = fileLine.indexOf("(by:");
            description = fileLine.substring(NEW_TASK_INDEX, deadlineIndex);
            String deadline = getTimeFromFile(fileLine, deadlineIndex);
            TaskList.addNewDeadline("deadline" + description + "/by" + deadline);
            break;
        case ('E'):
            int eventIndex = fileLine.indexOf("(at:");
            description = fileLine.substring(NEW_TASK_INDEX, eventIndex);
            String event = getTimeFromFile(fileLine, eventIndex);
            TaskList.addNewEvent("event" + description + "/at" + event);
            break;
        }
    }

    /**
     * Returns the substring where the event or deadline time would be
     * The input is guaranteed to be correct because the Duke saved file is of a fixed length format
     *
     * @param fileLine  one line of input in the file
     * @param timeIndex the position in the string indicating where time information starts
     * @return string containing time information for Deadline or Event
     */
    private static String getTimeFromFile(String fileLine, int timeIndex) {
        return fileLine.substring(timeIndex + 4, fileLine.length() - 1);
    }

    /**
     * Saves the Task List to the file
     *
     * @param filePath  path where file is stored
     * @param textToAdd text to be written to the file
     * @throws IOException if error writing to file
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Saves the Task List data into a single String
     * and returns it to be written to the saved file
     *
     * @return String containing List of Tasks
     */
    private static String getAllTaskListData() {
        String data = "";
        for (int i = 0; i < TaskList.maxTaskIndex; i++) {
            data = data + (TaskList.tasks.get(i).toString()) + "\n";
        }
        return data;
    }

    private static char getTaskType(String fileLine) {
        return fileLine.charAt(1);
    }

    private static char getDone(String fileLine) {
        return fileLine.charAt(4);
    }

}
