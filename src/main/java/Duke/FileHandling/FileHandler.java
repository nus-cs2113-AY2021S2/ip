package Duke.FileHandling;

import Duke.Commands.PrintList;
import Duke.Duke;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import Duke.Task.Task;
import Duke.Task.ToDoTask;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class FileHandler extends Duke{

    public static void readFile(List<Task> lists){
        try {
            String localDir = System.getProperty("user.dir");
            File file = new File(localDir + "/Duke.txt");
            if (file.createNewFile()) {
                System.out.println("A new file has been created! ^_^\nIt could be found at " + localDir);
            } else {
                System.out.println("Reading saved Task Lists ^_^\nIt could be found at " + localDir);
                Scanner readingFile = new Scanner(file);
                while (readingFile.hasNextLine()) {
                    String line = readingFile.nextLine();
                    String[] parts = line.split("\\|", 4);
                    String type = parts[0];
                    String isDone = parts[1];
                    String task = parts[2];
                    if (type.equals("T")) {
                        Task taskInFile = new ToDoTask(task);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    } else if (type.equals("D")) {
                        String time = parts[3];
                        DeadlineTask taskInFile = new DeadlineTask(task, time);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    } else {
                        String time = parts[3];
                        EventTask taskInFile = new EventTask(task, time);
                        lists.add(taskInFile);
                        if (isDone.equals("true")) {
                            taskInFile.markAsDone();
                        }
                    }
                }
                taskCount = lists.size();
                PrintList.printList(0, taskCount);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(@NotNull List<Task> lists){
        try {
            String localDir = System.getProperty("user.dir");
            FileWriter writer = new FileWriter(localDir + "/Duke.txt",false);
            for (Task taskInList : lists) {
                if (taskInList instanceof ToDoTask) {
                    writer.write(taskInList.getTaskType() + "|" + taskInList.isDone() + "|" + taskInList.getTask().trim());
                } else {
                    writer.write(taskInList.getTaskType() + "|" + taskInList.isDone() + "|" + taskInList.getTask().trim() + "|" + taskInList.getTaskTime().trim());
                }
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
