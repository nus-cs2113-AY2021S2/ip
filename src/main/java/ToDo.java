

import java.util.ArrayList;


public class ToDo extends Tasks{
    protected ArrayList<Tasks> listTasks;
    protected String mark;
    protected int indexValue;
    protected String userInput;

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }
    public String toPrintMarked(boolean isDone){
        if(isDone){
            return "\u2718";
        }
        else {
            return " ";
        }
    };

    public ToDo (ArrayList<Tasks> listTasks,String userInput){
        this.listTasks = listTasks;
        this.userInput = userInput;
        mark = "T";
        Tasks obj = new Tasks(userInput.substring(4),mark);
        listTasks.add(obj);
    }

    public void toPrintToDo(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println("["+mark+"]"+"[ ]"+userInput.substring(4));
        System.out.println("Now you have " +listTasks.size()+" tasks in the list.");
        displayLine();
    }
}
