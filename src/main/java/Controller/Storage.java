package Controller;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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
                //myWriter.write(((Deadline) task).getBy());
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

    public ArrayList<Task> loadFile() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        File myObj = new File("duke.txt");
        if (myObj == null) {
            throw new FileNotFoundException("Cannot load file as no such file exists. Create new list.");
        }
        else {
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] strings = data.split(" / ");
                if (strings[0].equals("T")) {
                    Task task = new ToDo(strings[2]);
                    if (strings[1].equals("1")) {
                        task.setDone(true);
                    }
                    else {
                        task.setDone(false);
                    }
                    tasks.add(task);
                }
                else if (strings[0].equals("D")) {
                    Task task = new Deadline(strings[2], strings[3]);
                    if (strings[1].equals("1")) {
                        task.setDone(true);
                    }
                    else {
                        task.setDone(false);
                    }
                    tasks.add(task);
                }
                else if (strings[0].equals("E")) {
                    //System.out.println(strings[4]);
                    Task task = new Event(strings[2], strings[3]);
                    if (strings[1].equals("1")) {
                        task.setDone(true);
                    }
                    else {
                        task.setDone(false);
                    }
                    tasks.add(task);
                }
            }
            myReader.close();
    }
    return tasks;
    }
}
