package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final int NEW_TASK_INDEX = 6;
    public static String filepath = "data.txt";

    static void loadData(String filepath) throws IOException {
        File f = new File(filepath);
        if (f.createNewFile()) {
            System.out.println("Welcome to Duke. Is this your first time using Duke on this machine?");
        } else {
            System.out.println("Your previous Task list from Duke has been loaded! :-)");
            processFileContents(filepath);
        }
    }

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

    private static void processFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            processFileInputLines(line);
        }
    }

    private static void processFileInputLines(String fileLine) {
        char taskType = getTaskType(fileLine);
        char isDone = getDone(fileLine);

        TaskList.incrementTasks();
        addNewTaskFromFile(fileLine, taskType);
        markCompletedTaskFromFile(isDone);

    }

    private static void markCompletedTaskFromFile(char isDone) {
        if (isDone == 'Y') {
            TaskList.tasks.get(TaskList.maxTaskIndex-1).markAsDone();
        }
    }

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

    private static String getTimeFromFile(String fileLine, int timeIndex) {
        return fileLine.substring(timeIndex + 4, fileLine.length() - 1);
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

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
