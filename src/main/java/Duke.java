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

        ArrayList<Task> lists = new ArrayList<Task>();
        Scanner input = new Scanner(System.in);

        while (true) {

            String word = input.nextLine();

            if (word.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (word.equals("list")) {
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println(i + 1 + "." + lists.get(i).toString());
                }
            } else if (word.contains("done")) {
                String numStr = word.substring(word.indexOf(" ") + 1);
                int num = Integer.parseInt(numStr);
                System.out.println("Nice! I've marked this task as done:");
                lists.get(num - 1).markAsDone();
                System.out.println(lists.get(num - 1).toString());


            } else if (word.contains("deadline")) {
                String sentence = word.substring(word.indexOf(" ") + 1);
                String title = sentence.substring(0, sentence.indexOf("/") - 1);
                String date = word.substring(word.indexOf("by") + 3);

                Task t = new Deadline(title, date);
                t.toString();
                lists.add(t);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                if (lists.size() == 1) {
                    System.out.println("Now you have " + lists.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + lists.size() + " tasks in the list.");
                }

            } else if (word.contains("todo")) {
                String sentence = word.substring(word.indexOf(" ") + 1);

                Task t = new Todo(sentence);
                t.toString();
                lists.add(t);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                if (lists.size() == 1) {
                    System.out.println("Now you have " + lists.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + lists.size() + " tasks in the list.");
                }


            } else if (word.contains("event")) {
                String sentence = word.substring(word.indexOf(" ") + 1);
                String title = sentence.substring(0, sentence.indexOf("/") - 1);
                String date = word.substring(word.indexOf("at") + 3);

                Task t = new Event(title, date);
                t.toString();
                lists.add(t);

                System.out.println("Got it. I've added this task:");
                System.out.println("  " + t);
                if (lists.size() == 1) {
                    System.out.println("Now you have " + lists.size() + " task in the list.");
                } else {
                    System.out.println("Now you have " + lists.size() + " tasks in the list.");
                }
            }
        }
    }
}
