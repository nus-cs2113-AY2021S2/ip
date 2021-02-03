import java.util.Scanner;

public class Duke {
    static String sectionDivider = "____________________________________________________________";
    public static void greetUser(){
        String greeting = "\t" + sectionDivider + "\n"
                + "\tHello! I'm Duke. \n"
                + "\tWhat can I do for you? \n"
                + "\t" + sectionDivider;
        System.out.println(greeting);
    }
    public static void signOff(){
        String sign_off = "\t" + sectionDivider + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + sectionDivider;
        System.out.println(sign_off);
    }

    static int numberOfTasks = 0;
    static Task tasks[] = new Task[100];

    public static void addTask(String taskDescription){
        System.out.println("\tadded: " + taskDescription);
        taskDescription = taskDescription.toLowerCase();
        Task t;
        if (taskDescription.contains("todo")) {
            t = new Todo(taskDescription.substring(5));
        } else if (taskDescription.contains("deadline")) {
            // Adds task as a Deadline
            taskDescription.replace("deadline ", "");
            int byIndex = taskDescription.indexOf("/by ");
            t = new Deadline(taskDescription.substring(9, byIndex-1), taskDescription.substring(byIndex+4));
        } else {
            // Adds task as an Event.
            taskDescription.replace("event ", "");
            int atIndex = taskDescription.indexOf("/at ");
            t = new Event(taskDescription.substring(6, atIndex-1), taskDescription.substring(atIndex+4));
        }
        tasks[numberOfTasks] = t;
        numberOfTasks++;
    }

    public static void markTaskAsDone(String taskDescription){
        // Marks one of the tasks as done.
        System.out.println("\t Nice! I've marked this task as done: ");
        String[] input = taskDescription.split(" ");
        int taskIndex = Integer.parseInt(input[1]);
        tasks[taskIndex-1].markAsDone();
        String output = String.format("%02d. [%s] %s", taskIndex, tasks[taskIndex-1].getStatusIcon(), tasks[taskIndex-1].getDescription());
        System.out.println("\t" + output);
    }

    public static void listTasks(){
        // Lists out the things under tasks.
        System.out.println("\t Here are the tasks in your list:");
        for(int i= 0; i!=numberOfTasks; i++){
            String output = String.format("%02d. %s", i+1, tasks[i]);
            System.out.println("\t" + output);
        }
    }

    public static void main(String[] args) {
        greetUser();

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + sectionDivider);
            if(line.equals("list")){
                listTasks();
            }else if (line.toLowerCase().contains("done")){
                markTaskAsDone(line);
            }else{
                addTask(line);
            }
            System.out.println("\t" + sectionDivider);
            line = in.nextLine();
        }

        signOff();
    }
}
