import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    static final int MAX_NO_OF_TASKS = 100;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Here are the things that i can do: ");
        System.out.println("1. Echo");
        System.out.println("2. Add task to your list & Mark task as done");
        System.out.println("-1: Quit Duke");

        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run){
            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Please input something and I will repeat it back to you:)");
                    Scanner a = new Scanner(System.in);
                    String sentence = a.nextLine();
                    while (true) {
                        if (sentence.equalsIgnoreCase("bye")){
                            break;
                        }
                        else{
                            System.out.println(sentence);
                            sentence = a.nextLine();
                        }
                    }
                    System.out.println("Thank you for using echo, you can now choose other function:)");
                    break;
                case 2:
                    System.out.println("Please enter the task you want to add in to the task list.");
                    Task[] tasks = new Task[MAX_NO_OF_TASKS];
                    int index = 0;
                    String task;
                    Scanner b = new Scanner(System.in);
                    task = b.nextLine();
                    while (true) {
                        if (task.equalsIgnoreCase("bye")) {
                            System.out.println("Bye, hope to see you again again soon! You can now choose other function:)");
                            break;
                        }
                        else if (task.equals("list")) {
                            for (int i=0; i<index; i++){
                                System.out.println((i+1) + "." + tasks[i].toString());
                            }
                        }
                        else if (task.contains("done")) {
                            String taskNo = task.substring(task.length()-1);
                            int new_taskNo = Integer.parseInt(taskNo);
                            tasks[new_taskNo-1].setTaskStatus(true);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(tasks[new_taskNo-1].toString());
                        }
                        else if (task.contains("todo")){
                            String taskName = task.substring(5);
                            tasks[index] = new Todo(taskName, 'T');
                            index = addTaskMessage(index, tasks[index]);
                        }
                        else if (task.contains("deadline")){
                            int slash = task.indexOf("/");
                            String taskName1 = task.substring(9, slash-1);
                            String by = task.substring(slash+3);
                            tasks[index] = new Deadline(taskName1,'D', by);
                            index = addTaskMessage(index, tasks[index]);
                        }
                        else if (task.contains("event")) {
                            int slash_sign = task.indexOf("/");
                            String taskName2 = task.substring(6, slash_sign - 1);
                            String at = task.substring(slash_sign + 3);
                            tasks[index] = new Event(taskName2, 'E', at);
                            index = addTaskMessage(index, tasks[index]);
                        }
                        else{
                            tasks[index] = new Task(task);
                            index = addTaskMessage(index, tasks[index]);
                        }
                        task = b.nextLine();
                        }
                case -1:
                    run = false;
            }
        }
    }
    private static int addTaskMessage(int index, Task tasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(tasks.toString());
        index++;
        System.out.println("Now you have " + index + " tasks in the list. ");
        return index;
        }
    }