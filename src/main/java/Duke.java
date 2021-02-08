import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________\n");
        System.out.println(" Hello! I'm Duke\n" +
                " What can I do for you?");
        System.out.println("____________________________________________________________\n");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        int index = 0;
        Task[] anArray = new Task[100];
        while (!line.equals("bye")) {
            while(!line.equals("list")) {
                if (line.contains("done")){
                    int i=Integer.parseInt(line.substring(line.length()-1));
                    anArray[i-1].markAsDone();
                    System.out.println("____________________________________________________________\n");
                    System.out.println("Nice! I've marked this task as done:\n ");
                    System.out.println(anArray[i-1].toString() + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else if (line.contains("todo")) {
                    System.out.println("____________________________________________________________\n");
                    Todo t = new Todo(line.replace("todo ",""));
                    anArray[index] = t;
                    index++;
                    System.out.println("Got it. I've added this task:\n");
                    System.out.println(t.toString() + "\n");
                    System.out.println("Now you have " + index + " tasks in the list.\n");
                    System.out.println("____________________________________________________________\n");
                }
                else if (line.contains("event")) {
                    System.out.println("____________________________________________________________\n");
                    line = line.replace("event", "");
                    String[] parts = line.split("/at");
                    Event t = new Event(parts[0].trim(), parts[1].trim());
                    anArray[index] = t;
                    index++;
                    System.out.println("Got it. I've added this task:\n");
                    System.out.println(t.toString() + "\n");
                    System.out.println("Now you have " + index + " tasks in the list.\n");
                    System.out.println("____________________________________________________________\n");
                }
                else if (line.contains("deadline")) {
                    System.out.println("____________________________________________________________\n");
                    line = line.replace("deadline", "");
                    String[] parts = line.split("/by");
                    Deadline t = new Deadline(parts[0].trim(), parts[1].trim());
                    anArray[index] = t;
                    index++;
                    System.out.println("Got it. I've added this task:\n");
                    System.out.println(t.toString() + "\n");
                    System.out.println("Now you have " + index + " tasks in the list.\n");
                    System.out.println("____________________________________________________________\n");
                }
                line = in.nextLine();
            }
            while(line.equals("list")){
                System.out.println("____________________________________________________________\n");
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < index; i++){
                    System.out.println(i+1 + ". " + anArray[i].toString() + "\n");
                }
                System.out.println("____________________________________________________________\n");
                line = "";

            }

        }
        System.out.println("____________________________________________________________\n");
        System.out.println(" Bye. Hope to see you again soon!\n");
        System.out.println("____________________________________________________________\n");
    }
}
