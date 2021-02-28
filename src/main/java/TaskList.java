import java.util.ArrayList;

public class TaskList {
	private static final int MAX_TASKS = 100;
	private ArrayList<Task> tasks;

	Ui ui = new Ui();

	public TaskList(){
		tasks = new ArrayList<Task>(MAX_TASKS);
	}

	public void add(Task task){
		tasks.add(task);
	}

	public Task get(int indexOfTask){
		Task task = tasks.get(indexOfTask);
		return task;
	}

	public int size(){
		return tasks.size();
	}

	public void addTodo(Parser userInput) {
		String taskDescription = userInput.getTaskDescription();
		int noOfTasks;
		if(taskDescription != null){
			tasks.add(new Todo(taskDescription));
			noOfTasks = tasks.size();
			ui.taskAddedMessage(tasks, noOfTasks);
		}
	}

	public void addDeadline(Parser userInput) {
		String taskDescription = userInput.getDeadlineDescription();
		String by = userInput.getBy();
		int noOfTasks;
		if (taskDescription != null & by != null){
			tasks.add(new Deadline(taskDescription,by));
			noOfTasks = tasks.size();
			ui.taskAddedMessage(tasks, noOfTasks);
		}

	}

	public void addEvent(Parser userInput) {
		String taskDescription = userInput.getEventDescription();
		String at = userInput.getAt();
		int noOfTasks;
		if (taskDescription != null & at != null){
			tasks.add(new Event(taskDescription,at));
			noOfTasks = tasks.size();
			ui.taskAddedMessage(tasks, noOfTasks);
		}
	}

	public void printList() {
		ui.lineSeparator();
		int noOfTasks = tasks.size();
		if(noOfTasks > 0) {
			System.out.println("Here are the tasks in your tasks:");
			for (int i = 0; i < noOfTasks; i++) {
				System.out.printf("\t%d. %s\n", i + 1, tasks.get(i).toString());
			}
		} else {
			System.out.println("You have no tasks to complete.");
		}
		ui.lineSeparator();
	}

	public void markAsDone(Parser userInput) {
		int taskIndex = userInput.getTaskIndex();
		if(taskIndex >= 0){
			tasks.get(taskIndex).setDone();
			ui.markedAsDoneMessage(tasks, taskIndex);
		}
	}

	public void deleteTask(Parser userInput) {
		int taskIndex = userInput.getTaskIndex();
		int noOfTasks;
		if(taskIndex >= 0){
			Task taskToDelete = tasks.get(taskIndex);
			tasks.remove(taskIndex);
			noOfTasks = tasks.size();
			ui.taskDeletedMessage(taskToDelete, noOfTasks);
		}
	}
}
