package preparetask;

import storage.Storage;
import tasks.DeadLines;

public class PrepareDeadline extends Storage {

    public PrepareDeadline(String userInput){
        String task = userInput.substring(9, userInput.indexOf("/")-1);
        String by = userInput.substring(userInput.indexOf("/") + 4, userInput.length());
        tasks[count] = new DeadLines(task, by);
        taskArrayList.add(tasks[count]); //store in array for deleting
        taskSentences.add("D"+"|"+tasks[count].getDone()+"|"+task+"|"+by); //store in array list for writing
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks[count++].printStatus();
        System.out.println("Now you have " + count + " tasks in the list");
        Storage.writeFile();
    }

}
