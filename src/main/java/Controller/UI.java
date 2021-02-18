package Controller;

import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private DukeController dc = new DukeController();
    private Parser parse = new Parser();
    private Storage store = new Storage();

    public UI() {};

    public void welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        System.out.println("--------------------------------------------");
    }

    public void scheduleTimetable(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();
        String[] strings = new String[3];
        strings = parse.processInput(in);
        int count1 = tasks.size();
        do {
            if (in.equals("list")) {
                dc.printList(tasks);
            }

            else if (in.contains("delete")) {
                dc.deleteTask(tasks, in);
            }

            else if (in.contains("done")) {
                dc.printDone(tasks, in);
            }

            else if (in.contains("todo")) {
                dc.printTodo(tasks, in, strings, count1);
                count1++;

            }

            else if (in.contains("deadline")) {
                dc.printDeadline(tasks, in, strings, count1);
                count1++;
            }

            else if (in.contains("event")) {
                dc.printEvent(tasks, in, strings, count1);
                count1++;
            }

            else if (in.contains("save")) {
                store.saveOutput(tasks);
            }

            else {
                dc.printDK(in);
            }
            in = sc.nextLine();
            strings = parse.processInput(in);

        } while (!in.equals("bye"));

        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------");
    }
}
