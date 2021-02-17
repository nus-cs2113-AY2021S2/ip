package duke.command;
import duke.task.*;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    private static final String lineSpacing = "\t----------------------------------";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String filePath = "./src/main/java/duke/data/tasks.txt";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        initialGreetings();
        readFileTasks();
        echo(); // Method contains main while loop
        finalGreetings();
    }

    public static void initialGreetings() {
        String greetings = "\tHello! I'm Duke\n" + "\tWhat can I do for you?\n\n";
        System.out.println(lineSpacing);
        System.out.println(greetings);
        System.out.println(lineSpacing);
    }

    public static void finalGreetings() {
        String exitStatements = "\tBye. Hope to see you again soon!\n";
        System.out.println(lineSpacing);
        System.out.println(exitStatements);
        System.out.println(lineSpacing);
    }

    public static void readFileTasks() {
        try {
            File myTasks = new File(filePath);
            Scanner taskReader = new Scanner(myTasks);
            while (taskReader.hasNextLine()) {
                String task = taskReader.nextLine();
                initialiseTask(task);
            }
            taskReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File path was not found! No previous tasks were read");
        } catch (DukeExceptions e) {
            System.out.println("Tasks were not formatted properly when writing into file!");
        }
    }

    public static void writeFileTasks() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks) {
                String textToAdd = task.formatTaskToWrite();
                fw.write(textToAdd + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void initialiseTask(String task) throws DukeExceptions {
        List<String> taskList = Arrays.asList(task.split("<separator>"));
        String taskType = taskList.get(0);
        switch (taskType) {
        case "todo":
            if (taskList.size() != 3) {
                throw new DukeExceptions();
            }
            String todoName = taskList.get(1);
            boolean todoIsCompleted = Boolean.parseBoolean(taskList.get(2));
            ToDo newTodo = new ToDo(todoName, taskType);
            newTodo.setIsCompleted(todoIsCompleted);
            tasks.add(newTodo);
            break;
        case "deadline":
            if (taskList.size() != 4) {
                throw new DukeExceptions();
            }
            String deadlineName = taskList.get(1);
            String deadlineTimeConstraint = taskList.get(2);
            boolean deadlineIsCompleted = Boolean.parseBoolean(taskList.get(3));
            Deadline newDeadline = new Deadline(deadlineName, taskType, deadlineTimeConstraint);
            newDeadline.setIsCompleted(deadlineIsCompleted);
            tasks.add(newDeadline);
            break;
        case "event":
            if (taskList.size() != 4) {
                throw new DukeExceptions();
            }
            String eventName = taskList.get(1);
            String eventTimeConstraint = taskList.get(2);
            boolean eventIsCompleted = Boolean.parseBoolean(taskList.get(3));
            Event newEvent = new Event(eventName, taskType, eventTimeConstraint);
            newEvent.setIsCompleted(eventIsCompleted);
            tasks.add(newEvent);
            break;
        default:
            throw new DukeExceptions();
        }
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        while (!line.equals("bye")) {
            parseUserCommands(line);
            writeFileTasks();
            line = in.nextLine();
        }
    }

    public static void parseUserCommands(String line) {
        if (line.equals("")) {
            return;
        }

        List<String> userCommands = Arrays.asList(line.split(" "));
        if (userCommands.size() < 1) {
            return;
        }

        if (userCommands.get(0).equals("list")) {
            displayTaskList();
        } else if (userCommands.get(0).equals("done")) {
            markCompletedTasks(userCommands.subList(1, userCommands.size()));
        } else if (userCommands.get(0).equals("delete")) {
            deleteTask(userCommands.subList(1, userCommands.size()));
        } else {
            try {
                appendNewTask(userCommands);
            } catch (DukeExceptions e) {
                System.out.println("Invalid instructions detected in the following line: " + line);
                System.out.println("Clarification on the use of this app can be found ...");
                System.out.println(lineSpacing);
            }
        }
    }

    public static void displayTaskList() {
        int counter = 0;
        System.out.println(lineSpacing);
        System.out.println("\tHere are the tasks in your list:");
        while (counter < tasks.size()) {
            Task task = tasks.get(counter);
            System.out.print("\t" + (counter+1) + ".");
            task.printTask();
            counter ++;
        }
        System.out.println(lineSpacing);
    }

    public static void markCompletedTasks(List<String> taskIndexes) {
        System.out.println(lineSpacing);
        System.out.println("\tNice! I've marked this task as done:");

        for (String index: taskIndexes) {
            int indexInt;
            try {
                indexInt = Integer.parseInt(index) - 1;
                if (indexInt >= tasks.size()){
                    throw new DukeExceptions();
                }
                else {
                    tasks.get(indexInt).setIsCompleted(true);
                    System.out.print("\t");
                    tasks.get(indexInt).printTask();
                }
            } catch (DukeExceptions e) {
                System.out.println("\tTask number " + index + " does not exist");
                System.out.println(lineSpacing);
            } catch (NumberFormatException e) {
                System.out.println("\tInvalid Expression! Must provide task numbers (" + index + ")");
                System.out.println(lineSpacing);
            }
        }
        System.out.println(lineSpacing);
    }

    public static void appendNewTask(List<String> userCommands) throws DukeExceptions{
        System.out.println(lineSpacing);
        switch (userCommands.get(0)) {
        case "todo":
            List<String> taskNameTodo = userCommands.subList(1, userCommands.size());
            tasks.add(new ToDo(
                    String.join(" ", taskNameTodo),
                    userCommands.get(0)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        case "deadline":
            int indexDeadline = userCommands.indexOf("/by");
            if (indexDeadline == -1) {
                throw new DukeExceptions();
            }
            List<String> taskNameDeadline = userCommands.subList(1, indexDeadline);
            List<String> timeConstraintDeadline = userCommands.subList(indexDeadline+1, userCommands.size());
            tasks.add(new Deadline(
                    String.join(" ", taskNameDeadline),
                    userCommands.get(0),
                    String.join(" ", timeConstraintDeadline)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        case "event":
            int indexEvent = userCommands.indexOf("/at");
            if (indexEvent == -1) {
                throw new DukeExceptions();
            }
            List<String> taskNameEvent = userCommands.subList(1, indexEvent);
            List<String> timeConstraintEvent = userCommands.subList(indexEvent+1, userCommands.size());
            tasks.add(new Event(
                    String.join(" ", taskNameEvent),
                    userCommands.get(0),
                    String.join(" ", timeConstraintEvent)
            ));
            System.out.println("\tGot it. I've added this task: ");
            break;
        default:
            throw new DukeExceptions();
        }
        System.out.print("\t  ");
        tasks.get(tasks.size()-1).printTask();
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        System.out.println(lineSpacing);
    }

    public static void deleteTask(List<String> taskIndexes) {
        System.out.println(lineSpacing);
        System.out.println("\tNoted. I've removed this task:");

        List<Integer> tasksIndexToDelete = new ArrayList<>();

        for (String index: taskIndexes) {
            int indexInt;
            try {
                indexInt = Integer.parseInt(index) - 1;
                if (indexInt >= tasks.size()){
                    throw new DukeExceptions();
                }
                else {
                    Task deletedTask = tasks.get(indexInt);
                    System.out.print("\t");
                    tasks.get(indexInt).printTask();
                    tasksIndexToDelete.add(indexInt);
                }
            } catch (DukeExceptions e) {
                System.out.println("\tTask number " + index + " does not exist");
            } catch (NumberFormatException e) {
                System.out.println("\tInvalid Expression! Must provide task numbers (" + index + ")");
            }
        }
        Collections.sort(tasksIndexToDelete, Collections.reverseOrder());
        for (Integer index: tasksIndexToDelete) {
            tasks.remove((int)index);
        }
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        System.out.println(lineSpacing);
    }
}
