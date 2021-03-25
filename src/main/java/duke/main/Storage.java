package duke.main;

import duke.items.Deadline;
import duke.items.Event;
import duke.items.Task;
import duke.items.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class in charge of file IO
 */
public class Storage {

    /**
     * Handles the file IO process
     */
    public static void fileHandling(){
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            createFile();
        }
    }

    /**
     * Loads data into current list of tasks
     *
     * @param line  Each line (1 line represents 1 task) in saved records (list.txt)
     */
    public static void loadTask(String line){
        String[] arr = line.split("\t");
        switch(arr[0]){
        case ("T"):
            Todo todo = new Todo(arr[2]);
            if (arr[1].equals("true")) {
                todo.setDone();
            }
            Task.addTask(todo);
            break;
        case ("D"):
            Deadline deadline = new Deadline(arr[2], arr[3]);
            if (arr[1].equals("true")) {
                deadline.setDone();
            }
            Task.addTask(deadline);
            break;
        case ("E"):
            Event event = new Event(arr[2], arr[3]);
            if (arr[1].equals("true")) {
                event.setDone();
            }
            Task.addTask(event);
            break;
        }
    }

    /**
     * Reads input from the file list.txt
     *
     * @throws FileNotFoundException  If file does not exist
     */
    public static void loadFile() throws FileNotFoundException {
        File f = new File("list.txt"); // create a File for the given file path
        Scanner s = new Scanner(f);            // create a Scanner using the File as the source
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
    }

    /**
     * Writes every task that exists within the Task list into the given format in list.txt
     *
     * @throws IOException  If write errors occurs
     */
    public static void writeToFile() throws IOException {
        createFile();
        FileWriter fw = new FileWriter("list.txt");
        for (int i=0; i<Task.getNumOfTasks(); i++) {
            ArrayList<Task> buffer = Task.getList();
            fw.write(formWriteData(buffer.get(i)));
        }
        fw.close();
    }


    /**
     * Returns the formatted String (of a task), to be stored in a .txt file
     *
     * @param task  Current task to be formatted into String
     * @return Formatted String
     */
    public static String formWriteData(Task task) {

        switch (task.getType()) {
        case ("T"):
            return (task.getType() + "\t" + task.isDone() + "\t" + task.getDescription() + "\n");
        case ("D"):
            Deadline temp1 = (Deadline) (task);
            return (temp1.getType() + "\t" + temp1.isDone() + "\t" + temp1.getDescription() + "\t" + temp1.getBy() + "\n");
        default:
            Event temp2 = (Event) (task);
            return (temp2.getType() + "\t" + temp2.isDone() + "\t" + temp2.getDescription() + "\t" + temp2.getAt() + "\n");
        }
    }

    /**
     * Creates file if file does not exist
     *
     * @throws IllegalArgumentException  If input errors occur
     */
    public static void createFile(){
        try {
            File myObj = new File("list.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File is overwritten.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}