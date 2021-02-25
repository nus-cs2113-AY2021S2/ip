package io;

import models.TaskList;
import parser.DataParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private static DataParser dataParser;
    private TaskList taskList;
    private int taskCount;

    public FileManager(TaskList taskList, int taskCount) {
        this.taskList = taskList;
        this.taskCount = taskCount;
        dataParser = new DataParser(taskList, taskCount);
    }

    public void saveFile() throws IOException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            if (!path.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(path);
        for (int i = 0; i < taskCount; i++) {
            fileWriter.write(taskList.get(i).formatData());
        }
        fileWriter.flush();
        fileWriter.close();
    }

    public void loadFile() throws FileNotFoundException {
        File path = new File("tasks.txt");
        if (!path.exists()) {
            throw new FileNotFoundException();
        }
        Scanner scanner = new Scanner(path);
        try {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                dataParser.parseData(line);
            }
        } catch (Exception e) {
            System.out.println("Failed to load!");
            taskCount = 0;
            taskList.getTaskList().clear();
        }
    }
}
