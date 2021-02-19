package controller;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class that deals with saving and loading the text file.
 */
public class Storage {

    /**
     * Save all output into a text file.
     * @param tasks Task list.
     */
    public void saveOutput(ArrayList<Task> tasks){
        try {
            FileWriter myWriter = new FileWriter("duke.txt");
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    myWriter.write(((Deadline) task).getType() + " / " + task.getDone() + " / " + task.getDescription() + " / " + ((Deadline) task).getBy());
                }
                else if (task instanceof Event) {
                    myWriter.write(((Event) task).getType() + " / " + task.getDone() + " / " + task.getDescription() + " / " + ((Event) task).getAt());
                }
                else if (task instanceof ToDo) {
                    myWriter.write(((ToDo) task).getType() + " / " + task.getDone() + " / " + task.getDescription());
                }
                myWriter.write("\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
            System.out.println("--------------------------------------------");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Load task from task file into task list.
     * @return Task list.
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File myObj = new File("duke.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] strings = data.split(" / ");
            if (strings[0].equals("T")) {
                Task task = loadToDo(strings);
                tasks.add(task);
            } else if (strings[0].equals("D")) {
                Task task = loadDeadline(strings);
                tasks.add(task);
            } else if (strings[0].equals("E")) {
                Task task = loadEvent(strings);
                tasks.add(task);
            }
        }
        myReader.close();
    return tasks;
    }

    /**
     * Creates deadline task from saved text file.
     * @param strings String input by user.
     * @return Deadline task object.
     */
    public Task loadDeadline(String[] strings) {
        Task task = new Deadline(strings[2], strings[3]);
        if (strings[1].equals("1")) {
            task.setDone(true);
        }
        else {
            task.setDone(false);
        }
        return task;
    }

    /**
     * Creates ToDo task from saved text file.
     * @param strings String input by user.
     * @return ToDo task object.
     */
    public Task loadToDo(String[] strings) {
        Task task = new ToDo(strings[2]);
        if (strings[1].equals("1")) {
            task.setDone(true);
        }
        else {
            task.setDone(false);
        }
        return task;
    }

    /**
     * Creates Event task from saved text file.
     * @param strings String input by user.
     * @return Event task object.
     */
    public Task loadEvent(String[] strings) {
        Task task = new Event(strings[2], strings[3]);
        if (strings[1].equals("1")) {
            task.setDone(true);
        }
        else {
            task.setDone(false);
        }
        return task;
    }
}

