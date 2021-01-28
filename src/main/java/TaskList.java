import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    // Adds task to list and prints the input in console
    public void addToList(String input) {
        Task newTask = new Task(input);
        tasks.add(newTask);
        String echoString = "____________________________________________________________\n" +
                "added: " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(echoString);
    }

    // Prints contents of list
    public void printList() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i).getCheckbox() + " " + tasks.get(i).getLabel());
        }
        System.out.println("____________________________________________________________\n");
    }

    // Set flag isDone in Task class of index = number - 1
    public void completeTask(int number) {
        int index = number - 1; // adjust for the list label starting from 1
        tasks.get(index).isDone(true);

        System.out.println("____________________________________________________________");
        System.out.println("Winner winner chicken dinner 🐔 The task is as good as done");
        System.out.println("   [✔] " + tasks.get(index).getLabel());
        System.out.println("____________________________________________________________\n");

    }
}
