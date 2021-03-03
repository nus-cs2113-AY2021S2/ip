package storage;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import parser.Parser;
import ui.Ui;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/***
 * Storage class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {

    private File dukeFile;
    private Task task;
    private Ui ui;
    private Parser parser;

    public Storage() throws DukeException {
        createDukeFolder();
        createDukeFile();
    }

    /***
     * Checks if Duke's directory exists
     * If it does not, a new Duke folder will be created
     */
    private void createDukeFolder() {
        File dukeFolder = new File("./data");
        if (!dukeFolder.isDirectory()) {
            try {
                dukeFolder.mkdirs();
            } catch (SecurityException e) {
                ui.printErrorMessage(e.getMessage());
            }
        }
    }

    /***
     * Checks if a duke.txt file exists
     * If it does not, a new duke.txt file will be created
     * @throws DukeException catches <code>NoSuchFileException</code>, <code>IOException</code>, <code>SecurityException</code> and throws new DukeException*/
    private void createDukeFile() throws DukeException {
        File dukeFile = new File("./data/Duke.txt");
        if (!dukeFile.exists()) {
            try {
                dukeFile.createNewFile();
            } catch (NoSuchFileException e) {
                throw new DukeException("duke.txt file not found");
            } catch (IOException e) {
                throw new DukeException("Unable to load File");
            } catch (SecurityException e) {
                throw new DukeException(e.getMessage());
            }
        }
        this.dukeFile = dukeFile;
    }

    /***
     * Update the duke.txt file accordingly when list of task changes
     * @param taskList ArrayList of task objects
     * @throws IOException
     */
    public void saveTaskListToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dukeFile);
        for (Task task : taskList) {
            String descriptionOfTask = task.getDescription().strip();
            boolean isDone = task.isDone();
            String textToAppend = null;
            if (task instanceof Todo) {
                textToAppend = "T|" + isDone + "|" + descriptionOfTask + "\n";
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                textToAppend = "D|" + isDone + "|" + descriptionOfTask + "|"
                        + deadline.getDate() + "|" + deadline.getTime() + "\n";
            } else if (task instanceof Event) {
                Event event = (Event) task;
                textToAppend = "E|" + isDone + "|" + descriptionOfTask + "|" + event.getAt().strip() + "\n";
            }
            fileWriter.write(textToAppend);
        }
        fileWriter.close();
    }

    /***
     * Load and process the text from the duke.txt file and add each task object into an ArrayList
     * @return ArrayList with the tasks added from the file
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFile() throws FileNotFoundException, DukeException {
        Scanner sc = new Scanner(dukeFile);
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String fileText = sc.nextLine();
            String[] taskType = fileText.split("\\|");
            switch (taskType[0]) {
            case "E":
                task = new Event(taskType[2], taskType[3]);
                break;
            case "D":
                task = new Deadline(taskType[2], formatDate(taskType[3]), formatTime(taskType[3]));
                break;
            case "T":
                task = new Todo(taskType[2]);
                break;
            }
            if (taskType[1].equals(true)) {
                task.setDone();
            }
            taskList.add(task);
        }
        return taskList;
    }

    private LocalDate formatDate(String inputDate) throws DukeException {
        LocalDate date = parser.parseDate(inputDate);
        return date;
    }

    private LocalTime formatTime(String inputTime) {
        LocalTime time = parser.parseTime(inputTime);
        return time;
    }
}


