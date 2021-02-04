import java.util.ArrayList;
import java.util.Scanner;



public class Duke {
    private static final String divider = "_______________________________\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void addTask(String taskName){
        Task currentTask = new Task(taskName);
        tasks.add(currentTask);
        System.out.println(divider + "added " + currentTask.description + "\n" + divider);
    }

    private static void listTask(){
        System.out.println(divider);
        System.out.println("Here are the tasks in your list:");
        for(int i=0; i<tasks.size(); ++i){
            System.out.println((i+1) + "." +
                    tasks.get(i).toString()+ "\n");
        }
        System.out.println(divider);
    }

    private static void markTaskDone(int index){
        Task currentTask = tasks.get(index-1);
        currentTask.markAsDone();
        System.out.println(divider + "Nice! I've marked this task as done:"+
                "\n["+ currentTask.getStatusIcon()+ "]" + currentTask.description +
                "\n" + divider);
    }

    public static void addToDo(String input){
        String taskName = input.substring(5);
        Task currentTask = new ToDo(taskName);
        tasks.add(currentTask);
        System.out.println(divider + "Got it. I've added this task:\n" +
                currentTask.toString() + "\n");
        System.out.println("Now you have "+tasks.size()+" tasks in your list.\n" + divider);
    }

    public static void addDeadline(String input){
        int deadlineIndex = input.indexOf("/");
        String description = input.substring(9, deadlineIndex);
        String deadline = input.substring(deadlineIndex+4);
        Task currentTask = new Deadline(description, deadline);
        tasks.add(currentTask);
        System.out.println(divider + "Got it. I've added this task:\n" +
                currentTask.toString() + "\n");
        System.out.println("Now you have "+tasks.size()+" tasks in your list.\n");
    }

    public static void addEvent(String input){
        int timeIndex = input.indexOf("/");
        String description = input.substring(6, timeIndex);
        String timeDetails = input.substring(timeIndex + 4);
        Task currentTask = new Event(description, timeDetails);
        tasks.add(currentTask);
        System.out.println(divider + "Got it. I've added this task:\n" +
                currentTask.toString() + "\n");
        System.out.println("Now you have " + tasks.size() + " tasks in your list.\n" + divider);

    }

    private static void exitDuke(){
        System.out.println("Bye. Hope to see you again soon!");
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        String input;

        while (in.hasNext()) {
            input = in.nextLine();
            if (input.equals("list")) {
                listTask();
            }else if(input.contains("done")) {
                int index = Integer.parseInt(input.substring(5));
                markTaskDone(index);
            }else if(input.contains("todo")){
                addToDo(input);
            }else if(input.contains("deadline")){
                addDeadline(input);
            }else if(input.contains("event")){
                addEvent(input);
            }else if(input.equals("bye")){
                exitDuke();
                break;
            }else{
                addTask(input);
            }
        }
        in.close();

    }
}
