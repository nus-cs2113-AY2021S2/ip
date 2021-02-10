import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm \n" + logo );
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String text = "";
        Boolean isContinue = true;

        Todo[] todos = new Todo[100];
        Integer index = 0;

        while (isContinue) {
            text = scanner.nextLine();
            if (text.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < index; i++){
                    int number = i+1;
                    System.out.println( number + "." + todos[i]);
                }
                System.out.println("____________________________________________________________");

            } else {
                String[] arr = text.split(" ", 2);
                String action = arr[0];
                String input = arr[1];

                if (action.equals("done")){
                    int setAsDone =Integer.parseInt(input) ;
                    setAsDone--;
                    todos[setAsDone].setDone(true);

                    System.out.println("____________________________________________________________");
                    System.out.println(todos[setAsDone]);
                    System.out.println("____________________________________________________________");

                } else if (action.equals("todo")) {
                    Todo newTodo = new Todo(input);
                    todos[index++] = newTodo;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todos[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (action.equals("deadline")) {
                    String[] inputs = input.split(" /by ", 2);
                    Deadline newDeadline = new Deadline(inputs[0], inputs[1]);
                    todos[index++] = newDeadline;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: " + input);
                    System.out.println(todos[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (action.equals("event")) {
                    String[] inputs = input.split(" /at ", 2);
                    Event newEvent = new Event(inputs[0], inputs[1]);
                    todos[index++] = newEvent;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: " + input);
                    System.out.println(todos[index - 1]);
                    System.out.println("Now you have " + index + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else {
                    System.out.println("Sorry, I do not know what you mean.");
                }
            }

        }

        scanner.close();

    }
}
