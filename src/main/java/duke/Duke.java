package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void runProgram(){
        Scanner in = new Scanner(System.in);
        String userInput;
        // Instead of using tasks[100], I used an ArrayList.
        ArrayList<Task> tasks = new ArrayList<>();
        Task newItem;

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            String[] command = userInput.split(" ");
            String instruction = command[0];

            switch(instruction){
            case "list":
                try{
                    listCommand(command, tasks);
                }
                catch (DukeException e){
                    System.out.println("The list command can only contain 1 word.");
                }
                catch (Exception e){
                    badUserInputMessage();
                }
                break;
            case "done":
                try{
                    doneCommand(command, tasks);
                }
                catch (DukeException e){
                    System.out.println("The done command consists of the word done, and an integer.");
                }
                catch (Exception e){
                    badUserInputMessage();
                }
                break;
            case "todo":
                try{
                    newItem = todoCommand(command, tasks);
                    newItemMessage(tasks, newItem);
                }
                catch (DukeException e){
                    System.out.println("The description of a todo cannot be empty!");
                }
                catch (Exception e){
                    badUserInputMessage();
                }
                break;
            case "event":
                try{
                    newItem = eventCommand(command, tasks);
                    newItemMessage(tasks, newItem);
                }
                catch (DukeException e){
                    System.out.println("Your input should contain /at separated by spaces, " +
                            "followed by the event time.");
                }
                catch(Exception e){
                    badUserInputMessage();
                }
                break;
            case "deadline":
                try{
                    newItem = deadlineCommand(command, tasks);
                    newItemMessage(tasks, newItem);
                }
                catch(DukeException e){
                    System.out.println("Your input should contain /by separated by spaces, " +
                            "followed by the deadline.");
                }
                catch (Exception e){
                    badUserInputMessage();
                }
                break;
            default:
                System.out.println("I have no such feature!");
                break;
            }

            userInput = in.nextLine();
        }
    }

    public static void listCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 1){
            int index = 1;
            System.out.println(LINE);
            for (Task task : tasks) {
                System.out.println(index + "." + task.toString());
                index += 1;
            }
            System.out.println(LINE);
        } else {
            throw new DukeException();
        }
    }

    public static void doneCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 2 && checkIfInteger(command[1])){
            int index = Integer.parseInt(command[1]) - 1;
            if (0 <= index && index < tasks.size()) {
                // If the given value to set as done is an existing index
                tasks.get(index).setAsDone();
                markTaskDoneMessage(tasks, index);
            } else {
                System.out.println("The input index that you have selected to indicate as done, "+
                        "is out of the range of existing indexes!");
            }
        } else {
            throw new DukeException();
        }
    }

    public static Task todoCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length > 1) {
            Task newItem = new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
            tasks.add(newItem);
            return newItem;
        } else {
            throw new DukeException();
        }
    }

    public static Task eventCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(checkForSubstring(command, "/at")){
            int separatorIndex = indexOfSubstring(command, "/at");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String at = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Event(description, at);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public static Task deadlineCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(checkForSubstring(command, "/by")){
            int separatorIndex = indexOfSubstring(command, "/by");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String by = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Deadline(description, by);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public static boolean checkForSubstring(String[] input, String substring){
        for(String string : input){
            if(string.equals(substring)){
                return true;
            }
        }
        return false;
    }

    public static int indexOfSubstring(String[] input, String substring){
        int index = 0;
        for(String string : input){
            if(string.equals(substring)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static boolean checkIfInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void welcomeMessage(){
        String logo = " _                          \n"
                + "| |                         \n"
                + "| |     _   _   ___   _ __  \n"
                + "| |    | | | | / _ \\ | '_ \\ \n"
                + "| |____| |_| || (_) || | | |\n"
                + "\\_____/ \\__, | \\___/ |_| |_|\n"
                + "         __/ |              \n"
                + "        |___/              \n";
        System.out.println("Hello from\n" + logo);

        System.out.println();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lyon");
        System.out.println("What can I do for you?");
    }

    public static void badUserInputMessage(){
        System.out.println(LINE);
        System.out.println("I'm sorry, your input does not comply with the available features I have.");
        System.out.println("Kindly try again!");
    }

    public static void markTaskDoneMessage(ArrayList<Task> tasks, int index){
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    public static void newItemMessage(ArrayList<Task> tasks, Task newItem){
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newItem.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void goodbyeMessage(){
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        welcomeMessage();
        runProgram();
        goodbyeMessage();
    }
    
}
