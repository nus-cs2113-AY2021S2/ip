import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");

        int itemCount = 0;
        String[] toDoList = new String[100];
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (true) {
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                boolean hasNewLine = false;
                int count = 1;
                String stringToPrint = "";
                for (String item : toDoList) {
                    if (item == null) {
                        break;
                    }
                    if (hasNewLine) {
                        stringToPrint += "\n";
                    }
                    hasNewLine = true;
                    stringToPrint += (Integer.toString(count) + ": " + item);
                    count += 1;
                }
                printWithBorder(stringToPrint);
            } else {
                toDoList[itemCount] = line;
                itemCount += 1;
                printWithBorder("I have added: " + line);
            }
            line = in.nextLine();
        }
        printWithBorder("Sad to see you go! ): See you soon!");
    }
    
    public static void printWithBorder(String line) {
        System.out.print("___________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("___________________________________________________\n");
    }
}