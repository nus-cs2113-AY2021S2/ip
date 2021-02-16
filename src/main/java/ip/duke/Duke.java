package ip.duke;

import ip.duke.task.Deadline;
import ip.duke.task.Event;
import ip.duke.task.Task;
import ip.duke.task.Todo;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static ArrayList<Task> list = new ArrayList<>();
    public static File filePath = new File("data/duke.txt");
    public static int index = 0;

    public static void main(String[] args) {
        try {
            loadData();
        } catch (IOException e) {
            System.out.println("IO exception :O");
        }

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printLine();
        printGreeting();


        int slashPosition;
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();

        while (!command.equals("bye")) {
            try {
                if (command.equals("list")) {
                    printTasks(list, list.size());
                } else if (command.startsWith("todo")) {
                    if (command.length() <= 5) {
                        throw new DukeException("todo");
                    }
                    recordTasks(list, index, command, "todo");
                    index++;
                } else {
                    int Position = Integer.parseInt(String.valueOf(command.indexOf('/')));
                    if (command.startsWith("deadline")) {
                        if (command.length() <= 9) {
                            throw new DukeException("deadline");
                        }
                        slashPosition = Position;
                        if (slashPosition != -1) {
                            recordTasks(list, index, command, "deadline");
                            index++;
                        } else {
                            printLine();
                            System.out.println("🙁 OOPS!!! The deadline time is missing.");
                            System.out.println("Please fill in the time! :)");
                            printLine();
                        }
                    } else if (command.startsWith("event")) {
                        if (command.length() <= 6) {
                            throw new DukeException("event");
                        }
                        slashPosition = Position;
                        if (slashPosition != -1) {
                            recordTasks(list, index, command, "event");
                            index++;
                        } else {
                            printLine();
                            System.out.println("🙁 OOPS!!! The event time is missing.");
                            System.out.println("Please fill in the time! :)");
                        }
                    } else if (command.startsWith("done")) {
                        if (command.length() <= 5) {
                            throw new DukeException("done");
                        }
                        markDone(list, command);
                    } else if (command.startsWith("delete")) {
                        if (command.length() <= 7) {
                            throw new DukeException("delete");
                        }
                        deleteTasks(list, command);
                    } else {
                        printLine();
                        System.out.println("🙁 OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println("Please input again! :)");
                        printLine();
                    }
                }
            } catch (DukeException e) {
                printLine();
                if (e.category.equals("event")) {
                    System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    System.out.println("☹ OOPS!!! The description of a " + e.category + " cannot be empty.");
                }
                System.out.println("Please complete the description! :)");
                printLine();
            }
            command = in.nextLine();

        }

        try {
            PrintWriter writer = new PrintWriter("data/duke.txt");
            writer.print("");
            writer.close();
            for (Task presentTask : list) {
                appendToFile(presentTask.toDataString());
                appendToFile(System.lineSeparator());
            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Unable to write to file");
        }
        printBye();
    }

    private static void loadData() throws IOException {
        try {
            Scanner dataScanner = new Scanner(filePath);
            while (dataScanner.hasNext()) {
                String[] dataEntry = dataScanner.nextLine().split("\\|");
                switch (dataEntry[0]) {
                case "T":
                    list.add(new Todo(dataEntry[2]));
                    break;
                case "D":
                    list.add(new Deadline(dataEntry[2], dataEntry[3]));
                    break;
                case "E":
                    list.add(new Event(dataEntry[2], dataEntry[3]));
                    break;
                default:
                    break;
                }
                if (dataEntry[1].equals("1")) {
                    list.get(index).setDone(true);
                }
                index++;
            }

        } catch (FileNotFoundException e) {
            File dataDirectory = new File("data");
            dataDirectory.mkdir();
            File dukeFile = new File("data","duke.txt");
            dukeFile.createNewFile();
        }
    }

    private static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt", true);
        fw.write(textToAppend);
        fw.close();
    }


    public static void printGreeting() {
        System.out.println("Hello! I'm ip.taskmaster.Duke");
        System.out.println("What can I do for you?\n");
        printLine();
    }


    public static void recordTasks(ArrayList<Task> list, int index, String command, String category) {
        printLine();
        System.out.println("Got it. I've added this task:");
        if (category.equals("todo")) {
            list.add(index, new Todo(command.substring(5)));
        } else {
            String Time = command.substring(command.indexOf("/") + 4);
            if (category.equals("deadline")) {
                String content = command.substring(9, command.indexOf("/"));
                list.add(index, new Deadline(content, Time));
            } else if (category.equals("event")) {
                String content = command.substring(6, command.indexOf("/"));
                list.add(index, new Event(content, Time));
            }
        }
        System.out.println(list.get(index).toString());
        int count = index + 1;
        System.out.println("Now you have " + count + " tasks in the list.");
        printLine();
    }

    public static void markDone(ArrayList<Task> list, String command) {
        int i;
        i = Integer.parseInt(command.substring(5));
        list.get(i - 1).setDone(true);
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + " " + list.get(i - 1).toString());
        printLine();

    }

    public static void deleteTasks(ArrayList<Task> list, String command) {
        printLine();
        int i = Integer.parseInt(command.substring(7));
        String content = list.get(i-1).toString();
        list.remove(i - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println(content);
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        printLine();
    }

    public static void printTasks(ArrayList<Task> list, int index) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + "." + list.get(i - 1).toString());
        }
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("-----------------------------------------");
    }
}

