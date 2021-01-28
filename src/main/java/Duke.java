import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static void printSeparator() {
        for(int i = 0; i < 40; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void exitMethod() {
        System.out.print("My cover's blown!\n");
        printSeparator();
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
        exitMethod();
    }

    public static void listMode() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] list = new String[100];
        int listCount = 0;
        while (!line.equals("bye")){
            if(line.equals("list")) {
                for(int i = 0; i < listCount; i++) {
                    System.out.print((i+1) + ". " + list[i] + '\n');
                }
            } else {
                list[listCount++] = line;
                System.out.print("added: " + line + '\n');
            }
            printSeparator();
            line = in.nextLine();
        }
        exitMethod();
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
        //echoMode();
        listMode();
    }
}
