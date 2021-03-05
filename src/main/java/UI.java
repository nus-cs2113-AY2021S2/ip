

import java.util.Scanner;

/**
This class implements the Display Interface and this class basically takes in user input and writes and,
 saves it to the hard disk file"duke.txt".
 */


public class UI implements Display {
    Scanner sc;
    Storage storage;

    public UI(Storage store) {
        this.storage = store;
        this.sc = new Scanner(System.in);
    }
    // This function here just displays a line.
    public void displayLine(){
        System.out.println("_____________________________");
    }

    // This function displays the welcome message
    public void welcomeMessage() {
        displayLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        displayLine();
    }
    // this function will initiate or run the code and it will only end once bye has been entered.
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
            default:
                Parser.delete(request[1], list);
                break;
            }
            input = sc.nextLine();
        }
    }

    //This function here will print out the good bye message.
    public void goodByeMessage(){
        storage.save();
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
}