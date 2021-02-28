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
		System.out.println("Bye. Hope to see you again soon!");
	}
}
