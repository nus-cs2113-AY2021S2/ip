package duke.model;

public interface Task {
    
    boolean isTaskDone();

    int getIndex();

    String getMessage();

    String getDescription();
}