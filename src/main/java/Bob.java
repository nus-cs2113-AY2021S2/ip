import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    // List of tasks Bob has
    public static ArrayList<String> tasks = new ArrayList<String>();

    public static void addToList(String input) {
        tasks.add(input);
        String echoString = "____________________________________________________________\n" +
                "added: " + input + "\n" +
                "____________________________________________________________\n";
        System.out.println(echoString);
    }

    public static void printList() {
        System.out.print("____________________________________________________________\n");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String welcome = "____________________________________________________________\n" +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n";
        System.out.println(welcome);

        // Scanner loop until bye command
        boolean isScanning = true;
        while (isScanning) {
            String line = in.nextLine();

            switch (line) {
            case "list":
                printList();
                break;
            case "bye":
                isScanning = false;
                break;
            default:
                addToList(line);
            }
        }

        String goodbye = "____________________________________________________________\n" +
                " Chao 👌\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }
}
