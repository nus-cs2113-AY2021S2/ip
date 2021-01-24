import java.util.Scanner;
import java.util.Arrays;

public class Duke {
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
        System.out.println("3. View task list and mark task as done");
        System.out.println("-1: Quit Duke");

        TaskList tl = new TaskList();
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
                    System.out.println("Enter 'list' to view your task list.");
                    String task;
                    Scanner b = new Scanner(System.in);
                    int index = 0;
                    task = b.nextLine();
                    String[] tasks = new String[100];
                    while (true) {
                        if (task.equalsIgnoreCase("bye")) {
                            System.out.println("Bye, hope to see you again again soon! You can now choose other function:)");
                            break;
                        }
                        else if (task.equals("list")) {
                            tl.showTaskList();
                        }
                        else{
                            tl.addTask(task);
                        }
                        task = b.nextLine();
                    }
                    break;
                case 3:
                    tl.showTaskList();
                    System.out.println("Please enter the task number to be marked as done: ");
                    Scanner c = new Scanner(System.in);
                    int taskNo = c.nextInt();
                    Task t = new Task (tl.tasks[taskNo-1]);
                    t.markAsDone();
                    break;
                case -1:
                    run = false;
                default:
                    break;
            }
        }
    }
}
