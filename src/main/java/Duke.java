import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Scanner in = new Scanner(System.in);
    private static int taskNumber = 0;
    private static Task[] t = new Task[100];
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

    public static void markAsDone(int number){
        System.out.println("Nice! I've marked this task as done:");
        t[number-1].markAsDone();
        System.out.println(t[number-1].toString());
        System.out.println(line);
    }
    public static void addTodo(String newTask){
        System.out.println("Got it. I've added this task: ");
        Todo todo = new Todo(newTask);
        t[taskNumber] = todo;
        taskNumber++;
        System.out.println(todo.toString());
        System.out.println("Now you have "+ taskNumber+ " tasks in the list.");
        System.out.println(line);
    }
    public static void addDeadline(String deadline){
        String[] split = deadline.split(" /by");
        System.out.println("Got it. I've added this task: ");
        Deadline day = new Deadline(split[0],split[1]);
        t[taskNumber]=day;
        taskNumber++;
        System.out.println(day.toString());
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
        System.out.println("Now you have "+ taskNumber+ " tasks in the list.");
        System.out.println(line);
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
                int num = Integer.parseInt(input.substring(5));
                markAsDone(num);
            } else if(input.startsWith("todo")){
                input = input.substring(5);
                addTodo(input);
            } else if(input.startsWith("deadline")){
                input = input.substring(8);
                addDeadline(input);
            } else if(input.startsWith("event")){
                input = input.substring(5);
                addEvent(input);
            } else{
                System.out.println("Only todo, done, deadline, event and list commands are accepted");
                System.out.println(line);
            }

        }

    }



}