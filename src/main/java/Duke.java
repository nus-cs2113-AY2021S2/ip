import java.util.Scanner;
import java.util.Random;

public class Duke {

    public static void printSeparator() {
        for(int i = 0; i < 40; i++) {
            System.out.print('-');
        }
        System.out.print('\n');
    }

    public static void exitMethod() {
        System.out.print("My cover's blown!\n");
        printSeparator();
    }

    public static void echoMode(String line) {
        Random rd = new Random();
        for(int i = 0; i < line.length(); i++) {
            if(rd.nextBoolean()) {
                System.out.print(Character.toUpperCase(line.charAt(i)));
            } else {
                System.out.print(Character.toLowerCase(line.charAt(i)));
            }
        }
        System.out.print('\n');
    }

    public static void printTasks(Task[] tasks, int taskCount) {
        System.out.print("Here are the tasks in your list:\n");
        for(int i = 0; i < taskCount; i++) {
            System.out.print((i+1) + "." + tasks[i] + '\n');
        }
    }

    public static void listMode() {
        String line, details;
        String[] lineParts;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (!line.equals("bye")){
            lineParts = line.split(" ");
            switch(lineParts[0]) {
            case "list":
                printTasks(tasks, taskCount);
                break;
            case "done":
                int index = Integer.parseInt(lineParts[1]) - 1;
                if (index <= taskCount) {
                    tasks[index].markAsDone();
                } else {
                    System.out.print("That is not a valid task index, please try again.\n");
                }
                break;
            case "todo":
                tasks[taskCount++] = new Todo(line.replace("todo ", ""));
                break;
            case "deadline":
                int byIndex = line.indexOf("/by");
                tasks[taskCount++] = new Deadline(line.substring(9, byIndex), line.substring(byIndex + 4));
                break;
            case "event":
                int atIndex = line.indexOf("/at");
                tasks[taskCount++] = new Event(line.substring(6, atIndex), line.substring(atIndex + 4));
                break;
            default:
                echoMode(line);
            }
            printSeparator();
            line = in.nextLine();
        }
        exitMethod();
    }

    public static void main(String[] args) {
        printSeparator();
        System.out.print("Greetings, fellow humans!\nI am CI.\nHow may I help you?\n");
        printSeparator();
        listMode();
    }
}
