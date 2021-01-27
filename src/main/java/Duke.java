import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String getStatus(boolean isDone) {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }
    public static void main(String[] args) {
/*        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> lists = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);

        while (true) {

            String word = input.nextLine();

            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println(i+1 + ".[" + getStatus (lists.get(i).isDone) +"] "+ lists.get(i).description);
                }
            } else if (word.contains("done")){
                String numStr = word.substring(word.indexOf(" ")+1);
                int num = Integer.parseInt(numStr);
                System.out.println("Nice! I've marked this task as done:" );
                Task d = new Task (lists.get(num-1).description);
                lists.get(num-1).isDone = d.markAsDone();

            }
            else {
                Task t = new Task(word);
                lists.add(t);
                System.out.println("added: " + word);
            }
        }
    }
}
