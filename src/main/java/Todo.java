import MyExceptions.NoContentInTodo;

public class Todo {
    protected String name;
    protected boolean isDone;

    Todo(String name) throws NoContentInTodo{
        if (name.equals("")) {
            throw new NoContentInTodo();
        }
        this.name = name;
        isDone = false;
    }

    Todo(){
        this.name = "";
        isDone = false;
    }

    public void markDone() {
        isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2718" : " ");
    }

    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + name;
    }
}
