import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "**********************************\n"
                + "*       Systems: [Online]        *\n"
                + "*      Protocol: [Dominion]      *\n"
                + "*          Race: [Terran]        *\n"
                + "**********************************\n";

        String greeting = "______________________________________________________\n"
                + "Systems Accessed...\n"
                + "Decrypting Overwrite...\n"
                + "Welcome Commander, can I be of assistance, commander?\n"
                + "______________________________________________________\n";

        String goodbye = "______________________________________________________\n"
                + "Good Bye Commander.\n"
                + "______________________________________________________\n";

        String listTemplate = "***************************************\n"
                + "*                                     *\n"
                + "*   [Objectives]-[Missions]-[Tasks]   *\n"
                + "*                                     *\n"
                + "***************************************\n"
                + "\n"
                + "[Status] [S/N]\n";

        System.out.println(logo);
        System.out.println(greeting);

        // UI Initialization Above

        final int MAX_ARRAY_LENGTH = 100;
        int listIndex = 0;
        String input;
        Scanner in = new Scanner(System.in);

        List[] listArray = new List[MAX_ARRAY_LENGTH];

        while (true){
            input = in.nextLine();

            switch (input.toUpperCase()) {   // Force words to Upper to compare
            case "BYE":
                System.out.println(goodbye);
                System.exit(0); // Ends the program
            case "LIST":
                System.out.println(listTemplate);
                List.printList(listArray, listIndex);
                break;
            default:
                String[] inputArray = input.split(" ");
                if (inputArray[0].equalsIgnoreCase("done")) {
                    String taskDone = List.markDone(listArray, inputArray[1], listIndex);
                    String markDoneTemplate = "______________________________________________________\n"
                            + "[Mission Completed] " + taskDone + "\n"
                            + "______________________________________________________\n";
                    System.out.println(markDoneTemplate);
                } else {
                    listArray[listIndex] = new List();
                    listArray[listIndex].addList(input);
                    String echo = "______________________________________________________\n"
                            + "[Orders received] " + input + "\n"
                            + "______________________________________________________\n";
                    System.out.println(echo);
                    listIndex++;
                }
                break;
            }
        }
    }
}
