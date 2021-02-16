package Duke;

import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;

public class TextFile {

    public static TaskList loadTaskList() {
        TaskList taskList = new TaskList();
        File readFile;
        try {
            readFile = new File("database.txt");
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
                        Deadline newDeadline = new Deadline(taskName, period);
                        if (isCompleted.equals("1")) {
                            newDeadline.setDone();
                        }
                        taskList.addTask(newDeadline);
                        break;
                    }
                    case "E": {
                        String taskName = entryData.next().trim();
                        String period = entryData.next().trim();
                        Event newEvent = new Event(taskName, period);
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