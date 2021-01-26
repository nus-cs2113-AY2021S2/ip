import java.util.Scanner;

public class Duke {
    private static final int MAX_ARRAY_LENGTH = 100;
    private static int taskLength = 0;
    private static boolean shouldLoop = true;
    private static String input;

    private static String logo
            = "******************************************************\n"
            + "*                 Systems: [Online]                  *\n"
            + "*                Protocol: [Dominion]                *\n"
            + "*                    Race: [Terran]                  *\n"
            + "******************************************************\n";

    private static String greeting
            = "______________________________________________________\n"
            + "Systems Accessed...\n"
            + "Decrypting Overwrite...\n"
            + "Welcome Commander, can I be of assistance, commander?\n"
            + "______________________________________________________\n";

    private static String goodbye
            = "______________________________________________________\n"
            + "Good Bye Commander.\n"
            + "______________________________________________________\n";

    public static void main(String[] args) {
        // UI Initialization
        System.out.println(logo);
        System.out.println(greeting);

        Scanner in = new Scanner(System.in);

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
        System.out.println(goodbye);
    }
}
