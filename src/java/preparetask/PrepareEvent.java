package preparetask;

import storage.Storage;
import tasks.Events;

/**
 * Parses argument in the context of the add event task command
 * @throws StringIndexOutOfBoundsException if event is empty
 */
public class PrepareEvent extends Storage {

    /**
     * @param userInput contains full command args string
     */
    public PrepareEvent(String userInput) {
        try {
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
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
