import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] list = new Task[100];
        int taskInList = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");
        Scanner in = new Scanner(System.in);
        while (true) {
            String line;
            String[] arr;
            line = in.nextLine();
            arr = line.split(" ");
            if (line.equals("bye")) {
                break;

            } else if (line.equals("list")) {
                int i = 0;
                System.out.println("Here are the tasks in your list:");
                while (list[i] != null) {
                    if (list[i].isDone()) {
                        System.out.println(i+1 + ".[" + list[i].getType() + "][\u2713] " + list[i].getDescription());
                    } else {
                        System.out.println(i+1 + ".[" + list[i].getType() + "][\u2718] " + list[i].getDescription());
                    }
                    i++;
                }

            } else if (line.split(" ")[0].equals("done")) {
                int index = Integer.parseInt(line.split(" ")[1]) - 1;
                list[index].setDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[\u2713] " + list[index].getDescription());

            } else {
                System.out.println("Got it. I've added this task: ");
                int i = 0;
                while (list[i] != null) {
                    i++;
                }
                int indexOfSpace = line.indexOf(" ");
                String type = line.substring(0,indexOfSpace);

                if (type.equals("todo")){
                    list[i] = new Todo(line.substring(indexOfSpace+1));
                    System.out.println("  [T][\u2718] " + line.substring(indexOfSpace+1) );
                } else {
                    int indexOfSlash = line.indexOf("/");
                    String item = line.substring(indexOfSpace + 1, indexOfSlash - 1);
                    String extra = line.substring(indexOfSlash + 4);

                    if (arr[0].equals("deadline")) {
                        list[i] = new Dateline(item, "by: " + extra);
                        System.out.println("  [D][\u2718] " + item + " (by: " + extra +")" );
                    } else if (arr[0].equals("event")) {
                        list[i] = new Event(item, "at: " +extra);
                        System.out.println("  [E][\u2718] " + item + " (at: " + extra +")" );
                    }
                }
                taskInList++;
                System.out.println("Now you have " + String.valueOf(taskInList) +" tasks in the list.");
            }
        }

        System.out.println("____________________________________________________________\n");
        System.out.println("Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
//todo Owen: Answer CS2113 tutorial question
//deadline weekly CS2113 quiz /by Monday 9pm
//event CS2113 lecture /at Friday 4 to 6pm
//done 1
// list