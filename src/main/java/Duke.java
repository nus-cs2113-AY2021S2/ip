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

        String[] taskList = new String[100];
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;

        String bye = "bye";
        String list = "list";

        while (isSame) {
            for (int i=0 ; i< taskList.length ; i++) {
                String input = sc.nextLine().toLowerCase();

                if (input.equalsIgnoreCase(bye)) {
                    System.out.println("Bye. Hope to see you again soon!");
                    isSame = false;
                    System.exit(0);
                }

                else if (input.equalsIgnoreCase(list)) {
                    for (int j=0 ; j< taskList.length ; j++) {
                        if (taskList[j]==null) {
                            break;
                        }
                        System.out.println(j+1 + ". " + taskList[j]);
                    }
                    i--;
                }

                else {
                    taskList[i] = input;
                    System.out.println("added: " + input);
                    continue;
                }
            }
        }
    }
}
