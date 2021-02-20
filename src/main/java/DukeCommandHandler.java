import java.util.ArrayList;

public class DukeCommandHandler {
    private static final String DIVIDER = "\t_______________________________\n";
    protected static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String LOGO =
            "▒▒▒▒▒▒▒█▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▒▒▒▒▒▓▒▒▓▒▒▒▒▒▒▒░█\n"
                    + "▒▒▒▒▒▒▒█░▒▒▓▒▒▒▒▒▒▒▒▒▄▄▒▓▒▒░█░▄▄\n"
                    + "▒▒▄▀▀▄▄█░▒▒▒▒▒▒▓▒▒▒▒█░░▀▄▄▄▄▄▀░░█\n"
                    + "▒▒█░░░░█░▒▒▒▒▒▒▒▒▒▒▒█░░░░░░░░░░░█\n"
                    + "▒▒▒▀▀▄▄█░▒▒▒▒▓▒▒▒▓▒█░░░█▒░░░░█▒░░█\n"
                    + "▒▒▒▒▒▒▒█░▒▓▒▒▒▒▓▒▒▒█░░░░░░░▀░░░░░█\n"
                    + "▒▒▒▒▒▄▄█░▒▒▒▓▒▒▒▒▒▒▒█░░█▄▄█▄▄█░░█\n"
                    + "▒▒▒▒█░░░█▄▄▄▄▄▄▄▄▄▄█░█▄▄▄▄▄▄▄▄▄█\n"
                    + "▒▒▒▒█▄▄█░░█▄▄█░░░░░░█▄▄█░░█▄▄█\n"
                    + " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    protected static void greeting() {
        System.out.print(LOGO);
        System.out.print("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    protected static String checkSingular() {
        if (tasks.size() > 1) {
            return " tasks ";
        }
        return " task ";
    }

    protected static void listTask() {
        System.out.print(DIVIDER);
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println("\t" + (i + 1) + "." +
                    tasks.get(i).toString());
        }
        System.out.print(DIVIDER);
    }

    protected static void markTaskDone(String input) {
        //task number can be found on 5th index of input
        int taskNumber = Integer.parseInt(input.substring(5));

        //to check if tasks exists
        if(taskNumber<1 || taskNumber>tasks.size()){
            DukeException.invalidTask();
            return;
        }

        Task currentTask = tasks.get(taskNumber - 1);
        currentTask.markAsDone();
        Duke.storage.saveData();
        System.out.print(DIVIDER + "\tNice! I've marked this task as done:" +
                "\n\t" + currentTask.toString() + "\n" + DIVIDER);
    }

    protected static void addTask(String input)
            throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("add");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "add" command
            DukeException.addTaskInvalidDescription();
            return;
        }
        if (taskInfo.isBlank()) {
            //if input after "add" command is only whitespace
            DukeException.addTaskInvalidDescription();
            return;
        }
        Task currentTask = new Task(taskInfo);
        tasks.add(currentTask);
        Duke.storage.saveData();
        System.out.print(DIVIDER + "\tadded " + currentTask.taskDescription + "\n" + DIVIDER);
    }

    protected static void addToDo(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("todo");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "to-Do" command
            DukeException.toDoInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "to-Do" command is only whitespace
            DukeException.toDoInvalidDescription();
            return;
        }

        Task currentTask = new ToDo(taskInfo);
        tasks.add(currentTask);
        Duke.storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n" +
                "\t\t" + currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list." + "\n" + DIVIDER);
    }

    protected static void addDeadline(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("deadline");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "deadline" command
            DukeException.deadlineInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "deadline" command is only whitespace
            DukeException.deadlineInvalidDescription();
            return;
        }

        String[] splitTaskInfo = taskInfo.split("/by", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String deadline = splitTaskInfo[1].trim();
        Task currentTask = new Deadline(taskDescription, deadline);
        tasks.add(currentTask);
        Duke.storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);
    }

    protected static void addEvent(String input) throws ArrayIndexOutOfBoundsException {
        String[] splitInput = input.split("event");
        String taskInfo;
        try {
            taskInfo = splitInput[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            //if no input after "event" command
            DukeException.eventInvalidDescription();
            return;
        }

        if (taskInfo.isBlank()) {
            //if input after "event" command is only whitespace
            DukeException.eventInvalidDescription();
            return;
        }
        String[] splitTaskInfo = taskInfo.split("/at", 2);
        String taskDescription = splitTaskInfo[0].trim();
        String timeDetails = splitTaskInfo[1].trim();
        Task currentTask = new Event(taskDescription, timeDetails);
        tasks.add(currentTask);
        Duke.storage.saveData();
        System.out.println(DIVIDER + "\tGot it. I've added this task:\n\t\t" +
                currentTask.toString());
        System.out.print("\tNow you have " + tasks.size() + checkSingular() + "in your list.\n" + DIVIDER);

    }

    protected static void deleteTask(String input) {
        //to add exception
        //task number can be found on 7th index of input
        int taskNumber = Integer.parseInt(input.substring(7));

        //to  check if task to delete exists
        if(taskNumber<1 || taskNumber>tasks.size()){
            DukeException.invalidTask();
            return;
        }

        Task currentTask = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber-1);
        Duke.storage.saveData();
        System.out.print(DIVIDER + "\tNoted. I've removed this task:" +
                "\n\t" + currentTask.toString() + "\n"
                + DIVIDER);
        System.out.print("\tNow you have " + (tasks.size()) + checkSingular() + "in your list.\n" + DIVIDER);

    }

    protected static void exitDuke() {
        System.out.print("Bye. Hope to see you again soon!\n");
    }

}
