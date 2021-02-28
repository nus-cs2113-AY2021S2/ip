

import java.util.ArrayList;

public class Delete extends Commands{
    protected ArrayList<Tasks> listTasks;
    protected int indexNumber;
    protected String description;

    public Delete (ArrayList<Tasks> listTasks,int indexNumber,String description){
        this.listTasks = listTasks;
        this.indexNumber = indexNumber;
        this.description = description;
    }
    public String toPrintMarked(boolean isDone){
        if(isDone){
            return "\u2718";
        }
        else {
            return " ";
        }
    };
    public void toPrintDelete(){
        displayLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("["+listTasks.get(indexNumber-1).taskType+"]"+" ["+toPrintMarked(listTasks.get(indexNumber-1).isDone)+"]"+ listTasks.get(indexNumber-1).commandDescription);
        int temp = listTasks.size() -1;
        System.out.println("Now you have " +temp+" tasks in the list.");
        displayLine();
    }
    public void deleteTask(){
        listTasks.remove(indexNumber-1);
    }
}
