/*
 * The Storage class deals with saving tasks into files and loading tasks from files.
 * */
package storage;

import java.util.Scanner;

import parser.Parser;
import tasklist.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {
    private static Parser parser = new Parser();

    /*
     * The loadFile method loads the contents of a text file.
     * If the text file "duke.txt" cannot be found, a FileNotFoundException is thrown
     * @params tasks specifies the tasklist to which the tasks from the text file "duke.txt" are added to.
     * */
    public static void loadFile(TaskList tasks) throws FileNotFoundException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(filePath);
        if (filePath.length() != 0) {
            while (s.hasNext()) {
                String currentLine = s.nextLine();
                String[] inputs = currentLine.split(" ");
                int startingIndex = 2;
                if (inputs[1].equals("[")) {
                    startingIndex = 3;
                }
                if (inputs[0].equals("[T]")) {
                    parser.loadTodo(tasks, inputs, startingIndex);
                } else if (inputs[0].equals("[D]")) {
                    parser.loadDeadline(tasks, inputs, startingIndex);
                } else if (inputs[0].equals("[E]")) {
                    parser.loadEvent(tasks, inputs, startingIndex);
                }
            }
        }
    }

    /*
     * The saveFile method saves the contents of the list into a text file "duke.txt".
     * If an error is encountered when creating a new file "duke.txt", an IOException is thrown
     * @params tasks specifies the tasklist from which the tasks to be written onto the text file "duke.txt"
     * are retrieved from.
     * */
    public static void saveFile(TaskList tasks) throws IOException {
        File filePath = new File("duke.txt");
        if (!filePath.exists()) {
            if (filePath.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasks.getTaskCount(); i++) {
            String task = tasks.getCurrentTask(i).toString() + "\n";
            fileWriter.write(task);
        }
        fileWriter.close();
    }
}