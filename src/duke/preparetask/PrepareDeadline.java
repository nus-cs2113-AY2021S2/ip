package duke.preparetask;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.DeadLines;

/**
 * Parses argument in the context of the add event task command.
 * Take the argument and create deadline object
 */
public class PrepareDeadline extends Storage {

    /**
     * @param userInput contains full command args string
     *
     * @throws StringIndexOutOfBoundsException if deadline is empty
     * @throws DukeException if format is wrong
     */
    public PrepareDeadline(String userInput) {
        try {
            if (!(userInput.contains("/by"))) {
                throw new DukeException();
            }
            String task = userInput.substring(9, userInput.indexOf("/") - 1);
            String by = userInput.substring(userInput.indexOf("/") + 4);

            tasks[taskCount] = new DeadLines(task, by);
            taskArrayList.add(tasks[taskCount]);
            taskSentences.add("D" + "|" + tasks[taskCount].getDone() + "|" + task + "|" + by);
            System.out.println("Got it. I've added this task:");
            System.out.print("  ");
            tasks[taskCount++].printStatus();
            System.out.println("Now you have " + taskCount + " tasks in the list");
            Storage.writeFile();
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a deadline task cannot be empty.");
        } catch(DukeException ex){
            System.out.println("Please re-enter. Format of deadline task is wrong. " +
                    "E.g., deadline DESCRIPTION /by DATETIME");
        }
    }

}
