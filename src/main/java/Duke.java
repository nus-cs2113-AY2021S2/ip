import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();

        while (true){
            String line = in.nextLine();

            if (line.equals("list")){
                System.out.println("Here are the tasks in your list: ");
                int index = 1;
                for (Task t : tasks){
                    System.out.println((index++)+".[" + t.getStatusIcon() + "] "+t.description);
                }
                continue;
            }
            else if (line.contains("done")){
                System.out.println("Nice! I've marked this task as done: ");
                String[] words = line.split(" ");
                int doneIndex = Integer.parseInt(words[1]);
                Task doneTask = tasks.get(doneIndex-1);
                doneTask.markAsDone();
                System.out.println("[" + doneTask.getStatusIcon() + "] "+doneTask.description);
            }
            else if (line.equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________\n");
                break;
            }
            else {
                System.out.println("added: " + line);
                tasks.add(new Task(line));
                continue;
            }
        }
    }
}
