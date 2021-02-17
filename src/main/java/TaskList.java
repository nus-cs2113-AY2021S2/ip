import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    protected static final String LINE_STRING = "____________________________________________________________\n";

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /*
     * Prints out message by default unless supplied with 2nd arg false
     */
    public void addToList(Task newTask) {
        this.addToList(newTask,true);
    }

    public void addToList(Task newTask, Boolean isPrinting) {
        tasks.add(newTask);
        if (isPrinting) {
            addToListMessage(tasks.size() - 1);
        }
    }

    private void addToListMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("Say no more fam. The task is added:\n  " + this.getStatus(index));
        System.out.println((index + 1) + " tasks in the list.");
        System.out.println(LINE_STRING);
    }

    // Prints contents of list
    public void printList() {
        System.out.print(LINE_STRING);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + this.getStatus(i));
        }
        System.out.println(LINE_STRING);
    }

    // gets a status printed
    private String getStatus(int index) {
        return tasks.get(index).getTypeString() + tasks.get(index).getCheckbox() +
                " " + tasks.get(index).toString();
    }

    public int getSize() {
        return tasks.size();
    }


    public void completeTask(int number) {
        completeTask(number, true);
    }

    public void completeTask(int number, boolean isPrinting) {
        int index = number - 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);
        if (isPrinting) {
            completeTaskMessage(index);
        }
    }

    private void completeTaskMessage(int index) {
        System.out.print(LINE_STRING);
        System.out.println("Task marked as done, gg ez");
        System.out.println("  " + this.getStatus(index));
        System.out.println(LINE_STRING);
    }
}
