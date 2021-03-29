/**
 * setting task as a Todo
 */
public class Todo extends Task{

    /**
     * Todo Constructor
     * @param description name of taks
     */
    public Todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String write(){
        return "T|" + this.getStatusIcon() + "|" + this.description;
    }
}