import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;



public class Mark {
    public static void main(String[] args) {
        String line;
        ArrayList<String> task = new ArrayList<String>(999);
        int counter = 0;
        int count = 0;

        Scanner in = new Scanner(System.in);

        System.out.println(
                "\t____________________________________________________________\n" +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
        String[] list = new String[999];

        while (true) {
            line = in.nextLine();
            list[counter] = line;
            if (line.equals("bye")) {
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tBye. Hope to see you again soon!\n" +
                        "\t____________________________________________________________"
                );
                System.exit(0);
            }else if (line.equals("list")) {
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tHere are the tasks in your list:\n");
                int number = 1;
                for (String item : Arrays.copyOf(list, counter)) {
                    boolean chk = task.contains(item);
                    if(chk) {
                        System.out.println("\t" + number + ".[\u2718] " + item + "\n");
                    }else {
                        System.out.println("\t" + number + ".[ ] " + item + "\n");
                    }
                    number++;
                }
                System.out.println(
                        "\t____________________________________________________________\n");
                continue;
            }else if (line.substring(0, line.indexOf(" ")).equals("done")){
                int num = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                task.add(list[num-1]);
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done: \n" + "\t" +
                        "[\u2718] " + list[num-1]  + "\n" +
                                "\t____________________________________________________________\n"
                );
                continue;
            }else{
                System.out.println("\t____________________________________________________________\n" +
                        "\tadded: " + line + "\n" +
                        "\t____________________________________________________________\n");
            }
            counter++;
        }
    }
}
