

import java.util.Scanner;

/**
 * This class implements the Display Interface and this class basically takes in user input and writes and,
 * saves it to the hard disk file"duke.txt".
 */

public class UI implements Display {
    Scanner sc;
    Storage storage;

    /**
     * Constructor initializes a UI object.
     *
     * @param store This stores the user input.
     */

    public UI(Storage store) {
        this.storage = store;
        this.sc = new Scanner(System.in);
    }

    /**
     * This methods here just displays a line.
     */

    public void displayLine(){
        System.out.println("_____________________________");
    }

    /**
     * This function displays the welcome message.
     */

    public void welcomeMessage() {
        displayLine();
        System.out.println("   Hello from\n" +
                "    ____        _        \n" +
                "   |  _ \\ _   _| | _____ \n" +
                "   | | | | | | | |/ / _ \\\n" +
                "   | |_| | |_| |   <  __/\n" +
                "   |____/ \\__,_|_|\\_\\___|\nWhat can I do for you?");
        displayLine();
    }

    /**
     * This methods will initiate or run the code and it will only end when,
     * the user enters "bye"
     */

    public void run() {
        String input = sc.nextLine();
        TaskList list = storage.load();
        while (!input.equals("bye")) {
            input = input.trim().replaceAll("\\s{2,}", " ");
            displayLine();
            if (input.equals("list")) {
                System.out.println(list);
                displayLine();
                input = sc.nextLine();
                continue;
            }
            String[] request = input.split(" ");
            switch (request[0]) {
            case "done":
                Parser.update(request[1], list);
                break;
            case "todo":
                Parser.addTodo(input, list);
                break;
            case "deadline":
                try {
                    Parser.getDeadline(input, list);
                } catch (InvalidDeadlineException ex) {
                    System.out.println("Oops, somewhere your deadline was wrong. Please check whether you used a /by tag");
                    displayLine();
                }
                break;
            case "event":
                try {
                    Parser.getEvent(input, list);
                } catch (InvalidEventException ex) {
                    System.out.println("Oops seems like your event is invalid. Please check your /at tag");
                    displayLine();
                }
                break;
            case "find":
                try {
                    Parser.find(input, list);
                } catch (InvalidNumberException ex) {
                    System.out.println("More than one keyword was entered");
                }
                break;
            case "delete":
                try{
                    Parser.delete(request[1], list);
                } catch (InvalidDeleteException ex){
                    System.out.println("Wrong format for the delete command has been entered");
                }
                break;
            case "":
                System.out.println("Please enter something");
                break;
            default:
                System.out.println("Please enter something valid");
                break;
            }
            input = sc.nextLine();
        }
    }

    /**
     * This methods will display the good bye message to the user.
     */

    public void goodByeMessage(){
        storage.save();
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
}