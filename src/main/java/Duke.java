import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
<<<<<<< HEAD
        printDashes();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDashes();

        Scanner in = new Scanner(System.in);
        String line;

        while(true){
            line = in.nextLine();
            printDashes();

            if(line.equals("bye")){
                break;
            }

            System.out.println(line);
            printDashes();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }

    private static void printDashes(){
        System.out.println("____________________________________________________________");
=======
>>>>>>> parent of 215ebfb... Level 0 first attempt
    }
}
