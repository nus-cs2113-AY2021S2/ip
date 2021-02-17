import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final TaskManager taskManager = new TaskManager();
    private static final Constants constants = new Constants();


    /**
     * Saves tasks into file.
     */
    public void saveFile() throws IOException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            if (!path.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        for (int i = 0; i < taskManager.getTaskCount(); i++) {
            fileWriter.write(taskManager.getTask(i).formatData());
        }
        fileWriter.flush();
        fileWriter.close();
    }


    /**
     * Loads tasks from file.
     */
    public void loadFile() throws FileNotFoundException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(path);
        try {
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                if (input.startsWith("todo ")) {
                    //Adds new ToDoTask
                    taskManager.addToDo(input);
                } else if (input.startsWith("event ")) {
                    //Adds new EventTask
                    taskManager.addEvent(input);
                } else if (input.startsWith("deadline ")) {
                    //Adds new DeadlineTask
                    taskManager.addDeadline(input);
                }
                if (scanner.nextLine().equals("true")) {
                    taskManager.setDoneStatus("done " + taskManager.getTaskCount(), true);
                }
            }
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_LOAD_ERROR);
            taskManager.reset();
        }
    }

}
