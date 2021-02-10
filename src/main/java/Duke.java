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
                    System.out.println(i+1+" .[ " +inputTasks[i].displayTaskType()+ " ]" + " [" +inputTasks[i].setDisplay()+ "] " + inputTasks[i].getDescription() + inputTasks[i].extraDescription);
                }
                System.out.println("____________________________________________________________");
            }
            else if(input.split(" ")[0].equals("done")){
                int num = Integer.parseInt(input.split(" ")[1]);
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" +inputTasks[num-1].displayTaskType()+ "] [\u2718] " + inputTasks[num-1].getDescription());
                System.out.println("____________________________________________________________");
                inputTasks[num-1].setDone();
            }
            else if(input.split(" ")[0].equals("todo")){
                int startIndex = input.indexOf("o");
                ToDo temp = new ToDo(input.substring(startIndex+4),input.split(" ")[0]);
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println("[ " +inputTasks[counter].displayTaskType()+ " ]" + " [" +inputTasks[counter].setDisplay()+ "] " + inputTasks[counter].getDescription() + inputTasks[counter].extraDescription);
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list");
                System.out.println("____________________________________________________________");

            }
            else if(input.split(" ")[0].equals("deadline")){
                int startIndex = input.indexOf("n");
                int endIndex = input.indexOf("\\at");
                String preciseInput = input.substring(startIndex+3,endIndex-1).trim();
                Deadline temp = new Deadline(preciseInput,input.split(" ")[0],input.substring(endIndex+4));
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println("[ " +inputTasks[counter].displayTaskType()+ " ]" + " [" +inputTasks[counter].setDisplay()+ "] " + inputTasks[counter].getDescription() + inputTasks[counter].extraDescription);
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list");
                System.out.println("____________________________________________________________");

            }
            else if(input.split(" ")[0].equals("event")){

                int startIndex = input.indexOf("t");
                int endIndex = input.indexOf("\\by");
                String preciseInput = input.substring(startIndex+2,endIndex-1).trim();
                Event temp = new Event(preciseInput,input.split(" ")[0],input.substring(endIndex+4));
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println("[ " +inputTasks[counter].displayTaskType()+ " ]" + " [" +inputTasks[counter].setDisplay()+ "] " + inputTasks[counter].getDescription() + inputTasks[counter].extraDescription);
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list");
                System.out.println("____________________________________________________________");

            }
            else {
                Tasks temp = new Tasks(input);
                inputTasks[counter]=temp;
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: ");
                System.out.println(input);
                counter++;
                System.out.println("Now you have " + counter + " tasks in the list");
                System.out.println("____________________________________________________________");
            }
            input = in.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.print("____________________________________________________________");
        System.out.print("\n");

    }
}
