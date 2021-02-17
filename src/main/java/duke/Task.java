package duke;

public class Task {

    private final String taskName;
    private boolean isDone;

    public Task(String task){
        this.taskName = task;
        setDone(false);
    }

    public void setDone(boolean isDone){

        this.isDone=isDone;
    }

    public String getName(){

        return this.taskName;
    }

    public boolean getDone(){

        return isDone;
    }

    public void printStatus(){
        if(getDone()){
            System.out.print("["+"\u2713"+"] "+getName());
        }else{
            System.out.print("["+" "+"] "+getName());
        }
    }
}