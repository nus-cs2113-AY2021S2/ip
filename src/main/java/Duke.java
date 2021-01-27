import java.util.Scanner;

public class Duke {
    public static String line = "\t________________________________________";
    public static Task[] tasks = new Task[100];
    public static int currentTask = 0;

    public static void takeInput() {
        while (true) {
            Scanner scannerObject = new Scanner(System.in);
            String command = scannerObject.nextLine();
            if (command.equals("bye")) {
                bye();
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.startsWith("done")) {
                done(command.split(" ")[1]);
            } else if (command.startsWith("todo")) {
                addTodo(command.split(" ", 2)[1]);
            } else if (command.startsWith("event")) {
				addEvent(command.split(" ", 2)[1]);
			} else if (command.startsWith("deadline")) {
				addDeadline(command.split(" ", 2)[1]);
			}
        }
    }

    public static void bye() {
        System.out.println(line);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(line + "\n");
    }

    public static void list() {
        System.out.println(line);
        System.out.println("\tHere are the tasks in your list:");
        for (int i=0; i<currentTask; i++) {
            System.out.println("\t" + Integer.toString(i+1) + ".[" + tasks[i].getType() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        System.out.println(line + "\n");
    }

    public static void addTodo(String todo) {
        Todo t = new Todo(todo);
        tasks[currentTask] = t;
        currentTask++;
        System.out.println(line);
        System.out.println("\tGot it. I've added this task:");
		System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
		System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
        System.out.println(line + "\n");
    }

	public static void addEvent(String event) {
		Event t = new Event(event);
		tasks[currentTask] = t;
		currentTask++;
		System.out.println(line);
        System.out.println("\tGot it. I've added this task:");
		System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
		System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
        System.out.println(line + "\n"); 
	}

	public static void addDeadline(String deadline) {
		Deadline t = new Deadline(deadline);
		tasks[currentTask] = t;
		currentTask++;
		System.out.println(line);
        System.out.println("\tGot it. I've added this task:");
		System.out.println("\t   [" + t.getType() + "][" + t.getStatusIcon() + "] " + t.getDescription());
		System.out.println("\tNow you have " + Integer.toString(currentTask) + " tasks in the list.");
        System.out.println(line + "\n"); 
	}

    public static void done(String item) {
        int i = Integer.parseInt(item)-1;
        if (i >= currentTask || i < 0) {
            System.out.println(line);
            System.out.println("\tItem does not exist!");
            System.out.println(line + "\n");
        } else {
            tasks[i].markAsDone();
            System.out.println(line);
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  [" + tasks[i].getType() + "][" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
            System.out.println(line + "\n");
        }
    }

    public static void main(String[] args) {
        System.out.println(line);
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(line + "\n");
        takeInput();
    }
}
