package duke.util;

import java.io.InputStream;
import java.util.Scanner;

import duke.commands.Command;
import duke.commands.ListCommand;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    
    public final Scanner scanner;
    public static final String COMMANDS = 
            "Commands:\n"
            + "    todo taskName\n"
            + "    deadline deadlineName /by dd/mm/yyyy hhmm\n" 
            + "    event eventName /at dd/mm/yyyy hhmm\n"
            + "    list\n"
            + "    done taskNumber\n"
            + "    delete taskNumber\n"
            + "    help\n"
            + "    bye\n";
    
    public Ui() {
        this(System.in);
    }

    public Ui(InputStream in) {
        this.scanner = new Scanner(in);
    }

    public void displayWelcomeMessage(TaskList taskList, Ui ui, Parser parser) {
        String logo = "         __    _    _              ____        __           \n"
                + "        / /_  (_)  (_)___ ___     / __ \\__  __/ /_____      \n"
                + "       / __ \\/ /  / / __ `__ \\   / / / / / / / //_/ _ \\     \n"
                + "      / / / / /  / / / / / / /  / /_/ / /_/ / ,< /  __/     \n"
                + "     /_/ /_/_/  /_/_/ /_/ /_/  /_____/\\__,_/_/|_|\\___/     \n";
        System.out.print(logo + "\n");
        System.out.print("What do you have to do today?\n");
        System.out.print(COMMANDS);
        Command listCommand = new ListCommand();
        try {
            listCommand.execute(taskList, ui, parser);
        } catch (Exception e) {
            printErrorMessage(e);
        }
    }

    public void printWithBorder(String line) {
        System.out.print("_______________________________________________________________________________\n");
        System.out.print(line + "\n");
        System.out.print("_______________________________________________________________________________\n");
    }

	public void showLoadingError(Exception e) {
        String errorMessage = "ERROR retrieving data!\n" + e.getLocalizedMessage();
        printWithBorder(errorMessage);
	}

    public String readLine() {
        String line = scanner.nextLine();
        return line;
    }

    public void printHelpMessage() {
        printWithBorder(COMMANDS);
    }

    public void listAllTasks(TaskList taskList) {
        String listOfTasksString = "Here are the tasks in your list:";
        for (int i = 0; i < taskList.getListSize(); i += 1) {
            Task task = taskList.getTask(i);
            listOfTasksString += ("\n    " + Integer.toString(i + 1) + ". " + task.toString());
        }
        printWithBorder(listOfTasksString);
    }

    public void printErrorMessage(Exception e) {
        String errorMessage = "There was an ERROR!\n" + e.getLocalizedMessage();
        printWithBorder(errorMessage);
	}

    public void printSuccessfullyMarkedDoneMessage(Task task) {
        String taskSuccessfullyMarkedDoneMessage = "Very nice! I've marked this task as done:\n    " + task.toString();
        printWithBorder(taskSuccessfullyMarkedDoneMessage);
	}
    
    public void printTaskSuccessfullyAddedMessage(Task task, int taskCount) {
        String className = task.getClass().getSimpleName();
        String taskSuccessfullyAddedMessage = 
                "Alrighty! I have added this new " + className + ":\n"
                + "    " + task.toString() + "\n"
                + "You now have " + Integer.toString(taskCount) + " tasks in the list.";
        printWithBorder(taskSuccessfullyAddedMessage);
    }

    public void printTaskSuccessfullyDeletedMessage(Task task) {
        String taskSuccessfullyDeletedMessage = "Yay! I've successfuly deleted this task:\n    " 
                + task.toString();
        printWithBorder(taskSuccessfullyDeletedMessage);
	}

	public void showSavingError(Exception e) {
        String errorMessage = "ERROR saving data!\n" + e.getLocalizedMessage() + e.toString();
        printWithBorder(errorMessage);
	}

    public void displayExitMessage() {
        String exitMessage = "Sad to see you go! ): See you soon!";
        printWithBorder(exitMessage);
    }
}