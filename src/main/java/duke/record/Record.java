package duke.record;

import duke.Duke;
import duke.input.InputData;
import duke.input.InputType;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Record {
    private final ArrayList<Task> records = new ArrayList<>();

    public Record() {
        System.out.print("Initializing " + Duke.NAME + "...");
        readRecords();
        System.out.println(" Completed!");
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
            System.out.println("No record found.");
        }
    }

    public void addRecord(String[] detailFragments, String taskType) {
        boolean isAdded = addRecordToCollection(detailFragments, taskType);

        if (isAdded) {
            int numberOfRecords = records.size();
            System.out.println("Got it. I've added this task:");
            System.out.println("\t" + records.get(numberOfRecords - 1));
            System.out.printf("Now you have %d tasks in the list.\n", numberOfRecords);
            saveRecords();
        }
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
                save.write(task.getSaveString() + "\n");
            }
            save.close();
        } catch (IOException e) {
            System.out.println("IOException - File failed to be saved");
            e.printStackTrace();
        }
    }

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

    public void showList() {
        System.out.println("Here is your task List:");
        for (int i = 0; i < records.size(); i++) {
            System.out.println((i + 1) + ". " + records.get(i));
        }
    }

    private boolean isIndexOutOfBound(int index) {
        return index < 0 || index >= records.size();
    }

    private void showInvalidEmptyDescription() {
        System.out.println("The description of a task cannot be empty.");
    }
}
