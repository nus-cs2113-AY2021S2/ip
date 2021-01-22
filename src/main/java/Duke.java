import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;
        String bye = "bye";
        while (isSame) {
            String input = sc.nextLine().toLowerCase();
            if (input.equalsIgnoreCase(bye)){
                System.out.println("Bye. Hope to see you again soon!");
                isSame = false;
                System.exit(0);
            }
            else {
                System.out.println(input);
                continue;
            }
        }
    }
}
