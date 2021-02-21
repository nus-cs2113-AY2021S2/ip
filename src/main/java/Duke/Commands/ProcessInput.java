package Duke.Commands;

import Duke.Duke;
import Duke.Exceptions.DukeException;
import Duke.UI.UI;

import java.util.Scanner;

public class ProcessInput extends Duke {
    public static void processInput(){
        Scanner userInput = new Scanner(System.in);
        while(true) {
            inputString = userInput.nextLine().trim();
            String[] inputStringSplit = inputString.split(" ", 2);
            System.out.println(LINE);
            switch (inputStringSplit[0]) {
                case "bye":
                    return;
                case "done":
                    if (inputStringSplit.length > 1) {
                        MarkAsDone.markAsDone(inputStringSplit[1]);
                    } else {
                        DukeException.noIndexInput();
                    }
                    break;
                case "delete":
                    if (inputStringSplit.length > 1) {
                        Delete.deleteTask(inputStringSplit[1]);
                    } else {
                        DukeException.noIndexInput();
                    }
                    break;
                case "list":
                    PrintList.printList(0, taskCount);
                    break;
                case "help":
                    UI.helpMenu();
                    break;
                case "date":
                    if (inputStringSplit.length > 1) {
                        FindTask.FindWithDate(inputStringSplit[1]);
                    } else {
                        DukeException.taskWithoutTime();
                    }
                    break;
                case "find":
                    if (inputStringSplit.length > 1) {
                        FindTask.FindWithKeyword(inputStringSplit[1]);
                    } else {
                        DukeException.taskWithoutTime();
                    }
                    break;
                default:
                    if (AddToList.keywordCheck(inputStringSplit[0])) {
                        if (inputStringSplit.length > 1) {
                            AddToList.AddToList(inputStringSplit[0], inputStringSplit[1]);
                        } else {
                            DukeException.taskDescriptionEmpty();
                        }
                    } else {
                        DukeException.illegalInput();
                    }
                    break;
            }
        }
    }
}
