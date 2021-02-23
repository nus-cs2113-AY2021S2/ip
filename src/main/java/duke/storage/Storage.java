package duke.storage;


import duke.ui.Ui;
import duke.error.WrongFormatException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.task.Task;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import static duke.ui.Ui.checkError;

public class Storage {
    /**
     * Method to load Duke.txt
     * Throws Exception if Duke.txt not Found
     * @throws FileNotFoundException - File Not Found Exception
     */
    public static void loadFile(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        Scanner line = new Scanner(file);
        while (line.hasNext()) {
            String input = line.nextLine();
            if (input.toLowerCase().substring(4).startsWith("todo")) {
                importTodo(input,list);
            } else if (input.toLowerCase().substring(4).startsWith("deadline")) {
                importDeadline(input,list);
            } else if (input.toLowerCase().substring(4).startsWith("event")) {
                importEvent(input,list);
            }
        }
    }

    /**
     * Imports todo from Duke.txt to Program
     * @param input - Command
     */
    public static void importTodo(String input, ArrayList<Task> list) {
        try{
            if (input.substring(4).equals("todo")) {
                throw new WrongFormatException();
            }
            String command = input.substring(9);
            if (command.isBlank()) {
                throw new WrongFormatException();
            }
            Todo t = new Todo(command);
            list.add(t);
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Todo: " + t.getName()
                + "\" from Duke.txt!");
            Ui.printBorder();
        } catch(WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Imports Deadline from Duke.txt to Program
     * @param input - Command
     */
    public static void importDeadline(String input, ArrayList<Task> list) {
        try{
            if (input.substring(4).equals("deadline")) {
                throw new WrongFormatException();
            }
            String command = input.substring(13);
            if (!command.contains(" /by ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /by ");
            String description = parts[0];
            String date = parts[1];
            Deadline t = new Deadline(description,date);
            list.add(t);
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Deadline: " + t.getName()
                + "\" from Duke.txt!");
            Ui.printBorder();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Imports Event from Duke.txt to Program
     * @param input - Command
     */
    public static void importEvent(String input, ArrayList<Task> list) {
        try {
            if (input.equals("event")) {
                throw new WrongFormatException();
            }
            String command = input.substring(10);
            if (!command.contains(" /at ")) {
                throw new WrongFormatException();
            }
            String[] parts = command.split(" /at ");
            String description = parts[0];
            String date = parts[1];
            Event t = new Event(description, date);
            list.add(t);
            if (input.charAt(1) == '\u2713') {
                t.markAsDone();
            }
            System.out.println("Imported \"Event: " + t.getName()
                + "\" from Duke.txt!");
            Ui.printBorder();
        } catch (WrongFormatException e) {
            checkError("INVALID_FORMAT");
        }
    }

    /**
     * Method to update Duke.txt
     * Throws Exception if Duke.txt not found
     * @throws IOException - IO Exception Error
     * @param list - ArrayList
     */
    public static void saveFile(ArrayList<Task> list) throws IOException {
        File file = new File("Duke.txt");
        if (!file.exists()){
            if(!file.createNewFile()) {
                throw new IOException();
            }
        }
        FileWriter fw = new FileWriter("Duke.txt");
        for(int i = 0; i< list.size(); i++) {
            fw.write(list.get(i).outputData());
            fw.write("\n");
        }
        fw.close();
    }
}
