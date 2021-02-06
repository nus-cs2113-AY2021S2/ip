public class Tasks{
    private String description;
    private  boolean isDone;
    public Tasks (String description) {
        this.description = description;
        this.isDone =false;
    }
    public void setDescription(String description) {

        this.description = description;
    }

    public void setDone() {

        isDone = true;
    }

    public String getDescription() {

        return description;
    }

    public String  setDisplay(){
        if (isDone) {
            return "\u2718";
        } else {
            return " ";
        }
    }

}
