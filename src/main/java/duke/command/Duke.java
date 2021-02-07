package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.Scanner;
public class Duke {
    private static final int TASK_MAX_SIZE = 100;
    private static final int DONE_LENGTH = 5;
    private static final int EVENT_LENGTH = 5;
    private static final int TODO_LENGTH = 5;
    private static final int DEADLINE_LENGTH = 8;

    private static Scanner in = new Scanner(System.in);
    private static int taskNumber = 0;
    private static Task[] t = new Task[TASK_MAX_SIZE];
    private static String line = "*********************************************";

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

    public static void printList(Task[]t){
        int count=1;
        System.out.println("Here are the tasks in your list: ");
        for(int i=0; i<taskNumber;i++) {
            System.out.println(count+"."+t[i].toString());
            count++;
        }
        System.out.println(line);
    }

    public static void markAsDone(String input){
        try {
            int newNum = Integer.parseInt(input.substring(DONE_LENGTH));
            if (newNum >= taskNumber || newNum < 0) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println("Nice! I've marked this task as done:");
            t[newNum - 1].markAsDone();
            System.out.println(t[newNum - 1].toString());
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
            t[taskNumber] = todo;
            taskNumber++;
            System.out.println(todo.toString());
            printTotalTasks();
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            System.out.println(line);
        }
    }
    public static void addDeadline(String deadline){
        String[] split = deadline.split(" /by");
        System.out.println("Got it. I've added this task: ");
        Deadline day = new Deadline(split[0],split[1]);
        t[taskNumber]=day;
        taskNumber++;
        System.out.println(day.toString());
        printTotalTasks();

    }
    public static void printTotalTasks(){
        System.out.println("Now you have "+ taskNumber+ " tasks in the list.");
        System.out.println(line);
    }

    public static void addEvent(String event){
        String[] split = event.split(" /at");
        System.out.println("Got it. I've added this task: ");
        Event time = new Event(split[0],split[1]);
        t[taskNumber]=time;
        taskNumber++;
        System.out.println(time.toString());
       printTotalTasks();
    }

    public static void main(String[] args) {
        helloMessage();
        String input;
        while(true){
            input = in.nextLine();
            System.out.println(line);
            if(input.equals("bye")){
                byeMessage();
            } else if(input.equals("list")) {
                printList(t);
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
            } else{
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println(line);
            }

        }

    }



}