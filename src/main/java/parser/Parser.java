/*
 * The Parser class deals with making sense of the user input
 * */
package parser;

import commands.Deadline;
import commands.Event;
import commands.Todo;
import constants.Constants;
import exceptions.DukeException;
import tasklist.TaskList;

import java.util.Arrays;
import java.util.List;

public class Parser {
    private static Constants constants = new Constants();
    public static final List<String> commands = Arrays.asList("event", "deadline",
            "todo", "list", "done", "delete", "bye", "find");

    /*
     * The processUserCommand method checks if the userInput begins with a valid command
     * @params userCommand takes in the userInput as a String
     * */
    public String processUserCommand(String userCommand) {
        if (commands.contains(userCommand)) {
            return userCommand;
        } else {
            return "invalid_user_input";
        }
    }

    /*
     * The processTodo method within the Parser class processes the user input into the required format
     * @params userInput is a String[] that stores the user input
     * */

    public String processTodo(String[] userInput) {
        String description = "";
        try {
            int wordsCount = userInput.length;
            if (userInput.length == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("todo")) {
                    continue;
                } else if (wordsCount == 0) {
                    description = description + word;
                } else {
                    description = description + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(constants.border);
            System.out.println("OOPS!! The description of the todo cannot be empty.");
            System.out.println(constants.border);
        }
        return description;
    }

    /*
     * The processEvent method within the Parser class processes the user input into the required format
     * @params userInput is a String[] that stores the user input
     * */
    public String[] processEvent(String[] userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            boolean atFlag = false;
            int wordsCount = userInput.length;
            if (wordsCount == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("/at")) {
                    atFlag = true;
                    continue;
                }
                if (word.equals("event")) {
                    continue;
                }
                if (atFlag && (wordsCount == 0)) {
                    information[1] = information[1] + word;
                } else if (atFlag) {
                    information[1] = information[1] + word + " ";
                } else {
                    information[0] = information[0] + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(constants.border);
            System.out.println("OOPS!! The description of the event cannot be empty.");
            System.out.println(constants.border);
        }
        return information;
    }

    /*
     * The processDeadline method within the Parser class processes the user input into the required format
     * @params userInput is a String[] that stores the user input
     * */
    public String[] processDeadline(String[] userInput) {
        String[] information = new String[2];
        information[0] = "";
        information[1] = "";
        try {
            boolean byFlag = false;
            int wordsCount = userInput.length;
            if (wordsCount == 1) {
                throw new DukeException("missing_parameter");
            }
            for (String word : userInput) {
                wordsCount--;
                if (word.equals("/by")) {
                    byFlag = true;
                    continue;
                }
                if (word.equals("deadline")) {
                    continue;
                }
                if (byFlag && (wordsCount == 0)) {
                    information[1] = information[1] + word;
                } else if (byFlag) {
                    information[1] = information[1] + word + " ";
                } else {
                    information[0] = information[0] + word + " ";
                }
            }
        } catch (DukeException e) {
            System.out.println(constants.border);
            System.out.println("OOPS!! The description of the deadline cannot be empty.");
            System.out.println(constants.border);
        }
        return information;
    }

    /*
     * The loadTodo method loads tasks of type "Todo" into a tasklist
     * @params tasks specifies the tasklist to add the "Todo" task to.
     * @params inputs specifies the content to be added to the "Todo" task
     * @params startingIndex specifies which index the String[] inputs should start to be read from
     * */
    public void loadTodo(TaskList tasks, String[] inputs, int startingIndex) {
        String description = "";
        for (int i = startingIndex; i < inputs.length; i++) {
            description = description + inputs[i] + " ";
        }
        Todo todo = new Todo(description);
        tasks.addTask(todo);
        if (inputs[1].equals("[X]")) {
            tasks.markTaskAsDone(tasks.getTaskCount() - 1);
        }
    }

    /*
     * The loadDeadline method loads tasks of type "Deadline" into a tasklist
     * @params tasks specifies the tasklist to add the "Deadline" task to.
     * @params inputs specifies the content to be added to the "Deadline" task
     * @params startingIndex specifies which index the String[] inputs should start to be read from
     * */
    public void loadDeadline(TaskList tasks, String[] inputs, int startingIndex) {
        String description = "";
        String by = "";
        boolean byFlag = false;
        for (int i = startingIndex; i < inputs.length; i++) {
            if (inputs[i].equals("(by:")) {
                byFlag = true;
            } else if (!byFlag) {
                description = description + inputs[i] + " ";
            } else {
                by = by + inputs[i] + " ";
            }
        }
        by = by.stripTrailing();
        by = by.replace(")", "");
        Deadline deadline = new Deadline(description, by);
        tasks.addTask(deadline);
        if (inputs[1].equals("[X]")) {
            tasks.markTaskAsDone(tasks.getTaskCount() - 1);
        }
    }

    /*
     * The loadEvent method loads tasks of type "Event" into a tasklist
     * @params tasks specifies the tasklist to add the "Event" task to.
     * @params inputs specifies the content to be added to the "Event" task
     * @params startingIndex specifies which index the String[] inputs should start to be read from
     * */
    public void loadEvent(TaskList tasks, String[] inputs, int startingIndex) {
        String description = "";
        String at = "";
        boolean atFlag = false;
        for (int i = startingIndex; i < inputs.length; i++) {
            if (inputs[i].equals("(at:")) {
                atFlag = true;
            } else if (!atFlag) {
                description = description + inputs[i] + " ";
            } else {
                at = at + inputs[i] + " ";
            }
        }
        at = at.stripTrailing();
        at = at.replace(")", "");
        Event event = new Event(description, at);
        tasks.addTask(event);
        if (inputs[1].equals("[X]")) {
            tasks.markTaskAsDone(tasks.getTaskCount() - 1);
        }
    }
}
