import Class.Task;
import Command.Command;
import ErrorHandling.EmptyDescription;
import ErrorHandling.OutOfBound;
import ErrorHandling.UnknownCommand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static String lineDivider = "    --------------------------------------------------------------------------";

    public static void main(String[] args) throws IOException, UnknownCommand, EmptyDescription, OutOfBound {
        Scanner in = new Scanner(System.in).useDelimiter(" ");
        String command = "";
        List<Task> tasks = new ArrayList<Task>();
        Command Command = new Command();

        Command.readFile(tasks);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        do {
            command = in.nextLine();
            System.out.println(lineDivider);
            try {
                Command.takeCommand(command, tasks);
            } catch (UnknownCommand e) {
                System.out.println("     Oops!!! I'm sorry, but I have no idea what that means =(");
            } catch (EmptyDescription e) {
                System.out.println("     Oops!!! The description of " + e.TaskName() + " cannot be empty.");
            } catch (OutOfBound e) {
                System.out.println("     Oops!!! I think you are trying to access things that aren't even there yet!");
            }
            System.out.println(lineDivider);
        } while (!command.equals("bye"));

        Command.writeFile(tasks);
    }
}
