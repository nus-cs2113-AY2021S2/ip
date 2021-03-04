import commands.*;
import myexceptions.*;
import parser.Parser;
import storage.FileManager;
import tasklist.Tasklist;
import ui.TextUI;

import java.io.IOException;

import static common.Messages.*;

/**
 * Main program of GuiltySpark.
 *
 * @author Jonathan Khoo
 * @version 3.0
 */
public class Spark {

    private TextUI ui;

    public static void main(String[] args)
            throws
            IOException, BlankDescriptionException,
            MissingDateException, InvalidSpaceException {

        runCommandLoopUntilExitCommand();
    }


    private static void runCommandLoopUntilExitCommand()
            throws BlankDescriptionException,
            MissingDateException, IOException, InvalidSpaceException {
        boolean isExit = false;
        FileManager.restoreFileContents("Spark.txt");
        do {
            TextUI ui = new TextUI();
            ui.printWelcomeMessage();
            String fullInputCommand = ui.getUserCommand();

            String[] command = Parser.splitTextIntoTwoFields(fullInputCommand);
            switch (command[0]) {
            case "todo":
                AddTodo.execute(fullInputCommand);
                break;
            case "deadline":
                AddDeadline.execute(fullInputCommand);
                break;
            case "event":
                AddEvent.execute(fullInputCommand);
                break;
            case "list":
                try {
                    ListTasks.execute();
                    break;
                } catch (EmptyListException e) {
                    System.out.println(EMPTY_LIST_EXCEPTION_MESSAGE);
                    break;
                }

            case "bye":
                FileManager.writeToFile("Spark.txt", Tasklist.getTaskList());
                SystemExit.execute();
                isExit = true;
                break;
            case "delete":
                try {
                    DeleteTask.execute(fullInputCommand);
                } catch (BlankDescriptionException e) {
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_NUMBER_MESSAGE);
                } catch (OutOfRangeException e) {
                    System.out.println(OUT_OF_RANGE_MESSAGE);
                } catch (EmptyListException e) {
                    System.out.println("There's nothing to delete!");
                    System.out.println(EMPTY_LIST_EXCEPTION_MESSAGE);
                }
                break;
            case "find":
                try {
                    Find.execute(fullInputCommand);
                    break;
                } catch (BlankDescriptionException e) {
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                } catch (NoMatchesFoundException e) {
                    System.out.println(NO_MATCHES_FOUND_MESSAGE);
                }
                break;

            case "done":
                try {
                    MarkAsDone.execute(fullInputCommand);
                } catch (OutOfRangeException e) {
                    System.out.println(OUT_OF_RANGE_MESSAGE);
                } catch (BlankDescriptionException e) {
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                } catch (NumberFormatException e) {
                    System.out.println(INVALID_NUMBER_MESSAGE);
                }
                break;

            default:
                System.out.println("No valid command detected! Try again!");
            }

        } while (!isExit);
    }

}

 