package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Storage class for the user's task datas to be stored in a txt file after finishing the session
 */
public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method that loads the task list from database.txt
     * @return the TaskList object as saved from the previous session
     */
    public TaskList loadTaskList() {
        TaskList taskList = new TaskList();

        File readFile;
        try {
            readFile = new File(filePath);
            if (readFile.createNewFile()) {
                return taskList;
            }
            else {
                Scanner myReader = new Scanner(readFile);
                while (myReader.hasNext()) {
                    String entry = myReader.nextLine();
                    Scanner entryData = new Scanner(entry);
                    entryData.useDelimiter("\\|"); //escaping the character |
                    String typeOfEntry = entryData.next().trim();
                    String isCompleted = entryData.next().trim();
                    switch (typeOfEntry) {
                    case "T": {
                        String taskName = entryData.next().trim();
                        Todo newTodo = new Todo(taskName);
                        if (isCompleted.equals("1")) {
                            newTodo.setDone();
                        }
                        taskList.addTask(newTodo);
                        break;
                    }
                    case "D": {
                        String taskName = entryData.next().trim();
                        String period = entryData.next().trim();
                        LocalDate deadlineDate = LocalDate.parse(period);
                        Deadline newDeadline = new Deadline(taskName, deadlineDate);
                        if (isCompleted.equals("1")) {
                            newDeadline.setDone();
                        }
                        taskList.addTask(newDeadline);
                        break;
                    }
                    case "E": {
                        String taskName = entryData.next().trim();
                        String period = entryData.next().trim();
                        LocalDate eventDate = LocalDate.parse(period);
                        Event newEvent = new Event(taskName, eventDate);
                        if (isCompleted.equals("1")) {
                            newEvent.setDone();
                        }
                        taskList.addTask(newEvent);
                        break;
                    }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return taskList;
    }

    /**
     * Method that saves the user's task data into database.txt
     * @param taskList the user's latest taskList
     */
    public static void saveData(TaskList taskList) {
        try {
            File database;
            database = new File("database.txt");
            database.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        String stringForSaving = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task currentTask = taskList.getTask(i);
            stringForSaving += currentTask.toStringSave();
            stringForSaving += "\n";
        }
        try {
            FileWriter fileEditor = new FileWriter("database.txt");
            fileEditor.write(stringForSaving);
            fileEditor.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}