

import java.util.ArrayList;


public class List extends Commands {
    protected int i =1;
    protected ArrayList<Tasks> listTasks;
    public List(ArrayList<Tasks> listTasks){
        this.listTasks = listTasks;
    }
    public String toPrintMarked(boolean isDone){
        if(isDone){
            return "\u2718";
        }
        else {
            return " ";
        }
    };
    public void toPrintList(){
        displayLine();
        for(Tasks value: listTasks){
            System.out.println(i+".["+value.taskType+"]"+" ["+toPrintMarked(value.isDone)+"]"+ value.commandDescription);
            i++;
        }
        i =1;
        displayLine();
    }
}
