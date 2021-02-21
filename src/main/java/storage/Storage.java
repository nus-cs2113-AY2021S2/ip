package storage;

import task.*;
import constant.Constant;
import printer.Printer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Storage class for handling the loading and storing of file to the hard disk.
 */
public class Storage {
    private static final Constant constant = new Constant();
    private static final Printer printer = new Printer();
    private static final TaskHandler taskHandler = new TaskHandler();

    /**
     * Try-catch block for attempting to load a file if it exist.
     * Print the relevant welcome message and file loading status.
     */
    public static void initFile() {
        printer.printWelcomeMessage();
        try {
            System.out.println(constant.FILE_LOAD_MESSAGE);
            tryRunFile();
            System.out.println(constant.FILE_LOAD_SUCCESS);
            System.out.println(constant.DIVIDER_LINE);
            System.out.println(constant.FILE_LOAD_SUCCESS_WELCOME);
            System.out.println(constant.DIVIDER_LINE);
        } catch (IOException e) {
            System.out.println(constant.FILE_LOAD_FAILURE);
            System.out.println(constant.DIVIDER_LINE);
        }
    }

    /**
     * Load and read the file input and then add them to the task list.
     *
     * @throws IOException if the file is unable to load.
     */
    private static void tryRunFile() throws IOException {
        File sourceFile = new File(constant.FILE_PATH);
        String fileData;
        if (sourceFile.createNewFile()){
            throw new IOException();
        }
        Scanner fileScanner = new Scanner(sourceFile);
        while (fileScanner.hasNextLine()) {
            fileData = fileScanner.nextLine();
            processFileData(fileData, taskHandler.getTaskCount());
            taskHandler.increaseTaskCount();
        }
    }

    /**
     * Add the type of task to the task list based on the input record.
     * Default print out any unexpected data found during the process.
     *
     * @param fileData is the record of 1 task from the file.
     * @param taskNumber is the current index in the task list managed by taskHandler.
     */
    private static void processFileData(String fileData, int taskNumber) {
        String[] splitFileData = fileData.split(" \\| ");
        String fileInput, fileDescription, fileTiming;
        int argumentLength = splitFileData.length;
        switch (splitFileData[0]) {
        case "T":
            fileInput = extractFileInfo(splitFileData,2, argumentLength);
            taskHandler.addTask(new Todo(fileInput));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            break;
        case "D":
            fileDescription = extractFileInfo(splitFileData, 2, argumentLength - 1);
            fileTiming = extractFileInfo(splitFileData,argumentLength - 1, argumentLength);
            taskHandler.addTask(new Deadline(fileDescription + " ", fileTiming));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            break;
        case "E":
            fileDescription = extractFileInfo(splitFileData, 2, argumentLength - 1);
            fileTiming = extractFileInfo(splitFileData,argumentLength - 1, argumentLength);
            taskHandler.addTask(new Event(fileDescription + " ", fileTiming));
            checkFileInputDoneStatus(taskNumber, splitFileData);
            break;
        default:
            System.out.println("Unknown file data");
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
     * Try-catch block for attempting to save the current task list into a file.
     * Prints an error if error an occur during the saving process.
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
        FileWriter fw = new FileWriter(constant.FILE_PATH);
        fw.write(convertToFileInput());
        fw.close();
    }

    /**
     * Convert the task list to the saving format.
     *
     * @return a string containing the format for writing in the file by calling method.
     */
    private static String convertToFileInput() {
        return taskHandler.toFileFormat();
    }
}
