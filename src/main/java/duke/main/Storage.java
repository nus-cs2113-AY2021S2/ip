package duke.main;

import duke.items.Deadline;
import duke.items.Event;
import duke.items.Task;
import duke.items.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void loadTask(String line){
        String[] arr = line.split("\t");
        switch(arr[0]){
        case ("T"):
            Todo current1 = new Todo(arr[2]);
            if (arr[1].equals("true")) {
                current1.setDone();
            }
            Task.addTask(current1);
            break;
        case ("D"):
            Deadline current2 = new Deadline(arr[2], arr[3]);
            if (arr[1].equals("true")) {
                current2.setDone();
            }
            Task.addTask(current2);
            break;

        case ("E"):
            Event current3 = new Event(arr[2], arr[3]);
            if (arr[1].equals("true")) {
                current3.setDone();
            }
            Task.addTask(current3);
            break;
        }
    }

    public static void loadFile() throws FileNotFoundException {
        File f = new File("list.txt"); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            loadTask(s.nextLine());
        }
    }

    public static void writeToFile() throws IOException {
        createFile();
        FileWriter fw = new FileWriter("list.txt");
        for (int i=0; i<Task.getNumOfTasks(); i++) {
            ArrayList<Task> buffer = Task.getList();
            switch (buffer.get(i).getType()) {

            case ("T"):
                fw.write(buffer.get(i).getType() + "\t" + buffer.get(i).isDone() + "\t" + buffer.get(i).getDescription() + "\n");
                break;
            case ("D"):
                Deadline temp1 = (Deadline)(buffer.get(i));
                fw.write(buffer.get(i).getType() + "\t" + buffer.get(i).isDone() + "\t" + buffer.get(i).getDescription() + "\t" + temp1.getBy() + "\n");
                break;
            case ("E"):
                Event temp2 = (Event) (buffer.get(i));
                fw.write(buffer.get(i).getType() + "\t" + buffer.get(i).isDone() + "\t" + buffer.get(i).getDescription() + "\t" + temp2.getAt() + "\n");
                break;
            }
        }
        fw.close();
    }

    public static void createFile(){
        try {
            File myObj = new File("list.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File is overwritten.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}


