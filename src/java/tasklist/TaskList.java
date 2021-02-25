package tasklist;

import storage.Storage;

public class TaskList extends Storage {

    public static void list(){
        for (int i = 0; i < taskArrayList.size(); i++) {
            System.out.print(i + 1 + ".");
            taskArrayList.get(i).printStatus();
        }
    }

    public static void done(String userInput){
        String text;
        int index = Integer.parseInt(userInput.substring(5, userInput.length()));
        tasks[index - 1].setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        tasks[index - 1].printStatus();
        text = taskSentences.get(index-1);
        text = text.replace("false","true");
        taskSentences.set(index-1,text); //rewrite and update the status
        Storage.writeFile(); //update file
    }

    public static void delete(String userInput){
        int index = Integer.parseInt(userInput.substring(7, userInput.length()));
        System.out.println("Noted. I've removed this task:");
        taskArrayList.get(index - 1).printStatus(); //print the task to be remove before delete
        taskArrayList.remove(index-1); //remove obj
        taskSentences.remove(index-1); //remove sentence from file
        count = count - 1; //update count value after deleting
        System.out.println("Now you have "+ taskArrayList.size() +" tasks in the list.");
        Storage.writeFile(); //update file
    }


}
