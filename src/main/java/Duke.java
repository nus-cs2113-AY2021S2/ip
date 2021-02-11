import jdk.jshell.spi.ExecutionControl;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeException {
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
                try {
                    String sentence = word.substring(word.indexOf(" ") + 1);
                    String title = sentence.substring(0, sentence.indexOf("/") - 1);
                    String date = word.substring(word.indexOf("by") + 3);


                    //if (sentence.equals("deadline")) throw new DukeException(sentence);

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
                } catch (Exception e) {
                    System.out.println("OOPS!!! The description of a deadline cannot be empty.");
                }
            } else if (word.contains("todo")) {
                try {
                    String sentence = word.substring(word.indexOf(" ") + 1);

                    if (sentence.equals("todo")) throw new DukeException(sentence);

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
                } catch (Exception e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (word.contains("event")) {
                try {
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
                } catch (Exception e) {
                    System.out.println("OOPS!!! The description of a event cannot be empty.");
                }
            } else {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
