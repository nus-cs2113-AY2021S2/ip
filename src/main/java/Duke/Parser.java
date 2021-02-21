package Duke;

import Duke.Commands.*;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Todo;

import Duke.Exceptions.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        Scanner scan = new Scanner(fullCommand);
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
                int taskNumber;
                try {
                    taskNumber = scan.nextInt();
                } catch (Exception e) {
                    throw new DoneInputError();
                }
                currentCommand = new DoneCommand(taskNumber);
                break;
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
                int taskNumber;
                try {
                    taskNumber = scan.nextInt();
                } catch (Exception e) {
                    throw new DeleteInputError();
                }
                currentCommand = new DeleteCommand(taskNumber);
                break;
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
            default: {
                throw new CommandNotFoundError();
            }
        }
        return currentCommand;
    }

}
