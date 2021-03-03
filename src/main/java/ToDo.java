

import java.util.ArrayList;


public class ToDo extends Tasks{
    protected ArrayList<Tasks> listTasks;
    protected String mark = "T";
    protected int indexValue;
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


    public ToDo (String userInput){
        super(userInput);
        mark = "T";
        super.setCommandDescription(userInput.substring(4));
//        Tasks obj = new Tasks(userInput.substring(4),mark);
    }

    public ToDo (String userInput, boolean dummy) {
        super(userInput);

    }

    public void toPrintToDo(){
        displayLine();
        System.out.println("Got it. I've added this task: ");
        System.out.println(this);
    }

    public String toString() {
        return "["+mark+"]"+super.toString();
    }
}
