package Command;

import ErrorHandling.EmptyDescription;
import ErrorHandling.OutOfBound;
import ErrorHandling.UnknownCommand;
import Class.Task;
import Class.Todo;
import Class.Deadline;
import Class.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Command {
    private static String setDescription(String[] subStrings, int slashIndex){
        String description = "";
        // Input the description of the task
        for(int i = 1;i < slashIndex;++i){
            description += subStrings[i];
            if(i != slashIndex-1){
                description += " ";
            }
        }

        return description;
    }

    private static String setTime(String[] subStrings, int slashIndex){
        String time = "";
        // Input the time for the task (will be skipped if there is no time parameter)
        for (int i = slashIndex + 1; i < subStrings.length; ++i) {
            time += subStrings[i];
            if(i != subStrings.length-1){
                time += " ";
            }
        }

        return time;
    }

    public static void readFile(List<Task> tasks) throws IOException, UnknownCommand, EmptyDescription {
        try {
            File saveFolder = new File("data");
            if (!saveFolder.exists()) {
                saveFolder.mkdir();
            }
            File saveFile = new File("data/tasks.txt");
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }

            Scanner s = new Scanner(saveFile);
            int isDone;

            while (s.hasNext()) {
                String input = s.nextLine();
                String[] subStrings = input.split(" ");
                int slashIndex = subStrings.length;
                // String description = input.substring(2, input.indexOf('|') - 1);
                for (int i = 0; i < subStrings.length; ++i) {
                    if (subStrings[i].equals("|")) {
                        slashIndex = i;
                        break;
                    }
                }
                String description = "", time = "";

                // Input the description of the task
                for(int i = 1;i < slashIndex;++i){
                    description += subStrings[i];
                    if(i != slashIndex-1){
                        description += " ";
                    }
                }

                // Input the time for the task (will be skipped if there is no time parameter)
                for (int i = slashIndex + 1; i < subStrings.length; ++i) {
                    time += subStrings[i];
                    if(i != subStrings.length-1){
                        time += " ";
                    }
                }

                // Check if the task is done
                isDone = Character.getNumericValue(input.charAt(0));

                switch (subStrings[1]) {
                case "todo":
                    Todo todo = new Todo(description);
                    if (isDone == 1) {
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "event":
                    Event event = new Event(description, time);
                    if (isDone == 1) {
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                case "deadline":
                    Deadline deadline = new Deadline(description, time);
                    if (isDone == 1) {
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the save file.");
            e.printStackTrace();
        }
    }

    public static void writeFile(List<Task> tasks) {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for (Task task : tasks) {
                fw.write(task.isDone() + " " + task.getType() + " " + task.getTaskName() + " | " + task.getTime() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the save file.");
            e.printStackTrace();
        }
    }

    private static void addMessage(Task task, int size) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.getDescription());
        System.out.println("     Now you have " + size + " tasks in this list.");
    }

    public static void takeCommand(String command, List<Task> tasks) throws UnknownCommand, EmptyDescription, OutOfBound {
        String[] subStrings = command.split(" ");
        int slashIndex = subStrings.length;
        for (int i = 0; i < subStrings.length; ++i) {
            if (subStrings[i].equals("/") || subStrings[i].equals("/by") || subStrings[i].equals("/at")) {
                slashIndex = i;
                break;
            }
        }
        String description = setDescription(subStrings,slashIndex);
        String time = setTime(subStrings,slashIndex);

        switch (subStrings[0]) {
        case "list":
            printTasks(tasks);
            break;
        case "bye":
            System.out.println("     Bye. Hope to see you again soon!");
            break;
        case "done":
            if (subStrings.length == 2) {
                int taskNo = Integer.parseInt(subStrings[1]);
                if (taskNo > tasks.size()) {
                    throw new OutOfBound();
                } else {
                    Task taskDone = tasks.get(taskNo - 1);
                    taskDone.markAsDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("      " + taskDone.getDescription());
                    tasks.set(taskNo - 1, taskDone);
                }
            } else {
                throw new EmptyDescription("done");
            }
            break;
        case "todo":
            if (subStrings.length > 1) {
                Todo todo = new Todo(description);
                tasks.add(todo);
                addMessage(todo, tasks.size());
            } else {
                throw new EmptyDescription("todo");
            }
            break;
        case "deadline":
            if (slashIndex != -1) {
                Deadline deadline = new Deadline(description, time);
                tasks.add(deadline);
                addMessage(deadline, tasks.size());
            } else {
                throw new EmptyDescription("deadline");
            }
            break;
        case "event":
            if (slashIndex != -1) {
                Event event = new Event(description, time);
                tasks.add(event);
                addMessage(event, tasks.size());
            } else {
                throw new EmptyDescription("event");
            }
            break;
        default:
            throw new UnknownCommand();
        }
    }

    public static void printTasks(List<Task> tasks) {
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println("     " + i + "." + task.getDescription());
            ++i;
        }
    }
}
