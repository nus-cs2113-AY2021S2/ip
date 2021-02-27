package duke.task;

import duke.ui.Ui;

import java.util.ArrayList;
import java.util.StringJoiner;

public class TaskList {
    protected static ArrayList<Task> taskArray = new ArrayList<>();

    /**
     * Returns the size of taskArray.
     *
     * @return the size of the ArrayList.
     */
    public static int returnTaskCount() {
        return taskArray.size();
    }

    /**
     * Returns the type of Task that is indexed by the index parsed into the method.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     * @return the type of the indexed Task.
     */
    public static String returnTaskType(int index) {
        return taskArray.get(index).getTaskType();
    }

    /**
     * Returns the description of the Task that is indexed by the index parsed into the method.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     * @return the description of the indexed Task.
     */
    public static String returnTaskItem(int index) {
        return taskArray.get(index).getTaskItem();
    }

    /**
     * Returns the done status of the Task that is indexed by the index parsed into the method.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     * @return the done status of the indexed Task.
     */
    public static String returnStatusIcon(int index) {
        return taskArray.get(index).getStatusIcon();
    }

    /**
     * Returns the ArrayList in a file output format to be used for the save/load method.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     * @return the Task in a file output format.
     */
    private static String returnFileFormat(int index) {
        return taskArray.get(index).getFileFormat();
    }

    /**
     * Prints all the Task objects in the ArrayList
     */
    public static void printList() {
        Ui.printTaskHeader();
        for (Task t : taskArray) {
            int index = taskArray.indexOf(t);
            Ui.printTaskIndex(index);
            Ui.printTaskItem(index);
        }
        System.out.println();
    }

    /**
     * Marks the Task object indexed by the index parsed into the method as done.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     */
    public static void markDone(int index) {
        taskArray.get(index).isDone = true;
    }

    /**
     * Deletes the Task object indexed by the index parsed into the method.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     */
    public static void deleteTask(int index) {
        Ui.printDeletePrompt(index);
        taskArray.remove(index);
    }

    /**
     * Adds the Task object into the ArrayList.
     *
     * @param task is the new created object type that is specified by the user.
     * @return the index of the Task object in the ArrayList.
     */
    public static int addToTaskList(Task task) {
        taskArray.add(task);
        return taskArray.indexOf(task);
    }

    /**
     * Converts the entire ArrayList into a file output format.
     *
     * @return the ArrayList in a file output format.
     */
    public static String convertToFileInput() {
        StringJoiner string = new StringJoiner("\n");

        for (Task t : taskArray) {
            int index = taskArray.indexOf(t);
            string.add(getFileInput(index));
        }

        return string.toString();
    }

    /**
     * Concatenates and returns the Task object variables as one string per Task for
     * saving into the file.
     *
     * @param index is the validated index of the Task object in the ArrayList.
     * @return the concatenated string of Task variables.
     */
    private static String getFileInput(int index) {
        return returnTaskType(index) + " , " + returnStatusIcon(index) + " , " + returnFileFormat(index);
    }

    /**
     * Searches the ArrayList for Task objects that contains the keyWord that is
     * parsed into the method.
     *
     * @param keyWord is the String input that the user wants to find in the ArrayList
     */
    public static void searchWord(String keyWord) {
        boolean isFound = false;
        Ui.printSearchHeader();
        for (Task t : taskArray) {
            String currentDescription = t.getTaskItem().toUpperCase();
            if (currentDescription.contains(keyWord.toUpperCase())) {
                isFound = true;
                printSearchedTasks(t);
            }
        }
        if (!isFound) {
            Ui.printNullSearch();
        }
    }

    /**
     * Prints all Task objects that contains with the keyWord provided.
     *
     * @param t is the Task object that contains the keyWord.
     */
    private static void printSearchedTasks(Task t) {
        int index = taskArray.indexOf(t);
        System.out.print("[" + (index + 1) + "]");
        Ui.printTaskItem(index);
        System.out.println();
    }
}
