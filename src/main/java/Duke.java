import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static Task[] Tasks = new Task[100];

    public static void main(String[] args) {
        printGreeting();
        printLine();
        loopAddAndList();
        sayGoodbye();
    }
    public static void loopAddAndList() {
        while(true) {
            if(Task.totalTasks > 100) {
                System.out.println("Maximum capacity reached");
                return;
            }
            String line = getInput();
            if(line.equalsIgnoreCase("bye")) {
                return;
            }
            if(line.equalsIgnoreCase("list")) {
                listItems();
            }
            else {
                addItem(line);
            }
        }
    }
    public static void listItems() {
        for(int i = 0; i < Task.totalTasks; i++) {
            System.out.println(i+1 + ". " + Tasks[i].name);
        }
        printLine();
    }
    public static void addItem(String nameOfTask) {
        int current = Task.totalTasks;
        Tasks[current] = new Task(nameOfTask);
        System.out.println("Wawaweewah! Added: " + nameOfTask);
        Task.totalTasks += 1;
        printLine();
    }

    public static void printGreeting() {
        System.out.println("Wagwan! I is Ali G. West side.");
        System.out.println("What is we chattin bout today?");
    }
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }
    public static void sayGoodbye() {
        System.out.println("Goodbye, big up yourself, keep it real, respekt.");
        printLine();
    }
    public static String getInput() {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        return line;
    }
}
