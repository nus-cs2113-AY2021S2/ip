package dukchess.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dukchess.entity.Deadline;
import dukchess.entity.Event;
import dukchess.entity.Task;
import dukchess.entity.Todo;

public class TaskList extends ArrayList<Task> {
    private FileWriter taskFileWriter;
    private Scanner taskFileScanner;

    public TaskList(File taskFile) throws IOException {
        super();
        initializeFromFile(taskFile);
    }

    public void flushTaskList() {
        try {
            for (int i = 0; i < this.size(); i++) {
                Task currentTask = this.get(i);
                String currentLine = currentTask.toString();
                taskFileWriter.append(currentLine).append(System.getProperty("line.separator"));
            }
            this.taskFileWriter.close();
            this.taskFileScanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeFromFile(File taskFile) throws IOException {
        if (taskFile == null) {
            return;
        }
        this.taskFileScanner = new Scanner(taskFile);

        Pattern taskStringPattern = Pattern.compile("\\[(\\w)\\]\\[(.?)\\] ([^(]+)(\\((.+)\\))?");
        while (taskFileScanner.hasNext()) {
            Task task = null;
            String currentTaskString = taskFileScanner.nextLine();

            Matcher taskStringMatches = taskStringPattern.matcher(currentTaskString);
            taskStringMatches.matches();
            String taskType = taskStringMatches.group(1);
            boolean isDone = taskStringMatches.group(2).equals("X");
            String taskDescription = taskStringMatches.group(3).trim();
            String taskDetails = taskStringMatches.group(5);

            switch (taskType) {
                case "T":
                    task = new Todo(taskDescription, isDone);
                    break;
                case "E":
                    // TODO: add some checks to this
                    String whenIsEventAt = taskDetails.split("at: ")[1];
                    task = new Event(taskDescription, isDone, whenIsEventAt);
                    break;
                case "D":
                    // TODO: add some checks to this
                    String whenIsTaskDue = taskDetails.split("by: ")[1];
                    task = new Deadline(taskDescription, isDone, whenIsTaskDue);
                    break;
            }
            super.add(task);
        }

        this.taskFileWriter = new FileWriter(taskFile, false);
    }
}
