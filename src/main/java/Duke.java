import java.util.Scanner;

public class Duke {

    private static void initialiseWelcomeMessage() {
        String logo
                = "******************************************************\n"
                + "*                 Systems: [Online]                  *\n"
                + "*                Protocol: [Dominion]                *\n"
                + "*                    Race: [Terran]                  *\n"
                + "******************************************************\n";

        String greeting
                = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        System.out.println(logo);
        System.out.println(greeting);
    }

    private static void printGoodbyeMessage() {
        String goodbye
                = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        System.out.println(goodbye);
    }

    private static String getCommand(String userInput) {
        String[] inputArray = userInput.split(" ");
        String command = inputArray[0];
        return command.toUpperCase();
    }

    private static void executeCommand(String command, String userInput) {
        String errand;
        String timestamp;

        switch(command) {
        case "TODO":
            errand = getErrand(userInput);
            Task todo = new Todo(errand);
            todo.addToTaskList(errand, null); // Todo has no timestamp
            break;
        case "EVENT":
            errand = getErrand(userInput);
            timestamp = getTimestamp(userInput);
            Task event = new Event(errand, timestamp);
            event.addToTaskList(errand, timestamp);
            break;
        case "DEADLINE":
            errand = getErrand(userInput);
            timestamp = getTimestamp(userInput);
            Task deadline = new Deadline(errand, timestamp);
            deadline.addToTaskList(errand, timestamp);
            break;
        case "LIST":
            Task.printList();
            break;
        case "BYE":
            printGoodbyeMessage();
            break;
        }
    }

    private static String getTimestamp(String userInput) {
        int slashPosition = userInput.indexOf("/");
        String timestamp = userInput.substring(slashPosition+1);
        String[] timestampArray = timestamp.split(" ");
        timestamp = timestampArray[1];
        return timestamp.trim();
    }

    private static String getErrand(String userInput) {
        String errand;
        int spacePosition = userInput.indexOf(" ");
        int slashPosition = userInput.indexOf("/");

        // Slash not found, no timestamp behind
        if(slashPosition == -1) {
            errand = userInput.substring(spacePosition);
        } else {
            errand = userInput.substring(spacePosition, slashPosition);
        }
        return errand.trim();
    }

    public static void main(String[] args) {
        String userInput;
        String command;
        Scanner in = new Scanner(System.in);

        initialiseWelcomeMessage();

        do {
            userInput = in.nextLine();
            command = getCommand(userInput);
            executeCommand(command, userInput);
        } while (!command.equals("BYE"));



        /*
        Task task = new Task();
        Task[] mission = new Task[MAX_ARRAY_LENGTH];

        while (shouldLoop){
            input = in.nextLine();

            switch (input.toUpperCase()) {   // Force words to Upper to compare
            case "BYE":
                shouldLoop = false;
                break;
            case "LIST":
                task.printList(mission, taskLength);
                break;
            default:
                String[] inputArray = input.split(" ");
                if (inputArray[0].equalsIgnoreCase("done")) {
                    String taskDone = task.markDone(mission, inputArray[1], taskLength);
                    String markDoneTemplate = "______________________________________________________\n"
                            + "[Mission Completed] " + taskDone + "\n"
                            + "______________________________________________________\n";
                    System.out.println(markDoneTemplate);
                } else { // All other inputs aside from keywords
                    mission[taskLength] = new Task();
                    mission[taskLength].addList(input);
                    String echo = "______________________________________________________\n"
                            + "[Orders received] " + input + "\n"
                            + "______________________________________________________\n";
                    System.out.println(echo);
                    taskLength++;
                }
                break;
            }
        }

        printGoodbyeMessage();
        */
    }
}
