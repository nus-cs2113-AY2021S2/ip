import Exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        TaskList tasks = new TaskList();
        Scanner input = new Scanner(System.in);
        Storage storage = new Storage();

        File fileName = new File("taskList.txt");
        if (!fileName.exists()) {
            fileName.createNewFile();
        } else {
            storage.loadFile();
        }
        while (true) {
            String inputs = input.nextLine();
            try {
                if (inputs.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;

                } else if (inputs.equals("list")) {
                    tasks.printTasksLists();

                } else if (inputs.contains("done")) {
                    String numStr = inputs.substring(inputs.indexOf(" ") + 1);
                    int num = Integer.parseInt(numStr);
                    tasks.markAsDone(num);

                } else if (inputs.contains("deadline")) {
                    tasks.addDeadline(inputs);

                } else if (inputs.contains("todo")) {
                    tasks.addTodo(inputs);

                } else if (inputs.contains("event")) {
                    tasks.addEvent(inputs);

                } else if (inputs.contains("delete")) {
                    String numStr = inputs.substring(inputs.indexOf(" ") + 1);
                    int num = Integer.parseInt(numStr);
                    tasks.deleteTask(num);

                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (NumberFormatException e) {
                System.out.println("OOPS!!! That task number does not exist");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("OOPS!!! That input is in the wrong format!");
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("Sorry, something wrong with file");
            }
        }
    }
}
