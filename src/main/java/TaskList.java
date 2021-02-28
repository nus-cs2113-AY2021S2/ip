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
		int noOfTasks = tasks.size();
		if(noOfTasks > 0) {
			ui.printList(tasks,noOfTasks);
		} else {
			ui.noTaskInListMessage();
		}
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
