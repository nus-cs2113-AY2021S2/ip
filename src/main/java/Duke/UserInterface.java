package Duke;

import java.util.Scanner;

/**
 * UserInterface class that interacts with the user and the command line during the running of the app
 */
public class UserInterface {

    /**
     * scans the raw user input
     * @return the raw user input
     */
    public String getInput() {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        return command;
    }

    /**
     * standardizes the format of all outputs and printing the output of
     * the user's command or of any errors that occur
     * @param commandOutput
     */
    public void printOutput(String commandOutput) {
        System.out.println(Output.printLine());
        System.out.println(commandOutput);
        System.out.println(Output.printLine());
    }
}
