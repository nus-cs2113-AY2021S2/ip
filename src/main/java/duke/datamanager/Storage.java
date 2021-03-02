package duke.datamanager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles all the file-processing activities
 */
public class Storage {
    /**
     * This method handles the reading and loading of saved data into Duke
     * @throws IOException when the file fails to be read
     */
    public static void readFile() throws IOException {
        File data = new File("data/savedata.txt");
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner sc = new Scanner(data);
        sc.nextLine(); // skip the first introductory line
        while (sc.hasNext()) {
            String taskInput = sc.nextLine();
            String[] taskComponents = taskInput.split(" \\| ");
            switch (taskComponents[0]){
                case "E":
                    Event newEvent = readEvent(taskComponents);
                    Task.addNewTask(newEvent);
                    break;
                case "D":
                    Deadline newDeadline = readDeadline(taskComponents);
                    Task.addNewTask(newDeadline);
                    break;
                case "T":
                    Todo newTask = readTodo(taskComponents);
                    Task.addNewTask(newTask);
                    break;
            }
        }
    }

    /**
     * @param filePath is the file's destination path
     * @param textToAppend is the contents to be added to the file's current contents
     * @throws IOException if anything fails
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + '\n');
        fw.close();
    }

    /**
     * This method handles the saving of data to the file after significant operations are performed by the user
     * leading to the modification of data
     * @throws IOException when the file reading falls through
     */
    public static void saveToFile() throws IOException {
        String filePath = "data/savedata.txt";
        FileWriter firstLineWriter = new FileWriter(filePath);
        firstLineWriter.write("Duke's Most Recent Task List\n");
        firstLineWriter.close();

        for (int i = 0; i < Task.getTaskCounter(); i++){
            Task task = Task.getTaskList().get(i);
            String textToAppend = "";
            if (task instanceof Event) {
                textToAppend = writeEvent((Event) task);
            } else if (task instanceof Deadline) {
                textToAppend = writeDeadline((Deadline) task);
            } else {
                textToAppend = writeTodo((Todo) task);
            }
            appendToFile(filePath, textToAppend);
        }
    }


    /**
     * @param taskComponents are the key contructor elements for a Todo
     * @return a complete, valid todo object and adds it to the main list
     */
    public static Todo readTodo(String[] taskComponents) {
        Todo newTodo = new Todo(taskComponents[2].trim());
        boolean isDone = taskComponents[1].equals("1");
        if (isDone) {
            newTodo.markAsDone();
            Task.incrementCompletedTaskCounter();
        }
        return newTodo;
    }

    /**
     * @param taskComponents are the key components in the deadline constructor
     * @return a complete, valid deadline object
     */
    public static Deadline readDeadline(String[] taskComponents) {
        String[] deadlineData = taskComponents[2].split("\\|\\|");
        Deadline newDeadline = new Deadline(deadlineData[0].trim(), deadlineData[1].trim());
        boolean isDone = taskComponents[1].equals("1");
        if (isDone) {
            newDeadline.markAsDone();
            Task.incrementCompletedTaskCounter();
        }
        return newDeadline;
    }

    /**
     * @param taskComponents are the key components in the event constructor
     * @return a complete, valid event object
     */
    public static Event readEvent(String[] taskComponents) {
        String[] eventData = taskComponents[2].split("\\|\\|");
        Event newEvent = new Event(eventData[0].trim(), eventData[1].trim());
        boolean isDone = taskComponents[1].equals("1");
        if (isDone) {
            newEvent.markAsDone();
            Task.incrementCompletedTaskCounter();
        }
        return newEvent;
    }

    /**
     * @param todo is the Todo object to be saved to the file
     * @return a String to be written to the file
     */
    public static String writeTodo(Todo todo) {

        return "T | " +
                isDoneWriter(todo) +
                todo.getDescription();
    }

    /**
     * @param deadline is the Deadline object to be saved to the file
     * @return a String to be written to the file
     */
    public static String writeDeadline(Deadline deadline) {

        return "D | " +
                isDoneWriter(deadline) +
                deadline.getDescription() +
                " || " +
                deadline.writeDateTime();

    }

    /**
     * @param event is the Event object to be saved to the file
     * @return a String to be written to the file
     */
    public static String writeEvent(Event event) {
        return "E | " +
                isDoneWriter(event) +
                event.getDescription() +
                " || " +
                event.writeDateTime();
    }

    /**
     * Helper function to generate contents to be written to the file
     * @param task is the task to be checked
     * @return a String element corresponding to whether the task being checked is complete or not
     */
    public static String isDoneWriter(Task task){
        if (task.isDone()) {
            return "1 | ";
        }
        else {
            return "0 | ";
        }
    }

}
