import java.util.Scanner;

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

    public static void echoMode() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")){
            System.out.print(line + '\n');
            printSeparator();
            line = in.nextLine();
        }
        exitMethod();
    }

    public static void listMode() {
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] tasks = new Task[100];
        int taskCount = 0;
        while (!line.equals("bye")){
            if(line.equals("list")) {
                System.out.print("Here are the tasks in your list:\n");
                for(int i = 0; i < taskCount; i++) {
                    System.out.print((i+1) + "." + tasks[i].getStatusIcon() + tasks[i].getDescription() + '\n');
                }
            } else if (line.contains("done")) {
                String[] temp = line.split(" ");
                int index = Integer.parseInt(temp[1]) - 1;
                if(index <= taskCount) {
                    tasks[index].markAsDone();
                    System.out.print("Nice! I've marked this task as done:\n" + tasks[index].getStatusIcon()
                            + tasks[index].getDescription() + '\n');
                } else {
                    System.out.print("That is not a valid task index, please try again.\n");
                }
            } else {
                tasks[taskCount++] = new Task(line, false);
                System.out.print("added: " + line + '\n');
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
        //echoMode();
        listMode();
    }
}
