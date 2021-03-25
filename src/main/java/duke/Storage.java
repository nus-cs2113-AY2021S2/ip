package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {

    /**
     *
     * @param filePath
     * @param tasks
     * @throws IOException
     */
    public static void readFile(String filePath, TaskList tasks) throws IOException {
        File file = new File(filePath + "/duke.txt");
        try {
            if (file.createNewFile()){
                System.out.println("file created at current location: "
                        + String.format(file.getAbsolutePath()));
            } else {
                System.out.println("file is at location: \n"
                        + String.format(file.getAbsolutePath()));
            }
        } catch (IOException e) {
            Ui.errorMessageDuringFileCreation();
        }

        try {
            Scanner SCANNER = new Scanner(file);

            while(SCANNER.hasNextLine()) {
                String[] split = SCANNER.nextLine().split("\\|",0);
                String taskType = split[0];
                String isDoneString = split[1];
                String taskDetails = split[2];
                Boolean isDone;
                if(isDoneString.equals("1")) {
                    isDone = true;
                } else {
                    isDone = false;
                }

                switch (taskType) {
                case "T":
                    Task task = new Todo(taskDetails);
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                    break;
                case "E":
                    String at = split[3];
                    task = new Event(taskDetails, at);
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                    break;
                case "D":
                    String by = split[3];
                    task = new Deadline(taskDetails, by);
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println(e + "caught.");

        }
    }

    /**
     * Update duke.txt file each time a new task is added/delete/marked.
     *
     * @param tasks
     */
    public static void writeToFile(TaskList tasks) {
        String filePath = new File("").getAbsolutePath();
        filePath = filePath + "/duke.txt";

        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for(int i=0; i<tasks.size(); i++) {
                fileWriter.write(tasks.getDescriptionAtIndex(i) + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error while writing to file. Try again!");
        }
    }
}
