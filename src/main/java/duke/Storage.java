package duke;

import duke.tasksmanager.Deadlines;
import duke.tasksmanager.Events;
import duke.tasksmanager.Tasks;
import duke.tasksmanager.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

import static duke.TaskList.tasks;

public class Storage {

    public static ArrayList<Tasks> updatedTasksList = new ArrayList<>();

    public static String storagePath;
    public static int tasksCount;

    public Storage(String filepath) {
        this.storagePath = filepath;
        this.tasksCount = 0;
    }


    //Methods to Save 'DukeList.txt':
    /**
     * If file exists, loads older data from file into ArrayList 'tasks'
     * Else, creates new file
     * In both cases, file used to store new data in this iteration of Duke Main
     * @throws IOException
     */
    public static ArrayList<Tasks> loadPrevListIntoNewList() throws IOException {
        try {
            Scanner s = new Scanner(new File(storagePath)); // create a Scanner using the File as the source
            // add one todo, deadline OR event task
            // based on current entry:
            while (s.hasNext()) {
                String[] prevListEntryWord = s.nextLine().split("/");
                switch (prevListEntryWord[0]) {
                    case "T":
                        updatedTasksList.add(new ToDos(prevListEntryWord[2]));
                        break;
                    case "D":
                        updatedTasksList.add(new Deadlines(prevListEntryWord[2], prevListEntryWord[3]));
                        break;
                    case "E":
                        updatedTasksList.add(new Events(prevListEntryWord[2], prevListEntryWord[3]));
                        break;
                    default:
                        break;
                }
                if (prevListEntryWord[1].equals("true")) {
                    updatedTasksList.get(tasksCount).markAsDone(); //if is a 'false', task is auto-marked as not done
                }
                tasksCount++; //increment taskCount for added task
            }
        } catch (FileNotFoundException e) {
            //Split given filepath by "/":
            String[] storagePathArray = storagePath.split("/");
            //For the first time, create a new file for the user:
            File dataDirectory = new File(storagePathArray[0]);
            dataDirectory.mkdir();
            File dukeFile = new File(storagePathArray[0], storagePathArray[1]); //File(parent, child)
            dukeFile.createNewFile();
        } finally {
            return updatedTasksList;
        }
    }

    public static void overwriteDukeListFile() {
        //write to file:
        try {
            PrintWriter writer = new PrintWriter(storagePath);
            writer.print("");
            writer.close();
            for (int i = 0; i < tasksCount; i++) {
                Tasks currentTask = tasks.get(i);
                appendToDukeListFile(currentTask.typeOfTask + "/" +
                        currentTask.isDone + "/" +
                        currentTask.description);
                if (currentTask.typeOfTask.equals("D") || currentTask.typeOfTask.equals("E")) {
                    appendToDukeListFile("/" + currentTask.date);
                }
                appendToDukeListFile(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Write: File not found");
        } catch (IOException e) {
            System.out.print("Unable to write to file");
        }
    }

    public static void appendToDukeListFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(storagePath, true);
        fw.write(textToAdd);
        fw.close();
    }

}
