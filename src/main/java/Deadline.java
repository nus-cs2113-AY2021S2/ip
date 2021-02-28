

import java.util.ArrayList;

public class Deadline extends Tasks{
    protected ArrayList<Tasks> listTasks;
    protected String mark;
    protected String modifiedInput;

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public Deadline(ArrayList<Tasks> listTasks,String userInput){
        this.listTasks = listTasks;
        int startIndex = userInput.indexOf("n");
        int endIndex = userInput.indexOf("/by");
        String preciseInput = userInput.substring(startIndex+3,endIndex-1).trim();
        String timing = userInput.substring(endIndex+4);
        this.modifiedInput = preciseInput + "(by: "+timing+")";
        mark = "D";
        Tasks obj = new Tasks(modifiedInput,mark);
        listTasks.add(obj);
    }
    public void toPrintDeadline(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("["+mark+"]"+"[ ] "+this.modifiedInput);
        System.out.println("Now you have " +listTasks.size()+" tasks in the list.");
        displayLine();
    }
}
