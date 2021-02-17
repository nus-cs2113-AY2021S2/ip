package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static duke.Duke.taskList;

public class FileLoader {
    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int DATE_INDEX = 3;

    private static final String IS_DONE = "1";
    private static final String EVENT_TYPE = "[E]";
    private static final String DEADLINE_TYPE = "[D]";
    private static final String TODO_TYPE = "[T]";

    private static final String FILE_DIRECTORY = "src/data";


    public static void readFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        if (!scanner.hasNext()) {
            throw new FileNotFoundException();
        }

        while(scanner.hasNext()){
            String data = scanner.nextLine();
            processFileContents(data);
        }
    }

    public static void processFileContents(String data) {
        String[] contents  = splitContent(data);
        String taskType = contents[TASK_TYPE_INDEX].trim();
        String isDone = contents[DONE_INDEX].trim();
        String description = contents[DESCRIPTION_INDEX].trim();

        switch(taskType) {
        case EVENT_TYPE:
            String at = contents[DATE_INDEX];
            Task event = new Event(description,at);
            checkIsDone(isDone,event);
            taskList.add(event);
            break;
        case DEADLINE_TYPE:
            String by = contents[DATE_INDEX];
            Task deadline = new Deadline(description,by);
            checkIsDone(isDone,deadline);
            taskList.add(deadline);
            break;
        case TODO_TYPE:
            Task todo = new ToDo(description);
            checkIsDone(isDone,todo);
            taskList.add(todo);
            break;
        }
    }

    public static String[] splitContent(String content) {
        return content.split("\\|");
    }

    public static void checkIsDone(String isDone, Task task) {
        if (isDone.equals(IS_DONE)) {
            task.setAsDone();
        }
    }

    public static void createDirectory(String fileDirectory) {
        new File(fileDirectory).mkdir();
    }

    public static void loadFile() {
        String filePath = "src/data/duke.txt";
        System.out.print("Loading last saved data...\n\n");
        try {
            readFileContents(filePath);
            System.out.print("Load successful ☺\n\n");
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.print("Load failed... System unable to find last saved data ☹\n\n"
                    + "Proceed application without loading...\n\n");
            createDirectory(FILE_DIRECTORY);
        }
    }

}
