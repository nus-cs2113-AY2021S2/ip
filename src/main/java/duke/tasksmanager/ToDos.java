package duke.tasksmanager;

public class ToDos extends Tasks {

    public ToDos(String description) {
        super(description);
        this.typeOfTask = "T";
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    @Override
    public String convertToTaskOutputString() {
        return "[" + getTypeOfTask() + "]" + super.convertToTaskOutputString();
    }

}
