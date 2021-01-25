import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> inputs = new ArrayList<>();

        String DECO_LINE = "____________________________________________________________";
        String HELLO_MESSAGE = " Hello! I'm Duke";
        String ASK_MESSAGE = " What can I do for you?";
        String BYE_MESSAGE = " Bye. Hope to see you again soon!";

        System.out.println(DECO_LINE);
        System.out.println(HELLO_MESSAGE);
        System.out.println(ASK_MESSAGE);
        System.out.println(DECO_LINE);
        System.out.println();

        Scanner readinput = new Scanner(System.in);
        String input = readinput.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(DECO_LINE);
                for (int i = 0; i < inputs.size(); i++) {
                    System.out.println(" " + (i + 1) + ". [" + inputs.get(i).getStatusIcon() + "] " + inputs.get(i).description);

                }
                System.out.println(DECO_LINE);
                System.out.println();
            } else if (input.split(" ")[0].equals("done")) {
                System.out.println(DECO_LINE);
                try {
                    int ind = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (ind < inputs.size() && ind >= 0) {
                        if (inputs.get(ind).isDone) {
                            System.out.println("Task is already marked done!");
                        } else {
                            inputs.get(ind).markAsDone();
                            System.out.println(" [" + inputs.get(ind).getStatusIcon() + "] " + inputs.get(ind).description);
                        }
                    } else {
                        System.out.println("Index is out of boundary!");
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Format is wrong!");
                }
                System.out.println(DECO_LINE);
            } else {
                System.out.println(DECO_LINE);
                System.out.println(" added: " + input);
                System.out.println(DECO_LINE);
                System.out.println();
                inputs.add(new Task(input));
            }
            input = readinput.nextLine();
        }

        System.out.println(DECO_LINE);
        System.out.println(BYE_MESSAGE);
        System.out.println(DECO_LINE);
    }
}
