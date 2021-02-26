package storage;

import tasks.DeadLines;
import tasks.Events;
import tasks.Task;
import tasks.ToDos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;


/**
 * Functions that stores the task data by reading and writing the task data
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
     * @throws Exception if file does not exist.
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
            br.close();
        } catch (Exception e) {
            System.out.println("No file exist");
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
            System.out.println("Error writing to file");
        }
    }

}
