import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Jarvis {
    private final ArrayList<Task> list = new ArrayList<>();   // create ArrayList

    public Jarvis() {}

    // initialisation of JARVIS
    public void initialise() throws InterruptedException {
        System.out.println("------------------------------------------------");
        System.out.println("Importing all preferences from home interface.");
        int DELAY = 500;    // 0.5 seconds delay
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.println("Systems are now fully operational.");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        System.out.print("Initialising");
        TimeUnit.MILLISECONDS.sleep(DELAY);
        for (int i = 0; i <= 2; i++) {
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(DELAY);
        }
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("\tHello, sir. J.A.R.V.I.S at your service.");
        System.out.println("------------------------------------------------");
    }

    public void performTask() {
        Scanner in = new Scanner(System.in);    // create Scanner object
        while (true) {
            String command = in.nextLine();
            if (command.startsWith("bye")) {
                System.out.println("\tGoodbye, sir.");
                System.out.println("------------------------------------------------");
                return;
            } else if (command.startsWith("add") || command.startsWith("list") || command.startsWith("done")) {
                if (command.startsWith("add")) {
                    String task = command.replaceFirst("add ", "");
                    list.add(new Task(task));
                    System.out.println("\tadded: " + task);
                    System.out.println("------------------------------------------------");
                } else if (command.startsWith("list")) {
                    System.out.println("\tHere are the tasks in your list, sir:");
                    for (int i = 0; i < list.size(); i++) {
                        Task t = list.get(i);
                        System.out.println(String.format("\t\t%d. ", i + 1) + "[" + t.getTaskStatus() + "] " + t.description);
                    }
                    System.out.println("------------------------------------------------");
                } else if (command.startsWith("done")) {
                    String task = command.replaceFirst("done ", "");
                    int taskNumber = Integer.parseInt(task.substring(0, 1));
                    Task t = list.get(taskNumber - 1);
                    t.setTaskStatus(true);
                    System.out.println("\tWell done, sir! I've marked this task as done:");
                    System.out.println("\t\t[" + t.getTaskStatus() + "] " + t.description);
                    System.out.println("------------------------------------------------");
                }
            } else {
                System.out.println("\t" + command);
                System.out.println("------------------------------------------------");
            }
        }
    }
}
