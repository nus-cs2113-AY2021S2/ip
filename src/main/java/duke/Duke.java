package duke;

import duke.myExceptions.InvalidInput;
import duke.myExceptions.NoContent;
import duke.myExceptions.NoTime;
import duke.myTasks.Deadline;
import duke.myTasks.Event;
import duke.myTasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//built JAR file
public class Duke {

    public static ArrayList<Todo> list = new ArrayList<>();
    public static int index = 0;
    public static boolean isRunning = true;
    public static String filePath = "savefile.txt";

    public static void addToList(Todo task) {
        list.add(task);
        index++;
    }

    public static void printList() {
        for (int i = 0; i < index; i++) {
            System.out.println((i + 1) + ". " + list.get(i).toString());
        }
    }

    public static void printAddMessage(Todo task) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + index + " tasks in the list.");
    }

    public static void executeInstruction(String command, String content) throws InvalidInput, NoContent, NoTime, NullPointerException, NumberFormatException, ArrayIndexOutOfBoundsException{
        switch (command) {
        case "bye":
            isRunning = false;
            save();
            break;
        case "list":
            printList();
            break;
        case "done":
            list.get(Integer.parseInt(content) - 1).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(list.get(Integer.parseInt(content) - 1).toString());
            break;
        case "todo":
            Todo todo = new Todo(content);
            addToList(todo);
            printAddMessage(todo);
            break;
        case "event":
            Event event = new Event(content);
            addToList(event);
            printAddMessage(event);
            break;
        case "deadline":
            Deadline deadline = new Deadline(content);
            addToList(deadline);
            printAddMessage(deadline);
            break;
        default:
            throw new InvalidInput();
        }
    }

    public static void checkSave() {
        Path path = Paths.get(filePath); //creates Path instance
        try {
            Path p = Files.createFile(path);     //creates file at specified location

        } catch (IOException e) {
            loadSave();
        }
    }

    public static void loadSave() {
        File f = new File(filePath);
        try {Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String line = s.nextLine();
                switch(line.charAt(1)) {
                case 'T':
                    Todo todo = new Todo(line.substring(7));
                    addToList(todo);
                    break;
                case 'D':
                    int add = line.indexOf(':') - 3;
                    Deadline deadline = new Deadline(line.substring(7, add + 1) + "/by" + line.substring(add + 4));
                    addToList(deadline);
                    break;
                case 'E':
                    int add1 = line.indexOf(':') - 3;
                    Event event = new Event(line.substring(7, add1 + 1) + "/at" + line.substring(add1 + 4));
                    addToList(event);
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException | NoContent | NoTime e) {
            System.out.println("error loading save");
        }
    }

    public static void save() {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("");//clear file
            fw.close();
            FileWriter f = new FileWriter(filePath, true); // create a FileWriter in append mode
            for (int i = 0; i < index; i++) {
                f.write(list.get(i).toString() + "\n");
            }
            f.close();
        } catch (IOException e) {
            System.out.println("cannot write to file");
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        checkSave();
        Scanner in = new Scanner(System.in);
        String input;
        do {
            input = in.nextLine();
            String command;
            String content = "";
            if (input.indexOf(' ') == -1) {
                command = input;
            } else {
                command = input.substring(0, input.indexOf(' '));
                content = input.substring(input.indexOf(' ') + 1);
            }
            try {
                executeInstruction(command, content);
            } catch (InvalidInput e) {
                System.out.println("Invalid command used");
            } catch (NoContent | StringIndexOutOfBoundsException e) {
                System.out.println("Needs a name");
            } catch (NoTime e) {
                System.out.println("Needs time. Add \"/by <time>\" to command.");
            } catch (NullPointerException | IndexOutOfBoundsException e) {
                System.out.println("You have inputted an invalid index number");
            } catch (NumberFormatException e) {
                System.out.println("Specify which task to mark as done");
            }
        } while (isRunning);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
