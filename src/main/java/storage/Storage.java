package storage;

import java.util.Scanner;

import commands.Task;
import commands.Todo;
import commands.Deadline;
import commands.Event;
import exceptions.DukeException;
import tasklist.TaskList;
import ui.Ui;
import parser.Parser;

import java.util.ArrayList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;



public class Storage {

    public static void loadFile(TaskList tasks) throws FileNotFoundException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(filePath);
        if (filePath.length() != 0) {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] words = currentLine.split(" ");
                if (words[0].equals("[T]")) {
                    String description = "";
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        description = description + words[i] + " ";
                    }
                    Todo todo = new Todo(description);
                    tasks.addTask(todo);
                    if (words[1].equals("[X]")) {
                        tasks.markTaskAsDone(tasks.getTaskCount());
                    }
                    tasks.incrementTaskCount();

                } else if (words[0].equals("[D]")) {
                    String description = "";
                    String by = "";
                    boolean byFlag = false;
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        if (words[i].equals("(by:")) {
                            byFlag = true;
                        } else if (!byFlag) {
                            description = description + words[i] + " ";
                        } else {
                            if (i == words.length - 1) {
                                by = by + words[i];
                            } else {
                                by = by + words[i] + " ";
                            }
                        }
                    }
                    by = by.replace(")", "");
                    Deadline deadline = new Deadline(description, by);
                    tasks.addTask(deadline);
                    if (words[1].equals("[X]")) {
                        tasks.markTaskAsDone(tasks.getTaskCount());
                    }
                    tasks.incrementTaskCount();
                } else if (words[0].equals("[E]")) {
                    String description = "";
                    String at = "";
                    boolean atFlag = false;
                    int startingIndex = 2;
                    if (words[1].equals("[")) {
                        startingIndex = 3;
                    }
                    for (int i = startingIndex; i < words.length; i++) {
                        if (words[i].equals("(at:")) {
                            atFlag = true;
                        } else if (!atFlag) {
                            description = description + words[i] + " ";
                        } else {
                            if (i == words.length - 1) {
                                at = at + words[i];
                            } else {
                                at = at + words[i] + " ";
                            }
                        }
                    }
                    at = at.replace(")", "");
                    Event event = new Event(description, at);
                    tasks.addTask(event);
                    if (words[1].equals("[X]")) {
                        tasks.markTaskAsDone(tasks.getTaskCount());
                    }
                    tasks.incrementTaskCount();
                }
            }
        }
    }

    public static void saveFile(TaskList tasks) throws IOException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            if (filePath.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            String task = tasks.printCurrentTask(i).toString() + "\n";
            fileWriter.write(task);
        }
        fileWriter.close();
    }
}
