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

public class Storage {

    public static Task[] tasks = new Task[100];
    //Array list to store the task objects
    public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    //to store all the task in string and write to file
    public static ArrayList<String> taskSentences = new ArrayList<String>();
    public static int count = 0;
    private static String home = System.getProperty("user.home");
    private static String sentence;
    private static String[] words;

    public Storage(){

    }

    public static void readFile(){

        try{
            BufferedReader br = new BufferedReader(new FileReader(home+"/tasklist.txt")); //Read from this file
            while((sentence=br.readLine())!=null){
                words = sentence.split("\\|");
                switch(words[0]){
                    case "T":
                        tasks[count] = new ToDos(words[2]); //create new todo object
                        taskArrayList.add(tasks[count]);   //add todo object to arraylist
                        taskSentences.add("T"+"|"+words[1]+"|"+words[2]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                    case "D":
                        tasks[count] = new DeadLines(words[2],words[3]); //create new deadline object
                        taskArrayList.add(tasks[count]);   //add deadline object to arraylist
                        taskSentences.add("D"+"|"+words[1]+"|"+words[2]+"|"+words[3]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                    case "E":
                        tasks[count] = new Events(words[2],words[3]); //create new events object
                        taskArrayList.add(tasks[count]);   //add event object to arraylist
                        taskSentences.add("E"+"|"+words[1]+"|"+words[2]+"|"+words[3]); //update the task array list
                        if(words[1].equals("true")){     //if read from file is done set the object as done
                            tasks[count].setDone(true);
                        }
                        count++;                          //increment count
                        break;
                }
            }
            br.close();
        }catch(Exception ex){
            System.out.println("No file at the moment");
        }
    }

    public static void writeFile(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(home+"/tasklist.txt")); //write to this file
            for(String s:taskSentences){
                bw.write(s+"\n");
            }
            bw.close();
        }catch(Exception ex){
            System.out.println("Error writing to file");
        }
    }

}
