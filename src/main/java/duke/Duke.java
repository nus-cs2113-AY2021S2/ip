package duke;

import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<Task>();
    public static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        String userInput = "";
        Output.printStart();

        Storage.readFile(tasks);

        while (true) {
            userInput = SCANNER.nextLine();
            Output.printBorder();
            if (userInput.trim().equals("bye")) {
                Storage.writeToFile(tasks);
                Output.printBye();
                break;
            } else if (userInput.startsWith("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(i + 1 + ".");
                    System.out.println(tasks.get(i));
                }
            } else if (userInput.split(" ")[0].equals("done")) {
                if(userInput.length() < 6) {
                    Output.printEmptyCommand("done");
                    continue;
                }
                int processedInput;
                processedInput = Integer.parseInt(userInput.replaceAll("[^0-9]", "")) - 1;
                tasks.get(processedInput).setDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasks.get(processedInput));
            } else if (userInput.split(" ")[0].equals("todo")) {
                if(userInput.length() < 6) {
                    Output.printEmptyCommand("todo");
                    continue;
                }
                System.out.println("Got it. I've added this task: ");
                Task newTask = new Todo(userInput.substring(5));
                tasks.add(newTask);
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.split(" ")[0].equals("deadline")) {
                if(userInput.length() < 10) {
                    Output.printEmptyCommand("deadline");
                    continue;
                }
                System.out.println("Got it. I've added this task: ");
                String by = "";
                String processedDeadlineInput;
                int getSlashIndex = 0;
                for (int i = 0; i < userInput.length(); i++) {
                    char getSlash = userInput.charAt(i);
                    if (getSlash == '/') {
                        getSlashIndex = i;
                        break;
                    }
                }
                by = userInput.substring(getSlashIndex + 4);
                processedDeadlineInput = userInput.substring(9, getSlashIndex).trim();
                Task newTask = new Deadline(processedDeadlineInput, by);
                tasks.add(newTask);
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (userInput.split(" ")[0].equals("event")) {
                if(userInput.length() < 7) {
                    Output.printEmptyCommand("event");
                    continue;
                }
                System.out.println("Got it. I've added this task: ");
                String at;
                String processedEventInput;
                int getSlashIndex = 0;
                for (int i = 0; i < userInput.length(); i++) {
                    char getSlash = userInput.charAt(i);
                    if (getSlash == '/') {
                        getSlashIndex = i;
                        break;
                    }
                }
                at = userInput.substring(getSlashIndex + 4).trim();
                processedEventInput = userInput.substring(6, getSlashIndex).trim();
                Task newTask = new Event(processedEventInput, at);
                tasks.add(newTask);
                System.out.println("  " + newTask.toString());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                Output.printWrongCommand();
            }

            Output.printBorder();
        }

    }
}

