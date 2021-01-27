import java.util.ArrayList;
import java.util.Scanner;

public class Bob {
    public static TaskList taskList = new TaskList();

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
            String[] inputArr = line.split(" ");

            // String before first space taken as command rest as args
            switch (inputArr[0]) {
            case "list":
                taskList.printList();
                break;
            case "done":
                taskList.completeTask(Integer.parseInt(inputArr[1]));
                break;
            case "bye":
                isScanning = false;
                break;
            default:
                taskList.addToList(line);
            }
        }

        String goodbye = "____________________________________________________________\n" +
                " Chao 👌\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }
}
