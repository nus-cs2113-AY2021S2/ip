public class Todo extends Task{

    public Todo(String description){
        super(description);
        this.natureOfTask = "T";
    }

    public String toString(){
        return String.format("[T]" + super.toString());
    }
}
