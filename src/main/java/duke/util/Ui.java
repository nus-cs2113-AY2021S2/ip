package duke.util;

import duke.task.Task;
import duke.task.TaskList;

public class Ui {
    
    public static final String COMMANDS = 
            "Commands:\n"
            + "    todo taskName\n"
            + "    deadline deadlineName /by time\n" 
            + "    event eventName /at time\n"
            + "    list\n"
            + "    done taskNumber\n"
            + "    delete taskNumber\n"
            + "    help\n"
            + "    bye\n";

    public void displayWelcomeMessage() {
        String logo = "         __    _    _              ____        __           \n"
                + "        / /_  (_)  (_)___ ___     / __ \\__  __/ /_____      \n"
                + "       / __ \\/ /  / / __ `__ \\   / / / / / / / //_/ _ \\     \n"
                + "      / / / / /  / / / / / / /  / /_/ / /_/ / ,< /  __/     \n"
                + "     /_/ /_/_/  /_/_/ /_/ /_/  /_____/\\__,_/_/|_|\\___/     \n";
        System.out.print(logo + "\n");
        System.out.print("What do you have to do today?\n");
        System.out.print(COMMANDS + "\n");
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
        String errorMessage = "There was an ERROR!\n" + e.getMessage();
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