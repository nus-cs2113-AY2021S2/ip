package duke;
import duke.myExceptions.*;
import duke.myTasks.*;
import java.util.Scanner;
import java.util.ArrayList;


public class Duke {

    public static ArrayList<Todo> list = new ArrayList<>();
    public static int index = 0;
    public static boolean isRunning = true;

    public static void addToList(Todo task) {
        list.add(task);
        index++;
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public static void printAddMessage(Todo task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public static void executeInstruction(String command, String content) throws InvalidInput, NoContent, NoTime, NullPointerException, NumberFormatException, ArrayIndexOutOfBoundsException{
        switch (command) {
        case "bye":
            isRunning = false;
            break;
        case "list":
            printList();
            break;
        case "done":
            list.get(Integer.parseInt(content) - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(Integer.parseInt(content) - 1).toString());
            break;
        case "todo":
            Todo todo = new Todo(content);
            addToList(todo);
            printAddMessage(todo);
            break;
        case "event":
            Event event = new Event(content);
            addToList(event);
            printAddMessage(event);
            break;
        case "deadline":
            Deadline deadline = new Deadline(content);
            addToList(deadline);
            printAddMessage(deadline);
            break;
        default:
            throw new InvalidInput();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            String command;
            String content = "";
            if (input.indexOf(' ') == -1) {
                command = input;
            } else {
                command = input.substring(0, input.indexOf(' '));
                content = input.substring(input.indexOf(' ') + 1);
            }
            try {
                executeInstruction(command, content);
            } catch (InvalidInput e) {
                System.out.println("Invalid command used");
            } catch (NoContent | StringIndexOutOfBoundsException e) {
                System.out.println("Needs a name");
            } catch (NoTime e) {
                System.out.println("Needs time. Add \"/by <time>\" to command.");
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid index number");
            } catch (NumberFormatException e) {
                System.out.println("Specify which task to mark as done");
            }
        } while (isRunning);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
