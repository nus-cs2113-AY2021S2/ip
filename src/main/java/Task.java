public class Task {
    private String taskName;
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
        if(isDone){
            System.out.print("["+"\u2713"+"] "+taskName);
        }else{
            System.out.print("["+" "+"] "+taskName);
        }
    }

}