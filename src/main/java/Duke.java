import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import Exception.DeadlineFormatException;
import Exception.TaskFormatException;
import Exception.TodoFormatException;
import Exception.EventFormatException;
import Exception.TaskAlreadyDoneException;
import Exception.DoneFormatException;
import Exception.DeleteFormatException;
import FileHandler.FileHandler;
import FileHandler.FileTaskWriter;
import TaskClass.Deadline;
import TaskClass.Event;
import TaskClass.TaskList;
import TaskClass.ToDo;


public class Duke {
    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPERATOR = "    ____________________________________________________________";

    public static void main(String[] args) throws IOException {
        System.out.println("    Hello from\n" + LOGO);
        System.out.println(LINE_SEPERATOR +
                "\n    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                LINE_SEPERATOR);
        Scanner sc = new Scanner(System.in);
        String sourceFilePath = "data" + File.separator + "task.txt";
        File source = new File(sourceFilePath);
        //FileHandler fileHandler;
        //FileTaskWriter fileTaskWriter;
        FileTaskWriter fileTaskWriter = new FileTaskWriter(sourceFilePath);
        try {
            if(!source.exists()) {
                if (!source.getParentFile().exists()) {
                    source.getParentFile().mkdirs();
                    source.createNewFile();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(LINE_SEPERATOR + "    Duke can't find the task list.\n" + LINE_SEPERATOR);
        }
        FileHandler fileHandler = new FileHandler(source);
        TaskList tasks = new TaskList(fileHandler.parseToArraylist());
        //FileWriter sourceToWrite = new FileWriter(sourceFilePath);
        //fileTaskWriter = new FileTaskWriter(sourceFilePath);
        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            try {
                if (userInput.equals("list")) {
                    tasks.printTaskList();
                    userInput = sc.nextLine();
                    continue;
                }
                String[] inputParts = userInput.trim().split("\\s+",2);
                switch (inputParts[0]){
                case "done":
                    if(inputParts.length == 1) {
                        throw new DoneFormatException();
                    }
                    tasks.setTaskDone(inputParts[1]);
                    break;
                case "delete":
                    if(inputParts.length == 1) {
                        throw new DeleteFormatException();
                    }
                    tasks.deleteTask(inputParts[1]);
                    break;
                case "todo":
                    if(inputParts.length < 2) {
                        throw new TodoFormatException();
                    }
                    ToDo newTodo = new ToDo(inputParts[1],false);
                    tasks.addTask(newTodo);
                    break;
                case "deadline":
                    if (inputParts.length < 2) {
                        throw new DeadlineFormatException();
                    }
                    String[] deadlineNameDoby = inputParts[1].split("/by");
                    if(deadlineNameDoby.length < 2) {
                        throw new DeadlineFormatException();
                    }
                    String deadlineName = deadlineNameDoby[0].trim();
                    String deadlineDoby = deadlineNameDoby[1].trim();
                    Deadline newDeadline = new Deadline(deadlineName,false,deadlineDoby);
                    tasks.addTask(newDeadline);
                    break;
                case "event":
                    if (inputParts.length < 2) {
                        throw new EventFormatException();
                    }
                    String[] eventNameDoby = inputParts[1].split("/at");
                    if(eventNameDoby.length < 2) {
                        throw new EventFormatException();
                    }
                    String eventName = eventNameDoby[0].trim();
                    String eventDoby = eventNameDoby[1].trim();
                    Event newEvent = new Event(eventName,false,eventDoby);
                    tasks.addTask(newEvent);
                    break;
                default:
                    System.out.println(LINE_SEPERATOR + "\n    Invalid input format.");
                    System.out.println("    Input format:\n    todo todo name\n    deadline deadline name /by time of deadline" +
                            "\n    event event name /at time of the event\n" + LINE_SEPERATOR);
                    break;
                }
            } catch (DoneFormatException e) {
                System.out.println(LINE_SEPERATOR + "\n" + e.toString() + LINE_SEPERATOR);
            } catch (TaskAlreadyDoneException e) {
                System.out.println(LINE_SEPERATOR + "\n    The task is done already.\n" + LINE_SEPERATOR);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(LINE_SEPERATOR + "\n    Invalid input.\n    The task index is out of bound.\n" + LINE_SEPERATOR);
            } catch (TaskFormatException e) {
                System.out.println(LINE_SEPERATOR + "\n" + e.toString() + LINE_SEPERATOR);
            } catch (DeleteFormatException e) {
                System.out.println(LINE_SEPERATOR + "\n" + e.toString() + LINE_SEPERATOR);
            }
            fileTaskWriter.storeToFile(tasks);
            userInput = sc.nextLine();
        }
        System.out.print(LINE_SEPERATOR +
                "\n    Bye. Hope to see you again soon!" +
                "\n" + LINE_SEPERATOR);
    }
}
