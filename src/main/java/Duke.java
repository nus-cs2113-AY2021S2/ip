import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printDashes();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printDashes();

        Scanner in = new Scanner(System.in);
        String line;
        String[] tasks = new String[100];
        int taskCount = 0;

        while(true){
            line = in.nextLine();
            printDashes();

            if(line.equals("bye")){
                break;
            }

            if(line.equals("list")){
                for(int i=0; i<taskCount; ++i){
                    System.out.printf("%d. %s\n", i+1, tasks[i]);
                }
            }else{
                tasks[taskCount] = line;
                ++taskCount;
                System.out.println("added: " + line);
            }

            printDashes();
        }

        System.out.println("Bye. Hope to see you again soon!");
        printDashes();
    }

    private static void printDashes(){
        System.out.println("____________________________________________________________");
    }
}
