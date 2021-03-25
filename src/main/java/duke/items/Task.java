package duke.items;

import java.time.LocalDate;
import java.util.ArrayList;
import duke.exceptions.*;
import duke.main.UI;

import static duke.main.Parser.*;
import static duke.main.UI.convertDateToStringFormat;
import static duke.main.UI.convertStringFormatToDate;

/** Parent class for other classes (todo, deadline, event) */
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
    public static void addTask(Task task){
        list.add(task);
        numOfTasks++;
    }

    /**
     * Adds a Todo task object into the ArrayList list
     *
     * @param line Input command
     * @throws InvalidParameterLengthExceptions  If length of entire todo command is < 2
     */
    public static void addTodo(String line) throws InvalidParameterLengthExceptions {
        validateTodoCommand(line);
        System.out.println("Got it. I've added this task: ");
        Todo todo = new Todo(extractTodo(line));
        list.add(todo);
        numOfTasks++;
        System.out.print("  ");
        todo.print();
        UI.displayListUpdate();

    }

    /**
     * Adds a Deadline task object into the ArrayList list
     *
     * @param line Input command
     * @throws DeadlineParameterExceptions  If /by is missing
     * @throws EventParameterExceptions  If /at is missing
     * @throws InvalidParameterLengthExceptions  If number of parameters < 4
     */
    public static void addDeadline(String line) throws EventParameterExceptions, InvalidParameterLengthExceptions, DeadlineParameterExceptions {
        String[] extractDeadline = extractDeadlineAndEvent(line);
        Deadline deadline = new Deadline(extractDeadline[0], convertDateToStringFormat(extractDeadline[1]));
        System.out.println("Got it. I've added this task: ");
        list.add(deadline);
        numOfTasks++;
        System.out.print("  ");
        deadline.print();
        UI.displayListUpdate();
    }

    /**
     * Adds a Event task object into the ArrayList list
     *
     * @param line Input command
     * @throws DeadlineParameterExceptions  If /by is missing
     * @throws EventParameterExceptions  If /at is missing
     * @throws InvalidParameterLengthExceptions  If number of parameters < 4
     */
    public static void addEvent(String line) throws EventParameterExceptions, InvalidParameterLengthExceptions, DeadlineParameterExceptions {
        String[] extractEvent = extractDeadlineAndEvent(line);
        Event event = new Event(extractEvent[0], convertDateToStringFormat(extractEvent[1]));
        System.out.println("Got it. I've added this task: ");
        list.add(event);
        numOfTasks++;
        System.out.print("  ");
        event.print();
        UI.displayListUpdate();
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
    public static void findAt(String find){
        int counter = 1;
        for (int i =0; i<numOfTasks;i++) {
            if ( (list.get(i) instanceof Event) && ((Event) list.get(i)).getAt().equals(find)){
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
     */
    public static void findBy(String find){
        int counter = 1;
        for (int i =0; i<numOfTasks;i++) {
            if ( !(list.get(i) instanceof Deadline)){
                continue;
            }
            LocalDate inputDate = LocalDate.parse(find);
            LocalDate current = convertStringFormatToDate(((Deadline)list.get(i)).getBy());
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
        System.out.println("[√] " + list.get(index).getDescription());
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
            System.out.println("[T][√] " + description );
        } else {
            System.out.println("[T][X] " + description );
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
     * Changes the description attribute of Task
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
     * Changes the isDone attribute of Task
     */
    public void setDone() {
        this.isDone = true;
    }
}