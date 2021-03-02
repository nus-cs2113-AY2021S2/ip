import java.util.ArrayList;

/**
 * Holds the task list and functions for modification of list and tasks.
 */
public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * Instantiates a TaskList to take the values from storage load
     *
     * @param load tasks from storage load
     */
    public TaskList(ArrayList<Task> load) {
        this.tasks = load;
    }

    /**
     * Instantiates a new TaskList
     */
    public TaskList() {
    }

    /**
     * Adds a task to the list
     *
     * @param tasks TaskList instantiation
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     * @param inputTask user parsed string input
     */
    public void addTask(TaskList tasks, Ui ui, Storage storage, String inputTask) {
        Task userTask = new Task(inputTask);
        this.tasks.add(userTask);
        Ui.taskAddedMessage(userTask, tasks.getSize());
    }

    /**
     * Adds a deadline to the list
     *
     * @param tasks TaskList instantiation
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     * @param inputDeadline user parsed string input
     */
    public void addDeadline(TaskList tasks, Ui ui, Storage storage, String inputDeadline) {
        //splits the string into description and time
        String[] deadlineTokens = inputDeadline.split("/by", 2);
        try {
            // if the tokens to not match the required amount, throw error
            if (deadlineTokens.length == 1) {
                throw new DukeException("deadline");
            }
            Deadline deadline = new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim());
            this.tasks.add(deadline);
            Ui.taskAddedMessage(deadline, tasks.getSize());
        } catch (DukeException e) {
            e.getError("deadline");
        }
    }

    /**
     * Adds an event to the list
     *
     * @param tasks TaskList instantiation
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     * @param inputEvent user parsed string input
     */
    public void addEvent(TaskList tasks, Ui ui, Storage storage, String inputEvent) {
        //splits the string into description and time
        String[] eventTokens = inputEvent.split("/at", 2);
        try {
            // if the tokens to not match the required amount, throw error
            if (eventTokens.length == 1) {
                throw new DukeException("event");
            }
            Event event = new Event(eventTokens[0].trim(), eventTokens[1].trim());
            this.tasks.add(event);
            Ui.taskAddedMessage(event, tasks.getSize());
        } catch (DukeException e) {
            e.getError("event");
        }
    }

    /**
     * prints the contents of the list
     */
    public void printList() {
        if (tasks.size() == 0) {
            Ui.emptyListMessage();
        } else {
            Ui.notEmptyListMessage();
            for (int i = 0; i < tasks.size(); i++) {
                Ui.printListItems(i, tasks.get(i).toString());
            }
        }
    }

    public void findTasks(String stringToFind) {
        boolean tasksFound = false;
        Ui.foundListMessage();
        for (int i = 0; i < tasks.size(); i++) {
            String description = TaskList.tasks.get(i).getDescription();
            if (description.contains(stringToFind)) {
                Ui.foundTasks(i+1, TaskList.tasks.get(i).toString());
                tasksFound = true;
            }
        }
        if (!tasksFound) {
            Ui.noMatchingTasks();
        }
    }

    /**
     * Marks a task as done
     *
     * @param tasks TaskList instantiation
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     * @param index user parsed integer input
     */
    public void markDone(TaskList tasks, Ui ui, Storage storage, int index) {
        // location of the task will be at -1 since array is 0 based index
        int taskNumber = index-1;
        try {
            // checks if the task exists
            if (index <= 0 || index > tasks.getSize()) {
                throw new DukeException("done");
            }
            this.tasks.get(taskNumber).markAsDone();
            Ui.markAsDoneMessage(this.tasks.get(taskNumber).toString());
        } catch (DukeException e) {
            e.getError("done");
        }
    }

    /**
     * Deletes a task
     *
     * @param tasks TaskList instantiation
     * @param ui Ui instantiation
     * @param storage Storage instantiation
     * @param index user parsed integer input
     */
    public void deleteTask(TaskList tasks, Ui ui, Storage storage, int index) {
        // location of the task will be at -1 since array is 0 based index
        int taskNumber = index-1;
        try {
            // checks if the task exists
            if (index <= 0 || index > tasks.getSize()) {
                throw new DukeException("delete");
            }
            Task toBeDeleted = this.tasks.get(taskNumber);
            this.tasks.remove(taskNumber);
            Ui.deleteTaskMessage(toBeDeleted.toString(), index);
        } catch (DukeException e) {
            e.getError("delete");
        }
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Takes the header for each task and returns the task type
     *
     * @param simpleType the header for each task
     * @return type of task
     */
    public String formatType(String simpleType){
        String taskType = null;
        if (simpleType.contains("E")) {
            taskType = "event";
        } else if (simpleType.contains("D")) {
            taskType = "deadline";
        } else if (simpleType.contains("T")) {
            taskType = "todo";
        }
        return taskType;
    }

    /**
     * formats the task done to save file requirements
     *
     * @param isDone the status of whether the task is done
     * @return desired true/false format for saving
     */
    public String formatIsDone(String isDone) {
        return (isDone.contains("\u2713") ? "1" : "0");
    }

    /**
     * returns the description formatted for saving
     *
     * @param index the index of the task in the list
     * @return the formatted description and time
     */
    public String formatDescription(int index) {
        return (tasks.get(index).toSaveFormat());
    }

    /**
     * formats the tasks in the task list for saving purposes
     *
     * @param index of the current task being saved
     * @return formatted tasks for saving
     */
    public String toSaveFile(int index) {
        //gets the string from tasks and splits it into tokens
        String[] taskView = tasks.get(index).toString().split("]");
        //first token is the type of task ie, todo, deadline, event
        String simpleType = taskView[0];
        //second token is whether the task is done
        String isDone = taskView[1];
        String[] taskFormat = new String[3];
        //processing each section of the string to be returned
        taskFormat[0] = formatType(simpleType);
        taskFormat[1] = formatIsDone(isDone);
        taskFormat[2] = formatDescription(index);
        return (taskFormat[0] + " | " + taskFormat[1] + " | " + taskFormat[2]);
    }
}