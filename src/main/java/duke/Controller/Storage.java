package duke.controller;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.toDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public void saveFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("saveDuke.txt");
            for (Task task : tasks) {
                if (task instanceof toDo) {
                    writer.write("T" + " / " + task.getSaveDone() + " / " + task.getDescription());
                } else if (task instanceof Deadline) {
                    writer.write("D" + " / " + task.getSaveDone() + " / " + task.getDescription() + " / " + ((Deadline) task).getByDate());
                } else if (task instanceof Event) {
                    writer.write("E" + " / " + task.getSaveDone() + " / " + task.getDescription() + " / " + ((Event) task).getAtDate());
                }
                writer.write("\n");
            }
            writer.close();
            System.out.print("Yea cuhhhh... filed saved!\n");
        } catch (IOException e) {
            System.out.println("Cannot save file my G!");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> printFileContents() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File saveDuke = new File("saveDuke.txt");
            Scanner fileReader = new Scanner(saveDuke);
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] stringSplit = line.split(" / ");
                if (stringSplit[0].equals("T")) {
                    Task task = new toDo(stringSplit[2]);
                    if (stringSplit[1].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                } else if (stringSplit[0].equals("E")) {
                    Task task = new Event(stringSplit[2], stringSplit[3]);
                    if (stringSplit[1].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                } else if (stringSplit[0].equals("D")) {
                    Task task = new Deadline(stringSplit[2], stringSplit[3]);
                    if (stringSplit[1].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot load file...you are clapped! Churn up new list please.");
            return tasks;
        }
        System.out.println("File loaded boi...lets gooooooo!");
        return tasks;
    }
}
