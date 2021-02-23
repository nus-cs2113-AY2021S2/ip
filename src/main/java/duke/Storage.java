package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {

    public Ui ui = new Ui();
    public Path filePath;
    private static final String FILE_SEPARATOR = "CHOPCHOP";
    public String dateTimeFormat = "yyyy-MM-dd HH:mm";
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateTimeFormat);

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath.toString());
        for (Task t: tasks.tasks) {
            if("Todo".equals(t.getTaskType())) {
                fw.write(t.getTaskType() + FILE_SEPARATOR + t.getDone() + FILE_SEPARATOR
                        + t.getDescription() + "\n");
            } else {
                fw.write(t.getTaskType() + FILE_SEPARATOR + t.getDone() + FILE_SEPARATOR
                        + t.getDescription() + FILE_SEPARATOR  + t.getDate().format(formatter) + "\n");
            }
        }
        fw.close();
    }

    public TaskList loadFile() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            File f = new File(filePath.toString());
            if (!f.createNewFile()) {
                Scanner s = new Scanner(f); // create a Scanner using the File as the source
                while (s.hasNext()) {
                    String[] lineParts = s.nextLine().split(FILE_SEPARATOR);
                    switch(lineParts[0]) {
                    case "Todo":
                        tasks.addTodo(new Todo(lineParts[2], Boolean.parseBoolean(lineParts[1])));
                        break;
                    case "Deadline":
                        tasks.addDeadline(new Deadline(lineParts[2], LocalDateTime.parse(lineParts[3], formatter),
                                Boolean.parseBoolean(lineParts[1])));
                        break;
                    case "Event":
                        tasks.addEvent(new Event(lineParts[2], LocalDateTime.parse(lineParts[3], formatter),
                                Boolean.parseBoolean(lineParts[1])));
                        break;
                    }
                }
                ui.showLoadingSuccess();
            } else {
                throw new DukeException();
            }
        } catch (Exception e) {
            ui.showError(e);
        }
        return tasks;
    }
}
