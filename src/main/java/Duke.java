import commands.*;
import myexceptions.*;
import parser.Parser;
import storage.FileManager;
import tasklist.*;
import ui.TextUI;

import java.io.IOException;

import static common.Messages.*;

public class Duke {


    //--------------------------------------------------------------------------------
    private TextUI ui;

    private static void runCommandLoopUntilExitCommand()
            throws BlankDescriptionException, EmptyListException,
            MissingDateException, IOException, InvalidSpaceException, NoMatchesFoundException {
        boolean isExit = false;
        FileManager.restoreFileContents("Duke.txt");
        do {
            TextUI ui = new TextUI();

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
                try{
                    ListTasks.execute();
                    break;
                } catch (EmptyListException e) {
                    System.out.println(EMPTY_LIST_EXCEPTION_MESSAGE);
                    break;
                }

            case "bye":
                FileManager.writeToFile("Duke.txt", Tasklist.getTaskList());
                SystemExit.execute();
                isExit = true;
                break;
            case "delete":
                try {
                    DeleteTask.execute(fullInputCommand);
                } catch (BlankDescriptionException e) {
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                } catch (NumberFormatException e){
                    System.out.println(INVALID_NUMBER_MESSAGE);
                } catch (OutOfRangeException e) {
                    System.out.println(OUT_OF_RANGE_MESSAGE);
                } catch (EmptyListException e) {
                    System.out.println("There's nothing to delete!");
                    System.out.println(EMPTY_LIST_EXCEPTION_MESSAGE);
                }
                break;
            case "find":
                try{
                    Find.execute(fullInputCommand);
                    break;
                } catch (BlankDescriptionException e){
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                } catch (NoMatchesFoundException e) {
                    System.out.println(NO_MATCHES_FOUND_MESSAGE);
                }
                break;

            case "done":
                try{
                    MarkAsDone.execute(fullInputCommand);
                } catch (OutOfRangeException e) {
                    System.out.println(OUT_OF_RANGE_MESSAGE);
                } catch (BlankDescriptionException e) {
                    System.out.println(BLANK_EXCEPTION_MESSAGE);
                }
                break;

            default:
                System.out.println("No valid command detected! Try again!");
            }

        } while (!isExit);
    }


    public static void main(String[] args)
            throws InvalidCommandException, InvalidDateException,
            IOException, BlankDescriptionException, EmptyListException,
            MissingDateException, InvalidSpaceException, NoMatchesFoundException {


        runCommandLoopUntilExitCommand();



        /*
        String text = "hi";

        try{
            restoreFileContents("Duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        while(!text.equalsIgnoreCase("Bye")){
            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            //tasklist.Task t = new tasklist.Task(text);
            try {
                String[] arr = text.split(" "); //split command input into words
                String command = arr[0];


                switch (command) {
                case "deadline":
                case "tasklist.Deadline":
                case "DEADLINE":
                    try {
                        if(!text.contains("/") && !text.substring(9).isBlank()){
                            throw new MissingDateException();
                        }
                        int indexOfBy = text.indexOf('/');
                        description = text.substring(9, indexOfBy - 1);
                        by = text.substring(indexOfBy + 1);
                        Deadline deadlineTask = new Deadline(description, by);

                        storeTask(deadlineTask);
                    } catch (StringIndexOutOfBoundsException | BlankDescriptionException e) {
                        System.out.println("Description cannot be blank!");
                    } catch (MissingDateException e){
                        System.out.println("Missing date! Utilise '/' to key in your date!");
                    }
                    break;
                case "todo":
                case "tasklist.Todo":
                case "TODO":

                    try {
                        if(text.substring(4).isBlank()){
                            throw new BlankDescriptionException();
                        }
                        description = text.substring(5);
                        Todo todoTask = new Todo(description);
                        storeTask(todoTask);
                        break;
                    } catch (BlankDescriptionException e) {
                        System.out.println("tasklist.Todo cannot be empty");
                        break;
                    }


                case "event":
                case "tasklist.Event":
                case "EVENT":

                    try {
                        if(!text.contains("/") && !text.substring(6).isBlank()){
                            throw new MissingDateException();
                        }
                        indexOfBy = text.indexOf('/');

                        String description = text.substring(6, indexOfBy - 1);
                        String by = text.substring(indexOfBy + 1);
                        Event eventTask = new Event(description, by);
                        storeTask(eventTask);
                    } catch (IndexOutOfBoundsException | BlankDescriptionException e) {
                        System.out.println("tasklist.Event description cannot be empty! Try again!");
                    } catch (MissingDateException e){
                        System.out.println("tasklist.Event must have a date! Try again!");
                    }
                    break;
                case "List":
                case "list":
                case "LIST":
                    listArray(tasks);
                    break;
                case "Done":
                case "done":
                case "DONE":
                    try {
                        Integer taskIndex = Integer.parseInt(arr[1]);
                        markAsDone(taskIndex);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("What do you want to mark as done?");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("That's not in the list! Try again!");
                        break;
                    }
                case "Delete":
                case "delete":
                case "DELETE":
                    //try {
                        Integer taskIndex = Integer.parseInt(arr[1]);
                        markAsDeleted(taskIndex);
                        break;
                    //} catch (ArrayIndexOutOfBoundsException e) {
                      //  System.out.println("What do you want to mark as done?");
                        //break;
                    //}
                case "Bye":
                case "bye":
                case"BYE":
                    break;


                default:

                    throw new InvalidCommandException();
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("No input detected. What did you want to do?");
            } catch (InvalidCommandException e){
                System.out.println("Invalid command detected! What do you want to do?");
            } catch (IndexOutOfBoundsException e){
                System.out.println("There are only " + tasks.size() + " number of tasks in the list! Try again!");
            }
            String file2 = "Duke.txt";
            try {
                writeToFile(file2, tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

    private static void markAsDeleted(Integer taskIndex) {
        if (taskIndex > tasks.size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        Task t = tasks.get(taskIndex - 1);
        System.out.println("Noted! I have deleted this task for you: ");
        System.out.println(taskIndex + "." + t.getStatusIcon() + " " + t.getDescription());
        tasks.remove(taskIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list!");
    }
    */

    }
}
