package storage;

import task.*;
import constant.Constant;
import printer.Printer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Storage {
    private static final Constant constant = new Constant();
    private static final Printer printer = new Printer();
    private static final TaskHandler taskHandler = new TaskHandler();

    // load new file
    public static void initFile() {
        printer.printHelloStatement();
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

    private static void checkFileInputDoneStatus(int taskNumber, String[] splitFileData) {
        if (splitFileData[1].equals("X")) {
            taskHandler.markDone(taskNumber);
        }
    }

    private static String extractFileInfo(String[] splitFileData, int start, int end) {
        StringJoiner joiner = new StringJoiner(" ");
        for(int i = start; i < end; i++) {
            joiner.add(splitFileData[i]);
        }
        return joiner.toString();
    }

    // save current file
    public static void saveFile() {
        try {
            tryWriteToFiles();
        } catch (IOException e) {
            System.out.println("Error in IO!");
        }
    }

    private static void tryWriteToFiles() throws IOException {
        FileWriter fw = new FileWriter(constant.FILE_PATH);
        fw.write(convertToFileInput());
        fw.close();
    }

    private static String convertToFileInput() {
        return taskHandler.toFileFormat();
    }
}
