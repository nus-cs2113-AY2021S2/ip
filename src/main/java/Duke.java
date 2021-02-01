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
        String[] anArray = new String[100];
        while (!line.equals("bye")) {
            while(!line.equals("list")) {
                if (!line.equals("")) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println("added: " + line);
                    anArray[index] = line;
                    index++;
                    System.out.println("____________________________________________________________\n");
                }
                line = in.nextLine();
            }
            while(line.equals("list")){
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < index; i++){
                    System.out.println(i+1 + ": " + anArray[i] + "\n");
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
