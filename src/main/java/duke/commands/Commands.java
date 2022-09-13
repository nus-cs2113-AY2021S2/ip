package duke.commands;

import duke.error.ListEmptyException;
import duke.ui.Ui;
import duke.error.DoneCheckedException;
import duke.error.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;
import java.util.ArrayList;

public class Commands {

    /**
     * Add a todo task to the list
     *
     * @param input Task description
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void addTodo(String input, ArrayList<Task> list) {
        try{
            if (input.equals("todo")) {
                throw new WrongFormatException();
            }
            String command = input.substring(5);
            if (command.isBlank()) {
                throw new WrongFormatException();
            }
            Todo t = new Todo(command);
            list.add(t);
            int index = list.indexOf(t);
            Ui.printAddedTask(index,list);
        } catch (WrongFormatException e) {
            Ui.checkError("INVALID_FORMAT");
        }
    }

    /**
     * Add a deadline task to the list
     *
     * @param input - add Task description
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void addDeadline(String input, ArrayList<Task> list) {
        try{
            if (input.equals("deadline")) {
                throw new WrongFormatException();
            }
            String command = input.substring(9);
            if (!command.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /by ");
            String description = parts[0];
            String date = parts[1];
            Deadline t = new Deadline(description,date);
            list.add(t);
            int index = list.indexOf(t);
            Ui.printAddedTask(index,list);
        } catch (WrongFormatException e) {
            Ui.checkError("INVALID_FORMAT");
        }
    }

    /**
     * Add an event task to the list
     *
     * @param input Task description
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void addEvent(String input,ArrayList<Task> list)  {
        try {
            if (input.equals("event")) {
                throw new WrongFormatException();
            }
            String command = input.substring(6);
            if (!command.contains(" /at ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /at ");
            String description = parts[0];
            String date = parts[1];
            Event t = new Event(description,date);
            list.add(t);
            int index = list.indexOf(t);
            Ui.printAddedTask(index,list);
        } catch (WrongFormatException e) {
            Ui.checkError("INVALID_FORMAT");
        }
    }

    /**
     * Check if Done command is valid
     * Checks if task exists and presence of a numerical value after done command
     *
     * @param input Command executed
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void doneTask(String input,ArrayList<Task> list) {
        try{
            if (input.equalsIgnoreCase("done")) {
                throw new WrongFormatException();
            }
            if (!input.substring(5).matches("[0-9]+")) {
                throw new WrongFormatException();
            }
            int index = Integer.parseInt(input.substring(5));
            if (index > list.size()) {
                throw new IndexOutOfBoundsException();
            }
            checkTask(index - 1,list);
        } catch (IndexOutOfBoundsException e) {
            Ui.checkError("INDEX_EXCEEDS_LIST");
        } catch (WrongFormatException e) {
            Ui.checkError("WRONG_DONE_FORMAT");
        }
    }

    /**
     * Marks task as done via a tick
     * Checks if task has already been marked done
     *
     * @param index Command executed
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    private static void checkTask(int index,ArrayList<Task> list){
        try{
            if (list.get(index).checkIsDone()) {
                throw new DoneCheckedException();
            }
            System.out.println("Good Job, I will mark this as done!");
            list.get(index).markAsDone();
            System.out.println("[" + list.get(index).getType() + "] ["
                    + list.get(index).getStatusIcon() + "] " + list.get(index).getName()
                    + list.get(index).getDate());
            Ui.printBorder();
        } catch (DoneCheckedException e) {
            Ui.checkError("DONE_CHECKED_ERROR");
        }
    }

    /**
     * Delete task from the list
     * Checks if task exists and presence of a numerical value after delete command
     *
     * @param input Command executed
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void deleteTask(String input,ArrayList<Task> list){
        try {
            if (input.equalsIgnoreCase("delete")) {
                throw new WrongFormatException();
            }
            if (input.substring(7).matches("0")) {
                throw new WrongFormatException();
            }
            if (!input.substring(7).matches("[0-9]+")) {
                throw new WrongFormatException();
            }
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index + 1 > list.size()) {
                throw new IndexOutOfBoundsException();
            }

            System.out.println("Noted! I will delete this at once!");
            System.out.println("[" + list.get(index).getType() + "] ["
                    + list.get(index).getStatusIcon() + "] " + list.get(index).getName()
                    + list.get(index).getDate());
            list.remove(index);
            Ui.printNoOfTask(list);
        } catch (WrongFormatException e) {
            Ui.checkError("WRONG_DELETE_FORMAT");
        } catch (IndexOutOfBoundsException e) {
            Ui.checkError("DELETE_EMPTY_LIST");
        }
    }

    /**
     * List all task items in the list
     *
     * @param list The ArrayList to store all existing tasks from Duke.txt
     */
    public static void printList(ArrayList<Task> list){
        try {
            if (!(list.size()>0)) {
                throw new ListEmptyException();
            }
            System.out.println(" LIST");
            for (int i = 0; i < list.size(); i++) {
                System.out.println(i + 1 +  ". [" + list.get(i).getType() +  "] " + "["
                        + list.get(i).getStatusIcon() + "] " + list.get(i).getName()
                        + list.get(i).getDate());
            }
            Ui.printBorder();
        } catch (ListEmptyException e) {
            Ui.checkError("EMPTY_LIST");
        }
    }

    /**
     * Searches the list for all tasks that contains the substring of the input given by user
     *
     * @param input user input
     * @param list The ArrayList to store all existing tasks from Duke.txt
     * @throws WrongFormatException if no substring found or is whitespace after find command
     */
    public static void findTask(String input, ArrayList<Task> list) throws WrongFormatException {
        try {
            String searchItem = input.substring(5);
            if (searchItem.length() == 0 ) {
                throw new WrongFormatException();
            }
            Ui.printFindTask(searchItem,list);
        } catch (WrongFormatException e) {
            Ui.checkError("INVALID_FORMAT");
        }

    }
}
