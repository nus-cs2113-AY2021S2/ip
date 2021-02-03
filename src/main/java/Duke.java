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
                    System.out.println("[X]" + anArray[i-1].getDescription() + "\n");
                    System.out.println("____________________________________________________________\n");
                }
                else if (!line.equals("")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("added: " + line);
                    Task t = new Task(line);
                    anArray[index] = t;
                    index++;
                    System.out.println("____________________________________________________________\n");
                }
                line = in.nextLine();
            }
            while(line.equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < index; i++){
                    System.out.println(i+1 + ".[" + anArray[i].getStatusIcon() + "] " + anArray[i].getDescription() + "\n");
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
