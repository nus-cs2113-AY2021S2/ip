package duke;

import duke.Exceptions.DukeException;
import duke.Exceptions.TaskTypeNotFoundException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String FILEPATH;
    private ArrayList<Task> taskList;

    public Storage(String filePath) {
        this.FILEPATH = filePath;
        taskList = new ArrayList<>();
    }

    public ArrayList<Task> load() throws DukeException {

        File f = new File(FILEPATH);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            Ui.showFileNotFound();
            throw new DukeException();
        }
        Ui.showLoadingData();
        while (s.hasNext()) {
            String line = s.nextLine();
            int dividerPosition;
            char taskType = line.charAt(1);
            switch (taskType) {
            case 'T':
                taskList.add(new Todo(line.substring(7)));
                break;
            case 'D':
                dividerPosition = line.indexOf("(by:");
                taskList.add(new Deadline(line.substring(7, dividerPosition - 1),
                        line.substring(dividerPosition + 5, line.length() - 1)));
                break;
            case 'E':
                dividerPosition = line.indexOf("(at:");
                taskList.add(new Event(line.substring(7, dividerPosition - 1),
                        line.substring(dividerPosition + 5, line.length() - 1)));
                break;
            default:
                throw new TaskTypeNotFoundException();
            }
            if (line.charAt(4) == '\u2713') {
                taskList.get(taskList.size() - 1).setDone();
            }
        }
        return taskList;
    }

    public void save(TaskList saveList) throws IOException {
        taskList = saveList.getTaskList();
        File f = new File(FILEPATH);
        if (f.createNewFile()) {
            Ui.showCreateNewFile(FILEPATH);
        }
        FileWriter fw = new FileWriter(FILEPATH);

        for (Task task : taskList) {
            if (task != null) {
                fw.write(task.toString() + "\n");
            }
        }
        fw.close();
    }

}
