import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String border = "    ____________________________________________________________\n";
        String indent = "     ";
        String lineLowerCase;
        String line;

        Scanner in = new Scanner(System.in);

        List<String> tasks = new ArrayList<>();

        System.out.println("Hello there! This is\n" + logo + "How may I help you ?\n");

        do {
            line = in.nextLine();
            lineLowerCase = line.toLowerCase();
            switch(lineLowerCase) {
            case "bye":
                System.out.print(border + indent + "Good Bye. Hope to see you again soon!" + "\n" + border);
                break;
            case "list":
                int index = 0;

                System.out.print(border);
                for (String task : tasks) {
                    ++index;
                    System.out.print(indent + index + ". " + task + "\n");
                }
                System.out.print(border);
                break;
            default:
                tasks.add(line);
                System.out.print(border + indent + "added: " + line + "\n" + border);
                break;
            }
        } while (!lineLowerCase.equals("bye"));
    }
}
