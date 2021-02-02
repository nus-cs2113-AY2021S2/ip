import java.util.Scanner;
public class Duke {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] textList = new String[101];
        String[] doneList = new String[101];
        int listNumber = 1;
        String doneString = null;

        Greetings.welcome();

        String inputCommand = sc.nextLine();

        while (!inputCommand.equals("bye")) {
            if (inputCommand.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < listNumber; i++) {
                    System.out.println(i + "." + "[" + doneList[i] + "] " + textList[i]);
                }
            } else if (inputCommand.equals("blah")) {
                System.out.println(inputCommand);
            } else if (inputCommand.equals("bye")) {
                break;
            } else {
                String trimmed_inputCommand = inputCommand.trim();
                if (trimmed_inputCommand.length() > 4) {
                    doneString = trimmed_inputCommand.substring(0, 4);
                }
                if ("done".equals(doneString) && (trimmed_inputCommand.length() > 4)) {
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[X] " + inputCommand);
                    int doneIndex = Integer.parseInt(inputCommand.substring(5));
                    doneList[doneIndex] = "X";

                } else {
                    textList[listNumber] = inputCommand;
                    doneList[listNumber] = " ";
                    System.out.println("added: " + textList[listNumber]);
                    listNumber++;
                }
            }
            inputCommand = sc.nextLine();
        }
        Greetings.goodbye();
    }

}
