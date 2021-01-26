import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;

public class Jarvis {
    public Jarvis() {}

    // initialisation of JARVIS
    public void initialise() throws InterruptedException {
        System.out.println("------------------------------------------------");
        System.out.println("Importing all preferences from home interface.");
        int delay = 500;    // 0.5 seconds delay
        TimeUnit.MILLISECONDS.sleep(delay);
        System.out.println("Systems are now fully operational.");
        TimeUnit.MILLISECONDS.sleep(delay);
        System.out.print("Initialising");
        TimeUnit.MILLISECONDS.sleep(delay);
        for (int i = 0; i <= 2; i++) {
            System.out.print(".");
            TimeUnit.MILLISECONDS.sleep(delay);
        }
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("\tHello, sir. J.A.R.V.I.S at your service.");
        System.out.println("------------------------------------------------");
    }

    public void performTask() {
        Scanner in = new Scanner(System.in); // create Scanner object
        ArrayList<String> list = new ArrayList<>(); // create ArrayList
        while (true) {
            String command = in.nextLine();
            if (command.startsWith("bye")) {
                System.out.println("\tGoodbye, sir.");
                System.out.println("------------------------------------------------");
                return;
            } else if (command.startsWith("add")) {
                String item = command.replaceFirst("add ", "");
                list.add(item);
                System.out.println("\tadded: " + item);
                System.out.println("------------------------------------------------");
            } else if (command.startsWith("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(String.format("\t%d. ", i + 1) + list.get(i));
                }
                System.out.println("------------------------------------------------");
            } else {
                System.out.println("\t" + command);
                System.out.println("------------------------------------------------");
            }
        }
    }
}
