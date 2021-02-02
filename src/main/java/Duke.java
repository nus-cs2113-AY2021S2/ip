import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);
        ArrayList<Todo> todos = new ArrayList<Todo>();

        System.out.println("~____________________________________________________________~");
        System.out.println("What's up! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("~____________________________________________________________~\n");

        boolean isRunning = true;
        while (isRunning) {
            String phrase = sc.nextLine();

            if (phrase.equals("bye")) {
                // Exits program
                isRunning = false;
            } else if (phrase.equals("list")) {
                // List all todos
                System.out.println("~____________________________________________________________~");
                System.out.println("Here are the todos in your list:");
                int i = 0;
                for (Todo todo : todos) {
                    System.out.println(++i + ". [" + todo.getStatusIcon() + "] " + todo.getDescription());
                };
                System.out.println("~____________________________________________________________~\n");
            } else if (phrase.startsWith("done ")) {
                // Set a todo as done
                int todoIndex = phrase.charAt(phrase.length()-1) - '0';

                System.out.println("~____________________________________________________________~");
                System.out.println("Nice! I've marked this todo as done:");
                Todo todo = todos.get(todoIndex - 1);
                todo.markAsDone();
                System.out.println("[" + todo.getStatusIcon() + "] " + todo.getDescription());
                System.out.println("~____________________________________________________________~\n");
            } else {
                // Add a todo
                System.out.println("~____________________________________________________________~");
                Todo todo = new Todo(phrase);
                System.out.println("added: " + phrase);
                todos.add(todo);
                System.out.println("~____________________________________________________________~\n");
            }
        }
        System.out.println("\n~____________________________________________________________~");
        System.out.println("Alright cheers mate!");
        System.out.println("~____________________________________________________________~");
    }
}
