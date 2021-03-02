package duke.controller;

import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI class that handles the user interface commands.
 */
public class UI {

    /**
     * Creates new task list object.
     */
    private TaskList tasklist = new TaskList();

    /**
     * Creates parser object.
     */
    private Parser parser = new Parser();

    /**
     * Creates storage object.
     */
    private Storage store = new Storage();

    /**
     * Creates UI object.
     */
    public UI() {};

    /**
     * Displays welcome message.
     */
    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Ay yo homie! You lookin PENGGGGGGGG today!\nIt's ya boi Duke the Dawg. What can I do for ma G?\n");
    }

    /**
     * Displays all available commands for user input.
     */
    public void displayCommands() {
        String instructions = "1. todo TASKNAME : adds a todo task to the list.\n" +
            "2. event TASKNAME /at TASKDATETIME* : adds a event task to the list.\n" +
            "3. deadline TASKNAME /by TASKDATETIME* : adds a deadline task to the list.\n" +
            "4. list : displays list of tasks.\n" +
            "5. done NUMBER : checks task as done.\n" +
            "6. delete NUMBER : removes task from list.\n" +
            "7. save : save task list in a seperate text file.\n" +
            "8. find KEYWORD : Displays tasks containing keyword.\n" +
            "9. date DATE** : Displays tasks matched with date.\n" +
            "10. bye : exits system.\n" +
            "\n*TASKDATETIME must be keyed in the following format dd-MM-yyyy HH:mm.\n" +
            "**DATE must be keyed in the following format dd-MM-yyyy.\n";
        System.out.println(instructions);
    }

    /**
     * Handles user input and provides the desired output for each command.
     * If user does not input any task or date, returns an error message.
     * @param tasks Array list of tasks.
     */
    public void handleTasklist(ArrayList<Task> tasks) {
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;
        while (isSame) {
            String input = sc.nextLine();
            String stringTask = parser.sortTask(input);
            String stringDate = parser.sortDate(input);
            if (stringTask.contains("retry")) {
                System.out.println("Hoi allow it fam! Why you typed nothing in? Are you dumb? Try again... you melon!");
                continue;
            } else if (stringDate.contains("missing")) {
                System.out.println("Hoi you forgot your date yea? Type in your time period boi!");
                continue;
            }

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Ciao Ciao. See ya soon fam!");
                isSame = false;
                System.exit(0);
            } else if (input.equalsIgnoreCase("list")) {
                tasklist.printTaskList(tasks);
            } else if (input.contains("done")) {
                tasklist.showDone(tasks, input);
            } else if (input.contains("delete")) {
                tasklist.deleteTask(tasks, input);
            } else if (input.contains("todo")) {
                tasklist.printToDo(tasks, stringTask);
            } else if (input.contains("deadline")) {
                tasklist.printDeadline(tasks, stringTask, stringDate);
            } else if (input.contains("event")) {
                tasklist.printEvent(tasks, stringTask, stringDate);
            } else if (input.contains("find")) {
                String keyword = parser.extractKeyword(input);
                tasklist.findTask(tasks, keyword);
            } else if (input.contains("date")) {
                try {
                    String date = parser.extractDate(input);
                    LocalDate Date = parser.processSearch(date);
                    tasklist.findByDate(tasks, Date);
                } catch (DateTimeParseException e) {
                    System.out.println("Please key in specified format dd-MM-yyyy.");
                }
            } else if (input.contains("save")) {
                store.saveFile(tasks);
            } else {
                System.out.println("What are you tryna say to me? Chatting nonsense yea?");
                continue;
            }
        }
    }
}

