package storage;

import task.Deadline;
import task.Event;
import task.Todo;
import task.TaskHandler;
import constant.Constant;
import ui.Ui;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Storage class for handling the loading and storing of file to the hard disk.
 */
public class Storage {
    private static final TaskHandler taskHandler = new TaskHandler();

    /**
     * Print the welcome and file loading message status.
     */
    public static void initFile() {
        Ui.printWelcomeMessage();
        try {
            System.out.println(Constant.FILE_LOAD_MESSAGE);
            tryRunFile();
            System.out.println(Constant.FILE_LOAD_SUCCESS);
            System.out.println(Constant.DIVIDER_LINE);
            System.out.println(Constant.FILE_LOAD_SUCCESS_WELCOME);
            System.out.println(Constant.DIVIDER_LINE);
        } catch (IOException e) {
            System.out.println(Constant.FILE_LOAD_FAILURE);
            System.out.println(Constant.DIVIDER_LINE);
        }
    }

    /**
     * Load and read the file input and add them to the task list.
     *
     * @throws IOException if the file is unable to load.
     */
    private static void tryRunFile() throws IOException {
        File sourceFile = new File(Constant.FILE_PATH);
        String fileData;
        if (sourceFile.createNewFile()){
            throw new IOException();
        }
        Scanner fileScanner = new Scanner(sourceFile);
        while (fileScanner.hasNextLine()) {
            fileData = fileScanner.nextLine();
            processFileData(fileData, taskHandler.getTaskCount());
        }
    }

    /**
     * Check the type of tasks and add to the task list.
     *
     * @param fileData is the record of 1 task from the file.
     * @param taskNumber is the current index in the task list managed by taskHandler.
     */
    private static void processFileData(String fileData, int taskNumber) {
        String[] splitFileData = fileData.split(" \\| ");
        String fileDescription, fileTiming;
        int argumentLength = splitFileData.length;
        switch (splitFileData[0]) {
        case "T":
            fileDescription = extractFileInfo(splitFileData,2, argumentLength);
            taskHandler.addTask(new Todo(fileDescription));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            taskHandler.increaseTaskCount();
            break;
        case "D":
            fileDescription = extractFileInfo(splitFileData, 2, argumentLength - 1);
            fileTiming = extractFileInfo(splitFileData,argumentLength - 1, argumentLength);
            taskHandler.addTask(new Deadline(fileDescription + " ", fileTiming));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            taskHandler.increaseTaskCount();
            break;
        case "E":
            fileDescription = extractFileInfo(splitFileData, 2, argumentLength - 1);
            fileTiming = extractFileInfo(splitFileData,argumentLength - 1, argumentLength);
            taskHandler.addTask(new Event(fileDescription + " ", fileTiming));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            taskHandler.increaseTaskCount();
            break;
        default:
            System.out.println("Unknown file data at line: " + (taskNumber+1));
        }
    }

    /**
     * Mark the task as done or undone based on the input records.
     *
     * @param taskNumber is the current index in the task list managed by taskHandler.
     * @param splitFileData contains the information such as type, status, and description of a task.
     */
    private static void checkFileInputDoneStatus(int taskNumber, String[] splitFileData) {
        if (splitFileData[1].equals("X")) {
            taskHandler.markDone(taskNumber);
        }
    }

    /**
     * Extract a single task information based on the requirement given in param.
     *
     * @param splitFileData contains the information such as type, status, and description of a task.
     * @param start is the index of the first block to concatenate.
     * @param end is the index of the last block to concatenate.
     * @return the information requested by the calling method (task description or timing field).
     */
    private static String extractFileInfo(String[] splitFileData, int start, int end) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = start; i < end; i++) {
            joiner.add(splitFileData[i]);
        }
        return joiner.toString();
    }

    /**
     * Save the task list to the hard disk.
     */
    public static void saveFile() {
        try {
            tryWriteToFiles();
        } catch (IOException e) {
            System.out.println("Error in IO!");
        }
    }

    /**
     * Write the records in the task list to the file.
     *
     * @throws IOException if an error if error an occur during the writing to file process.
     */
    private static void tryWriteToFiles() throws IOException {
        FileWriter fw = new FileWriter(Constant.FILE_PATH);
        fw.write(convertToFileInput());
        fw.close();
    }

    /**
     * Convert the task list to the saving format for hard disk writing.
     *
     * @return a string in file saving format
     */
    private static String convertToFileInput() {
        return taskHandler.toFileFormat();
    }
}
