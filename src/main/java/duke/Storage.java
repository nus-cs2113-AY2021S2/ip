package duke;

import static duke.common.Constants.DEFAULT_STATUS;
import static duke.common.Constants.DONE_STATUS;
import static duke.common.Constants.TODO_TASK_TYPE;
import static duke.common.Constants.DEADLINE_TASK_TYPE;
import static duke.common.Constants.EVENT_TASK_TYPE;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import duke.commands.DeadlineCommand;
import duke.commands.EventCommand;
import duke.commands.TodoCommand;

/**
 * Methods to load from and save to disk
 */
public class Storage {
    private String filePath;
    private TaskList tasks;
    private Ui ui;

    public Storage(String filePath) {
        tasks = new TaskList();
        this.filePath = filePath;
        ui = new Ui();
    }

    public TaskList loadFromDisk() {
        File f = new File(filePath);
        try {
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdir();
            }
            if (!f.exists()) {
                f.createNewFile();
            }
            return convertLinesToList(f);
        } catch (IOException e) {
            ui.printErrorMessage(ui.LOADING_ERROR_MESSAGE);
            return new TaskList();
        }
    }

    public void saveToDisk(TaskList tasks) {
        String textToOverwrite = tasks.convertToText();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToOverwrite);
            fw.close();
        } catch (IOException e) {
            ui.printErrorMessage(ui.SAVING_ERROR_MESSAGE);
        }
    }

    public TaskList convertLinesToList(File f) throws IOException {
        Scanner scan = new Scanner(f);
        while (scan.hasNext()) {
            convertLineToListItem(scan.nextLine());
        }
        return tasks;
    }

    public void convertLineToListItem(String line) {
        line = line.trim();
        String[] tokens = line.split(" \\| ");
        switch (tokens[0]) {
        case TODO_TASK_TYPE:
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                TodoCommand addTodo = new TodoCommand(tasks, tokens[2], status);
                addTodo.execute();
            } catch (NumberFormatException e) {
                return;
            }
            break;
        case DEADLINE_TASK_TYPE:
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                LocalDate deadlineDate = LocalDate.parse(tokens[3]);
                LocalTime deadlineTime = LocalTime.parse(tokens[4]);
                DeadlineCommand addDeadline = new DeadlineCommand(tasks, tokens[2], status, deadlineDate, deadlineTime);
                addDeadline.execute();
            } catch (NumberFormatException | DateTimeParseException e) {
                return;
            }
            break;
        case EVENT_TASK_TYPE:
            try {
                String status = Integer.parseInt(tokens[1]) == 1 ? DONE_STATUS : DEFAULT_STATUS;
                EventCommand addEvent = new EventCommand(tasks, tokens[2], status, tokens[3]);
                addEvent.execute();
            } catch (NumberFormatException e) {
                return;
            }
            break;
        default:
            break;
        }
    }
}
