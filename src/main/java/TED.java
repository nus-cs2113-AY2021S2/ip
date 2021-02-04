import java.util.ArrayList;
import java.util.Scanner;


public class TED {
    public static void main(String[] args) {

        String line;
        String elem;
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner in = new Scanner(System.in);

        System.out.println(
                "\t____________________________________________________________\n" +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n");

        while (true) {

            line = in.nextLine();

            if (line.startsWith("deadline")) {
                elem = line.substring(line.indexOf(" ")+1, line.indexOf("/")-1);
                Deadline t = new Deadline(elem, line.substring(line.indexOf("/")+4));
                tasks.add(t);
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tGot it. I've added this task: \n" +
                        "\t" + t + "\n" +
                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
                        "\t____________________________________________________________");
            } else if (line.charAt(0) == 'e') {
                elem = line.substring(line.indexOf(" ")+1, line.indexOf("/")-1);
                event t = new event(elem, line.substring(line.indexOf("/")+4));
                tasks.add(t);
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tGot it. I've added this task: \n" +
                        "\t" + t + "\n" +
                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
                        "\t____________________________________________________________");
            } else if (line.startsWith("todo")) {
                elem = line.substring(5);
                todo t = new todo(elem);
                tasks.add(t);
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tGot it. I've added this task: \n" +
                        "\t" + t + "\n" +
                        "\tNow you have " + tasks.size() + " tasks in the list.\n" +
                        "\t____________________________________________________________");
            } else if (line.equals("list")) {
                System.out.print(
                        "\t____________________________________________________________\n" +
                        "\tHere are the tasks in your list:\n");
                int number = 1;
                for (Task t : tasks) {
                    System.out.print("\t" + number + "." + t + "\n");
                    number++;
                }
                System.out.print(
                        "\t____________________________________________________________\n");
            } else if (line.startsWith("done")) {
                int num = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                tasks.get(num-1).getDone(true);
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done: \n" + "\t" +
                        tasks.get(num-1)  + "\n" +
                        "\t____________________________________________________________\n");
            } else {
                System.out.print(
                        "\t____________________________________________________________\n" +
                        "\tBye. Hope to see you again soon!\n" +
                        "\t____________________________________________________________");
                System.exit(0);
            }
        }
    }
}
