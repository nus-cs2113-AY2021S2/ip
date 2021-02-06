import java.util.Scanner;

public class Duke {
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

    public static void displayGoodByeMessage(){
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public static void main (String[] args) {
        // Creating a System.in object to take in the user input.
        Scanner in = new Scanner(System.in);
        // Creating an array of Tasks class to store different descriptions
        Tasks[] inputTasks = new Tasks[100]; // will act like pointers so you have to create different objects all the time.
        // Creating a counter to be able to traverse through the entire array.
        int counter = 0;
        displayInitialMessage();
        String input = in.nextLine();
        // This loop will only end once the user inputs the string bye.
        while (!input.equals("bye")) {

            //Displays the tasks defined by the user
            if (input.equals("list")) {
                displayLine();
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i< counter; i++) {
                    System.out.println(i+1+".[ "+inputTasks[i].setDisplay()+" ]"+inputTasks[i].getDescription());
                }
                displayLine();
            } else if (input.contains("done")) {
                // This will mark the task as done.
                int num = Integer.parseInt(input.split(" ")[1]);
                displayLine();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[\u2718] " + inputTasks[num-1].getDescription());
                displayLine();
                inputTasks[num-1].setDone();
            } else {
                Tasks temp = new Tasks(input);
                inputTasks[counter]=temp;
                displayLine();
                System.out.println("added: " + input);
                displayLine();
                counter++;
            }
            input = in.nextLine();
        }
        displayGoodByeMessage();
    }
}
