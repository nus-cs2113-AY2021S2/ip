import java.util.EmptyStackException;
import java.util.Scanner;



public class Duke implements BasicCommands {

    public static void displayInitialMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public static void displayGoodByeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addedMessage(){
        System.out.println("Got it. I've added this task: ");
    }

    public static void displayCount(int counter){
        System.out.println("Now you have " + counter + " tasks in the list");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int max = 100;
        Tasks[] inputTasks = new Tasks[max]; // will act like pointers so you have to create different objects all the time.

        int counter = 0;
        displayInitialMessage();
        String input = in.nextLine();
        try {
            while (!input.equals("bye")) {
                if (input.equals("list")) {
                    ListCommand command = new ListCommand(inputTasks, counter);
                    command.printList();
                } else if (input.split(" ")[0].equals("done")) {
                    int num = Integer.parseInt(input.split(" ")[1]);
                    DoneCommand command = new DoneCommand(inputTasks, num);
                    command.markDone();
                } else if (input.split(" ")[0].equals("todo")) {
                    AddCommand command = new AddCommand(inputTasks, input, counter, 1);
                    inputTasks = command.addTask();
                    counter++;

                } else if (input.split(" ")[0].equals("deadline")) {
                    AddCommand command = new AddCommand(inputTasks, input, counter, 0);
                    inputTasks = command.addTask();
                    counter++;

                } else if (input.split(" ")[0].equals("event")) {
                    AddCommand command = new AddCommand(inputTasks, input, counter, 2);
                    inputTasks = command.addTask();
                    counter++;
                }
                else {
                    throw new EmptyStackException();
                }

                input = in.nextLine();
            }

            displayGoodByeMessage();
        }
        catch (StringIndexOutOfBoundsException e ){
            displayLine();
            System.out.println("☹ OOPS!!! The description of the command cannot be empty.");
            displayLine();
        }
        catch (EmptyStackException e){
            displayLine();
            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            displayLine();
        }
    }
}
