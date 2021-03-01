package duke;

import static duke.Ui.LOADING_ERROR_MESSAGE;
import static duke.Ui.SAVING_ERROR_MESSAGE;
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
 * Represents the file used to store the {@code TaskList}.
 */
public class Storage {
    private final String filePath;
    private TaskList tasks;
    private final Ui ui;

    public Storage(String filePath) {
        tasks = new TaskList();
        this.filePath = filePath;
        ui = new Ui();
    }

    /**
     * Loads the {@code TaskList} data from this storage file, then returns it.
     * Returns a new {@code TaskList} if the directory or file does not exist.
     */
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
            ui.printErrorMessage(LOADING_ERROR_MESSAGE);
            return new TaskList();
        }
    }

    /**
     * Saves the {@code TaskList} data created during a run to this storage file.
     */
    public void saveToDisk(TaskList tasks) {
        String textToOverwrite = tasks.convertToText();
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(textToOverwrite);
            fw.close();
        } catch (IOException e) {
            ui.printErrorMessage(SAVING_ERROR_MESSAGE);
        }
    }

    /**
     * Generates the {@code TaskList} used throughout a run by converting, line-by-line,
     * the differently-formatted data loaded from this storage file.
     * @param f file to load from
     * @return a {@code TaskList} loaded with previous data
     * @throws IOException if file or directory does not exist.
     */
    public TaskList convertLinesToList(File f) throws IOException {
        Scanner scan = new Scanner(f);
        while (scan.hasNext()) {
            convertLineToListItem(scan.nextLine());
        }
        return tasks;
    }

    /**
     * Parses the given {@code line} into a valid {@code Command}, then executes it.
     * @param line line of text read from the storage .txt file
     */
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
