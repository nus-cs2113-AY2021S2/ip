package duke;

import duke.task.Task;

import java.util.ArrayList;

public class DukeFileManager {
    private String filepath;

    public DukeFileManager(String filepath) {
        this.filepath = filepath;
    }

    public void openFile() {

    }

    public ArrayList<Task> readFromFile() {
        return new ArrayList<Task>();
    }
}
