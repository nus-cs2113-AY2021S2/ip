import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        startGreetings();
        echo();
        endGreetings();
    }

    public static void startGreetings() {
        String greetings = "-------------------------------------\n"
                         + "Hello! I'm Duke\n"
                         + "What can I do for you?\n\n"
                         + "-------------------------------------";
        System.out.println(greetings);
    }

    public static void endGreetings() {
        String exitStatements = "-------------------------------------\n"
                              + "Bye. Hope to see you again soon!\n"
                              + "-------------------------------------";
        System.out.println(exitStatements);
    }

    public static void echo() {
        ArrayList<String> textList = new ArrayList<String>();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                displayTextList(textList);
            }
            else {
                System.out.println("-------------------------------------");
                System.out.println("added: " + line);
                System.out.println("-------------------------------------");
                textList.add(line);
            }
            line = in.nextLine();
        }
    }

    public static void displayTextList(ArrayList<String> textList) {
        int counter = 0;
        System.out.println("-------------------------------------");
        while (counter < textList.size()) {
            System.out.print((counter+1) + ". ");
            System.out.print(textList.get(counter) + "\n");
            counter ++;
        }
        System.out.println("-------------------------------------");
    }
}
