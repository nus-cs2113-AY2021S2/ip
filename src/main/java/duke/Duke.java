package duke;

import java.util.ArrayList;
import java.util.Scanner;



public class Duke {

    //to store the sentence
    private static String line;
    //to keep track of the number of task
    public static int count = 0;
    private static Scanner in = new Scanner(System.in);
    //Array of object
    public static Task[] tasks = new Task[100];
    //Array list to store the task objects
    public static ArrayList<Task> taskArrayList = new ArrayList<Task>();
    //to store all the task in string and write to file
    public static ArrayList<String> taskSentences = new ArrayList<String>();

    public static void main(String[] args) throws DukeException {

        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");
        Read.readFile(); //before starting read task from the files first

        while(true){

            line = in.nextLine();
            try {
                if(!(line.contains("bye")||line.contains("todo")||line.contains("deadline")||line.contains("event")
                        ||line.contains("list")||line.contains("done")||line.contains("delete"))){
                    throw new DukeException();
                }
                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!\n");
                    break;
                } else if (line.contains("todo")) {
                    try {
                        if (line.length() == 4) {
                            throw new DukeException();
                        }
                        String task = line.substring(5, line.length());
                        tasks[count] = new ToDos(task);
                        taskArrayList.add(tasks[count]); //store in array list for deleting
                        taskSentences.add("T"+"|"+tasks[count].getDone()+"|"+task); //store in array list for writing
                        System.out.println("Got it. I've added this task");
                        System.out.print("  ");
                        tasks[count++].printStatus();
                        System.out.println("Now you have " + count + " tasks in the list");
                        Write.writeFile(); // update file
                    } catch (DukeException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (line.contains("deadline")) {
                    String task = line.substring(9, line.indexOf("/")-1);
                    String by = line.substring(line.indexOf("/") + 4, line.length());
                    tasks[count] = new DeadLines(task, by);
                    taskArrayList.add(tasks[count]); //store in array for deleting
                    taskSentences.add("D"+"|"+tasks[count].getDone()+"|"+task+"|"+by); //store in array list for writing
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    tasks[count++].printStatus();
                    System.out.println("Now you have " + count + " tasks in the list");
                    Write.writeFile();
                } else if (line.contains("event")) {
                    String task = line.substring(6, line.indexOf("/")-1);
                    String by = line.substring(line.indexOf("/") + 4, line.length());
                    tasks[count] = new Events(task, by);
                    taskArrayList.add(tasks[count]); //store in task array list
                    taskSentences.add("E"+"|"+tasks[count].getDone()+"|"+task+"|"+by); //store in array list for writing
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    tasks[count++].printStatus();
                    System.out.println("Now you have " + count + " tasks in the list:");
                    Write.writeFile(); //update file
                } else if (line.contains("list")) {
                    for (int i = 0; i < taskArrayList.size(); i++) {
                        System.out.print(i + 1 + ".");
                        taskArrayList.get(i).printStatus();
                    }
                } else if (line.contains("done")) {
                    String text;
                    int index = Integer.parseInt(line.substring(5, line.length()));
                    tasks[index - 1].setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    tasks[index - 1].printStatus();
                    text = taskSentences.get(index-1);
                    text = text.replace("false","true");
                    taskSentences.set(index-1,text); //rewrite and update the status
                    Write.writeFile(); //update file
                } else if (line.contains("delete")){
                    int index = Integer.parseInt(line.substring(7, line.length()));
                    System.out.println("Noted. I've removed this task:");
                    taskArrayList.get(index - 1).printStatus(); //print the task to be remove before delete
                    taskArrayList.remove(index-1); //remove obj
                    taskSentences.remove(index-1); //remove sentence from file
                    count = count - 1; //update count value after deleting
                    System.out.println("Now you have "+ taskArrayList.size() +" tasks in the list.");
                    Write.writeFile(); //update file
                }
            }catch(DukeException e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

}
