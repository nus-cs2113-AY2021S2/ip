import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * print list as a checklist when command=list
     */
    public void printList() {
        Ui.printDash();
        if (tasks.size() != 0) {
            Ui.validListMessage();
            for (int i = 1; i <= tasks.size(); ++i) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        } else {
            Ui.emptyListMessage();
        }
        Ui.printDash();
    }

    /**
     * finds keyword from list of tasks
     *
     * @param filterString
     */
    public void filterList(String filterString) {
        Ui.printDash();
        tasks.stream()
                .filter((s) -> s.getDescription().contains(filterString))
                .forEach(s -> System.out.println(s.toString()));
        Ui.printDash();
    }

    /**
     * adds tasks into Array List
     * @param line
     */
    public void addTasks(String line) {
        Ui.printDash();
        String[] action = line.split(" ");
        if (action[0].equals("todo")) {
            try {
                line = line.substring(Duke.TODO_LENGTH).trim();
                runTodo(line);
            } catch (NullPointerException | EmptyLineException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! Please enter a valid command.");
                Ui.printDash();
                return;
            }
        } else if (action[0].equals("deadline")) {
            try {
                line = line.substring(Duke.DEADLINE_LENGTH);
                runDeadline(line);
            } catch (EmptyLineException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! Please enter a valid command.");
                Ui.printDash();
                return;
            }
        } else if (action[0].contains("event")) {
            try {
                line = line.substring(Duke.EVENT_LENGTH);
                runEvent(line);
            } catch (EmptyLineException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! Please enter a valid command.");
                Ui.printDash();
                return;
            }
        } else {
            Ui.invalidCommandMessage();
            Ui.printDash();
            return;
        }
        Ui.addedTaskMessage();
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        Ui.printDash();
    }

    /**
     * remove tasks from Array List
     *
     * @param command
     */
    public void deleteTasks(String command) {
        Ui.printDash();
        command = command.replace("delete", " ");
        command = command.strip();
        try {
            int count = Integer.parseInt(command);
            --count; // array starts from 0
            System.out.println("\tNoted. I've removed this task:\n" + tasks.get(count).toString());

            tasks.remove(tasks.get(count));
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
            Ui.printDash();
        } catch (NumberFormatException e) {
            Ui.invalidTaskNumberMessage();
            Ui.printDash();
        }
    }

    /**
     * update list when tasks are done
     *
     * @param command
     */
    public void taskCompleted(String command) {
        Ui.printDash();
        int count;
        command = command.replace("done", " ");
        command = command.strip();
        count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks.get(count).markAsDone();

        try {
            count = Integer.parseInt(command); // convert string 2 into int 2
        } catch (NumberFormatException e) {
            Ui.emptyTaskNumberMessage();
            Ui.printDash();
            return;
        }
        try {
            --count; // array starts from 0
            tasks.get(count).markAsDone();
            Ui.taskIsDoneMessage();
            System.out.println(tasks.get(count).toString());
        } catch (NullPointerException e) {
            Ui.invalidTaskNumberMessage();
            Ui.printDash();
            return;
        }
        Ui.printDash();
    }

    /**
     *
     * @param description
     */
    public void runDeadline(String description) throws EmptyLineException {
        String[] details = description.trim().split("/by");
        if (details.length != 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            throw new EmptyLineException();
        }
        tasks.add(new Deadline(details[0], details[1]));
    }

    /**
     *
     * @param description
     * @throws EmptyLineException
     */
    public void runTodo(String description) throws EmptyLineException {
        if (description.length() == 0) {
           throw new EmptyLineException();
        }
        tasks.add(new Todo(description));
    }

    /**
     *
     * @param description
     */
    public void runEvent(String description) throws EmptyLineException {
        String[] details = description.split("/at");
        if (details.length != 2 || details[0].trim().isEmpty() || details[1].trim().isEmpty()) {
            throw new EmptyLineException();
        }
        tasks.add(new Event(details[0].trim(), details[1]));
    }


}
