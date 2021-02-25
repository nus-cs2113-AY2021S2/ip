package preparetask;

import storage.Storage;
import tasks.Events;

public class PrepareEvent extends Storage {

    public PrepareEvent(String userInput){
        String task = userInput.substring(6, userInput.indexOf("/")-1);
        String by = userInput.substring(userInput.indexOf("/") + 4, userInput.length());
        tasks[count] = new Events(task, by);
        taskArrayList.add(tasks[count]); //store in task array list
        taskSentences.add("E"+"|"+tasks[count].getDone()+"|"+task+"|"+by); //store in array list for writing
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        tasks[count++].printStatus();
        System.out.println("Now you have " + count + " tasks in the list:");
        Storage.writeFile(); //update file
    }
}
