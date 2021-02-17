package duke.command;
import java.io.File;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
    private static final String FILE_PATH = "tasks.txt";

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
        int count=1;
        System.out.println("Here are the tasks in your list: ");
        for(int i=0; i<tasksCount;i++) {
            System.out.println(count+"."+tasks.get(i).toString());
            count++;
        }
        System.out.println(line);
    }
    public static void writeFile(){
        try{
            FileWriter fw = new FileWriter(FILE_PATH,false);
            for(int i=0; i<tasksCount;i++){
                fw.write(tasks.get(i).toString());
            }
            fw.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void readFile() throws IOException {
        try{
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner scanner = new Scanner(f); // create a Scanner using the File as the source
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch(FileNotFoundException f){
            Files.createFile(Paths.get("tasks.txt"));
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DELETE_LENGTH));
            if (newNum >= tasksCount || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Noted. I've removed this task:" + "\n"+ tasks.get(newNum - 1));
            tasks.remove(newNum-1);
            tasksCount--;
            System.out.println("Now you have "+ tasksCount +" tasks in the list.");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
            System.out.println(line);
        }
    }

    public static void markAsDone(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DONE_LENGTH));
            if (newNum >= tasksCount || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(newNum-1).markAsDone();
            System.out.println(tasks.get(newNum-1).toString());
            System.out.println(line);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The task number is invalid!");
            System.out.println(line);
        }

    }

    public static void addTodo(String newTask){
        try {
            newTask = newTask.substring(TODO_LENGTH);
            System.out.println("Got it. I've added this task: ");
            Todo todo = new Todo(newTask);
            tasks.add(todo);
            tasksCount++;
            System.out.println(todo.toString());
            printTotalTasks();
            writeFile();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(line);
        }
    }
    public static void addDeadline(String input){
        String[] split = input.split(" /by");
        System.out.println("Got it. I've added this task: ");
        Deadline deadline = new Deadline(split[0],split[1]);
        tasks.add(deadline);
        tasksCount++;
        System.out.println(deadline.toString());
        printTotalTasks();
        writeFile();

    }
    public static void printTotalTasks(){
        System.out.println("Now you have "+ tasksCount+ " tasks in the list.");
        System.out.println(line);
    }

    public static void addEvent(String input){
        String[] split = input.split(" /at");
        System.out.println("Got it. I've added this task: ");
        Event event = new Event(split[0],split[1]);
        tasks.add(event);
        tasksCount++;
        System.out.println(event.toString());
        printTotalTasks();
        writeFile();
    }

    public static void main(String[] args) throws IOException {
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
                markAsDone(input);
            } else if(input.startsWith("todo")){
                addTodo(input);
            } else if(input.startsWith("deadline")){
                input = input.substring(DEADLINE_LENGTH);
                addDeadline(input);
            } else if(input.startsWith("event")){
                input = input.substring(EVENT_LENGTH);
                addEvent(input);
            } else if(input.startsWith("delete")){
                deleteTask(input);
            } else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }

        }

    }



}