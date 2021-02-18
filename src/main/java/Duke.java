import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        MessagePrinter.showWelcomeMessage();

        try {
            FileManager.loadFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            String fullCommand = getUserInput();
            String[] partOfCommand = fullCommand.split(" ");

            if (fullCommand.equals("bye")) {
                MessagePrinter.showByeMessage();
                break;
            } else if (fullCommand.equals("list")) {
                TaskManager.printList();
            } else if (fullCommand.contains("done")) {
                try {
                    TaskManager.displayTaskDone(Integer.parseInt(partOfCommand[1]));
                } catch (IndexOutOfBoundsException oob) {
                    MessagePrinter.printBorder();
                    System.out.println("     ☹ OOPS!!! Please specify which task number is done");
                    MessagePrinter.printBorder();
                } catch (NullPointerException npe) {
                    MessagePrinter.printBorder();
                    System.out.println("     ☹ OOPS!!! There is no such task number");
                    MessagePrinter.printBorder();
                } catch ( DukeException de){
                    MessagePrinter.printBorder();
                    System.out.println("     ☹ OOPS!!! This task is already marked as done");
                    MessagePrinter.printBorder();
                }
            } else {
                TaskManager.addNewTask(partOfCommand, fullCommand);
            }
        } //while loop
    } //main method

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
