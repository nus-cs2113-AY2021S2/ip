import java.util.Arrays;
import java.util.Scanner;



public class Mark {
    public static void main(String[] args) {
        String line;
        int counter = 0;

        Scanner in = new Scanner(System.in);

        System.out.println(
                "\t____________________________________________________________\n" +
                "\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n");
        String[] list = new String[999];
        Task[] tlist = new Task[999];

        while (true) {
            line = in.nextLine();
            Task t = new Task(line);
            tlist[counter] = t;
            list[counter] = line;
            if (line.equals("bye")) {
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tBye. Hope to see you again soon!\n" +
                        "\t___________________________________________________________");
                System.exit(0);
            }else if (line.equals("list")) {
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tHere are the tasks in your list:\n");
                int number = 1;
                for (int i = 0; i < counter; i++) {
                        System.out.println("\t" + number + ".[" + tlist[i].getStatusIcon() + "] " + list[i] + "\n");
                    number++;
                }
                System.out.println(
                        "\t____________________________________________________________\n");
                continue;
            }else if (line.substring(0, line.indexOf(" ")).equals("done")){
                int num = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                t.getDone(true);
                tlist[num-1] = t;
                System.out.println(
                        "\t____________________________________________________________\n" +
                        "\tNice! I've marked this task as done: \n" + "\t" +
                        "[" +t.getStatusIcon() + "] " + list[num-1]  + "\n" +
                                "\t____________________________________________________________\n");
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
