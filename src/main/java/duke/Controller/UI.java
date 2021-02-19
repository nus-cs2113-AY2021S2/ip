package duke.Controller;

import duke.Tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private TaskList tasklist = new TaskList();
    private Parser parser = new Parser();
    private Storage store = new Storage();

    public UI() {};

    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Ay yo homie! You lookin PENGGGGGGGG today!\nIt's ya boi Duke the Dawg. What can I do for ma G?\n");
    }

    public void handleTasklist(ArrayList<Task> tasks) throws IOException {
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;
        while (isSame) {
            String input = sc.nextLine();
            String stringTask = parser.extractTask(input);
            String stringDate = parser.extractDate(input);

             /* If input is "bye", system exits with message.
             If input is "list", list of tasks will be displayed.
             If input is "done", the task number to be marked as done.
                - Exception handling for "done" includes not indicating task number and input task number out of range.
             If input is "delete", the task number is removed from the list.
                - Exception handling for "delete" includes not indicating task number and input task number out of range.
             If input is "save", the task list is save in a seperate txt file.
             If input is "todo", classify task as ToDo.
             If input is "Deadline", classify task as Deadline.
             If input is "Event", classify task as Event.
             If there is no task specified after specifying the type of task, system will prompt for another input*/
            if (stringTask.contains("retry")) {
                System.out.println("Hoi allow it fam! Why you got no tasks? Are you dumb? Try again... you melon!");
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
                tasklist.printToDo(tasks, input, stringTask);
            } else if (input.contains("deadline")) {
                tasklist.printDeadline(tasks, input, stringTask, stringDate);
            } else if (input.contains("event")) {
                tasklist.printEvent(tasks, input, stringTask, stringDate);
            } else if (input.contains("save")){
                store.saveFile(tasks);
            } else {
                System.out.println("What are you tryna say to me? Chatting nonsense yea?");
                continue;
            }
        }
    }
}

