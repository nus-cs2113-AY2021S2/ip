package duke;

import duke.tasks.*;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    public static Task[] tasks = new Task[100];
    public static int currentTask = 0;

    public static void dividerLine() {
        System.out.println("\t________________________________________\n");
    }

    public static void loadData() {
        try {
            File dataFile = new File("data/duke.txt");
            if (true) {
                Scanner fileReader = new Scanner(dataFile);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] splitLine = line.split("\\s\\|\\s");
                    if (line.startsWith("T")) {
                        String todo = splitLine[2];
                        Todo t = new Todo(todo);
                        tasks[currentTask] = t;
                        currentTask++;
                        if (splitLine[1].equals("1")) {
                            t.markAsDone();
                        }
                    } else if (line.startsWith("E")) {
                        String description = splitLine[2];
                        String date = splitLine[3];
                        Event t = new Event(description, date);
                        tasks[currentTask] = t;
                        currentTask++;
                        if (splitLine[1].equals("1")) {
                            t.markAsDone();
                        }
                    } else if (line.startsWith("D")) {
                        String description = splitLine[2];
                        String date = splitLine[3];
                        Deadline t = new Deadline(description, date);
                        tasks[currentTask] = t;
                        currentTask++;
                        if (splitLine[1].equals("1")) {
                            t.markAsDone();
                        }
                    }
                }
                fileReader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    public static void saveData() {
        try {
            System.out.println("\tSaving your data...");
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            for (int i=0; i<currentTask; i++) {
                fileWriter.write(tasks[i].getType() + " | " + ((tasks[i].isDone()) ? "1" : "0") + " | "
                        + tasks[i].getDescription() + ((tasks[i].hasDate()) ? " | " + tasks[i].getDate() : "") + '\n' );
            }
            fileWriter.close();
            System.out.println("\tData saved");
        } catch (IOException e) {
            System.out.println("\tAn error occurred while saving your data.");
        }
    }

    public static void takeInput() {
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            String command = userInput.nextLine();
            if (command.equals("bye")) {
                dividerLine();
                exitDuke();
                dividerLine();
                userInput.close();
                break;
            } else if (command.equals("list")) {
                dividerLine();
                list();
                dividerLine();
            } else if (command.startsWith("done")) {
                dividerLine();
                finishTask(command);
                dividerLine();
            } else if (command.startsWith("todo")) {
                dividerLine();
                addTodo(command);
                dividerLine();
            } else if (command.startsWith("event")) {
                dividerLine();
                addEvent(command);
                dividerLine();
            } else if (command.startsWith("deadline")) {
                dividerLine();
                addDeadline(command);
                dividerLine();
            } else if (command.equals("save")) {
                dividerLine();
                saveData();
                dividerLine();
            } else {
                dividerLine();
                unknownCommand();
                dividerLine();
            }
        }
    }
    
    public static String printUsage(String type) {
        switch(type) {
        case "todo":
            return "todo [task description]";
        case "event":
            return "event [event description] /at [date]";
        case "deadline":
            return "deadline [task description] /by [due date]";
        case "done":
            return "done [index of task]";
        default:
            return null;
        }
    }
    
    public static void unknownCommand() {
        System.out.println("\tSorry, this command is not available yet. Stay tuned! :)");
    }
    
    public static void list() {
        if (currentTask==0) {
            System.out.println("\tYou have no tasks in your list");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i=0; i<currentTask; i++) {
                System.out.println("\t" + Integer.toString(i+1) + ".[" + tasks[i].getType() + "]["
                        + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription() + " " + tasks[i].printDate());
            }
        }
    }

    public static void addTodo(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the task description !!!");
            System.out.println("\tUsage: " + printUsage("todo"));
        } else {
            String todo = splitCommand[1];
            Todo t = new Todo(todo);
            tasks[currentTask] = t;
            currentTask++;
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
            if (currentTask==1) {
                System.out.println("\tNow you have 1 task in the list.");
            } else {
                System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
            }
        }
    }

    public static void addEvent(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify event description !!!");
            System.out.println("\tUsage: " + printUsage("event"));
        } else {
            try {
                String description = splitCommand[1].split("/at")[0].strip();
                String date = splitCommand[1].split("/at")[1].strip();
                Event t = new Event(description, date);
                tasks[currentTask] = t;
                currentTask++;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.printDate());
                System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\t!!! Please specify event date !!!");
                System.out.println("\tUsage: " + printUsage("event"));
            }
        }
    }

    public static void addDeadline(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the task description !!!");
            System.out.println("\tUsage: " + printUsage("deadline"));
        } else {
            try {
                String description = splitCommand[1].split("/by")[0].strip();
                String date = splitCommand[1].split("/by")[1].strip();
                Deadline t = new Deadline(description, date);
                tasks[currentTask] = t;
                currentTask++;
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.printDate());
                System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("\t!!! Please specify the task due date !!!");
                System.out.println("\tUsage: " + printUsage("deadline"));
            }
        }
    }

    public static void finishTask(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the index of the task done !!!");
            System.out.println("\tUsage: " + printUsage("done"));
        } else {
            int itemIndex = Integer.parseInt(splitCommand[1])-1;
            if (itemIndex >= currentTask || itemIndex < 0) {
                System.out.println("\t!!! Item does not exist !!!");
                list();
            } else if (tasks[itemIndex].isDone()) {
                System.out.println("\tThis task is already done :)");
                System.out.println("\t  [" + tasks[itemIndex].getType() + "][" + tasks[itemIndex].getStatusIcon() + "] "
                        + tasks[itemIndex].getDescription() + " " + tasks[itemIndex].printDate());
            } else {
                tasks[itemIndex].markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  [" + tasks[itemIndex].getType() + "][" + tasks[itemIndex].getStatusIcon() + "] "
                        + tasks[itemIndex].getDescription() + " " + tasks[itemIndex].printDate());
            }
        }
    }

    public static void exitDuke() {
        saveData();
        System.out.println("\tBye, hope to see you again soon!");
    }

    public static void main(String[] args) {
        dividerLine();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        dividerLine();
        loadData();
        takeInput();
    }
}
