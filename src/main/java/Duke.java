import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.ToDo;
import ui.Ui;
import parser.Parser;



public class Duke {

    private static final Ui ui = new Ui();
    private static final Parser parser = new Parser();
    private static final ArrayList<Task> Tasks = new ArrayList<>();

    /**
     * Prints all the tasks which is stored in the Task arraylist
     *
     */
    public static void printList(){
        ui.showToUser(ui.DIVIDER, "Here are the tasks in your list:");
        for (int i=0; i<Tasks.size(); ++i){
            Task task = Tasks.get(i);
            Integer taskNumber = i+1;
            ui.showToUser(taskNumber + "." + task.toString());
        }
        ui.showToUser(ui.DIVIDER);
    }

    /**
     * Prints the task which the user added
     *
     */
    public static void printTaskAdded(){
        ui.showToUser(
                ui.DIVIDER,
                " Got it. I've added this task:\n" + Tasks.get(Tasks.size()-1).toString(),
                "Now you have " + Tasks.size() + " tasks in the list.",
                ui.DIVIDER);
    }

    /**
     * Checks if the user input is valid
     *
     * @param words user input split into individual words
     * @throws DukeException If input is not a valid command for the program
     */
    public static void validateInput(String[] words) throws DukeException {
        if(parser.parseCommand(words)) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Checks if the todo command is valid
     *
     * @param words user input split into description
     * @throws DukeException If input is not a valid todo command
     */
    public static void validateToDoInput(String[] words) throws DukeException {
        if(parser.parseToDoCommand(words)) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Checks if the list command is valid
     *
     * @param words user input
     * @throws DukeException If input is not a valid list command
     */
    public static void validateListInput(String[] words) throws DukeException {
        if (parser.parseListCommand(words)) {
            throw new DukeException("☹ OOPS!!! The description of a list should be empty.");
        }
    }

    /**
     * Loads the file and stores the information into the ArrayList Tasks
     *
     * @throws FileNotFoundException If the file cannot be found
     */
    public static void loadFile() throws FileNotFoundException {
        File f = new File("tasks.txt");
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] words = input.split(" ");
            boolean isTodo = words[0].equals("T");
            boolean isDeadline = words[0].equals("D");
            boolean isEvent = words[0].equals("E");
            boolean isDone = words[1].equals("Y");
            input = input.substring(4);
            words = input.split("/d");
            if (isDeadline) {
                Deadline deadline = new Deadline(words[0], words[1]);
                Tasks.add(deadline);
            }
            else if (isEvent) {
                Event event = new Event(words[0], words[1]);
                Tasks.add(event);
            }
            else if (isTodo) {
                ToDo toDo = new ToDo(words[0]);
                Tasks.add(toDo);
            }
            if (isDone) {
                Tasks.get(Tasks.size()-1).taskComplete();
            }
        }
    }

    /**
     * Saves the tasks information from the ArrayList into the file specified
     *
     * @param filepath Name of the file
     * @throws IOException If there is something wrong with writing into the file
     */
    public static void saveFile(String filepath) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task task: Tasks) {
            fw.write(task.toSaveFormat());
        }
        fw.close();
    }

    /**
     * Runs the code necessary for the bot to run
     *
     * @param args User input at the start of the program
     */
    public static void main(String[] args) {
        ArrayList<Task> Tasks = new ArrayList<>();
        String line;
        Scanner Input = new Scanner(System.in);
        ui.showIntroMessage();
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            ui.showToUser("File Not Found", "Creating new file...");
        }
        line = Input.nextLine();
        boolean inSystem = !line.equals("bye");
        while (inSystem) {
            String[] words = line.split(" ");
            boolean isList = words[0].equals("list");
            boolean isDone = words[0].equals("done");
            boolean isTodo = words[0].equals("todo");
            boolean isDeadline = words[0].equals("deadline");
            boolean isEvent = words[0].equals("event");
            boolean isDelete = words[0].equals("delete");
            try {
                validateInput(words);
            } catch (Exception e) {
                ui.showToUser(e.getMessage());
                line = Input.nextLine();
                inSystem = !line.equals("bye");
                continue;
            }
            if (isList) {
                try {
                    validateListInput(words);
                } catch (Exception e) {
                    ui.showToUser(e.getMessage());
                    line = Input.nextLine();
                    inSystem = !line.equals("bye");
                    continue;
                }
                printList();
            } else if (isDone) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                Tasks.get(taskNumber).taskComplete();
                ui.showToUser(ui.DIVIDER, "Nice! I've marked this task as done:\n" + " " + Tasks.get(taskNumber).toString(), ui.DIVIDER);
            } else if (isDelete) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                ui.showToUser(ui.DIVIDER, "Noted. I've removed this task:\n" + Tasks.get(taskNumber).toString());
                Tasks.remove(taskNumber);
                ui.showToUser("Now you have " + Tasks.size() + " tasks in the list.", ui.DIVIDER);
            } else if (isTodo) {
                try {
                    validateToDoInput(words);
                } catch (Exception e) {
                    ui.showToUser(e.getMessage());
                    line = Input.nextLine();
                    inSystem = !line.equals("bye");
                    continue;
                }
                line = line.replace("todo ", "");
                ToDo toDo = new ToDo(line);
                Tasks.add(toDo);
                printTaskAdded();
            } else if (isDeadline) {
                line = line.replace("deadline ", "");
                words = line.split("/by ");
                Deadline deadline = new Deadline(words[0], words[1]);
                Tasks.add(deadline);
                printTaskAdded();
            } else if (isEvent) {
                line = line.replace("event ", "");
                words = line.split("/at ");
                Event event = new Event(words[0], words[1]);
                Tasks.add(event);
                printTaskAdded();
            }
            line = Input.nextLine();
            inSystem = !line.equals("bye");
        }
        String file = "tasks.txt";
        try {
            saveFile(file);
        } catch (IOException e) {
            ui.showToUser("Something went wrong: " + e.getMessage());
        }
        ui.showExitMessage();
    }

}