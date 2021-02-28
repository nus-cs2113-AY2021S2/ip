import java.util.ArrayList;

/**
 * TaskList
 * This class encompasses methods to modify an ArrayList
 * of tasks for the Duke Application.
 *
 * @author Calvin
 * @version 0.2
 * @since 2021-02-28
 */
public class TaskList {
	private static final int MAX_TASKS = 100;
	private ArrayList<Task> tasks;

	Ui ui = new Ui();

	/**
	 * Constructor for the TaskList object needed by Duke.
	 * Creates an Arraylist of size 100.
	 */
	public TaskList(){
		tasks = new ArrayList<Task>(MAX_TASKS);
	}

	/**
	 * Adds task object to ArrayList
	 *
	 * @param task task object created by user or loaded from save file
	 */
	public void add(Task task){
		tasks.add(task);
	}

	/**
	 * Retrieves task object from ArrayList.
	 *
	 * @param indexOfTask the index which represents the position of the desired task object in the ArrayList
	 * @return desired task object
	 */
	public Task get(int indexOfTask){
		Task task;
		task = tasks.get(indexOfTask);
		return task;
	}

	/**
	 * Retrieves number of task objects in the list.
	 *
	 * @return number of task objects in the list.
	 */
	public int size(){
		return tasks.size();
	}

	/**
	 * Creates Todo object based on user input.
	 *
	 * @param userInput string that user has entered
	 */
	public void addTodo(Parser userInput) {
		String taskDescription = userInput.getTaskDescription();
		int noOfTasks;
		if(taskDescription != null){
			tasks.add(new Todo(taskDescription));
			noOfTasks = tasks.size();
			ui.taskAddedMessage(tasks, noOfTasks);
		}
	}

	/**
	 * Creates Deadline object based on user input.
	 *
	 * @param userInput string that user has entered
	 */
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

	/**
	 * Creates Event object based on user input.
	 *
	 * @param userInput string that user has entered
	 */
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

	/**
	 * Prints task objects in the list.
	 */
	public void printList() {
		int noOfTasks = tasks.size();
		if(noOfTasks > 0) {
			ui.printList(tasks,noOfTasks);
		} else {
			ui.noTaskInListMessage();
		}
	}

	/**
	 * Marks a task object as done based on user input.
	 *
	 * @param userInput string that user has entered
	 */
	public void markAsDone(Parser userInput) {
		int taskIndex = userInput.getTaskIndex();
		if(taskIndex >= 0){
			tasks.get(taskIndex).setDone();
			ui.markedAsDoneMessage(tasks, taskIndex);
		}
	}

	/**
	 * Deletes a task object based on user input.
	 *
	 * @param userInput string that user has entered
	 */
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

	/**
	 * Finds task objects based on keyword.
	 *
	 * @param userInput keyword that user has entered
	 */
	public void findTask(Parser userInput) {
		String keyword = userInput.getKeyword();
		int noOfTasks = tasks.size();
		ArrayList<Task> matchedTasks = new ArrayList<>();
		if(noOfTasks <= 0){
			ui.noTaskInListMessage();
		} else if(keyword != null){
			for(int i = 0; i < noOfTasks; i++){
				Task taskToCheck = tasks.get(i);
				if(taskToCheck.getDescription().contains(keyword)){
					matchedTasks.add(taskToCheck);
				}
			}
			int noOfMatches = matchedTasks.size();
			if (noOfMatches > 0){
				ui.printMatchedList(matchedTasks, matchedTasks.size());
			} else {
				ui.noMatchMessage();
			}
		}
	}
}
