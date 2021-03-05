import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /**
     * relative path of the data file.
     */
    private String filePath;

    /**
     * Constructor for an storage object.
     * @param filePath relative path of the data file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Store the list of tasks to a data file.
     * The tasks are saved as: [Task type][completion icon]Description
     * If the task is done, it will be marked as [✓]
     * If the task is still pending, it will be marked as [✘]
     * @param filePath relative path to the data file
     * @param tasks the list of existing tasks
     * @throws IOException If something went wrong when saving the tasks
     */
    static void writeToFile(String filePath, TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i=0; i<Task.getTaskCount(); i++) {
            Task t = tasks.getTaskByIndex(i);
            fw.write(t.toString()+"\n");
        }
        //fw.write(textToAdd);
        fw.close();
    }

    /**
     *Read the data file and print ot the tasks written in the file as: [Task type][completion icon]Description
     * If the task is done, it will be marked as [✓]
     * If the task is still pending, it will be marked as [✘]
     * Create a new file if the data file does not exist yet.
     * @param filePath relative path to the data file
     * @return list of tasks written in the data file
     * @throws FileNotFoundException If the data file specified does not exist.
     */
    static ArrayList<Task> printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String nextItem = s.nextLine();
            loadItem(nextItem, tasks);
            System.out.println(nextItem);
        }
        return tasks;
    }

    /**
     * Read from the data file content and store the data as a list of tasks
     * @param item A line item read from the data file
     * @param tasks A list of tasks
     */
    private static void loadItem(String item, ArrayList<Task> tasks) {
        char type = item.charAt(1);
        Task task;
        String description;
        switch (type){
        case 'T':
            task = new Todo(item.substring(6));
            task.loadStatus(item.substring(4,5));
            break;
        case 'E':
            description = item.substring(6, item.indexOf(" (at:"));
            String at = item.substring(item.indexOf(" (at:") + 6 , item.indexOf(")"));
            task = new Event(description, at);
            task.loadStatus(item.substring(4,5));
            break;
        case 'D':
            description = item.substring(6, item.indexOf(" (by:"));
            String by = item.substring(item.indexOf(" (by:") + 6 , item.indexOf(")"));
            task = new Deadline(description, by);
            task.loadStatus(item.substring(4,5));
            break;
        default:
            System.out.println("no tasks");
            task = null;
        }
        tasks.add(task);
    }

    /**
     * Create a new directory and an empty data file.
     */
    static void createDirAndFile() {
        try {
            System.out.println("Creating file: data/duke.txt");
            File newDir = new File("data");
            newDir.mkdir();
            File f = new File("data/duke.txt");
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
