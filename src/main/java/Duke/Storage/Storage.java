package Duke.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import Duke.Duke;
import Duke.Task.*;
import Duke.UI.Ui;

public class Storage {
    private static final String FILE_LOCATION = "src/main/java/Duke/Storage/Duke.txt";
    private static final String COMMAND_TODO_WORD = "todo";
    private static final String COMMAND_EVENT_WORD = "event";
    private static final String COMMAND_DEADLINE_WORD = "deadline";

    /***
     *  Before running the system, read the file which contains the information which user type inside before
     ***/
    public static void readFile(List<Task> lists){
        try {
            File file = new File(FILE_LOCATION);
            if (file.createNewFile()) {
                System.out.println("A new file has been created!");
            } else {
                System.out.println("Reading saved Task Lists!");
                Scanner readingFile = new Scanner(file);
                while (readingFile.hasNextLine()) {
                    String line = readingFile.nextLine();
                    String[] parts = line.split("-", 3);
                    String type = parts[0];
                    String isDone = parts[1];
                    String task = parts[2];
                    if (type.equals(COMMAND_EVENT_WORD)) {
                        Task taskInFile = new EventTask(task);
                        Duke.lists.add(taskInFile);
                        Ui.showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }else if (type.equals(COMMAND_DEADLINE_WORD)) {
                        Task taskInFile = new DeadlineTask(task);
                        Duke.lists.add(taskInFile);
                        Ui.showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }else if (type.equals(COMMAND_TODO_WORD)) {
                        Task taskInFile = new TodoTask(task);
                        Duke.lists.add(taskInFile);
                        Ui.showToUser(taskInFile.toString());
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }
                    Duke.count++;
                }
        }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /***
     *  Before end the system, write all the list information inside the file
     ***/
    public static void writeFile(List<Task> lists) {
        try {
            FileWriter writer = new FileWriter(FILE_LOCATION,false);
            for (Task taskInList : lists) {
                writer.write(taskInList.getTaskType() + "-" + taskInList.isDone() + "-" + taskInList.getTask());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
