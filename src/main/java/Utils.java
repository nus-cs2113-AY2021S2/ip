public class Utils {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public static int commandHandler(String line){
        String[] arr;
        arr = line.split(" ");
        switch (arr[0]) {

        case ("bye"):
            return -1;

        case ("list"):
            System.out.println("Here are the tasks in your list:");
            Task.printList();
            break;
        case ("done"):
            Task.setDone(Integer.parseInt(line.split(" ")[1]) - 1);
            break;
        default:
            System.out.println("Got it. I've added this task: ");
            int indexOfSpace = line.indexOf(" ");
            String type = line.substring(0, indexOfSpace);

            switch (type) {
            case ("todo"):
                Task.addTask(new Todo(line.substring(indexOfSpace + 1)));
                System.out.println("  [T][\u2718] " + line.substring(indexOfSpace + 1));
                break;
            case ("deadline"):
                int indexOfSlash = line.indexOf("/");
                String item = line.substring(indexOfSpace + 1, indexOfSlash - 1);
                String extra = line.substring(indexOfSlash + 4);
                Task.addTask(new Dateline(item, extra));
                System.out.println("  [D][\u2718] " + item + " (by: " + extra + ")");
                break;

            case ("event"):
                indexOfSlash = line.indexOf("/");
                item = line.substring(indexOfSpace + 1, indexOfSlash - 1);
                extra = line.substring(indexOfSlash + 4);
                Task.addTask(new Event(item, extra));
                System.out.println("  [E][\u2718] " + item + " (at: " + extra + ")");
                break;
            }
            System.out.println("Now you have " + Task.getNumOfTasks() + " tasks in the list.");
        }
        return 1;
        }
    }

