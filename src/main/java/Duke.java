import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line;
        Scanner in = new Scanner(System.in);

        String[] tasks = new String[100];
        int index = 0;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("---------------------------------------------------------");
        System.out.println(logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------------------------------");

        line = in.nextLine();
        while (!line.equals("bye")){
            System.out.println("---------------------------------------------------------");
            if (line.equals("list")){
                for (int i = 1; i <= index; i++){
                    System.out.println(i + ". " + tasks[i-1]);
                }
            }
            else{
                tasks[index] = line;
                index++;
                System.out.println("added: " + line);
            }
            System.out.println("---------------------------------------------------------");
            line = in.nextLine();
        }
        System.out.println("---------------------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("---------------------------------------------------------");
    }
}
