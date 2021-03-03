

public class Tasks {
    protected String commandDescription;
    protected boolean isDone =false;
    protected String taskType =" ";

    public Tasks(){};
    public Tasks(String commandDescription){
        this.commandDescription = commandDescription;
    }
    public Tasks(String commandDescription,boolean isDone){
        this.commandDescription = commandDescription;
        this.isDone = isDone;
    }
    public Tasks(String commandDescription, boolean isDone, String taskType){
        this.commandDescription = commandDescription;
        this.isDone= isDone;
        this.taskType = taskType;
    }
    public Tasks(String commandDescription, String taskType){
        this.commandDescription = commandDescription;
        this.taskType = taskType;
    }

    public void setCommandDescription(String cmd){
        this.commandDescription = cmd;
    }

    public void setDone() {
        isDone = true;
    }

    public String toString(){
        if (!this.isDone){
            return "[✗]" + this.commandDescription;
        } else {
            return "[✓]" + this.commandDescription;
        }
    }

}
