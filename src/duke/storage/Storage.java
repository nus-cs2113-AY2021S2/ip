package duke.storage;

import duke.ui.TextUi;
import duke.tasks.DeadLines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.ToDos;

import java.io.*;
import java.util.ArrayList;


/**
 * Contains functions that stores the task data by writing
 * and reading to and from the data file
 */
public class Storage {

    public static Task[] tasks = new Task[100];
    //Array list to store the task objects
    public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    //Array list in string for writing to file
    public static ArrayList<String> taskSentences = new ArrayList<String>();
    public static int taskCount = 0;
    private static final String home = System.getProperty("user.home");
    //store each line from file
    private static String sentence;
    //used to spilt each sentence from file
    private static String[] words;

    public Storage() {
    }

    /**
     * Represents the file and method used to read task data.
     * @throws IOException if file does not exist.
     * @throws ArrayIndexOutOfBoundsException if file is corrupted.
     */
    public static void readFile() {

        try {
            //Read from this file
            BufferedReader br = new BufferedReader(new FileReader(home + "/tasklist.txt"));

            while ((sentence = br.readLine()) != null) {
                words = sentence.split("\\|");

                switch (words[0]) {
                    case "T":
                        //create new todo object
                        tasks[taskCount] = new ToDos(words[2]);
                        //add todo object to arraylist
                        taskArrayList.add(tasks[taskCount]);
                        //update the task array list
                        taskSentences.add("T" + "|" + words[1] + "|" + words[2]);
                        //if status from file is true set the object as done
                        if (words[1].equals("true")) {
                            tasks[taskCount].setDone(true);
                        }
                        taskCount++;
                        break;
                    case "D":
                        tasks[taskCount] = new DeadLines(words[2], words[3]);
                        taskArrayList.add(tasks[taskCount]);
                        taskSentences.add("D" + "|" + words[1] + "|" + words[2] + "|" + words[3]);
                        if (words[1].equals("true")) {
                            tasks[taskCount].setDone(true);
                        }
                        taskCount++;
                        break;
                    case "E":
                        tasks[taskCount] = new Events(words[2], words[3]);
                        taskArrayList.add(tasks[taskCount]);
                        taskSentences.add("E" + "|" + words[1] + "|" + words[2] + "|" + words[3]);
                        if (words[1].equals("true")) {
                            tasks[taskCount].setDone(true);
                        }
                        taskCount++;
                        break;
                }
            }
            System.out.println("File processed successfully! Type `list` to show current available tasks.");
            System.out.println(TextUi.DIVIDER);
            br.close();
        } catch (IOException e) {
            System.out.println("No file exist, new file `tasklist.txt` created in home directory.");
        } catch(ArrayIndexOutOfBoundsException ex){
            System.out.println("File corrupted! Please delete `tasklist.txt` in the home directory " +
                    "and reboot the application." );
            //ends the program to prevent more file being corrupted
            System.exit(0);
        }
    }

    /**
     * Represents the file and method used to write task data.
     * @throws Exception if unable to write to file
     */
    public static void writeFile() {
        try {
            //write to this file
            BufferedWriter bw = new BufferedWriter(new FileWriter(home + "/tasklist.txt"));
            for (String s : taskSentences) {
                bw.write(s + "\n");
            }
            bw.close();
        } catch (Exception ex) {
            System.out.println("Error writing to file, please reboot.");
        }
    }

}
