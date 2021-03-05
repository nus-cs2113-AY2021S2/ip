import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the loading and storing of tasks data, from and to the hard disk.
 */
public class Storage {
    private static final String TODO_SYMBOL = "T";
    private static final String DEADLINE_SYMBOL = "D";
    private static final String EVENT_SYMBOL = "E";
    private static final int TODO_RECORD_LENGTH = 3;
    private static final int DEADLINE_RECORD_LENGTH = 4;
    private static final int EVENT_RECORD_LENGTH = 4;
    private static final int TASK_RECORD_SYMBOL_INDEX = 0;
    private static final int TASK_RECORD_IS_DONE_STATUS_INDEX = 1;
    private static final int TASK_RECORD_DESCRIPTION_INDEX = 2;
    private static final int TASK_RECORD_DESCRIPTION_2_INDEX = 3;
    private static final String IS_DONE_STATUS = "1";
    private static final String IS_NOT_DONE_STATUS = "0";

    private Ui ui;
    private File saveFile;
    private TaskList tasks;

    public Storage(Ui ui, String filePath) {
        this.ui = ui;
        this.saveFile = new File(filePath);
    }

    /**
     * Sets tasks to be used by the Storage class.
     *
     * @param tasks contains the list of tasks loaded from the save file.
     */
    public void setTaskList(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Load the list of tasks saved within the save file.
     *
     * @return an ArrayList of tasks loaded from the save file.
     */
    public ArrayList<Task> loadStoredTasksData() {
        ArrayList<Task> storedTasks;
        try {
            storedTasks = readFromSaveFile();
        } catch (FileNotFoundException e) {
            createNewFile();
            storedTasks = new ArrayList<Task>();
        }
        return storedTasks;
    }

    private ArrayList<Task> readFromSaveFile() throws FileNotFoundException {
        ArrayList<Task> storedTasks = new ArrayList<Task>();
        Scanner saveFileScanner = new Scanner(saveFile);
        int lineNum = 0;
        while (saveFileScanner.hasNextLine()) {
            lineNum++;
            String[] currentTaskRecordInfoWords = saveFileScanner.nextLine().split("\\|");
            stripWhiteSpacesForEachWord(currentTaskRecordInfoWords);
            processCurrentTaskRecord(currentTaskRecordInfoWords, lineNum, storedTasks);
        }
        saveFileScanner.close();
        return storedTasks;
    }

    private void stripWhiteSpacesForEachWord(String[] currentTaskRecordInfoWords) {
        for (int i = 0; i < currentTaskRecordInfoWords.length; i++) {
            currentTaskRecordInfoWords[i] = currentTaskRecordInfoWords[i].strip();
        }
    }

    private void processCurrentTaskRecord(String[] currentTaskRecordInfoWords, int lineNum,
                                          ArrayList<Task> storedTasks) {
        Task currentTask;
        boolean isValidTodoRecord = currentTaskRecordInfoWords[TASK_RECORD_SYMBOL_INDEX].equals(TODO_SYMBOL) &&
                (currentTaskRecordInfoWords.length == TODO_RECORD_LENGTH);
        boolean isValidDeadlineRecord = currentTaskRecordInfoWords[TASK_RECORD_SYMBOL_INDEX].equals(DEADLINE_SYMBOL) &&
                (currentTaskRecordInfoWords.length == DEADLINE_RECORD_LENGTH);
        boolean isValidEventRecord = currentTaskRecordInfoWords[TASK_RECORD_SYMBOL_INDEX].equals(EVENT_SYMBOL) &&
                (currentTaskRecordInfoWords.length == EVENT_RECORD_LENGTH);

        if (isValidTodoRecord) {
            currentTask = new Todo(currentTaskRecordInfoWords[TASK_RECORD_DESCRIPTION_INDEX]);
        } else if (isValidDeadlineRecord) {
            currentTask = new Deadline(currentTaskRecordInfoWords[TASK_RECORD_DESCRIPTION_INDEX],
                    currentTaskRecordInfoWords[TASK_RECORD_DESCRIPTION_2_INDEX]);
        } else if (isValidEventRecord) {
            currentTask = new Event(currentTaskRecordInfoWords[TASK_RECORD_DESCRIPTION_INDEX],
                    currentTaskRecordInfoWords[TASK_RECORD_DESCRIPTION_2_INDEX]);
        } else {
            ui.printBadTaskRecordFormatErrorMsg(lineNum);
            return;
        }

        if (currentTaskRecordInfoWords[TASK_RECORD_IS_DONE_STATUS_INDEX].equals(IS_DONE_STATUS)) {
            currentTask.markAsDone();
        } else if (!currentTaskRecordInfoWords[TASK_RECORD_IS_DONE_STATUS_INDEX].equals(IS_NOT_DONE_STATUS)) {
            ui.printBadTaskRecordFormatErrorMsg(lineNum);
            return;
        }
        storedTasks.add(currentTask);
    }

    private void createNewFile() {
        try {
            if (saveFile.createNewFile()) {
                ui.printCreatedSaveFileMsg(saveFile.getAbsolutePath());
                return;
            }
            throw new IOException();
        } catch (IOException e) {
            ui.printCreateSaveFileErrorMsg(saveFile.getAbsolutePath());
        }
    }

    /**
     * Save the entire task list into the save file.
     */
    public void saveStoredTasksData() {
        try {
            writeToSaveFile();
        } catch (IOException e) {
            ui.printWritingSaveFileErrorMsg(saveFile.getAbsolutePath());
        }
    }

    private void writeToSaveFile() throws IOException {
        FileWriter saveFileWriter = new FileWriter(saveFile);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            Task currentTask = tasks.getTaskAt(i);
            saveFileWriter.write(currentTask.getSaveFormatString() + "\n");
            saveFileWriter.flush();
        }
        saveFileWriter.close();
    }
}
