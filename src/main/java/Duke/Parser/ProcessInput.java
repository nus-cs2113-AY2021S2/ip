package Duke.Parser;

import Duke.Commands.*;
import Duke.Duke;
import Duke.Errors.Errors;
import Duke.UI.UI;

import java.util.Scanner;

public class ProcessInput extends Duke {
    /**
     * Take input from user
     * switch according to the first word
     * show errors if the second word is invalid
     */
    public static void processInput(){
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println(UI.LINE);
            switch (inputStringSplit[0]) {
                case "bye":
                    //ends program
                    return;
                case "done":
                    //mark a task as done
                    if (inputStringSplit.length > 1) {
                        //makes sure there's an index after "done", otherwise shows error
                        MarkAsDoneCommand.markAsDone(inputStringSplit[1]);
                    } else {
                        Errors.noIndexInput();
                    }
                    break;
                case "delete":
                    //deletes a task
                    if (inputStringSplit.length > 1) {
                        DeleteCommand.deleteTask(inputStringSplit[1]);
                    } else {
                        Errors.noIndexInput();
                    }
                    break;
                case "list":
                    //shows list
                    PrintListCommand.printList(0, taskCount);
                    break;
                case "help":
                    //shows help menu
                    UI.helpMenu();
                    break;
                case "date":
                    //finds tasks on a date
                    if (inputStringSplit.length > 1) {
                        FindTaskCommand.FindWithDate(inputStringSplit[1]);
                    } else {
                        Errors.noIndexInput();
                    }
                    break;
                case "find":
                    //finds tasks containing keyword
                    if (inputStringSplit.length > 1) {
                        FindTaskCommand.FindWithKeyword(inputStringSplit[1]);
                    } else {
                        Errors.noIndexInput();
                    }
                    break;
                case "todo":
                    if (inputStringSplit.length > 1) {
                        AddTodoCommand.execute(inputStringSplit[1]);
                    } else {
                        Errors.taskDescriptionEmpty();
                    }
                    break;
                case "deadline":
                    if (inputStringSplit.length > 1) {
                        AddDeadlineCommand.execute(inputStringSplit[1]);
                    } else {
                        Errors.taskDescriptionEmpty();
                    }
                    break;
                case "event":
                    if (inputStringSplit.length > 1) {
                        AddEventCommand.execute(inputStringSplit[1]);
                    } else {
                        Errors.taskDescriptionEmpty();
                    }
                    break;
                default:
                    Errors.illegalInput();
                    break;
            }
        }
    }
}
