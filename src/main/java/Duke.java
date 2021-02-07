import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void runProgram(){
        Scanner in = new Scanner(System.in);
        String userInput;
        // Instead of using tasks[100], I used an ArrayList.
        ArrayList<Task> tasks = new ArrayList<Task>();
        // Index int is to access elements of the ArrayList
        int index;
        Task newItem = null;

        userInput = in.nextLine();
        while (!userInput.equals("bye")) {
            String[] command = userInput.split(" ");
            String instruction = command[0];
            boolean isUserInputGood = true;
            boolean isNewItem = false;

            switch(instruction){
            case "list":
                if(command.length == 1){
                    index = 1;
                    System.out.println(LINE);
                    for (Task task : tasks) {
                        System.out.println(index + "." + task.toString());
                        index += 1;
                    }
                    System.out.println(LINE);
                }else{
                    isUserInputGood = false;
                }
                break;
            case "done":
                if(command.length == 2 && checkIfInteger(command[1])){
                    index = Integer.parseInt(command[1]) - 1;
                    if (0 <= index && index < tasks.size()) {
                        // If the given value to set as done is an existing index
                        tasks.get(index).setAsDone();
                        markTaskDoneMessage(tasks, index);
                        //TODO
                        // Currently a task can be marked as done repeatedly.
                        // This does not cause any errors, but may be required to fix
                    } else {
                        System.out.println("The input index that you have selected to indicate as done,"+
                                "is out of the range of existing indexes!");
                    }
                } else {
                    isUserInputGood = false;
                }
                break;
            case "todo":
                if(command.length > 1) {
                    newItem = new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
                    tasks.add(newItem);
                    isNewItem = true;
                } else {
                    isUserInputGood = false;
                }
                break;
            case "event":
                if(checkForSubstring(command, "/at")){
                    int separatorIndex = indexOfSubstring(command, "/at");
                    String description = String.join(" ", Arrays.copyOfRange(command,
                            1, separatorIndex));
                    String at = String.join(" ",Arrays.copyOfRange(command,
                            separatorIndex + 1, command.length));

                    newItem = new Event(description, at);
                    tasks.add(newItem);
                    isNewItem = true;
                } else{
                    isUserInputGood = false;
                }
                break;
            case "deadline":
                if(checkForSubstring(command, "/by")){
                    int separatorIndex = indexOfSubstring(command, "/by");
                    String description = String.join(" ", Arrays.copyOfRange(command,
                            1, separatorIndex));
                    String by = String.join(" ",Arrays.copyOfRange(command,
                            separatorIndex + 1, command.length));

                    newItem = new Deadline(description, by);
                    tasks.add(newItem);
                    isNewItem = true;
                } else{
                    isUserInputGood = false;
                }
                break;
            default:
                isUserInputGood = false;
                break;
            }

            if(!isUserInputGood) {
                badUserInputMessage();
            } else if (isNewItem){
                newItemMessage(tasks, newItem);
            }

            userInput = in.nextLine();
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
