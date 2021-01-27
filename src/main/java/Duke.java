import java.util.Scanner;

public class Duke {
    static String sectionDivider = "____________________________________________________________";
    static int numberOfTasks = 0;
    static Task tasks[] = new Task[100];

    public static void addTask(String taskDescription){
        System.out.println("\tadded: " + taskDescription);
        Task t = new Task(taskDescription);
        tasks[numberOfTasks] = t;
        numberOfTasks++;
    }

    public static void main(String[] args) {
        String greeting = "\t" + sectionDivider + "\n"
                + "\tHello! I'm Duke. \n"
                + "\tWhat can I do for you? \n"
                + "\t" + sectionDivider;
        String sign_off = "\t" + sectionDivider + "\n"
                + "\tBye. Hope to see you again soon!\n"
                + "\t" + sectionDivider;

        System.out.println(greeting);

        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while(line.toLowerCase().equals("bye") != true){
            System.out.println("\t" + sectionDivider);
            if(line.equals("list")){
                // Lists out the things under tasks.
                System.out.println("\t Here are the tasks in your list:");
                for(int i= 0; i!=numberOfTasks; i++){
                    String output = String.format("%02d. [%s] %s", i+1, tasks[i].getStatusIcon(), tasks[i].getDescription());
                    System.out.println("\t" + output);
                }
            }else if (line.contains("done")){
                // Marks one of the tasks as done.
                System.out.println("\t Nice! I've marked this task as done: ");
                String[] input = line.split(" ");
                int taskIndex = Integer.parseInt(input[1]);
                tasks[taskIndex-1].markAsDone();
                String output = String.format("%02d. [%s] %s", taskIndex, tasks[taskIndex-1].getStatusIcon(), tasks[taskIndex-1].getDescription());
                System.out.println("\t" + output);
            }else{
                addTask(line);
            }
            System.out.println("\t" + sectionDivider);
            line = in.nextLine();
        }
        System.out.println(sign_off);
    }


}
