

import java.util.Scanner;

public class UI implements Display {
    Scanner sc;
    Storage storage;

    public UI(Storage store) {
        this.storage = store;
        this.sc = new Scanner(System.in);
    }
    public void displayLine(){
        System.out.println("_____________________________");
    }

    public void welcomeMessage() {
        displayLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        displayLine();
    }

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

    public void goodByeMessage(){
        storage.save();
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
}