package duke.tasksmanager;

public class Events extends Tasks {

    protected String date;

    public Events(String description, String date) {
        super(description);
        this.typeOfTask = "E";
        this.date = date;
    }

    public String getTypeOfTask() {
        return this.typeOfTask;
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String convertToTaskOutputString() {
        return "[" + getTypeOfTask() + "]" + super.convertToTaskOutputString() + "(" + "at: " + this.date + ")";
    }

}
