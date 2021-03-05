package duke.preparetask;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Events;

/**
 * Parses argument in the context of the add event task command.
 * Take the argument and create event object
 */
public class PrepareEvent extends Storage {

    /**
     * @param userInput contains full command args string
     *
     * @throws StringIndexOutOfBoundsException if event is empty
     * @throws DukeException if format is wrong
     */
    public PrepareEvent(String userInput) {
        try {
            if (!(userInput.contains("/at"))) {
                throw new DukeException();
            }
            String task = userInput.substring(6, userInput.indexOf("/") - 1);
            String at = userInput.substring(userInput.indexOf("/") + 4);

            tasks[taskCount] = new Events(task, at);
            taskArrayList.add(tasks[taskCount]);
            taskSentences.add("E" + "|" + tasks[taskCount].getDone() + "|" + task + "|" + at); //add task in string format
            System.out.println("Got it. I've added this task:");
            System.out.print("  ");
            tasks[taskCount++].printStatus();
            System.out.println("Now you have " + taskCount + " tasks in the list:");
            Storage.writeFile();                                                            //update file
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a event task cannot be empty.");
        } catch(DukeException ex){
            System.out.println("Please re-enter. Format of deadline task is wrong. " +
                    "E.g., event DESCRIPTION /at DATE/TIME, LOCATION");
        }
    }
}
