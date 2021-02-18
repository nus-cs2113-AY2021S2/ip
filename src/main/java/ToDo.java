public class ToDo extends Task {


    public ToDo(String taskDescription) {
        super(taskDescription);
        this.taskType = "[T]";
    }



    @Override
    public String toString() {
        return super.toString();
    }
    @Override
    public String taskToText() {
        return "T|" + super.completed + "|" + super.taskDescription;
    }
}
