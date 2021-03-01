

import java.util.ArrayList;

public class Done extends sample.Commands {
    protected ArrayList<Tasks> listTasks;
    protected int indexNumber =0;
    protected boolean isDone = false;
    protected String doneInput;

    public Done(ArrayList<Tasks> listTasks,int indexNumber, String doneInput){
        this.listTasks = listTasks;
        this.indexNumber = indexNumber;
        isDone = true;
        this.doneInput = doneInput;
        listTasks.get(indexNumber-1).isDone = true;
    }
    public Done(ArrayList<Tasks> listTasks){
        this.listTasks = listTasks;
    }
    public void toPrintDone(){
        displayLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(listTasks.get(indexNumber-1));
//        System.out.println(" [X] "+ doneInput);
        displayLine();
    }
}
