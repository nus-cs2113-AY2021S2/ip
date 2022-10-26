package ui;

import commands.Deadline;
import commands.Event;
import commands.Todo;
import constants.Constants;
import exceptions.DukeException;
import parser.Parser;
import storage.Storage;
import tasklist.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Ui {

    private static Constants constants = new Constants();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static int maxTaskCount = 100;

    /*
     * The saveTaskList method saves the contents of the tasklist into a text file "duke.txt"
     *  If an error is encountered when creating a new file "duke.txt", an IOException is thrown
     * @params tasks specifies the tasklist from which the tasks to be written onto the text file "duke.txt"
     * */
    public void saveTaskList(TaskList tasks) {
        try {
            storage.saveFile(tasks);
        } catch (IOException e) {
            System.out.println("Unable to save the current list as a text file");
        }
    }

    /*
     * The programStartUp method loads the file "duke.txt"
     * If the text file "duke.txt" cannot be found, a FileNotFoundException is thrown
     * The method also prints the welcomeMessage and displays the current list
     * @params tasks specifies the tasklist to which the tasks from the text file "duke.txt" are added to.
     * */
    public void programStartUp(TaskList tasks) {
        try {
            storage.loadFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found");
        }
        System.out.println(constants.welcomeMessage);
        tasks.showList();
    }

    /*
     * The userInterface method processes the userInput
     * @params tasks specifies the tasklist to be modified(depending on the type of userInput)
     * @params inputs specifies the content to be used to modify the tasklist
     * */
    public boolean userInterface(TaskList tasks, String[] inputs) {
        try {
            String taskType = parser.processUserCommand(inputs[0]);
            if (taskType.equals("bye")) {
                saveTaskList(tasks);
                tasks.showList();
                return false;
            } else if (taskType.equals("todo")) {
                addTodo(tasks, inputs);
                saveTaskList(tasks);
            } else if (taskType.equals("deadline")) {
                addDeadline(tasks, inputs);
                saveTaskList(tasks);
            } else if (taskType.equals("event")) {
                addEvent(tasks, inputs);
                saveTaskList(tasks);
            } else if (taskType.equals("done")) {
                markTaskAsDoneGivenUserInputs(tasks, inputs);
                saveTaskList(tasks);
            } else if (taskType.equals("list")) {
                tasks.showList();
            } else if (taskType.equals("delete")) {
                deleteTask(tasks, inputs);
                saveTaskList(tasks);
            } else if (taskType.equals("find")) {
                findTask(tasks, inputs);
            } else {
                throw new DukeException("unrecognized_input");
            }
        } catch (DukeException e) {
            System.out.println(constants.dukeExceptionMessage);
        }
        return true;
    }

    /*
     * The addTodo method adds a task of type "Todo" into a tasklist
     * @params tasks specifies the tasklist to add the "Todo" task to.
     * @params inputs specifies the content to be added to the "Todo" task
     * */
    public void addTodo(TaskList tasks, String[] inputs) {
        String description = parser.processTodo(inputs);
        if (description.equals("")) {
            System.out.println("Please enter a valid input");
            return;
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
    }

    /*
     * The addDeadline method adds a task of type "Deadline" into a tasklist
     * @params tasks specifies the tasklist to add the "Deadline" task to.
     * @params inputs specifies the content to be added to the "Deadline" task
     * */
    public void addDeadline(TaskList tasks, String[] inputs) {
        String[] information = parser.processDeadline(inputs);
        String description = information[0];
        String by = information[1];
        if (description.equals("")) {
            System.out.println("Please enter a valid input");
            return;
        } else if (by.isEmpty()) {
            System.out.println("The /by field cannot be empty");
            return;
        }
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
    }

    /*
     * The addEvent method adds a task of type "Event" into a tasklist
     * @params tasks specifies the tasklist to add the "Event" task to.
     * @params inputs specifies the content to be added to the "Event" task
     * */
    public void addEvent(TaskList tasks, String[] inputs) {
        String[] information = parser.processEvent(inputs);
        String description = information[0];
        String at = information[1];
        if (description.equals("")) {
            System.out.println("Please enter a valid input");
            return;
        } else if (at.isEmpty()) {
            System.out.println("The /at field cannot be empty");
            return;
        }
        Event event = new Event(description, at);
        tasks.addTask(event);
    }

    /*
     * The markTaskAsDoneGivenUserInputs method marks a task on the list as done based on the userInput
     * @params tasks specifies the tasklist from which the tasks to be marked as done is retrieved from.
     * @params inputs is parsed to obtain the task number of the task to be marked as done
     * */
    public void markTaskAsDoneGivenUserInputs(TaskList tasks, String[] inputs) {
        if (inputs.length == 1) {
            System.out.println("Please specify the index of the task to be marked as done");
            return;
        }
        int taskNum = maxTaskCount + 1;
        try {
            taskNum = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("The index format is not recognized. Please enter an index from the list");
        }
        if (taskNum == (maxTaskCount + 1)) {
            return;
        } else {
            tasks.markTaskAsDone(taskNum);
        }
    }

    public void findTask(TaskList tasks, String[] inputs) {
        if (inputs.length == 1) {
            System.out.println("Please specify a keyword to find");
            return;
        }
        String keyword = inputs[1];
        tasks.find(keyword);
    }

    /*
     * The deleteTask method deletes a task on the list
     * @params tasks specifies the tasklist from which the tasks to be marked as done is retrieved from.
     * @params inputs is parsed to obtain the task number of the task to be deleted.
     * */
    public void deleteTask(TaskList tasks, String[] inputs) {
        if (inputs.length == 1) {
            System.out.println("Please specify the index of the task to be deleted");
            return;
        }
        int taskNum = maxTaskCount + 1;
        try {
            taskNum = Integer.parseInt(inputs[1]) - 1;
        } catch (NumberFormatException e) {
            System.out.println("The index format is not recognized. Please enter an index from the list");
        }
        try {
            if (taskNum == (maxTaskCount + 1)) {
                return;
            } else {
                tasks.deleteTask(taskNum);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constants.indexOutOfBoundsMessage);
        }
    }
}
