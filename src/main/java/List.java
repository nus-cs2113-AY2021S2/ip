import java.util.ArrayList;

public class List {

    private ArrayList<Task> TaskList = new ArrayList<Task>();

    public void addTask(String inputTask) {
        Task userTask = new Task(inputTask);
        TaskList.add(userTask);
        taskAddedMessage(userTask);
    }

    public void addDeadline(String inputDeadline) {
        String[] deadlineTokens = inputDeadline.split("/by", 2);
        Deadline deadline = new Deadline(deadlineTokens[0].trim(), deadlineTokens[1].trim());
        TaskList.add(deadline);
        taskAddedMessage(deadline);
    }

    public void addEvent(String inputEvent) {
        String[] eventTokens = inputEvent.split("/at", 2);
        Event event = new Event(eventTokens[0].trim(), eventTokens[1].trim());
        TaskList.add(event);
        taskAddedMessage(event);
    }

    public void printList() {
        if (TaskList.size() == 0) {
            System.out.println("whoops nothing to see here");
            return;
        }
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < TaskList.size(); i++) {
            System.out.println( i+1 + ". " + TaskList.get(i).toString());
        }
    }

    public void markDone(int index) {
        TaskList.get(index-1).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(TaskList.get(index-1).toString());
    }

    public void deleteTask(int index) {
        Task toBeDeleted = TaskList.get(index-1);
        TaskList.remove(index-1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(toBeDeleted.toString());
        System.out.println("Now you have " + TaskList.size() + " tasks in the list");
    }

    private void taskAddedMessage(Task inputTask) {
        System.out.println("Got it. I've added this task: \n" + inputTask.toString());
        System.out.println("Now you have " + TaskList.size() + " tasks in the list");
    }
}
