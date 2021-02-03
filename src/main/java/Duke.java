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
                    "[" + tasks.get(i).getStatusIcon()+"] "+
                    tasks.get(i).description+ "\n");
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
            }else if(input.contains("done")){
                int index = Integer.parseInt(input.substring(5));
                markTaskDone(index);
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
