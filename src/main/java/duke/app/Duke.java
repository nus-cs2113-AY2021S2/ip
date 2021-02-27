package duke.app;

import duke.commands.Command;
import duke.parser.Parser;
import duke.exception.DukeException;
import duke.file.Storage;
import duke.task.TaskList;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("duke.txt").run();
    }
}

    /*

    static final int MAX_NO_OF_TASKS = 100;
    public static void main(String[] args) {
        showWelcomeMessage();

        ArrayList<Task> userTasks = new ArrayList<>(MAX_NO_OF_TASKS);
        boolean exitLoopStatus = false;
        Scanner inputCommand = new Scanner(System.in);
        String userInput;
        FileOperation.readFile(userTasks);

        while(true){
            userInput = inputCommand.nextLine();
            String[] individualWords = userInput.split(" ", 2);
            switch(individualWords[0].toLowerCase()){
            case "list":
                displayListOfActivities(userTasks);
                break;
            case "todo":
                createTodoTask(userTasks, individualWords);
                FileOperation.writeFile(userTasks);
                break;
            case "deadline":
                createDeadlineTask(userTasks, individualWords);
                FileOperation.writeFile(userTasks);
                break;
            case "event":
                createEventTask(userTasks, individualWords);
                FileOperation.writeFile(userTasks);
                break;
            case "done":
                markActivityAsDone(userTasks, individualWords);
                FileOperation.writeFile(userTasks);
                break;
            case "delete":
                deleteATask(userTasks, individualWords);
                FileOperation.writeFile(userTasks);
                break;
            case "bye":
                exitLoopStatus = terminateProgram();
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            if (checkLoopStatus(exitLoopStatus)) {
                break;
            }
        }
    }

    private static void deleteATask(ArrayList<Task> userTasks, String[] individualWords) {
        int activityNumber;
        Task temporaryTask;
        try {
            activityNumber = Integer.parseInt(individualWords[1]);
            temporaryTask = userTasks.get(activityNumber-1);
            userTasks.remove(temporaryTask);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(temporaryTask.toString());
            System.out.println("Now you have " + userTasks.size() + " tasks in the list. ");
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }

    private static boolean checkLoopStatus(boolean exitLoopStatus) {
        if(exitLoopStatus){
            return true;
        }
        return false;
    }

    private static boolean terminateProgram() {
        boolean exitLoopStatus;
        System.out.println("Bye. Hope to see you again soon!");
        exitLoopStatus = true;
        return exitLoopStatus;
    }

    private static void markActivityAsDone(ArrayList<Task> userTasks, String[] individualWords) {
        int activityNumber;
        try {
            activityNumber = Integer.parseInt(individualWords[1]);
            userTasks.get(activityNumber-1).setTaskStatus(true);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(userTasks.get(activityNumber-1).toString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid task number");
        }
    }


    private static void createEventTask(ArrayList<Task> userTasks, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1].split("/at")[0];
            String eventTime = individualWords[1].split("/at")[1];
            userTasks.add(new Event(newUserTask, eventTime));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description or date of a event cannot be empty.");
        }
    }

    private static void createDeadlineTask(ArrayList<Task> userTasks, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1].split("/by")[0];
            String date = individualWords[1].split("/by")[1];
            userTasks.add(new Deadline(newUserTask, date));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description or date of a deadline cannot be empty.");
        }
    }

    private static void createTodoTask(ArrayList<Task> userTasks, String[] individualWords) {
        String newUserTask;
        try {
            newUserTask = individualWords[1];
            userTasks.add(new Todo(newUserTask));
            showTaskCreationMessage(userTasks.get(userTasks.size()-1), userTasks.size());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void displayListOfActivities(ArrayList<Task> userTasks) {
        System.out.println("Here are the tasks in your list:");
        for (int counter = 0; counter < userTasks.size(); counter++) {
            System.out.println((counter+1) + "." + userTasks.get(counter).toString());
        }
    }

    private static void showWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void showTaskCreationMessage(Task userTask, int noOfTasks) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(userTask.toString());
        System.out.println("Now you have " + noOfTasks + " tasks in the list. ");
    }
}

     */
