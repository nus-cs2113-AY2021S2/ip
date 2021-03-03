import java.util.Scanner;

import commands.Task;
import commands.Todo;
import commands.Deadline;
import commands.Event;
import exceptions.DukeException;
import tasklist.TaskList;
import ui.Ui;
import parser.Parser;
import storage.Storage;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static final int maxTasks = 100;
    private static final String border = "    -------------------------------------------------------------------------------------------------------------------------------";
    private static TaskList tasks = new TaskList();
    private static final Ui ui = new Ui();
    private static Parser parser = new Parser();
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        try {
            storage.loadFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found");
        }
        ui.printWelcomeMessage();
        tasks.printList();
        Scanner in = new Scanner(System.in);
        boolean isOn = true;
        while (isOn && in.hasNextLine()) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            try {
                String taskType = parser.processUserCommand(words[0]);
                if (taskType.equals("bye")) {
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                    tasks.printList();
                    isOn = false;
                } else if (taskType.equals("todo")) {
                    String description = parser.processTodo(words);
                    if (description.equals("")) {
                        continue;
                    }
                    Todo todo = new Todo(description);
                    tasks.addTask(todo);
                    ui.printAddTaskMessage(tasks);
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else if (taskType.equals("deadline")) {
                    String[] information = parser.processDeadline(words);
                    String description = information[0];
                    String by = information[1];
                    if (description.equals("")) {
                        continue;
                    }
                    Deadline deadline = new Deadline(description, by);
                    tasks.addTask(deadline);
                    ui.printAddTaskMessage(tasks);
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }

                } else if (taskType.equals("event")) {
                    String[] information = parser.processEvent(words);
                    String description = information[0];
                    String at = information[1];
                    if (description.equals("")) {
                        continue;
                    }
                    Event event = new Event(description, at);
                    tasks.addTask(event);
                    ui.printAddTaskMessage(tasks);
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else if (taskType.equals("done")) {
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    tasks.markTaskAsDone(taskNum);
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else if (taskType.equals("list")) {
                    tasks.printList();
                } else if (taskType.equals("delete")) {
                    if(words.length==1){
                        throw new DukeException("unrecognized_input");
                    }
                    int taskNum = Integer.parseInt(words[1]) - 1;
                    tasks.deleteTask(taskNum);
                    try {
                        storage.saveFile(tasks);
                    } catch (IOException e) {
                        System.out.println("Unable to save the current list as a text file");
                    }
                } else if (taskType.equals("find")) {
                    String keyword = words[1];
                    tasks.find(keyword);
                } else {
                    throw new DukeException("unrecognized_input");
                }
            } catch (DukeException e) {
                System.out.println(border);
                System.out.println("    OOPS!! I'm sorry, but I don't know what that means.");
                System.out.println(border);
            }
        }
    }
}
