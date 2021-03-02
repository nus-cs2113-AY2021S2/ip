import java.util.Locale;
import java.util.Scanner;
// git add .
// git tag level-3
// git commit -m ""
// git push origin --tags

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
        String input = "";
        Boolean isContinue = true;
        Task[] tasks = new Task[100];
        Integer numberOfTask = 0;

        while (isContinue) {
            input = scanner.nextLine();
            String[] splittedCommand = input.split("\\s+",2);
            String commandType = splittedCommand[0].toUpperCase();

            switch (commandType) {
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    isContinue = false;
                    break;
                case "LIST":
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < numberOfTask; i++){
                        int number = i+1;
                        System.out.println( number + "." + tasks[i]);
                    }
                    System.out.println("____________________________________________________________");
                    break;
                case "DONE":
                    int taskIndex =Integer.parseInt(splittedCommand[1]) ;
                    taskIndex--;
                    tasks[taskIndex].setDone(true);

                    System.out.println("____________________________________________________________");
                    System.out.println(tasks[taskIndex]);
                    System.out.println("____________________________________________________________");
                    break;
                case "TODO":
                    String description = splittedCommand[1];
                    if(description == ""){
                        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Todo newTodo = new Todo(description);
                    tasks[numberOfTask++] = newTodo;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(tasks[numberOfTask - 1]);
                    System.out.println("Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "DEADLINE":
                    String deadlineDescription = splittedCommand[1];
                    if(deadlineDescription == ""){
                        System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] deadline = deadlineDescription.split(" /by ", 2);
                    Deadline newDeadline = new Deadline(deadline[0], deadline[1]);
                    tasks[numberOfTask++] = newDeadline;

                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: " + splittedCommand[1]);
                    System.out.println(tasks[numberOfTask - 1]);
                    System.out.println("Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                case "EVENT":
                    String[] eventDescription = splittedCommand[1].split(" /at ", 2);
                    Event newEvent = new Event(eventDescription[0], eventDescription[1]);
                    tasks[numberOfTask++] = newEvent;
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task: " + splittedCommand[1]);
                    System.out.println(tasks[numberOfTask - 1]);
                    System.out.println("Now you have " + numberOfTask + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }



        }

        scanner.close();


    }
}
