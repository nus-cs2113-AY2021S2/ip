package Baggie.FileHandling;

import Baggie.Baggie;
import Baggie.Commands.PrintListCommand;
import Baggie.Task.DeadlineTask;
import Baggie.Task.EventTask;
import Baggie.Task.Task;
import Baggie.Task.ToDoTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Baggie.UI.TEXT.*;

public class FileHandler extends Baggie {

    /**
     * Loads tasks from file
     */
    public static void readFile(){
        try {
            String localDir = System.getProperty(USER_DIRECTORY); //get the path of current directory
            File file = new File(localDir + File.separator + FILE_NAME);
            if (file.createNewFile()) {
                //create new file if file does not exist
                System.out.println( A_NEW_FILE + file.getName() + HAS_BEEN_CREATED + IT_COULD_BE_FOUND + file.getAbsolutePath());
            } else {
                System.out.println( READ_FILE + file.getName()+ "] ^_^\n" + IT_COULD_BE_FOUND + file.getAbsolutePath());
                Scanner readingFile = new Scanner(file);
                while (readingFile.hasNextLine()) {
                    String line = readingFile.nextLine();
                    String[] parts = line.split("\\|", 4);
                    String type = parts[0];
                    String isDone = parts[1];
                    String task = parts[2];
                    Task taskInFile = new Task(task);
                    switch (type) {
                    case TASK_TYPE_TODO:
                    taskInFile = new ToDoTask(task);
                    lists.add(taskInFile);
                    break;
                    case TASK_TYPE_DEADLINE:
                    String time = parts[3];
                    taskInFile = new DeadlineTask(task, time);
                    lists.add(taskInFile);
                    break;
                    case TASK_TYPE_EVENT:
                    time = parts[3];
                    taskInFile = new EventTask(task, time);
                    lists.add(taskInFile);
                    break;
                    }
                    if (isDone.equals(TRUE)) {
                        taskInFile.markAsDone();
                    }
                }
                taskCount = lists.size();
                //prints list after copying every task from file
                PrintListCommand.printList(0, taskCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies list to file
     */
    public static void writeFile(){
        try {
            String localDir = System.getProperty(USER_DIRECTORY);
            FileWriter writer = new FileWriter(localDir + File.separator + FILE_NAME ,false);
            for (Task taskInList : lists) {
                writer.write(taskInList.getTaskType() + DIVIDER + taskInList.isDone() + DIVIDER + taskInList.getTask().trim());
                if (!(taskInList instanceof ToDoTask)) {
                    //if it's a deadline task or event task, there's time info
                    writer.write(DIVIDER + taskInList.getTaskTime().trim());
                }
                writer.write("\r\n");
            }
            System.out.println(FILE_SAVED);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
