package Duke;

import java.util.Scanner;

public class UserInterface {

    public String getInput() {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        return command;
    }

    public void printOutput(String commandOutput) {
        System.out.println(Output.printLine());
        System.out.println(commandOutput);
        System.out.println(Output.printLine());
    }
}
