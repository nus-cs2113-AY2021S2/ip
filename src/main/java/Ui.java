import java.util.ArrayList;

public class Ui {

	public static void printLogo(){
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println(logo);
	}

	public void startupMessage(){
		System.out.println("Hello from\n");
		printLogo();
		lineSeparator();
		System.out.println("\tHello! I'm Duke");
		System.out.println("\tWhat can I do for you?");
		lineSeparator();
	}

	public void lineSeparator(){
		System.out.println("\t____________________________________________________________");
	}

	public void taskAddedMessage(ArrayList<Task> tasks, int noOfTasks) {
		lineSeparator();
		System.out.println("\tGot it! I've added this task:");
		System.out.println("\t" + tasks.get(noOfTasks - 1).toString());
		System.out.printf("\tNow you have %d tasks in the list.\n", noOfTasks);
		lineSeparator();
	}

	public void markedAsDoneMessage(ArrayList<Task> tasks, int taskIndex) {
		lineSeparator();
		System.out.println("Nice! I've marked this task as done:");
		System.out.println("\t" + tasks.get(taskIndex).toString());
		lineSeparator();
	}

	public void taskDeletedMessage(Task taskToDelete, int noOfTasks) {
		lineSeparator();
		System.out.println("Noted. I've removed this task.");
		System.out.println("\t" + taskToDelete.toString());
		System.out.printf("Now you have %d tasks in the list.\n", noOfTasks);
		lineSeparator();
	}

	public void shutdownMessage() {
		lineSeparator();
		System.out.println("Bye. Hope to see you again soon!");
		lineSeparator();
	}

	public void printMatchedList(ArrayList<Task> matchedTasks, int size) {
		lineSeparator();
		System.out.println("Here are the matching tasks in your list:");
		for (int i = 0; i < size; i++){
			System.out.printf("\t%d. %s\n", i + 1, matchedTasks.get(i).toString());
		}
	}

	public void noTaskInListMessage() {
		lineSeparator();
		System.out.println("You have no tasks to in your list.");
		lineSeparator();
	}

	public void noMatchMessage() {
		lineSeparator();
		System.out.println("No matches found!");
		lineSeparator();
	}

	public void printList(ArrayList<Task> tasks, int noOfTasks) {
		lineSeparator();
		System.out.println("Here are the tasks in your list");
		for (int i = 0; i < noOfTasks; i++){
			System.out.printf("\t%d. %s\n", i + 1, tasks.get(i).toString());
		}
		lineSeparator();
	}

	public void invalidInputMessage() {
		lineSeparator();
		System.out.println("Sorry! Input not recognised, please try again.");
		lineSeparator();
	}

	public void missingBy() {
		lineSeparator();
		System.out.println("OOPS!! The deadline of the task is missing!");
		System.out.println("Format: deadline (description) /by (date time*)");
		System.out.println("* - optional");
		lineSeparator();
	}

	public void missingAt() {
		lineSeparator();
		System.out.println("OOPS!! The deadline of the task is missing!");
		System.out.println("Format: event (description) /at (date time)");
		lineSeparator();
	}

	public void invalidKeywordMessage() {
		lineSeparator();
		System.out.println("OOPS!! You have entered more than one keyword.");
		System.out.println("Format: find (one keyword)");
		lineSeparator();
	}
}
