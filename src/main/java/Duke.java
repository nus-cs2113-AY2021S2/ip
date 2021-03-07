import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

/**
 * A task manager.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor.
     *
     * @param filepath the path of storage
     */
    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Get Duke's responses
     */
    public void run() {
        Ui.helloMessage();
        boolean isContinue = true;
        while(isContinue){
            String fullcommand = ui.readCommand();
            if (fullcommand.equalsIgnoreCase("bye")) {
                Ui.byeMessage();
                break;
            } else {
                Parser.parse(fullcommand);
            }
            Ui.showLine();
        }
    }

    /**
     * executes the program Duke
     */
    public static void main(String[] args){
        new Duke("tasks.txt").run();
    }


}




/**
 * import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
// git add .
// git tag level-3
// git commit -m ""
// git push origin --tags

public class Duke {

    private Ui ui;

    public static ArrayList<Task> tasks = new ArrayList<>();
    public static boolean isContinue = true;

    public static void saveFile() throws IOException {
        File path = new File("duke.txt");
        if (!path.exists() && !path.createNewFile()) {
            throw new IOException();
        }
        try {
            FileWriter fileWriter = new FileWriter(path);
            for (int i = 0; i < numberOfTask(); i++) {
                fileWriter.write(tasks.get(i).formatData() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Failed to save the file");
            tasks.clear();
        }
    }

    public static void loadFile() throws FileNotFoundException {
        try {
            File path = new File("duke.txt");
            if (!path.exists()) {
                throw new FileNotFoundException();
            }
            Scanner scanner = new Scanner(path);
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                String[] data = input.split("\\s+|\\s+");
                String addCommand = "";
                switch (data[0]) {
                    case "T":
                        addCommand = "todo " + data[4];
                        break;
                    case "D":
                        addCommand = "deadline " + data[4] + " /by " + data[6];
                        break;
                    case "E":
                        addCommand = "event " + data[4] + " /at " + data[6];
                        break;
                }
                loadCommand(addCommand);
                if (data[2].equals(1)) {
                    loadCommand("done " + tasks.size());
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to load the file");
            tasks.clear();
        }

    }

    public static Integer numberOfTask() {
        return tasks.size();
    }

    public static void loadCommand(String input) {
        String[] splittedCommand = input.split("\\s+",2);
        String commandType = splittedCommand[0].toUpperCase();

        switch (commandType) {
            case "DONE":
                int taskIndex =Integer.parseInt(splittedCommand[1]) ;
                taskIndex--;
                tasks.get(taskIndex).setDone(true);
                break;
            case "TODO":
                String description = splittedCommand[1];
                if(description.equals("")){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Todo newTodo = new Todo(description);
                tasks.add(newTodo);
                break;
            case "DEADLINE":
                String deadlineDescription = splittedCommand[1];
                if(deadlineDescription.equals("")){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }
                String[] deadline = deadlineDescription.split(" /by ", 2);
                if(deadline.length < 2){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }
                Deadline newDeadline = new Deadline(deadline[0], deadline[1]);
                tasks.add(newDeadline);
                break;
            case "EVENT":
                String[] eventDescription = splittedCommand[1].split(" /at ", 2);
                Event newEvent = new Event(eventDescription[0], eventDescription[1]);
                tasks.add(newEvent);
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void command(String input) {
        String[] splittedCommand = input.split("\\s+",2);
        String commandType = splittedCommand[0].toUpperCase();

        switch (commandType) {
            case "BYE":
                System.out.println("Bye. Hope to see you again soon!");
                isContinue = false;
                break;
            case "LIST":
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < numberOfTask(); i++){
                    int number = i+1;
                    System.out.println( number + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
                break;
            case "FIND":
                String findDescription = splittedCommand[1];
                ArrayList<Task> foundTasks = new ArrayList<>();
                for (Task task : tasks) {
                    if (task.getDescription().contains(findDescription)) {
                        foundTasks.add(task);
                    }
                }

                if (foundTasks.size() > 0) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the matching task in your list:");
                    for (int i = 0; i < foundTasks.size(); i++) {
                        int number = i + 1;
                        System.out.println(number + "." + foundTasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("☹ OOPS!!! I cannot find the word " + findDescription + " in the list");
                }
                break;
            case "DONE":
                int taskIndex =Integer.parseInt(splittedCommand[1]) ;
                taskIndex--;
                tasks.get(taskIndex).setDone(true);

                System.out.println("____________________________________________________________");
                System.out.println("I've marked this as done: " + tasks.get(taskIndex));
                System.out.println("____________________________________________________________");
                break;
            case "TODO":
                String description = splittedCommand[1];
                if(description.equals("")){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Todo newTodo = new Todo(description);
                tasks.add(newTodo);


                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println(tasks.get(numberOfTask() - 1));
                System.out.println("Now you have " + numberOfTask() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "DEADLINE":
                String deadlineDescription = splittedCommand[1];
                if(deadlineDescription.equals("")){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }
                String[] deadline = deadlineDescription.split(" /by ", 2);
                if(deadline.length < 2){
                    System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    break;
                }

                Deadline newDeadline = new Deadline(deadline[0], deadline[1]);
                tasks.add(newDeadline);

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: " + splittedCommand[1]);
                System.out.println(tasks.get(numberOfTask() - 1));
                System.out.println("Now you have " + numberOfTask() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
            case "EVENT":
                String[] eventDescription = splittedCommand[1].split(" /at ", 2);
                Event newEvent = new Event(eventDescription[0], eventDescription[1]);
                tasks.add(newEvent);

                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: " + splittedCommand[1]);
                System.out.println(tasks.get(numberOfTask() - 1));
                System.out.println("Now you have " + numberOfTask() + " tasks in the list.");
                System.out.println("____________________________________________________________");
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        System.out.println(ui.getWelcome());


        Scanner scanner = new Scanner(System.in);
        String input;
        try {
            loadFile();
        }  catch (FileNotFoundException e) {
            System.out.println("Failed to load the file");
        }

        while (isContinue) {
            input = scanner.nextLine();
            command(input);
            try {
                saveFile();
            }  catch (IOException e) {
                System.out.println("Failed to load the file");
            }
        }

        scanner.close();
    }



}
**/