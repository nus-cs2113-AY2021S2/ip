

import java.util.ArrayList;

public class Event extends Tasks{
    protected ArrayList<Tasks> listTasks;
    protected String mark;
    protected String modifiedInput;

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public Event(ArrayList<Tasks> listTasks,String userInput){
        this.listTasks = listTasks;
        int startIndex = userInput.indexOf("t");
        int endIndex = userInput.indexOf("/at");
        String preciseInput = userInput.substring(startIndex+2,endIndex-1).trim();
        String timing = userInput.substring(endIndex+4);
        this.modifiedInput = preciseInput + "(at: "+timing+")";
        mark = "E";
        Tasks obj = new Tasks(modifiedInput,mark);
        listTasks.add(obj);
    }
    public void toPrintEvent(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("["+mark+"]"+"[ ] "+this.modifiedInput);
        System.out.println("Now you have " +listTasks.size()+" tasks in the list.");
        displayLine();
    }
}
