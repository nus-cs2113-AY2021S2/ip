public class Tasks{
    private String description;
    private  boolean isDone;
    public Tasks(String description){
        this.description = description;
        this.isDone =false;
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




}
