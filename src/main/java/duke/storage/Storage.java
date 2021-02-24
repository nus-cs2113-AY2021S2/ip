package duke.storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import duke.command.DukeExceptions;
import duke.task.*;

public class Storage {
    public String storageFilePath;

    public Storage(String storageFilePath) {
        this.storageFilePath = storageFilePath;
    }

    public ArrayList<Task> load() throws DukeExceptions{
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File taskFile = new File(storageFilePath);
            Scanner taskReader = new Scanner(taskFile);
            while (taskReader.hasNextLine()) {
                String storedTaskString = taskReader.nextLine();
                Task task = initializeTask(storedTaskString);
                taskList.add(task);
            }
        } catch(FileNotFoundException e) {
            System.out.println("File path was not found! No previous tasks were read");
        } catch(DukeExceptions e) {
            throw new DukeExceptions();
//            System.out.println("Formatting errors detected in " + storageFilePath);
        }
        return taskList;
    }

    public void save(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(storageFilePath);
            for (Task task: taskList.getTaskList()) {
                String textToAdd = task.formatTaskToWrite();
                fw.write(textToAdd + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public Task initializeTask(String task) throws DukeExceptions{
        List<String> taskProperties = Arrays.asList(task.split("<separator>"));
        String taskType = taskProperties.get(0);
        switch (taskType) {
        case "todo":
            if (taskProperties.size() != 3) {
                throw new DukeExceptions();
            }
            String todoName = taskProperties.get(1);
            boolean todoIsCompleted = Boolean.parseBoolean(taskProperties.get(2));
            ToDo newTodo = new ToDo(todoName, taskType);
            newTodo.setIsCompleted(todoIsCompleted);
            return newTodo;
        case "deadline":
            if (taskProperties.size() != 4) {
                throw new DukeExceptions();
            }
            String deadlineName = taskProperties.get(1);
            String deadlineTimeConstraint = taskProperties.get(2);
            boolean deadlineIsCompleted = Boolean.parseBoolean(taskProperties.get(3));
            Deadline newDeadline = new Deadline(deadlineName, taskType, deadlineTimeConstraint);
            newDeadline.setIsCompleted(deadlineIsCompleted);
            return newDeadline;
        case "event":
            if (taskProperties.size() != 4) {
                throw new DukeExceptions();
            }
            String eventName = taskProperties.get(1);
            String eventTimeConstraint = taskProperties.get(2);
            boolean eventIsCompleted = Boolean.parseBoolean(taskProperties.get(3));
            Event newEvent = new Event(eventName, taskType, eventTimeConstraint);
            newEvent.setIsCompleted(eventIsCompleted);
            return newEvent;
        default:
            throw new DukeExceptions();
        }
    }
}
