import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");
        boolean echoFlag = true;
        while(echoFlag) {
            Scanner in = new Scanner(System.in);
            String line = in.nextLine();
            if(line.equals("bye")){
                echoFlag = false;
            }
            else {
                System.out.println(line);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
