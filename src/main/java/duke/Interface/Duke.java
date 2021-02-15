package duke.Interface;

import duke.Tasks.*;
import duke.Controller.dukeController;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        dukeController duke = new dukeController();
        duke.displayWelcome();

        ArrayList <Task> tasks = duke.printFileContents();
        if (tasks == null) {
            tasks = new ArrayList<Task>();
        }
        Scanner sc = new Scanner(System.in);
        Boolean isSame = true;
        while (isSame) {
            String input = sc.nextLine();
            String stringTask = duke.extractTask(input);
            String stringDate = duke.extractDate(input);

             /* If input is "bye", system exits with message.
             If input is "list", list of tasks will be displayed.
             If input is "done", the task number to be marked as done.
             Exception handling for "done" includes not indicating task number and input task number out of range.
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
                duke.printTaskList(tasks);
            } else if (input.contains("done")) {
                duke.showDone(tasks, input);
            } else if (input.contains("delete")) {
                duke.deleteTask(tasks, input);
            } else if (input.contains("todo")) {
                duke.printToDo(tasks, input, stringTask);
            } else if (input.contains("deadline")) {
                duke.printDeadline(tasks, input, stringTask, stringDate);
            } else if (input.contains("event")) {
                duke.printEvent(tasks, input, stringTask, stringDate);
            } else if (input.contains("save")){
                duke.saveFile(tasks);
            } else {
                System.out.println("What are you tryna say to me? Chatting nonsense yea?");
                continue;
            }
        }
    }
}