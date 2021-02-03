import java.util.Scanner;



public class TED {
    public static void main(String[] args) {
        String line;
        String elem;
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
            String substringname = line.substring(line.indexOf(" ") + 1, line.indexOf("/")-1);
            String substingdate = line.substring(line.indexOf("/") + 4);
            if(line.substring(0, line.indexOf("e")).equals("d")){
                elem = substringname;
                Deadline t = new Deadline(elem, substingdate);
                tlist[counter] = t;
                list[counter] = elem;
                System.out.println(
                        "\t____________________________________________________________\n" +
                                "\tGot it. I've added this task: \n" +
                                "\t[" + t.getType() + "]" + "[ " + "]" + elem + "\n" +
                                "\tNow you have " + counter+1 + " tasks in the list.\n" +
                                "\t____________________________________________________________");
                continue;
            }else if(line.substring(0, line.indexOf("v")).equals("e")) {
                elem = substringname;
                event t = new event(elem, substingdate);
                tlist[counter] = t;
                list[counter] = elem;
                System.out.println(
                        "\t____________________________________________________________\n" +
                                "\tGot it. I've added this task: \n" +
                                "\t[" + t.getType() + "]" + "[ " + "]" + elem + "\n" +
                                "\tNow you have " + counter+1 + " tasks in the list.\n" +
                                "\t____________________________________________________________");
                continue;
            }else if (line.substring(0, line.indexOf("o")).equals("t")) {
                elem = line.substring(5);
                todo t = new todo(elem);
                tlist[counter] = t;
                list[counter] = elem;
                System.out.println(
                        "\t____________________________________________________________\n" +
                                "\tGot it. I've added this task: \n" +
                                "\t[" + t.getType() + "]" + "[ " + "]" + elem + "\n" +
                                "\tNow you have " + counter+1 + " tasks in the list.\n" +
                                "\t____________________________________________________________");
                continue;
            }

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
                    System.out.println("\t" + number + ".["+ tlist[i].getStatusIcon() + "] " + list[i] + "\n");
                    number++;
                }
                System.out.println(
                        "\t____________________________________________________________\n");
                continue;
            }else if (line.substring(0, line.indexOf(" ")).equals("done")){
                int num = Integer.parseInt(line.substring(line.indexOf(" ")+1));
                tlist[num-1].getDone(true);
                System.out.println(
                        "\t____________________________________________________________\n" +
                                "\tNice! I've marked this task as done: \n" + "\t" +
                                "[" +tlist[num-1].getStatusIcon() + "] " + list[num-1]  + "\n" +
                                "\t____________________________________________________________\n");
                continue;
            }else {
                System.out.println("\t____________________________________________________________\n" +
                        "\tadded: " + line + "\n" +
                        "\t____________________________________________________________\n");
            }
            counter++;
        }
    }
}
