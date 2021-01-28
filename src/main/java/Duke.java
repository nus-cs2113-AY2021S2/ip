import java.util.Scanner;

public class Duke {

    public static void printSeparator() {
        for(int i = 0; i < 40; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void echoMode() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            System.out.print(line + '\n');
            printSeparator();
            line = in.nextLine();
        }
        System.out.print("My cover's blown!\n");
        printSeparator();
    }

    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        printSeparator();
        System.out.print("Greetings, fellow humans!\nI am CI.\nHow may I help you?\n");
        printSeparator();
        echoMode();
    }
}
