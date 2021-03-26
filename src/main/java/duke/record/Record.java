package duke.record;

import duke.Duke;
import duke.exception.InvalidArgumentException;
import duke.input.InputParser;
import duke.input.InputType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.task.dateTime.DateTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Represents a {@code Record} object for managing and storing user's tasks. A {@code Record} object retrieves user's
 * tasks from a text file (Record.txt) in the local file system. It also manages (adds/deletes) tasks during runtime
 * via CLI. Any updates of user's tasks will be synchronized to the local text file.
 */
public class Record {
    private final ArrayList<Task> records = new ArrayList<>();

    /**
     * Constructor of Record <br>
     * Initializes the Record object by retrieving user's tasks from the Record.txt (if any). If Record.txt is found,
     * the program will print "Record found" and the tasks stored inside the text file will be retrieved and
     * store to the ArrayList {@code records}. Otherwise, the program will print "No record found" and the ArrayList
     * {@code records} remains empty.
     */
    public Record() {
        printStartInitializationPrompt();
        readRecords();
        printEndInitializationPrompt();
    }

    private void printStartInitializationPrompt() {
        System.out.println("Initializing " + Duke.APP_NAME + "...");
    }

    private void printEndInitializationPrompt() {
        System.out.println("Completed!");
        System.out.println("-----------------------------");
    }

    /**
     * Adds a new task to the {@code Record} object. If the task is added successfully, the program will print
     * message to prompt users that the given task has been added. If InvalidArgumentException is thrown, it indicates
     * that the given task failed to be added and the related reason of failure will be printed in the CLI.
     *
     * @param inputFragments ArrayList of information of a given task (i.e. taskName, isDone indicator (1/0) and date
     *                       [based on taskType])
     * @param taskType       Type of the given task (i.e. Deadline [D], Event [E] or Todo [T])
     */
    public void addRecord(String[] inputFragments, String taskType) {
        boolean isAdded = false;
        try {
            isAdded = addTaskToArrayList(inputFragments, taskType);
        } catch (InvalidArgumentException e) {
            printErrorMsg(e.getMessage());
        }

        if (isAdded) {
            int numberOfRecords = records.size();
            printSuccessAddedPrompt(numberOfRecords);
            saveRecords();
        }
    }

    private void printSuccessAddedPrompt(int numberOfRecords) {
        System.out.println("Got it. I've added this task:");
        System.out.println("\t" + records.get(numberOfRecords - 1));
        System.out.printf("Now you have %d tasks in the list.\n", numberOfRecords);
    }

    private boolean addTaskToArrayList(String[] inputFragments, String taskType) throws InvalidArgumentException {
        boolean isAdded;

        if (taskType.equals(Todo.TASK_TYPE)) {
            isAdded = addTodo(inputFragments);
        } else if (taskType.equals(Deadline.TASK_TYPE) || taskType.equals(Event.TASK_TYPE)) {
            isAdded = addTask(inputFragments, taskType);
        } else {
            throw new InvalidArgumentException("A non-taskType is passed to addRecord. Program terminated.");
        }

        return isAdded;
    }

    private boolean addTodo(String[] detailFragments) throws InvalidArgumentException {
        String taskName;
        if (detailFragments.length > 0) {
            taskName = String.join(" ", detailFragments);
            records.add(new Todo(taskName));
        } else {
            throw new InvalidArgumentException("The description of a To-do task cannot be empty.");
        }

        return true;
    }

    private boolean addTask(String[] inputFragments, String taskType) throws InvalidArgumentException {
        String[] taskNameAndDate = getTaskNameAndDate(inputFragments, taskType);
        String taskName = taskNameAndDate[0];
        String date = taskNameAndDate[1];

        if (taskType.equals(Deadline.TASK_TYPE)) {
            records.add(new Deadline(taskName, date));
        } else {
            records.add(new Event(taskName, date));
        }

        return true;
    }

    /**
     * Deletes a record from the {@code Record} object based on the given {@code index}. If the record is deleted
     * successfully, the program will print message to prompt users that the given task has been deleted.<br>
     * Otherwise, the program will print the reason of failure and given task is failed to be deleted.
     *
     * @param index index of record, which could be found with {@code list} command, that the user wants to delete
     */
    public void deleteRecord(int index) {
        if (isIndexOutOfBound(index)) {
            printErrorMsg("Index provided is out of bounds.");
            return;
        }

        Task deletedTask = records.remove(index);
        printSuccessDeletedPrompt(deletedTask);
        saveRecords();
    }

    private void printSuccessDeletedPrompt(Task deletedTask) {
        System.out.println("Got it. I've deleted this task:");
        System.out.println("\t" + deletedTask);
        System.out.printf("Now you have %d tasks in the list.\n", records.size());
    }

    /**
     * Mark a record as done based on the given {@code index}. If the record is marked as done successfully, the program
     * will print message to prompt users that the given task has been marked as done. Otherwise, the program will print
     * the reason of failure that makes the given task fail to be marked.
     *
     * @param index index of record, which could be found with {@code list} command, that the user wants to mark as
     *              done
     */
    public void markAsDone(int index) {
        if (isIndexOutOfBound(index)) {
            printErrorMsg("Index provided is out of bounds.");
            return;
        }
        records.get(index).setAsDone();
        printSuccessMarkedDonePrompt(index);
        saveRecords();
    }

    private void printSuccessMarkedDonePrompt(int index) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + records.get(index));
    }

    /**
     * Searches records based on the given {@code date}. If the date of stored tasks match the given {@code date},
     * the program will print those tasks in the CLI. If no record contains the given {@code date}, "Null"
     * will be printed.
     *
     * @param date A date in format of yyyy-mm-dd
     */
    public void searchDate(String date) throws InvalidArgumentException {
        DateTime dateTime;
        dateTime = new DateTime(date);
        System.out.printf("Here is your task with deadline/Due day by %s:\n", date);
        searchFromTheArrayList(dateTime);
    }

    private void searchFromTheArrayList(DateTime dateTime) {
        int counter = 1;
        for (Task task : records) {
            if (task.getDate() == null) {
                continue;
            }
            if (task.getDate().equals(dateTime.getDate())) {
                System.out.println(counter++ + ". " + task);
            }
        }
        if (counter == 1) {
            printNullMsg();
        }
    }

    /**
     * Finds records based on the given {@code keyword}. If the name of stored tasks contains the given
     * {@code keyword}, the program will print those tasks in the CLI. If no record contains the given {@code keyword},
     * "Null" will be printed.
     *
     * @param keyword A target String for searching
     */
    public void findRecords(String keyword) {
        System.out.printf("Here is your task List with keyword %s:\n", keyword);
        findFromTheArrayList(keyword);
    }

    private void findFromTheArrayList(String keyword) {
        boolean hasRecord = false;
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getTaskName().contains(keyword)) {
                System.out.println((i + 1) + ". " + records.get(i));
                hasRecord = true;
            }
        }
        if (!hasRecord) {
            printNullMsg();
        }
    }

    /**
     * Prints all stored records inside the {@code Record} object to the CLI. If no task is stored, "Null" will be
     * printed in the CLI.
     */
    public void showList() {
        System.out.println("Here is your task List:");
        listAllTasksFromTheArrayList();
    }

    private void listAllTasksFromTheArrayList() {
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
        if (records.size() == 0) {
            printNullMsg();
        }
    }

    private void readRecords() {
        try {
            File myObj = new File("records.txt");
            Scanner recordReader = new Scanner(myObj);
            retrieveRecordFromLocalFile(recordReader);
            recordReader.close();
        } catch (InvalidArgumentException | IllegalArgumentException e) {
            throw new Error("The local file is damaged. Please recover it or remove it manually.");
        } catch (FileNotFoundException e) {
            printNotFoundMsg();
            return;
        }
        printFoundMsg();
    }

    private void retrieveRecordFromLocalFile(Scanner recordReader) throws InvalidArgumentException {
        while (recordReader.hasNextLine()) {
            InputParser data = new InputParser(recordReader.nextLine(), InputType.recordInput);
            addTaskToArrayList(data.getArguments(), data.getTaskType());
            if (data.isDone()) {
                int indexOfNewlyAddedTask = records.size() - 1;
                records.get(indexOfNewlyAddedTask).setAsDone();
            }
        }
    }

    private String[] getTaskNameAndDate(String[] inputFragments, String taskType) throws InvalidArgumentException {
        int keywordIndex = getKeywordIndex(inputFragments, taskType);
        if (keywordIndex < 0 || keywordIndex == inputFragments.length - 1) {
            throw new InvalidArgumentException("Date is not provided or keyword is not matching");
        }
        if (keywordIndex == 0) {
            throw new InvalidArgumentException("Description cannot be empty!");
        }

        String taskName = getTaskName(inputFragments, keywordIndex);
        String dateTime = getDateTime(inputFragments, keywordIndex);
        return new String[]{taskName, dateTime};
    }

    private int getKeywordIndex(String[] inputFragments, String taskType) {
        return Arrays.asList(inputFragments).indexOf(taskType.equals(Deadline.TASK_TYPE) ? "/by" : "/at");
    }

    private String getTaskName(String[] inputFragments, int keywordIndex) {
        StringBuilder taskName = new StringBuilder();
        taskName.append(inputFragments[0]);
        for (int i = 1; i < keywordIndex; i++) {
            taskName.append(" ").append(inputFragments[i]);
        }
        return taskName.toString();
    }

    private String getDateTime(String[] inputFragments, int keywordIndex) {
        StringBuilder dueDay = new StringBuilder();
        dueDay.append(inputFragments[keywordIndex + 1]);
        for (int i = keywordIndex + 2; i < inputFragments.length; i++) {
            dueDay.append(" ").append(inputFragments[i]);
        }
        return dueDay.toString();
    }

    private void saveRecords() {
        try {
            FileWriter save = new FileWriter("Records.txt");
            writeRecordToLocalFile(save);
            save.close();
        } catch (IOException e) {
            printErrorMsg("File failed to be saved");
        }
    }

    private void writeRecordToLocalFile(FileWriter save) throws IOException {
        for (Task task : records) {
            save.write(task.toSave() + "\n");
        }
    }

    private boolean isIndexOutOfBound(int index) {
        return index < 0 || index >= records.size();
    }

    private void printNotFoundMsg() {
        System.out.println("No record found");
    }

    private void printNullMsg() {
        System.out.println("Null");
    }

    private void printFoundMsg() {
        System.out.println("Record found");
    }

    private void printErrorMsg(String message) {
        System.out.println("Error: " + message);
    }
}
