import java.util.Scanner;


public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Tasks[] inputTasks = new Tasks[100]; // will act like pointers so you have to create different objects all the time.

        int counter = 0;
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
        String input = in.nextLine();
        while(!input.equals("bye")){

            if(input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i< counter; i++) {
                    System.out.println(i+1+ ".[ " +inputTasks[i].setDisplay()+ " ]" + inputTasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
            }
            else if(input.contains("done")){
                int num = Integer.parseInt(input.split(" ")[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[\u2718] " + inputTasks[num-1].getDescription());
                System.out.println("____________________________________________________________");
                inputTasks[num-1].setDone();
            }
            else {
                Tasks temp = new Tasks(input);
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + input);
                System.out.println("____________________________________________________________");
                counter++;
            }
            input = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________");
        System.out.print("\n");

    }
}
