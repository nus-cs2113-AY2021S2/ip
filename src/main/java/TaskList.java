import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(ArrayList<Task> load) {
        this.tasks = load;
    }

    public TaskList() {
    }

    public void addTask(TaskList tasks, Ui ui, Storage storage, String inputTask) {
        Task userTask = new Task(inputTask);
        this.tasks.add(userTask);
        Ui.taskAddedMessage(userTask, tasks.getSize());
    }

    public void addDeadline(TaskList tasks, Ui ui, Storage storage, String inputDeadline) {
        String[] deadlineTokens = inputDeadline.split("/by", 2);
        Deadline deadline = new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim());
        this.tasks.add(deadline);
        Ui.taskAddedMessage(deadline, tasks.getSize());
    }

    public void addEvent(TaskList tasks, Ui ui, Storage storage, String inputEvent) {
        String[] eventTokens = inputEvent.split("/at", 2);
        Event event = new Event(eventTokens[0].trim(), eventTokens[1].trim());
        this.tasks.add(event);
        Ui.taskAddedMessage(event, tasks.getSize());
    }

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

    public void markDone(TaskList tasks, Ui ui, Storage storage, int index) {
        this.tasks.get(index-1).markAsDone();
        Ui.markAsDoneMessage(this.tasks.get(index-1).toString());
    }

    public void deleteTask(TaskList tasks, Ui ui, Storage storage, int index) {
        Task toBeDeleted = this.tasks.get(index-1);
        this.tasks.remove(index-1);
        Ui.deleteTaskMessage(toBeDeleted.toString(), index);
    }

    public void markDoneFromSave(String isDone) {
        if (isDone.equals("1")) {
            tasks.get(tasks.size()-1).markAsDone();
        }
    }

    public int getSize() {
        return tasks.size();
    }

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

    public String formatIsDone(String isDone) {
        return (isDone.contains("\u2713") ? "1" : "0");
    }

    public String formatDescription(int index) {
        return (tasks.get(index).toSaveFormat());
    }

    public String toSaveFile(int index) {
        String[] taskView = tasks.get(index).toString().split("]");
        String simpleType = taskView[0];
        String isDone = taskView[1];
        String[] taskFormat = new String[3];
        taskFormat[0] = formatType(simpleType);
        taskFormat[1] = formatIsDone(isDone);
        taskFormat[2] = formatDescription(index);
        return (taskFormat[0] + " | " + taskFormat[1] + " | " + taskFormat[2]);
    }

}
