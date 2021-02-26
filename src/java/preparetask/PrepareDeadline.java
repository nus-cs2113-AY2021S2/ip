package preparetask;

import storage.Storage;
import tasks.DeadLines;

/**
 * Parses argument in the context of the add deadline task command
 *
 * parameter "userInput" contains full command args string
 * parameter "task" contains the task
 * parameter "by" contains the date and/or time of task
 *
 * @throws StringIndexOutOfBoundsException if deadline is empty
 */

public class PrepareDeadline extends Storage {

    public PrepareDeadline(String userInput) {
        try {
            String task = userInput.substring(9, userInput.indexOf("/") - 1);
            String by = userInput.substring(userInput.indexOf("/") + 4);

            tasks[taskCount] = new DeadLines(task, by);
            taskArrayList.add(tasks[taskCount]);
            taskSentences.add("D" + "|" + tasks[taskCount].getDone() + "|" + task + "|" + by);  //add task in string format to arraylist
            System.out.println("Got it. I've added this task:");
            System.out.print("  ");
            tasks[taskCount++].printStatus();
            System.out.println("Now you have " + taskCount + " tasks in the list");
            Storage.writeFile();                                                                //update file
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

}
