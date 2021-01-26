import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
/*        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<String> lists = new ArrayList<String>();

        while (true) {
            Scanner input = new Scanner(System.in);

            String word = input.nextLine();

            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println(i+1 + ". " + lists.get(i));
                }
            } else {
                lists.add(word);
                System.out.println("added: " + word);
            }
        }
    }
}
