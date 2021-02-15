package duke.Controller;

import java.util.ArrayList;
import java.util.Scanner;
import duke.Tasks.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class dukeController {
    public dukeController() {}

    public void displayWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Ay yo homie! You lookin PENGGGGGGGG today!\nIt's ya boi Duke the Dawg. What can I do for ma G?\n");
    }

    /*Methods to process the input of the user.
    If input is not valid, returns string nonsense.
    The type of task is indicated by the first substring and the task description is found after the first blank space.
    If a forward slash present,  ths substring is split into the task and the end date which is indicated by the slash.
    Exception handling includes cases where no tasks are specified, invalid inputs, no date or time being specified (for event and deadline)*/
    public String extractTask(String input) {
        String Task;
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.contains("done")) {
            return "done";
        } else if (input.contains("delete")) {
            return "delete";
        } else if (input.contains("save")) {
            return "save";
        }
        else if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            int indexOfSpace = input.indexOf(" ");
            if (indexOfSpace == -1) {
                return "retry";
            } else {
                String subString = input.substring(indexOfSpace + 1);
                if (subString.contains("/")) {
                    int indexOfSlash = subString.indexOf("/");
                    Task = subString.substring(0, indexOfSlash - 1);
                } else {
                    Task = subString;
                }
                return Task;
            }
        } else {
            return "nonsense";
        }
    }

    public String extractDate(String input) {
        String Date = null;
        if (input.equalsIgnoreCase("list")) {
            return "list";
        } else if (input.equalsIgnoreCase("bye")) {
            return "bye";
        } else if (input.contains("done")) {
            return "done";
        } else if (input.contains("todo")) {
            return "todo";
        } else if (input.contains("delete")) {
            return "delete";
        } else if (input.contains("save")) {
            return "save";
        } else if (input.contains("deadline") || input.contains("event")) {
            int indexOfSpace = input.indexOf(" ");
            String subString = input.substring(indexOfSpace + 1);
            if (subString.contains("/")) {
                int indexOfSlash = subString.indexOf("/");
                String subStringDate = subString.substring(indexOfSlash);
                int indexNext = subStringDate.indexOf(" ");
                if (indexNext == -1) {
                    return "missing";
                } else {
                    Date = subStringDate.substring(indexNext + 1);
                    return Date;
                }
            } else {
                return "missing";
            }
        } else {
            return "nonsense";
        }
    }

    public void printTaskList(ArrayList<Task> tasks) {
        int number = 1;
        System.out.println("Here are the tings in yo list: ");
        for (Task task : tasks) {
            System.out.println(number + ". " + task.printDescription());
            number++;
        }
    }

    public void showDone(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                tasks.get(taskNumber - 1).markAsDone();
                System.out.println("Awwww yeah! I've marked this task as done... brrrrrap brrrrrap: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void deleteTask(ArrayList<Task> tasks, String input) {
        int indexSpace = input.indexOf(" ");
        if (indexSpace == -1) {
            System.out.println("I see you forget how to type numbers yea... you donut! Type in your task number man!");
        } else {
            String numberString = input.substring(indexSpace + 1);
            int taskNumber = Integer.parseInt(numberString);
            try {
                if (taskNumber > tasks.size() || taskNumber == 0) {
                    throw new NullPointerException("Are you blind? There is no such task number you fool!");
                }
                System.out.println("Awwww yeah! I've deleted this task like a beast: ");
                System.out.println(tasks.get(taskNumber - 1).printDescription());
                tasks.remove(taskNumber - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void printToDo(ArrayList<Task> tasks, String input, String description) {
        Task todo = new toDo(description);
        tasks.add(todo);
        System.out.println("Ayy I got you my brother. I've added this ting: ");
        System.out.println(todo.printDescription());
        System.out.println("Dayuum son! You have " + tasks.size() + " mad tings in the list.");
    }

    public void printDeadline(ArrayList<Task> tasks, String input, String description, String date) {
        Task deadline = new Deadline(description, date);
        tasks.add(deadline);
        System.out.println("Ayy I got you my brother. I've added this ting: ");
        System.out.println(deadline.printDescription());
        System.out.println("Jeeeeeeez! You have " + tasks.size() + " mad tings in the list.");
    }

    public void printEvent(ArrayList<Task> tasks, String input, String description, String date) {
        Task event = new Event(description, date);
        tasks.add(event);
        System.out.println("Ayy I got you my brother. I've added this ting: ");
        System.out.println(event.printDescription());
        System.out.println("I feer! You have " + tasks.size() + " mad tings in the list.");
    }

    public void saveFile(ArrayList<Task> tasks) throws IOException {
        try {
            FileWriter writer = new FileWriter("saveDuke.txt");
            for (Task task : tasks) {
                if (task instanceof toDo) {
                    writer.write("T" + " | " + task.getSaveDone() + " | " + task.getDescription());
                } else if (task instanceof Deadline) {
                    writer.write("D" + " | " + task.getSaveDone() + " | " + task.getDescription() + " | " + ((Deadline) task).getByDate());
                } else if (task instanceof Event) {
                    writer.write("E" + " | " + task.getSaveDone() + " | " + task.getDescription() + " | " + ((Event) task).getAtDate());
                }
                writer.write("\n");
            }
            writer.close();
            System.out.print("Yea cuhhhh... filed saved!\n");
        } catch (IOException e) {
            System.out.println("Cannot save file my G!");
            e.printStackTrace();
        }
    }

    public ArrayList<Task> printFileContents() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File saveDuke = new File("saveDuke.txt");
            Scanner fileReader = new Scanner(saveDuke);
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                String[] stringSplit = line.split(" | ");
                if (stringSplit[0].equals("T")) {
                    Task task = new toDo(stringSplit[4]);
                    if (stringSplit[2].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                } else if (stringSplit[0].equals("E")) {
                    Task task = new Event(stringSplit[4], stringSplit[6]);
                    if (stringSplit[2].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                } else if (stringSplit[0].equals("D")) {
                    Task task = new Deadline(stringSplit[4], stringSplit[6]);
                    if (stringSplit[2].equals("0")) {
                        task.setAsDone(false);
                    } else {
                        task.setAsDone(true);
                    }
                    tasks.add(task);
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Cannot load file...you are clapped! Churn up new list please.");
            return null;
        }
        return tasks;
    }
}