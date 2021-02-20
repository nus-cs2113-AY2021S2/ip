package duke;

import duke.exception.DataErrorException;
import duke.exception.FullListException;
import duke.inputhandlers.CommandRunner;
import duke.inputhandlers.Parser;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.ui.TextUi;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {

    private static final String DELIM = "-";
    private static final String PATH = "Duke_userdata.txt";

    public static ArrayList<Task> taskList = new ArrayList<>();
    public static TextUi ui = new TextUi();

    public static void main(String[] args) {
        ui.printHello();
        checkSavedData();
        receiveUserInput();
        ui.printBye();
    }
    

    /**
     * ----------------------------------------------- START OF METHODS -----------------------------------------------
     */

    private static void receiveUserInput() {
        Scanner in = new Scanner(System.in);
        CommandRunner runner = new CommandRunner();
        boolean isExit = false;

        //Loop to receive response.
        while (!isExit) {
            String input = in.nextLine();
            int command = Parser.parseCommand(input);
            
            // If list is full, will only allow LIST and BYE command to pass
            try {
                runner.checkListCapacity(command);
            } catch (FullListException e) {
                ui.printListFullWarning();
                continue;
            }

            isExit = runner.selectCommandToRun(command, input);
        }
    }
    
    
    /**
     * FILE IO
     */

    private static void checkSavedData() {
        try {
            readFromFile();
            System.out.println("Existing records found! Data loaded..." + '\n');
        } catch (FileNotFoundException e) {
            System.out.println("No previous records! Starting a new record..." + '\n');
            createNewFile();
        }
    }

    private static void createNewFile() {
        File file = new File(PATH);
        try {
            if (file.createNewFile()) {
                System.out.println("New file created!" + '\n');
            }
        } catch (IOException e) {
            System.out.println("Error creating new file...");
            e.printStackTrace();
        }
    }

    public static void saveData() {
        try {
            writeToFile();
        } catch (IOException e) {
            System.out.println("Something went wrong when saving...");
        }
    }

    private static void readFromFile() throws FileNotFoundException {

        File dataFile = new File(PATH);
        Scanner reader = new Scanner(dataFile);

        while (reader.hasNext()) {
            int lineNumber = 0;
            String line = reader.nextLine();
            lineNumber++;

            try {
                Task task = formTask(line);
                taskList.add(task);
                Task.taskCount++;
            } catch (DataErrorException e) {
                ui.printDataErrorWarning(lineNumber);
            }
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(PATH);

        for (Task t : taskList) {
            writer.write(formLine(t) + '\n');
        }
        writer.close();
    }

    private static String formLine(Task t) {
        String line = "";

        if (t instanceof Todo) {
            line += "T" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getJob();
        } else if (t instanceof Deadline) {
            line += "D" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getJob() + DELIM;
            line += ((Deadline) t).getBy();
        } else if (t instanceof Event) {
            line += "E" + DELIM;
            line += (t.isDone() ? "1" : "0") + DELIM;
            line += t.getJob() + DELIM;
            line += ((Event) t).getAt();
        }

        return line;
    }

    private static Task formTask(String line) throws DataErrorException {
        String[] words = line.split(DELIM);

        // type of task
        String type = words[0];

        // done
        boolean isDone = "1".equals(words[1]);

        // job description
        String job = words[2];

        Task newTask = null;

        switch (type) {
        case "D":
            newTask = new Deadline(job, words[3]);
            break;
        case "E":
            newTask = new Event(job, words[3]);
            break;
        case "T":
            newTask = new Todo(job);
            break;
        default:
            throw new DataErrorException();
        }
        
        newTask.setDone(isDone);

        return newTask;
    }

}
