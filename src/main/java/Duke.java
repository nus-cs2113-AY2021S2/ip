import java.util.Scanner;
import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        boolean isOn = true;
        List<String> list = new ArrayList<String>();

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");

        while(isOn) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if (line.equals("bye")) {
                System.out.println("\tBye fellow coder! Hope to see you again soon!");
                isOn = false;
            } else if (line.equals("list")) {
                int i = 0;
                while (i < list.size()) {
                    int num = i+1;
                    System.out.println("\t" + num + ". " + list.get(i));
                    i++;
                }
            } else {
                System.out.println("\t" + "added: " + line);
                list.add(line);
            }
        }
    }
}
