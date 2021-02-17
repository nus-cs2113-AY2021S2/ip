package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //to store the sentence
    private static String line;
    //to keep track of the number of task
    private static int count = 0;
    private static Scanner in = new Scanner(System.in);
    //Array of object
    private static Task[] tasks = new Task[100];
    //Array list to store the task objects
    private static ArrayList<Task> taskArrayList = new ArrayList<Task>();


    public static void main(String[] args) throws DukeException {

        System.out.println("Hello! I'm Duke\nWhat can i do for you?\n");

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
                        taskArrayList.add(tasks[count]); //store in task array list
                        System.out.println("Got it. I've added this task");
                        System.out.print("  ");
                        tasks[count++].printStatus();
                        System.out.println("Now you have " + count + " tasks in the list");
                    } catch (DukeException e) {
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } else if (line.contains("deadline")) {
                    String task = line.substring(9, line.indexOf("/"));
                    String by = line.substring(line.indexOf("/") + 4, line.length());
                    tasks[count] = new DeadLines(task, by);
                    taskArrayList.add(tasks[count]); //store in task array list
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    tasks[count++].printStatus();
                    System.out.println("Now you have " + count + " tasks in the list");
                } else if (line.contains("event")) {
                    String task = line.substring(6, line.indexOf("/"));
                    String by = line.substring(line.indexOf("/") + 4, line.length());
                    tasks[count] = new Events(task, by);
                    taskArrayList.add(tasks[count]); //store in task array list
                    System.out.println("Got it. I've added this task:");
                    System.out.print("  ");
                    tasks[count++].printStatus();
                    System.out.println("Now you have " + count + " tasks in the list:");
                } else if (line.contains("list")) {
                    for (int i = 0; i < taskArrayList.size(); i++) {
                        System.out.print(i + 1 + ".");
                        taskArrayList.get(i).printStatus();
                    }
                } else if (line.contains("done")) {
                    int index = Integer.parseInt(line.substring(5, line.length()));
                    tasks[index - 1].setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    tasks[index - 1].printStatus();
                } else if (line.contains("delete")){
                    int index = Integer.parseInt(line.substring(7, line.length()));
                    System.out.println("Noted. I've removed this task:");
                    taskArrayList.get(index - 1).printStatus();
                    taskArrayList.remove(index-1);
                    System.out.println("Now you have "+ taskArrayList.size() +" tasks in the list.");
                }
            }catch(DukeException e){
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
