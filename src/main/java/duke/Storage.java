package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    public static void readFile(ArrayList<Task> tasks) {
        File file = new File("src/main/java/duke.txt");
        try {
            Scanner SCANNER = new Scanner(file);

            while(SCANNER.hasNextLine()) {
                String[] split = SCANNER.nextLine().split("\\|",0);
                String taskType = split[0];
                String isDoneString = split[1];
                String taskDetails = split[2];
                Boolean isDoneDone;
                if(isDoneString.equals("1")) {
                    isDoneDone = true;
                } else {
                    isDoneDone = false;
                }

                switch (taskType) {
                    case "T":
                        Task task = new Todo(taskDetails);
                        if (isDoneDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                    case "E":
                        String at = split[3];
                        task = new Event(taskDetails, at);
                        if (isDoneDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                    case "D":
                        String by = split[3];
                        task = new Deadline(taskDetails, by);
                        if (isDoneDone) {
                            task.setDone();
                        }
                        tasks.add(task);
                        break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e + "caught.");

        }
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/java/duke.txt");
            for(int i=0; i<tasks.size(); i++) {
                //System.out.println(tasks.get(i).isDone());
                fileWriter.write(tasks.get(i).getDescription() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file.");
        }
    }
}
