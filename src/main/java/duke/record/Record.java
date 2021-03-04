package duke.record;

import duke.Duke;
import duke.input.InputData;
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
 * Represents a record for storing user's tasks. A {@code Record} object retrieves/saves user's tasks from/to a text
 * file in the local file system as well as stores the tasks during runtime.
 */
public class Record {
    private final ArrayList<Task> records = new ArrayList<>();

    /**
     * Constructor of Record <br>
     * Initializes the Record object by retrieving user's tasks from the Record.txt (if any). If Record.txt is found,
     * the program will print "Record found" and the tasks stored inside the text file will be retrieved and
     * store to the ArrayList {@code records}. Otherwise, the program will print "No record found" and the ArrayList
     * {@code records} remains an empty list.
     */
    public Record() {
        String line = "-----------------------------";
        System.out.println("Initializing " + Duke.NAME + "...");
        readRecords();
        System.out.println("Completed!");
        System.out.println(line);
    }

    /**
     * Adds a new record to the {@code Record} object. If the record is added successfully, the program will print
     * message to prompt users that the given task has been added.<br>
     * If IllegalArgumentException is thrown, it indicates that the given task failed to be added and the related
     * reason of failure will be printed in the CLI.
     *
     * @param detailFragments information of a given task (i.e. taskName, isDone indicator (1/0) and date [based on
     *                        taskType])
     * @param taskType        Type of the given task (i.e. Deadline [D], Event [E] or Todo [T])
     */
    public void addRecord(String[] detailFragments, String taskType) {
        boolean isAdded = false;
        try {
            isAdded = addRecordToCollection(detailFragments, taskType);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        if (isAdded) {
            int numberOfRecords = records.size();
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + records.get(numberOfRecords - 1));
            System.out.printf("Now you have %d tasks in the list.\n", numberOfRecords);
            saveRecords();
        }
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
            System.out.println("Invalid input! (Index cannot be out of bounds)");
            return;
        }
        System.out.println("Got it. I've deleted this task:");
        System.out.println("\t" + records.remove(index));
        System.out.printf("Now you have %d tasks in the list.\n", records.size());
        saveRecords();
    }

    /**
     * Mark a record as done based on the given {@code index}.If the record is marked as done successfully, the program
     * will print message to prompt users that the given task has been marked as done.<br>
     * Otherwise, the program will print the reason of failure and given task is failed to be marked.
     *
     * @param index index of record, which could be found with {@code list} command, that the user wants to mark as
     *              done
     */
    public void markAsDone(int index) {
        if (isIndexOutOfBound(index)) {
            System.out.println("Invalid input! (Index cannot be out of bounds)");
            return;
        }
        records.get(index).setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + records.get(index));
        saveRecords();
    }

    /**
     * Searches records based on the given {@code date}. If the dates of stored tasks match the given {@code date}, the
     * program will print those tasks in the CLI.
     *
     * @param date A date in format of yyyy-mm-dd
     */
    public void searchDate(String date) {
        DateTime dateTime;
        int counter = 1;
        try {
            dateTime = new DateTime(date);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.printf("Here is your task in %s:\n", date);
        for (Task task : records) {
            if(task.getDate() == null) continue;
            if (task.getDate().equals(dateTime.getDate())) {
                System.out.println(counter++ + ". " + task);
            }
        }
        if (counter == 1){
            System.out.println("Null");
        }
    }

    /**
     * Finds records based on the given {@code keyword}. If the names of stored tasks contains the given
     * {@code keyword}, the program will print those tasks in the CLI. If no record contains the given {@code keyword},
     * "NULL" will be printed.
     *
     * @param keyword A target String for searching
     */
    public void findRecords(String keyword) {
        boolean hasRecord = false;
        System.out.printf("Here is your task List with keyword %s:\n", keyword);
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getTaskName().contains(keyword)) {
                System.out.println((i + 1) + ". " + records.get(i));
                hasRecord = true;
            }
        }
        if (!hasRecord) {
            System.out.println("Null");
        }
    }

    /**
     * Prints all stored tasks inside the Record object to the CLI. If no task is stored,
     */
    public void showList() {
        System.out.println("Here is your task List:");
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
        if (records.size() == 0) {
            System.out.println("Null");
        }
    }

    private void readRecords() {
        try {
            File myObj = new File("records.txt");
            Scanner recordReader = new Scanner(myObj);
            while (recordReader.hasNextLine()) {
                InputData data = new InputData(recordReader.nextLine(), InputType.recordInput);
                addRecordToCollection(data.getOtherArguments(), data.getFirstArgument());
                if (data.isDone()) {
                    records.get(records.size() - 1).setAsDone();
                }
            }
            recordReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No record found");
            return;
        }
        System.out.println("Record found");
    }

    private boolean addRecordToCollection(String[] detailFragments, String taskType) {
        String date;
        String taskName;
        String[] details;
        boolean isAdded = false;

        switch (taskType) {
        case Todo.TASK_TYPE:
            if (detailFragments.length > 0) {
                taskName = String.join(" ", detailFragments);
                records.add(new Todo(taskName));
                isAdded = true;
            } else {
                showInvalidEmptyDescription();
            }
            break;
        case Deadline.TASK_TYPE:
            details = getTaskNameAndDate(detailFragments, taskType);
            if (details != null) {
                taskName = details[0];
                date = details[1];
                records.add(new Deadline(taskName, date));
                isAdded = true;
            }
            break;
        case Event.TASK_TYPE:
            details = getTaskNameAndDate(detailFragments, taskType);
            if (details != null) {
                taskName = details[0];
                date = details[1];
                records.add(new Event(taskName, date));
                isAdded = true;
            }
            break;
        default:
            throw new IllegalArgumentException("A non-taskType is passed to addRecord. Program terminated.");
        }
        return isAdded;
    }

    private String[] getTaskNameAndDate(String[] detailFragments, String taskType) {
        int keywordIndex = Arrays.asList(detailFragments).indexOf(taskType.equals(Deadline.TASK_TYPE) ? "/by" : "/at");
        if (keywordIndex < 0 || keywordIndex == detailFragments.length - 1) {
            System.out.println("Invalid argument! It may be resulted from:");
            System.out.println("1. No date/time provided");
            System.out.println("2. keywords not matching");
            return null;
        }
        if (keywordIndex == 0) {
            showInvalidEmptyDescription();
            return null;
        }
        StringBuilder taskName = new StringBuilder();
        StringBuilder dueDay = new StringBuilder();
        taskName.append(detailFragments[0]);
        for (int i = 1; i < keywordIndex; i++) {
            taskName.append(" ").append(detailFragments[i]);
        }
        dueDay.append(detailFragments[keywordIndex + 1]);
        for (int i = keywordIndex + 2; i < detailFragments.length; i++) {
            dueDay.append(" ").append(detailFragments[i]);
        }
        return new String[]{taskName.toString(), dueDay.toString()};
    }

    private void saveRecords() {
        try {
            FileWriter save = new FileWriter("Records.txt");
            for (Task task : records) {
                save.write(task.toSave() + "\n");
            }
            save.close();
        } catch (IOException e) {
            System.out.println("IOException - File failed to be saved");
            e.printStackTrace();
        }
    }

    private boolean isIndexOutOfBound(int index) {
        return index < 0 || index >= records.size();
    }

    private void showInvalidEmptyDescription() {
        System.out.println("The description of a task cannot be empty.");
    }
}
