package preparetask;

import exceptions.DukeException;
import storage.Storage;
import tasks.ToDos;

/**
 * Parses argument in the context of the add todo task command.
 * Take the argument and create todo object
 */
public class PrepareTodo extends Storage {
    private final String[] words;

    /**
     * @param userInput contains full command args string
     *
     * @throws DukeException if todo is empty
     */
    public PrepareTodo(String userInput) {
        words = userInput.split(" ");

        try {
            if (words.length == 1) {
                throw new DukeException();
            }
            String task = userInput.substring(5);

            tasks[taskCount] = new ToDos(task);
            taskArrayList.add(tasks[taskCount]);
            taskSentences.add("T" + "|" + tasks[taskCount].getDone() + "|" + task);  //add task in string format
            System.out.println("Got it. I've added this task");
            System.out.print("  ");
            tasks[taskCount++].printStatus();
            System.out.println("Now you have " + taskCount + " tasks in the list");
            Storage.writeFile();                                                 // update file
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
