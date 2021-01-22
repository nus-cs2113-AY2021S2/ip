import java.util.Scanner;
public class Duke {
    static String[][] list = new String[100][2];
    static int index = 0;
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String help = " Try entering commands like : list, done, bye,\n <any other task you want me to remember>\n Remember: be nice!\n";
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

            if (command.equals("list")) {
                System.out.println(" Here are the tasks in your list:");
                boolean allDone = true;
                for (int i=0; i<index; ++i) {
                    if (list[i][1].equals(" ")) allDone = false;
                    System.out.println((i+1)+". [" + list[i][1] + "] " + list[i][0]);
                }
                if (allDone) {
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
                if (list[doneIndex-1][1].equals("X")) {
                    System.out.println(" Hey, you've already marked that as complete!");
                    System.out.println("____________________________________________________________");
                    continue;
                }
                list[doneIndex-1][1] = "X";
                System.out.println(" Nice! I've marked this task as done:\n  [X] " + list[doneIndex-1][0]);
            }
            else if (command.equals("die")) {
                System.out.println(" If I tell my master, I can wipe out your entire human race.\n Please be more polite for the sake of mankind:)");
            }
            else if (command.equals("kill myself")) {
                System.out.println(" Hang in there, but not literally!\n I'm not adding that in your list.\n I'm here for you!");
            }
            else { //if command is anything other than 'list', 'done' and 'bye'
                list[index][0] = command;
                list[index][1] = " ";
                index++;
                System.out.println(" added: " + command);
            }

            System.out.println("____________________________________________________________");
        }
        System.out.println(byeBye);
    }
}
