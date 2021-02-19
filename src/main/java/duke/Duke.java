package duke;

import duke.tasks.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void dividerLine() {
        System.out.println("\t________________________________________\n");
    }

    public static void loadData() {
        try {
            Path dataFilePath = Paths.get("data/");
            Files.createDirectories(dataFilePath);
            File dataFile = new File("data/duke.txt");
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] splitLine = line.split("\\s\\|\\s");
                if (line.startsWith("T")) {
                    String todo = splitLine[2];
                    Todo t = new Todo(todo);
                    tasks.add(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                } else if (line.startsWith("E")) {
                    String description = splitLine[2];
                    String date = splitLine[3];
                    Event t = new Event(description, date);
                    tasks.add(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                } else if (line.startsWith("D")) {
                    String description = splitLine[2];
                    String date = splitLine[3];
                    Deadline t = new Deadline(description, date);
                    tasks.add(t);
                    if (splitLine[1].equals("1")) {
                        t.markAsDone();
                    }
                }
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            ;
        } catch (IOException e) {
            System.out.println("\tAn error occured while trying to create a data directory :(");
            //System.out.println(e.getMessage());
        }
    }

    public static boolean saveData() {
        try {
            System.out.println("\tSaving your data...");
            FileWriter fileWriter = new FileWriter("data/duke.txt");
            for (int i=0; i<tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                fileWriter.write(currentTask.getType() + " | " + ((currentTask.isDone()) ? "1" : "0") + " | "
                        + currentTask.getDescription() + ((currentTask.hasDate()) ? " | " + currentTask.getDate() : "") + '\n' );
            }
            fileWriter.close();
            System.out.println("\tData saved");
            return true;
        } catch (IOException e) {
            System.out.println("\tAn error occurred while saving your data :(");
            return false;
        }
    }

    public static void takeInput() {
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            String command = userInput.nextLine();
            if (command.equals("bye") || command.equals("exit")) {
                dividerLine();
                if (exitDuke()) {
                    dividerLine();
                    userInput.close();
                    break;
                }
                dividerLine();
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
            } else if (command.startsWith("delete")) {
                dividerLine();
                deleteTask(command);
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
        case "delete":
            return "delete [index of task]";
        default:
            return null;
        }
    }
    
    public static void unknownCommand() {
        System.out.println("\tSorry, this command is not available yet. Stay tuned! :)");
    }
    
    public static void list() {
        if (tasks.size()==0) {
            System.out.println("\tYou have no tasks in your list");
        } else {
            System.out.println("\tHere are the tasks in your list:");
            for (int i=0; i<tasks.size(); i++) {
                Task currentTask = tasks.get(i);
                System.out.println("\t" + Integer.toString(i+1) + ".[" + currentTask.getType() + "]["
                        + currentTask.getStatusIcon() + "] " + currentTask.getDescription() + " " 
                        + currentTask.printDate());
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
            tasks.add(t);
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
            if (tasks.size()==1) {
                System.out.println("\tNow you have 1 task in the list.");
            } else {
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
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
                tasks.add(t);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.printDate());
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
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
                tasks.add(t);
                System.out.println("\tGot it. I've added this task:");
                System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] "
                        + t.getDescription() + " " + t.printDate());
                System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
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
            if (itemIndex >= tasks.size() || itemIndex < 0) {
                System.out.println("\t!!! Item does not exist !!!");
                list();
            } else if (tasks.get(itemIndex).isDone()) {
                System.out.println("\tThis task is already done :)");
                Task currentTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription() + " " + currentTask.printDate());
            } else {
                tasks.get(itemIndex).markAsDone();
                System.out.println("\tNice! I've marked this task as done:");
                Task currentTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription() + " " + currentTask.printDate());
            }
        }
    }

    public static void deleteTask(String command) {
        String[] splitCommand = command.split(" ", 2);
        if (splitCommand.length==1) {
            System.out.println("\t!!! Please specify the index of the task to be deleted !!!");
            System.out.println("\tUsage: " + printUsage("delete"));
        } else {
            int itemIndex = Integer.parseInt(splitCommand[1])-1;
            if (itemIndex >= tasks.size() || itemIndex < 0) {
                System.out.println("\t!!! Item does not exist !!!");
                list();
            } else {
                System.out.println("\tNoted. I've removed this task:");
                Task currentTask = tasks.get(itemIndex);
                System.out.println("\t  [" + currentTask.getType() + "][" + currentTask.getStatusIcon() + "] "
                        + currentTask.getDescription() + ((currentTask.hasDate()) ? " " + currentTask.printDate() : ""));
                tasks.remove(itemIndex);
                if (tasks.size()==1) {
                    System.out.println("\tNow you have 1 task in the list.");
                } else {
                    System.out.println("\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.");
                }
            }
        }
    }

    public static boolean exitDuke() {
        if (saveData()) {
            System.out.println("\tBye, hope to see you again soon!");
            return true;
        } else {
            return false;
        }
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
