import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // print list as a checklist when command=list
    public void printList() {
        Duke.printDash();
        if (tasks.size() != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 1; i <= tasks.size(); ++i) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        } else {
            System.out.println("\tYou do not have any pending task.");
        }
        Duke.printDash();
    }

    public void addTasks(String description) {
        Duke.printDash();
        if (description.contains("todo")) {
            try {
                description = description.substring(Duke.TODO_LENGTH);
                runTodo(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                Duke.printDash();
                return;
            }
        } else if (description.contains("deadline")) {
            try {
                description = description.substring(Duke.DEADLINE_LENGTH);
                runDeadline(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
                Duke.printDash();
                return;
            }
        } else if (description.contains("event")) {
            try {
                description = description.substring(Duke.EVENT_LENGTH);
                runEvent(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a event cannot be empty");
                Duke.printDash();
                return;
            }
        } else {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            Duke.printDash();
            return;
        }
        System.out.println("\tGot it. I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        Duke.printDash();
    }

    // remove tasks from list
    public void deleteTasks(String command) {
        command = command.replace("delete", " ");
        command = command.strip();

        int count = Integer.parseInt(command);
        --count; // array starts from 0
        System.out.println("\tNoted. I've removed this task:\n" + tasks.get(count).toString());

        tasks.remove(tasks.get(count));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
    }

    // update list when tasks are done
    public void taskCompleted(String command) {
        int count;
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks.get(count).markAsDone();

        try {
            count = Integer.parseInt(command); // convert string 2 into int 2
        } catch (NumberFormatException e) {
            System.out.println("\tOOPS!!! Please indicate task number");
            Duke.printDash();
            return;
        }
        try {
            --count; // array starts from 0
            tasks.get(count).markAsDone();
            System.out.println("\tNice! I've marked this task as done: ");
            System.out.println(tasks.get(count).toString());
        } catch (NullPointerException e) {
            System.out.println("\tOOPS!!! Please enter valid task number");
            Duke.printDash();
            return;
        }
        Duke.printDash();
    }

    public void runDeadline(String description) {
        String[] details = description.split(" /by");
        tasks.add(new Deadline(details[0], details[1]));
    }

    public void runTodo(String description) {
        tasks.add(new Todo(description));
    }

    public void runEvent(String description) {
        String[] details = description.split(" /at");
        tasks.add(new Event(details[0], details[1]));
    }


}
