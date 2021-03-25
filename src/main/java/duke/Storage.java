package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.Constant.*;


public class Storage {

    /**
     * Creates text file that is to be read from if file does not exist.
     * Else, read from existing text file.
     *
     * @param filePath
     * @param tasks
     * @throws IOException
     */
    public static void readFile(String filePath, TaskList tasks) throws IOException {
        File file = new File(filePath + "/duke.txt");
        try {
            if (file.createNewFile()){
                Ui.fileCreatedMessage(file.getAbsolutePath());
            }
        } catch (IOException e) {
            Ui.errorMessageDuringFileCreation();
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String[] split = scanner.nextLine().split("\\|",0);
                String taskType = split[0];
                String isDoneString = split[1];
                String taskDetails = split[2];
                Boolean isDone;
                if (isDoneString.equals(MARK_DONE)) {
                    isDone = true;
                } else {
                    isDone = false;
                }

                switch (taskType) {
                case TODO_TASK_TYPE:
                    Task task = new Todo(taskDetails);
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                    break;
                case EVENT_TASK_TYPE:
                    String at = split[3];
                    task = new Event(taskDetails, at);
                    if (isDone) {
                        task.setDone();
                    }
                    tasks.addTask(task);
                    break;
                case DEADLINE_TASK_TYPE:
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
            Ui.printFileNotFoundExceptionMessage();
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
            for (int i = 0; i < tasks.size(); i++) {
                fileWriter.write(tasks.getDescriptionAtIndex(i) + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            Ui.printErrorMessageWritingToFile();
        }
    }
}
