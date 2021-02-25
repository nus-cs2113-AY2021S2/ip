package preparetask;

import exceptions.DukeException;
import storage.Storage;
import tasks.ToDos;

public class PrepareTodo extends Storage {
    private String words [];

    public PrepareTodo (String userInput){
        words = userInput.split(" ");
        try {
            if (words.length==1) {
                throw new DukeException();
            }
            String task = userInput.substring(5, userInput.length());
            tasks[count] = new ToDos(task);
            taskArrayList.add(tasks[count]); //store in array list for deleting
            taskSentences.add("T"+"|"+tasks[count].getDone()+"|"+task); //store in array list for writing
            System.out.println("Got it. I've added this task");
            System.out.print("  ");
            tasks[count++].printStatus();
            System.out.println("Now you have " + count + " tasks in the list");
            Storage.writeFile(); // update file
        } catch (DukeException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }
}
