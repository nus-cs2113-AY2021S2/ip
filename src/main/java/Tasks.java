public class Tasks{
    protected String description;
    protected  boolean isDone;
    protected String taskType;
    protected String extraDescription;

    public Tasks(String description){
        this.description = description;
        this.isDone =false;
        this.taskType = " ";
        this.extraDescription =" ";
    }
    public Tasks(String description, String taskType){
        this.description = description;
        this.isDone =false;
        this.taskType = taskType;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String  setDisplay(){
        if(this.isDone == true){
            return "\u2718";
        }
        else{
            return " ";
        }
    }
    public String getTaskType(){
        return taskType;
    }
    public String displayTaskType(){
        if(taskType.equals("todo")){
            return "T";
        }
        else if( taskType.equals("deadline")){
            return "D";
        }
        else if( taskType.equals("event")){
            return "E";
        }
        else{
            return " ";
        }
    }





}
