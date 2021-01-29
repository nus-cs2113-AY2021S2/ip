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
                    System.out.println("Enter 'list' to view your task list.");
                    TaskList tl = new TaskList();
                    String task;
                    Scanner b = new Scanner(System.in);
                    int index = 0;
                    task = b.nextLine();
                    while (true) {
                        if (task.equalsIgnoreCase("bye")) {
                            System.out.println("Bye, hope to see you again again soon! You can now choose other function:)");
                            break;
                        }
                        else if (task.equals("list")) {
                            tl.showTaskList();
                        }
                        else if (task.contains("done")) {
                            String taskNo = task.substring(task.length()-1);
                            int c = Integer.parseInt(taskNo);
                            String type = tl.tasks[c-1].substring(0,3);
                            String name = tl.tasks[c-1].substring(6);
                            tl.markAsDone(c, name, type);
                        }
                        else if (task.contains("todo")){
                            String taskName = task.substring(4);
                            tl.toDo(taskName);
                        }
                        else if (task.contains("deadline")){
                            int slash = task.indexOf("/");
                            String taskName1 = task.substring(9, slash-1);
                            String by = task.substring(slash+3);
                            tl.deadline(taskName1, by);
                        }
                        else if (task.contains("event")){
                            int slash_sign = task.indexOf("/");
                            String taskName2 = task.substring(6, slash_sign-1);
                            String at = task.substring(slash_sign+3);
                            tl.event(taskName2, at);
                        }
                        else{
                            tl.addTask(task);
                        }
                        task = b.nextLine();
                    }
                    break;
                case -1:
                    run = false;
            }
        }
    }
}
