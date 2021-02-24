package duke.items;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import duke.exceptions.*;

import static duke.main.UI.stringPatternToDate;

/**
 * Parent class for other classes (todo, deadline, event)
 */
public class Task {
    private static int numOfTasks = 0;
    protected String description;
    protected boolean isDone;
    protected static ArrayList<Task> list = new ArrayList<>();

    /**
     * Returns the ArrayList from Task
     *
     * @return ArrayList
     */
    public static ArrayList getList(){
        return list;
    }

    /**
     * Returns the number of Tasks that are in the ArrayList list
     *
     * @return numOfTasks
     */
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Prints each Task in the list using print() function
     */
    public static void printList(){
        for (int i =0; i<numOfTasks;i++) {
            System.out.print(i+1 + ".");
            list.get(i).print();
        }
    }

    /**
     * Adds the given Task object into the ArrayList list
     *
     * @param task Task object to be added
     */
    public static void addTask(Task task) {
        list.add(task);
        numOfTasks++;
    }

    /**
     * Finds all the Tasks in list that contains the given String
     *
     * @param find String item used to search the Tasks in the list
     */
    public static void findTask(String find) {
        int counter = 1;
        for (int i =0; i<numOfTasks;i++) {
            if (list.get(i).description.contains(find)) {
                System.out.print(counter + ".");
                list.get(i).print();
                counter++;
            }
        }
    }

    /**
     * Prints all Events on a specific date
     *
     * @param find date to search
     */
    public static void findAt(String find) {
        int counter = 1;
        for (int i =0; i<numOfTasks;i++) {
            if ( !(list.get(i) instanceof Event)){
                continue;
            }
            if (((Event)list.get(i)).getAt().contains(find)) {
                System.out.print(counter + ".");
                list.get(i).print();
                counter++;
            }
        }
    }

    /**
     * Prints all Deadlines before and including a certain date
     *
     * @param find date to search
     * @throws ParseException  If there is an error with date conversion
     */
    public static void findBy(String find) throws ParseException {
        int counter = 1;
        for (int i =0; i<numOfTasks;i++) {
            if ( !(list.get(i) instanceof Deadline)){
                continue;
            }
            LocalDate inputDate = LocalDate.parse(find);
            LocalDate current = stringPatternToDate(((Deadline)list.get(i)).getBy());
            if (!(inputDate.isBefore(current))){
                System.out.print(counter + ".");
                list.get(i).print();
                counter++;
            }
        }
    }
    /**
     * Removes a Task from the list
     *
     * @param index Task number
     */
    public static void deleteTask(int index) {
        numOfTasks--;
        System.out.println("Noted. I've removed this task: ");
        System.out.print("  ");
        list.get(index).print();
        list.remove(index);
    }

    /**
     * Set a specified Task to be done
     *
     * @param index Task number
     * @throws InvalidIndexExceptions  If index out of range
     */
    public static void setDone(int index) throws InvalidIndexExceptions{
        if (index >= numOfTasks){
            throw new InvalidIndexExceptions();
        }
        list.get(index).isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("[\u2713] " + list.get(index).getDescription());
    }

    /**
     * Task object initialisation
     *
     * @param description  Description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Prints the Task in a fixed format
     */
    public void print(){
        if (this.isDone) {
            System.out.println("[T][\u2713] " + description );
        } else {
            System.out.println("[T][\u2718] " + description );
        }
    }

    /**
     * Returns Description of the given Task object
     *
     * @return Description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Change the description attribute of Task
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns Type of the given Task object
     *
     * @return Type
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns whether the Task is done
     *
     * @return boolean of isDone
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Change the isDone attribute of Task
     */
    public void setDone() {
        this.isDone = true;
    }
}