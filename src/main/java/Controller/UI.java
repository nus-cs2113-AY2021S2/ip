package controller;

import tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class deals with the user interface with the user.
 */
public class UI {

    private DukeController dc = new DukeController();
    private Parser parse = new Parser();
    private Storage store = new Storage();

    public UI() {};

    /**
     * Welcome Message.
     */
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

    /**
     * Schedule Timetabling.
     * @param tasks Task list.
     */
    public void scheduleTimetable(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] strings = new String[3];
        strings = parse.processInput(input);
        int count1 = tasks.size();
        do {
            if (input.equals("list")) {
                dc.printList(tasks);
            }

            else if (input.contains("delete")) {
                dc.deleteTask(tasks, input);
            }

            else if (input.contains("done")) {
                dc.printDone(tasks, input);
            }

            else if (input.contains("todo")) {
                if (!dc.printTodo(tasks, input, strings, count1)) {
                    count1++;
                }
            }

            else if (input.contains("deadline")) {
                if (!dc.printDeadline(tasks, input, strings, count1)) {
                    count1++;
                }
            }

            else if (input.contains("event")) {
                if (!dc.printEvent(tasks, input, strings, count1)) {
                    count1++;
                }
            }

            else if (input.contains("save")) {
                store.saveOutput(tasks);
            }

            else if (input.contains("date")) {
                try {
                    LocalDate date = parse.processString(strings);
                    dc.findbyDate(tasks, date);
                }
                catch (DateTimeParseException e) {
                    System.out.println("Please key in within specified format yyyy-MM-dd");
                    System.out.println("--------------------------------------------");
                }
            }

            else if (input.contains("find")) {
                dc.findbyDescription(tasks, strings);
            }

            else {
                dc.printUnidentified(input);
            }
            input = sc.nextLine();
            strings = parse.processInput(input);

        } while (!input.equals("bye"));

        System.out.println("--------------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("--------------------------------------------");
    }
}
