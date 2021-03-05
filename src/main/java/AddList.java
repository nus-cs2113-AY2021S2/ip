import java.util.Scanner;
import java.util.Arrays;

public class AddList {
    public static void main(String[] args) {
        String line;
        int counter = 0;

        Scanner in = new Scanner(System.in);

        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
        String[] list = new String[999];

        while (true) {
            line = in.nextLine();
            list[counter] = line;
            if (line.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________");
                System.exit(0);
            }else if (line.equals("list")) {
                System.out.println("    ____________________________________________________________\n");
                int number = 1;
                for (String item : Arrays.copyOf(list, counter)) {
                    System.out.println("\t" + number +". " + item + "\n");
                    number++;
                }
                System.out.println("    ____________________________________________________________\n");
                continue;
            }else{
                System.out.println("   ____________________________________________________________\n" +
                        "   added: " + line + "\n" +
                        "   ____________________________________________________________\n");
            }
            counter++;
        }
    }
}
