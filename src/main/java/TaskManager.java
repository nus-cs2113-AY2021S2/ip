import errors.DescriptionSplitException;
import errors.ListFullException;
import errors.MissingKeywordException;
import tasks.DeadlineTask;
import tasks.EventTask;
import tasks.Task;
import tasks.ToDoTask;

import java.util.ArrayList;
import java.util.NavigableSet;
import java.util.TreeSet;

public class TaskManager {

    private static final Constants constants = new Constants();
    private static final Storage storage = new Storage();

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;


    /**
     * Returns task count.
     */
    public int getTaskCount() {
        return taskCount;
    }


    /**
     * Returns task at index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }


    /**
     * Clears task list.
     */
    public void reset() {
        taskCount = 0;
        tasks.clear();
    }


    /**
     * Creates new ToDoTask.
     *
     * @param input Input value by user.
     */
    public void addToDo(String input) {
        try {
            if (taskCount >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            }
            input = input.substring(constants.LENGTH_OF_WORD_TODO + 1);
            tasks.add(new ToDoTask(input));
            taskCount++;
            printAddedContent(input);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        }
    }


    /**
     * Creates new EventTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public void addEvent(String input) {
        try {
            if (!input.contains(" /at ")) {
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.substring(constants.LENGTH_OF_WORD_EVENT + 1).split(" /at ");
            if (taskCount >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < 2) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            tasks.add(new EventTask(inputSplit[0], inputSplit[1]));
            taskCount++;
            printAddedContent(inputSplit[0]);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(constants.MESSAGE_MISSING_AT_KEYWORD);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Creates new DeadlineTask.
     * Prints error message if invalid.
     *
     * @param input Input value by user.
     */
    public void addDeadline(String input) {
        try {
            if (!input.contains(" /by ")) {
                throw new MissingKeywordException();
            }
            String[] inputSplit = input.substring(constants.LENGTH_OF_WORD_DEADLINE + 1).split(" /by ");
            if (taskCount >= constants.MAX_SIZE) {
                //Array full
                throw new ListFullException();
            } else if (inputSplit.length < 2) {
                //Invalid input
                throw new DescriptionSplitException();
            }
            tasks.add(new DeadlineTask(inputSplit[0], inputSplit[1]));
            taskCount++;
            printAddedContent(inputSplit[0]);
            updateFile();
        } catch (ListFullException e) {
            System.out.println(constants.MESSAGE_LIST_FULL);
        } catch (MissingKeywordException e) {
            System.out.println(constants.MESSAGE_MISSING_BY_KEYWORD);
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Deletes multiple tasks from list.
     *
     * @param input Input value by user.
     */
    public void deleteTasks(String input) {
        try {
            String[] indices = input.split(" ");
            if (indices.length < 2) {
                throw new DescriptionSplitException();
            }
            TreeSet<Integer> validIndices = new TreeSet<>();
            for (int i = 1; i < indices.length; i++) {
                int index = processIndex(indices[i]);
                if (index != constants.INVALID_INDEX) {
                    validIndices.add(index);
                }
            }
            if (validIndices.size() < 1) {
                throw new IndexOutOfBoundsException();
            }
            NavigableSet<Integer> sortedIndices = validIndices.descendingSet();
            System.out.println(constants.MESSAGE_DELETED);
            for (int index : sortedIndices) {
                getTask(index).printStatus();
                System.out.println();
                tasks.remove(index);
                taskCount--;
            }
            System.out.println(constants.LINE);
            updateFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Prints list of task name.
     */
    public void printList() {
        System.out.println(constants.LINE);
        if (taskCount == 0) {
            System.out.println(constants.MESSAGE_EMPTY_LIST);
            return;
        }
        for (int i = 0; i < taskCount; i++) {
            System.out.print((i + 1) + ".");
            getTask(i).printStatus();
            System.out.println();
        }
        System.out.println(constants.LINE);
    }


    /**
     * Marks specified task as done.
     * If position is invalid, print error message.
     *
     * @param input Input value by user.
     */
    public void setDoneStatus(String input, Boolean isDone) {
        try {
            int position = Integer.parseInt(input.split(" ")[1]) - 1;
            if (position >= taskCount || position < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            getTask(position).setDone(isDone);
            if (isDone) {
                System.out.println(constants.MESSAGE_MARK_DONE);
            } else {
                System.out.println(constants.MESSAGE_MARK_UNDONE);
            }
            getTask(position).printStatus();
            System.out.println("\n" + constants.LINE);
            updateFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_UNRECOGNIZED_COMMAND);
        }
    }


    /**
     * Prints successfully added message.
     *
     * @param input Input value by user.
     */
    public static void printAddedContent(String input) {
        System.out.println(constants.LINE);
        System.out.println("8K: Added \"" + input + "\" to list.");
        System.out.println(constants.LINE);
    }


    /**
     * Processes index task from list. Returns -1 if invalid.
     *
     * @param stringIndex Index of task to remove.
     */
    public static int processIndex(String stringIndex) {
        try {
            int intIndex = Integer.parseInt(stringIndex) - 1;
            if (intIndex >= taskCount || intIndex < 0) {
                //Out of bounds
                throw new IndexOutOfBoundsException();
            }
            return intIndex;
        } catch (Exception e) {
            //Invalid index
            return constants.INVALID_INDEX;
        }
    }


    /**
     * Updates file.
     */
    private void updateFile() {
        try {
            storage.saveFile();
        } catch (Exception e) {
            System.out.println(constants.MESSAGE_SAVE_ERROR);
        }
    }

}
