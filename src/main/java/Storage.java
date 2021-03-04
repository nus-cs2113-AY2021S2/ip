import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    protected String filepath;
    protected ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * creates new file
     *
     * @return tasks array
     */
    public ArrayList<Task> createNewFile() {

        String pathOfFile = new File("").getAbsolutePath();
        File tasksFile = new File(pathOfFile + "/duke.txt");
        try {
            if (tasksFile.createNewFile()) {
                System.out.println("\tTo save your task locally,\n" +
                        "\tA new file has been created at:\n\t" +
                        tasksFile.getAbsolutePath() + "\n");
            }
        } catch (IOException e) {
            System.out.println("\tThere was an I/O error:\nBye!\n");
            e.printStackTrace();
        }
        return tasks;
    }

    public String getFilepath() {
        return this.filepath;
    }

    /**
     *
     * @return
     * @throws FileNotFoundException
     */
    public ArrayList<Task> uploadTasks() throws FileNotFoundException {
        File f = new File(getFilepath());
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" ==> ");
            if (task[0].equals("T")) {
                tasks.add(new Todo(task[2]));
            } else if (task[0].equals("D")) {
                tasks.add(new Deadline(task[2], task[3]));
            } else {
                tasks.add(new Event(task[2], task[3]));
            }
            if (task[1].equals("[X]")) {
                tasks.get(tasks.size()-1).markAsDone();
            }
        }
        return tasks;
    }

    /**
     * saves tasks into file
     *
     * @throws IOException
     */
    public void saveTasks() throws IOException {
        FileWriter fw = new FileWriter(getFilepath());
        for (Task task : tasks) {
            fw.write(task.stringToSave());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    /**
     * exits program
     */
    public void exitProgram() {
        Duke.printDash();
        System.out.println("Bye. Hope to see you again soon!");
        Duke.printDash();
        try {
            saveTasks();
        } catch (IOException e) {
            System.out.println("No file was saved due to an I/O error.\n");
        }
    }

}

