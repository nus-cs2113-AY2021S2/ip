import java.awt.*;
import java.util.List;
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
        List<Task> list = new ArrayList<Task>();

        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("");

        while(isOn) {
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            String[] part = line.split("(?<=\\D)(?=\\d)");
            if (line.equals("bye")) {
                System.out.println("\tBye fellow coder! Hope to see you again soon!");
                isOn = false;
            } else if (line.equals("list")) {
                int i = 0;
                while (i < list.size()) {
                    int num = i+1;
                    System.out.println("\t" + num + ". " + list.get(i).listTask());
                    i++;
                }
            } else if (part[0].equals("done ")) {
                int index = Integer.parseInt(part[1]) - 1;
                list.get(index).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t\t" + list.get(index).listTask());
            } else {
                System.out.println("\t" + "added: " + line);
                list.add(new Task(line));
                //System.out.println(part[0]);
            } 
        }
    }
}
