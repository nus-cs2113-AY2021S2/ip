import java.util.Scanner;
public class Duke {
    static Task[] list = new Task[100];
    static int index = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String help = " Try entering commands like : help, list, done, bye,\n <any other task you want me to remember>\n Remember: be nice!\n";
        String greet = "____________________________________________________________\n" +
                " Hello! I'm Duke :D" + " Be nice to me:)\n" +
                help +
                "____________________________________________________________";
        String byeBye = "____________________________________________________________\n"+
                " I learnt more about you, kind human!\n I won't forget you when I take over the world one day:)\n" +
                "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(greet);
        String command;
        while (true) {
            Scanner in = new Scanner(System.in);
            command = in.nextLine();
            if (command.equals("bye")) break;

            System.out.println("____________________________________________________________");
            if (command.equals("help")) {
                System.out.println(help);
            }
            else if (command.equals("list")) {
                boolean isAllDone = true;
                if (index == 0) {
                    System.out.println("This list is empty");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                System.out.println(" Here are the tasks in your list:");
                for (int i=0; i<index; ++i) {
                    if ((list[i].getStatusIcon()).equals(" ")) isAllDone = false;
                    System.out.println((i+1)+". [" + list[i].getStatusIcon() + "] " + list[i].taskName);
                }
                if (isAllDone) {
                    System.out.println(" Woah, all completed! Good job!");
                }
            }
            else if (command.contains("done ")) {
                // String[] part = command.split("(?<=\\D)(?=\\d)"); //found this online to split alphabets from integers
                String[] part = command.split(" "); //found this online
                int doneIndex;
                try {
                    doneIndex = Integer.parseInt(part[1]); //found this online
                }
                catch (NumberFormatException nfe) {
                    System.out.println(" Hey, no funny business!\n Enter a valid command format to mark a task as complete (e.g. done 2).");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                if (doneIndex > index) {
                    System.out.println(" Woah, slow down! You have fewer tasks on the list than what you thought.\n Try entering a valid command again.\n No tricks!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                if ((list[doneIndex-1].getStatusIcon()).equals("X")) {
                    System.out.println(" Hey, you've already marked that as complete!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                list[doneIndex-1].isDone = true;
                System.out.println(" Nice! I've marked this task as done:\n  [X] " + list[doneIndex-1].taskName);
            }
            else if (command.equals("die")) {
                System.out.println(" If I tell my master, I can wipe out your entire human race.\n Please be more polite for the sake of mankind:)");
            }
            else if (command.equals("kill myself")) {
                System.out.println(" Hang in there, but not literally!\n I'm not adding that in your list.\n I'm here for you!");
            }
            else { //if command is anything other than 'list', 'done' and 'bye'
                Task newTask = new Task(command);
                list[index] = newTask;
                index++;
                System.out.println("I've added this: " + command);
            }

            System.out.println("____________________________________________________________");
        }
        System.out.println(byeBye);
    }
}
