package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    private static final String USER_DIRECTORY = System.getProperty("user.dir");
    private static final String FILE_DIRECTORY = USER_DIRECTORY + "/src/data";

    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DATE_INDEX = 3;

    private static final String IS_DONE = "1";
    private static final String EVENT_TYPE = "[E]";
    private static final String DEADLINE_TYPE = "[D]";
    private static final String TODO_TYPE = "[T]";

    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasksData = new ArrayList<>();
        readFileContents(tasksData);
        return tasksData;
    }

    public void readFileContents(ArrayList<Task> tasksData) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            String data = scanner.nextLine();
            addFileContents(data, tasksData);
        }
    }

    public void addFileContents(String data, ArrayList<Task> tasksData) {
        String[] contents  = splitContent(data);
        String taskType = contents[TASK_TYPE_INDEX].trim();
        String isDone = contents[DONE_INDEX].trim();
        String description = contents[DESCRIPTION_INDEX].trim();

        switch(taskType) {
        case EVENT_TYPE:
            String at = contents[DATE_INDEX].trim();
            Task event = new Event(description, at);
            checkIsDone(isDone, event);
            tasksData.add(event);
            break;
        case DEADLINE_TYPE:
            String by = contents[DATE_INDEX].trim();
            Task deadline = new Deadline(description, by);
            checkIsDone(isDone, deadline);
            tasksData.add(deadline);
            break;
        case TODO_TYPE:
            Task todo = new ToDo(description);
            checkIsDone(isDone, todo);
            tasksData.add(todo);
            break;
        }
    }

    public String[] splitContent(String content) {
        return content.split("\\|");
    }

    public void checkIsDone(String isDone, Task task) {
        if (isDone.equals(IS_DONE)) {
            task.setAsDone();
        }
    }

    public void createDirectory() {
        new File(FILE_DIRECTORY).mkdir();
    }

    public void save(TaskList tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException ioException) {
            System.out.print("Something went wrong... System unable to find directory\n");
        }
    }

    private void writeToFile(TaskList tasks) throws IOException {
        String fileDestination = USER_DIRECTORY + "\\" + filePath;
        FileWriter fileWriter = new FileWriter(fileDestination);
        for (int i = 0; i < tasks.size(); ++i) {
            switch (tasks.get(i).getTaskType()) {
            case "[E]":
                //Fallthrough
            case "[D]":
                tasks.get(i).getDate();
                fileWriter.write(tasks.get(i).getTaskType() + " | "
                        + tasks.get(i).getIsDone() + " | "
                        + tasks.get(i).getDescription() + " | "
                        + tasks.get(i).getDate()
                        + System.lineSeparator());
                break;
            case "[T]":
                fileWriter.write(tasks.get(i).getTaskType() + " | "
                        + tasks.get(i).getIsDone() + " | "
                        + tasks.get(i).getDescription()
                        + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

}
