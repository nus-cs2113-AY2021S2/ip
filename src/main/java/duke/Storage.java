package duke;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void readFile(ArrayList<duke.Task> tasks) {
        try {
            File file = new File("src/main/java/duke.txt");
            Scanner SCANNER = new Scanner(file);

            while(SCANNER.hasNextLine()) {
                String[] split = SCANNER.nextLine().split("\\|",0);
                String taskType = split[0];
                String isDoneString = split[1];
                String taskDetails = split[2];
                Boolean isDone = false;
                if(isDoneString == "1") {
                    isDone = true;
                } else {
                    isDone = false;
                }

                switch (taskType) {
                    case "T":
                        duke.Task task = new duke.Todo(taskDetails);
                        if (isDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                    case "E":
                        String at = split[3];
                        task = new duke.Event(taskDetails, at);
                        if (isDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                    case "D":
                        String by = split[3];
                        task = new duke.Deadline(taskDetails, by);
                        if (isDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void writeToFile(ArrayList<duke.Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/duke.txt");
            for(int i=0; i<tasks.size(); i++) {
                fileWriter.write(tasks.get(i).getDescription() + "\n");
            }
         //   fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            System.out.println("Error while writing to file.");
        }
    }
}
