import java.util.ArrayList;

/**
 * This class handles all displays required byt the duke application.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class Ui {

	/**
	 * Displays Duke Logo
	 */
	public static void printLogo(){
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println(logo);
	}

	/**
	 * Displays welcome message when user starts the app.
	 */
	public void startupMessage(){
		System.out.println("Hello from\n");
		printLogo();
		lineSeparator();
		System.out.println("\tHello! I'm Duke");
		System.out.println("\tWhat can I do for you?");
		lineSeparator();
	}

	/**
	 * Displays line
	 */
	public void lineSeparator(){
		System.out.println("\t____________________________________________________________");
	}

	/**
	 * Displays message that task has been successfully added and the number of tasks in the list.
	 *
	 * @param tasks ArrayList of task objects used in the app session
	 * @param noOfTasks number of tasks that is currently in the ArrayList
	 */
	public void taskAddedMessage(ArrayList<Task> tasks, int noOfTasks) {
		lineSeparator();
		System.out.println("\tGot it! I've added this task:");
		System.out.println("\t" + tasks.get(noOfTasks - 1).toString());
		System.out.printf("\tNow you have %d tasks in the list.\n", noOfTasks);
		lineSeparator();
	}

	/**
	 * Displays message that task has been successfully marked as done and the task details.
	 *
	 * @param tasks ArrayList of task objects used in the app session
	 * @param taskIndex position of task object in the ArrayList
	 */
	public void markedAsDoneMessage(ArrayList<Task> tasks, int taskIndex) {
		lineSeparator();
		System.out.println("\tNice! I've marked this task as done:");
		System.out.println("\t" + tasks.get(taskIndex).toString());
		lineSeparator();
	}

	/**
	 * Displays message that task has been successfully deleted.
	 *
	 * @param taskToDelete task object that has been deleted
	 * @param noOfTasks number of tasks in the ArrayList after task was deleted.
	 */
	public void taskDeletedMessage(Task taskToDelete, int noOfTasks) {
		lineSeparator();
		System.out.println("\tNoted. I've removed this task.");
		System.out.println("\t" + taskToDelete.toString());
		System.out.printf("\tNow you have %d tasks in the list.\n", noOfTasks);
		lineSeparator();
	}

	/**
	 * Displays message when user has ended the session.
	 */
	public void shutdownMessage() {
		lineSeparator();
		System.out.println("\tBye. Hope to see you again soon!");
		lineSeparator();
	}

	/**
	 * Displays tasks that the findTask method in the TaskList class has matched based on the user input.
	 *
	 * @param matchedTasks ArrayList of task objects matched the findTask method
	 * @param size size of the ArrayList of task objects matched the findTask method
	 */
	public void printMatchedList(ArrayList<Task> matchedTasks, int size) {
		lineSeparator();
		System.out.println("\tHere are the matching tasks in your list:");
		for (int i = 0; i < size; i++){
			System.out.printf("\t%d. %s\n", i + 1, matchedTasks.get(i).toString());
		}
		lineSeparator();
	}

	/**
	 *  Displays error message when user tries to print an empty ArrayList.
	 */
	public void noTaskInListMessage() {
		lineSeparator();
		System.out.println("\tYou have no tasks to in your list.");
		lineSeparator();
	}

	/**
	 * Displays error message when findTask method in TaskList class returns no matches based on the user input.
	 */
	public void noMatchMessage() {
		lineSeparator();
		System.out.println("\tNo matches found!");
		lineSeparator();
	}

	/**
	 * Displays all tasks in the ArrayList
	 *
	 * @param tasks ArrayList of tasks used in the session
	 * @param noOfTasks size of the ArrayList of tasks used in the session
	 */
	public void printList(ArrayList<Task> tasks, int noOfTasks) {
		lineSeparator();
		System.out.println("\tHere are the tasks in your list");
		for (int i = 0; i < noOfTasks; i++){
			System.out.printf("\t%d. %s\n", i + 1, tasks.get(i).toString());
		}
		lineSeparator();
	}

	/**
	 * Displays error message when user inputs the an unrecognised input.
	 */
	public void invalidInputMessage() {
		lineSeparator();
		System.out.println("\tSorry! Input not recognised, please try again.");
		lineSeparator();
	}

	/**
	 * Displays error message when application is unable to find /by when user is creating a deadline.
	 */
	public void missingBy() {
		lineSeparator();
		System.out.println("\tOOPS!! The deadline of the task is missing!");
		System.out.println("\tFormat: deadline (description) /by (date time*)");
		System.out.println("\t* - optional");
		lineSeparator();
	}

	/**
	 * Displays error message when application is unable to find /as when user is creating an event.
	 */
	public void missingAt() {
		lineSeparator();
		System.out.println("\tOOPS!! The deadline of the task is missing!");
		System.out.println("\tFormat: event (description) /at (date time)");
		lineSeparator();
	}

	/**
	 * Displays error message when user enters more than one keyword for the findTask method in the TaskList class.
	 */
	public void invalidKeywordMessage() {
		lineSeparator();
		System.out.println("\tOOPS!! You have entered more than one keyword.");
		System.out.println("\tFormat: find (one keyword)");
		lineSeparator();
	}
}
