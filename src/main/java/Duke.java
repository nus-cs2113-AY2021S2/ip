import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static String LINE_DIVIDER = "\t____________________________________________________________";

    private static String getNumTasksString(int numberOfTasks) {
        if (numberOfTasks == 1) return "\tNow you have " + numberOfTasks + " task in the list.";
        return "\tNow you have " + numberOfTasks + " tasks in the list.";
    }

    public static void main(String[] args) {
        System.out.println(LINE_DIVIDER);
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        System.out.println("\t (Type 'help' for a list of commands)");
        System.out.println(LINE_DIVIDER);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        int numberOfTasks = 0;
        boolean isDone = false;
        while (!isDone) {
            String command = sc.nextLine();
            System.out.println(LINE_DIVIDER);
            String[] commandTokens = command.split(" ", 2);
            if (commandTokens.length == 0) {
                System.out.println("No input received, please try again!");
                continue;
            }
            String inputCommand = commandTokens[0];
            switch (inputCommand) {
            case "bye":
                System.out.println("\t Bye. Hope to see you again soon!");
                isDone = true;
                break;
            case "list":
                if (numberOfTasks > 0) {
                    System.out.println("\t Here is a list of all your tasks:");
                    for (int i = 0; i < numberOfTasks; i++) {
                        System.out.println("\t " + (i + 1) + ". " + tasks.get(i));
                    }
                } else {
                    System.out.println("\t You have no tasks right now!");
                }
                break;
            case "done":
                int taskNumber = -1;
                if (commandTokens.length < 2) {
                    System.out.println("\t I'm not sure which task you'd like to mark as complete.");
                    break;
                }
                try {
                    taskNumber = Integer.parseInt(commandTokens[1]) - 1;
                } catch (NumberFormatException nfe) {
                    System.out.println("\t That's an invalid task number!");
                    break;
                }
                if (0 <= taskNumber && taskNumber < numberOfTasks) {
                    tasks.get(taskNumber).markAsDone();
                    System.out.println("\t Nice! I've marked this task as done: ");
                    System.out.println("\t   " + tasks.get(taskNumber));
                } else {
                    System.out.println("\t That's an invalid task number!");
                }
                break;
            case "todo":
                if (commandTokens.length < 2) {
                    System.out.println("\t Could you tell me more about the task?");
                    break;
                }
                String todoDescription = commandTokens[1].trim();
                Todo todoTask = new Todo(todoDescription);
                tasks.add(todoTask);
                System.out.println("\t added: " + todoTask);
                numberOfTasks = numberOfTasks + 1;
                System.out.println(getNumTasksString(numberOfTasks));
                break;
            case "deadline":
                if (commandTokens.length < 2) {
                    System.out.println("\t Could you tell me more about the task?");
                    break;
                }
                String[] deadlineDetails = commandTokens[1].split("/by");
                if (deadlineDetails.length < 2) {
                    System.out.println("\t Please specify a deadline for the task!");
                    break;
                }
                String deadlineDescription = deadlineDetails[0].trim(), deadline = deadlineDetails[1].trim();
                Deadline deadlineTask = new Deadline(deadlineDescription, deadline);
                tasks.add(deadlineTask);
                System.out.println("\t added: " + deadlineTask);
                numberOfTasks = numberOfTasks + 1;
                System.out.println(getNumTasksString(numberOfTasks));
                break;
            case "event":
                if (commandTokens.length < 2) {
                    System.out.println("\t Could you tell me more about the task?");
                    break;
                }
                String[] eventDetails = commandTokens[1].split("/at");
                if (eventDetails.length < 2) {
                    System.out.println("\t Please specify a event date!");
                    break;
                }
                String eventDescription = eventDetails[0].trim(), eventDate = eventDetails[1].trim();
                Event eventTask = new Event(eventDescription, eventDate);
                tasks.add(eventTask);
                System.out.println("\t added: " + eventTask);
                numberOfTasks = numberOfTasks + 1;
                System.out.println(getNumTasksString(numberOfTasks));
                break;
            case "help":
                System.out.println("\t List of valid commands:");
                System.out.println("\t bye - Exits program");
                System.out.println("\t list - Lists all tasks");
                System.out.println("\t done <task_number> - Mark the specified task as done");
                System.out.println("\t todo <task_description> - Create a new task with the specified description");
                System.out.println("\t deadline <task_description> /by <deadline_date> - " +
                        "Create a new task with the specified description and deadline");
                System.out.println("\t event <task_description> /at <event_date> - " +
                        "Create a new task with the specified description and event date");
                break;
            default:
                System.out.println("\t I didn't quite catch what you were saying. Please try again.");
                System.out.println("\t Try using \"help\" for a list of commands.");
                break;
            }
            System.out.println(LINE_DIVIDER);
        }
    }
}
