package Duke;

import Duke.Commands.AddCommand;
import Duke.Commands.DateCommand;
import Duke.Commands.Command;
import Duke.Commands.DeleteCommand;
import Duke.Commands.DoneCommand;
import Duke.Commands.ExitCommand;
import Duke.Commands.FindCommand;
import Duke.Commands.ListCommand;
import Duke.Commands.SaveCommand;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.CommandNotFoundError;
import Duke.Exceptions.DateInputError;
import Duke.Exceptions.DeadlineInputError;
import Duke.Exceptions.DeleteInputError;
import Duke.Exceptions.DoneInputError;
import Duke.Exceptions.EventInputError;
import Duke.Exceptions.FindInputError;
import Duke.Exceptions.TodoInputError;


import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Parser class that processes the user input into useful commands,
 * throwing exceptions if the user's command is not valid
 */
public class Parser {

    /**
     * Parser command to extract commands and parameters from the user input
     * @param userInput
     * @return The Command object of the command that the user is calling
     * @throws DukeException
     */

    public static Command parse(String userInput) throws DukeException {
        Scanner scan = new Scanner(userInput);
        String instruction = scan.next();
        Command currentCommand;
        if (instruction.equals("bye")) {
            currentCommand = new ExitCommand();
            return currentCommand;
        }
        switch (instruction) {
            case "list": {
                currentCommand = new ListCommand();
                break;
            }
            case "done": {
                try {
                    int taskNumber;
                    taskNumber = scan.nextInt();
                    if (scan.hasNext()) {
                        throw new DoneInputError();
                    }
                    currentCommand = new DoneCommand(taskNumber);
                    break;
                } catch (Exception e) {
                    throw new DoneInputError();
                }
            }
            case "todo": {
                try {
                    String taskName = scan.nextLine().trim();
                    Todo newTodo = new Todo(taskName);
                    currentCommand = new AddCommand(newTodo);
                    break;
                } catch (Exception e) {
                    throw new TodoInputError();
                }
            }
            case "deadline": {
                try {
                    String taskName = "";
                    String inputWord = scan.next();
                    while (inputWord.charAt(0) != '/'){
                        taskName += inputWord;
                        taskName += " ";
                        inputWord = scan.next();
                    }
                    taskName = taskName.trim();
                    String deadlineDate = scan.nextLine().trim();
                    LocalDate deadlineDateObject = LocalDate.parse(deadlineDate);
                    Deadline newDeadline = new Deadline(taskName, deadlineDateObject);
                    currentCommand = new AddCommand(newDeadline);
                    break;
                } catch (Exception e) {
                    throw new DeadlineInputError();
                }
            }
            case "event": {
                try {
                    String taskName = "";
                    String inputWord = scan.next();
                    while (inputWord.charAt(0) != '/'){
                        taskName += inputWord;
                        taskName += " ";
                        inputWord = scan.next();
                    }
                    taskName = taskName.trim();
                    String eventDate = scan.nextLine().trim();
                    LocalDate eventDateObject = LocalDate.parse(eventDate);
                    Event newEvent = new Event(taskName, eventDateObject);
                    currentCommand = new AddCommand(newEvent);
                    break;
                } catch (Exception e) {
                    throw new EventInputError();
                }
            }
            case "delete": {
                try {
                    int taskNumber;
                    taskNumber = scan.nextInt();
                    if (scan.hasNext()) {
                        throw new DeleteInputError();
                    }
                    currentCommand = new DeleteCommand(taskNumber);
                    break;
                } catch (Exception e) {
                    throw new DeleteInputError();
                }
            }
            case "save": {
                currentCommand = new SaveCommand();
                break;
            }
            case "date": {
                try {
                    String queryDate = scan.nextLine().trim();
                    LocalDate queryDateObject = LocalDate.parse(queryDate);
                    currentCommand = new DateCommand(queryDateObject);
                    break;
                } catch (Exception e) {
                    throw new DateInputError();
                }
            }
            case "find": {
                try {
                    String keyword = scan.next();
                    if (scan.hasNext()) {
                        throw new FindInputError();
                    }
                    currentCommand = new FindCommand(keyword);
                    break;
                } catch (Exception e) {
                    throw new FindInputError();
                }
            }
            default: {
                throw new CommandNotFoundError();
            }
        }
        return currentCommand;
    }

}
