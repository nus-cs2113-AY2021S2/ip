import duke.command.ArrayIndexOutOfBoundsException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private static final int DONE_LENGTH = 5;
    private static final int EVENT_LENGTH = 5;
    private static final int TODO_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 8;
    private static final int DELETE_LENGTH = 7;
    private static Scanner scanner = new Scanner(System.in);
    private static int tasksCount = 0;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static String line = "*********************************************";
    private static String filePath = "data/tasks.txt";

    private static void helloMessage() {
        System.out.println(line);
        System.out.println("Hello! I'm Julia");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println(line);
    }
    private static void byeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println();
        System.out.println(line);
        System.exit(0);
    }

    public static void printList(ArrayList<Task> tasks){
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasksCount; i++) {
            System.out.printf("%d.%s\n", i + 1, tasks.get(i).toString());
        }
        System.out.println(line);
    }

    public static void deleteTask(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DELETE_LENGTH));
            if (newNum > tasksCount || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }

            System.out.println("Noted. I've removed this task:" + "\n"+ tasks.get(newNum-1).toString());
            tasks.remove(newNum-1);
            tasksCount--;
            printTotalTasks();

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
            System.out.println(line);
        }
    }

    public static void markTaskAsDone(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DONE_LENGTH));
            if (newNum > tasksCount || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(newNum - 1).markAsDone();
            System.out.println(tasks.get(newNum-1).toString());
            System.out.println(line);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
            System.out.println(line);
        }

    }

    public static void addTodo(String newTask) {
        try {
            newTask = newTask.substring(TODO_LENGTH);
            System.out.println("Got it. I've added this task: ");
            Todo todo = new Todo(newTask);
            tasks.add(todo);
            tasksCount++;
            System.out.println(todo.toString());
            printTotalTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(line);
        }
    }

    public static void addDeadline(String input) {
        String[] split = input.split(" /by");
        System.out.println("Got it. I've added this task: ");
        Deadline deadline = new Deadline(split[0], split[1]);
        tasks.add(deadline);
        tasksCount++;
        System.out.println(deadline.toString());
        printTotalTasks();

    }
    public static void printTotalTasks(){
        System.out.println("Now you have "+ tasksCount+ " tasks in the list.");
        System.out.println(line);
    }

    public static void addEvent(String input) {
        String[] split = input.split(" /at");
        System.out.println("Got it. I've added this task: ");
        Event event = new Event(split[0], split[1]);
        tasks.add(event);
        tasksCount++;
        System.out.println(event.toString());
        printTotalTasks();
    }
    public static void readFile(){
        try {
            if(Files.exists(Paths.get(filePath))){
                File f = new File(filePath);
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String text = sc.nextLine();
                    if (text.startsWith("[T]")) {
                        String getDescription = text.substring(7);
                        tasks.add(tasksCount, new Todo(getDescription));
                        tasksCount++;
                        if (text.substring(4,5).compareTo("\u2718")==0) {
                            tasks.get(tasksCount - 1).markAsDone();
                        }
                    } else if (text.startsWith("[E]")) {
                        String getDescription = text.substring(7, text.indexOf("(") - 1);
                        String event = text.substring(text.indexOf("(") + 5, text.indexOf(")"));
                        tasks.add(tasksCount, new Event(getDescription, event));
                        tasksCount++;
                        if (text.substring(4,5).compareTo("\u2718")==0) {
                            tasks.get(tasksCount - 1).markAsDone();
                        }
                    } else if (text.startsWith("[D]")) {
                        String description = text.substring(7, text.indexOf("(") - 1);
                        String deadline = text.substring(text.indexOf("(") + 5, text.indexOf(")"));
                        tasks.add(tasksCount,new Deadline(description, deadline));
                        tasksCount++;
                        if (text.substring(4,5).compareTo("\u2718")==0) {
                            tasks.get(tasksCount - 1).markAsDone();
                        }
                    }

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void writeFile(){
        try {
            if (Files.notExists(Paths.get("data/"))) {
                Files.createDirectory(Paths.get("data/"));
            } else if (Files.notExists(Paths.get(filePath))) {
                Files.createFile(Paths.get(filePath));
            }
            FileWriter fw = new FileWriter(filePath);
            for (int i=0; i<tasksCount;i++) {
                fw.write(tasks.get(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error! Unable to write to file");
        }



    }
    public static void main(String[] args){
        helloMessage();
        readFile();
        String input;
        while(true){
            input = scanner.nextLine();
            System.out.println(line);
            if(input.equals("bye")){
                byeMessage();
            } else if(input.equals("list")) {
                printList(tasks);
            } else if(input.startsWith("done")) {
                markTaskAsDone(input);
                writeFile();
            } else if(input.startsWith("todo")){
                addTodo(input);
                writeFile();
            } else if(input.startsWith("deadline")){
                input = input.substring(DEADLINE_LENGTH);
                addDeadline(input);
                writeFile();
            } else if(input.startsWith("event")){
                input = input.substring(EVENT_LENGTH);
                addEvent(input);
                writeFile();
            } else if(input.startsWith("delete")){
                deleteTask(input);
                writeFile();
            } else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }

        }
    }



}