import java.util.Scanner;

public class Bob {
    public static TaskList taskList = new TaskList();

    public static void main(String[] args) {
        welcomeMessage();
        scanInput();
        goodbyeMessage();
    }

    private static void scanInput() {
        Scanner in = new Scanner(System.in);
        scanLoop(in);
    }

    /**
     * Scanner loop until bye command
     */
    private static void scanLoop(Scanner in) {
        boolean isScanning = true;
        while (isScanning) {
            String line = in.nextLine();
            isScanning = scanSwitch(line);
        }
    }

    /**
     * String before first space taken as command rest as args
     */
    private static boolean scanSwitch(String line) {
        String[] lineArr = line.split(" ");
        boolean isScanning = true;

        switch (lineArr[0]) {
        case "list":
            taskList.printList();
            break;
        case "done":
            taskList.completeTask(Integer.parseInt(lineArr[1]));
            break;
        case "todo":
            addTodo(line);
            break;
        case "deadline":
            addDeadline(line);
            break;
        case "event":
            addEvent(line);
            break;
        case "bye":
            isScanning = false;
            break;
        default: //regular task
            taskList.addToList(new Task(line));
        }
        return isScanning;
    }

    private static void addTodo(String line) {
        String label = line.substring("todo".length() + 1);
        taskList.addToList(new Todo(label));
    }

    private static void addEvent(String line) {
        final String startTimeMarker = "/at";
        int divider = line.indexOf(startTimeMarker);
        String dueDate = line.substring(divider + startTimeMarker.length() + 1);
        String label = line.substring("event".length() + 1 ,divider);

        taskList.addToList(new Event(label, dueDate));
    }

    private static void addDeadline(String line) {
        final String dueTimeMarker = "/by";
        int divider = line.indexOf(dueTimeMarker);
        String dueDate = line.substring(divider + dueTimeMarker.length() + 1);
        String label = line.substring("deadline".length() + 1 ,divider);

        taskList.addToList(new Deadline(label, dueDate));
    }

    private static void goodbyeMessage() {
        String goodbye = "____________________________________________________________\n" +
                " Chao 👌\n" +
                "____________________________________________________________";
        System.out.println(goodbye);
    }

    private static void welcomeMessage() {
        String welcome = "____________________________________________________________\n" +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n";
        System.out.println(welcome);
    }
}
