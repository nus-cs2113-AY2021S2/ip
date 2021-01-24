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
        System.out.println("Hello, I'm Duke!\nWhat can i do for you?\n");
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
                    System.out.println("Enter 'list' to view task list and mark task as done.");
                    String task;
                    Scanner b = new Scanner(System.in);
                    int index = 0;
                    task = b.nextLine();
                    String[] tasks = new String[100];
                    while (true) {
                        if (task.equalsIgnoreCase("bye")) {
                            System.out.println("Bye, hope to see you again again soon!");
                            break;
                        }
                        else if (task.equals("list")) {
                            System.out.print("Here are the tasks in your list: \n");
                            for (int i = 0; i < index; i++) {
                                System.out.println((i + 1) + ": " + tasks[i]);
                            }
                            System.out.println("Please enter the task number to be mark as done: ");
                            Scanner c = new Scanner(System.in);
                            int taskNo = c.nextInt();
                            Task t = new Task (tasks[taskNo-1]);
                            t.markAsDone();
                            break;
                        }
                        else{
                            System.out.println("Added: " + task.trim());
                            tasks[index] = task;
                            index++;
                        }
                        task = b.nextLine();
                    }
                    break;
                case -1:

                    run = false;
                default:
                    break;
            }
        }
    }
}
