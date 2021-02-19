import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Duke {
    public static Task[] tasks = new Task[100];
    public static int size = 0;


    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // print welcome message
        System.out.println("Hello from\n" + logo);
        System.out.println("-".repeat(50));
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        printDash();
        String pathOfFile = new File("").getAbsolutePath();
        File tasksFile = new File(pathOfFile + "/duke.txt");
        try {
            if (tasksFile.createNewFile()) {
                System.out.println("\tTo save your task locally,\n" +
                        "\tA new file has been created at:\n\t" +
                        tasksFile.getAbsolutePath() + "\n");
            }
            uploadTasks(tasksFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("\tThere was an I/O error:\nBye!\n");
            e.printStackTrace();
        }
        Scanner myObj = new Scanner(System.in);
        String command = myObj.nextLine();

        while (!command.equals("bye")) {
            // prints a checklist
            if (command.equals("list")) {
                printList();
            }
            // marks current task as completed
            else if (command.contains("done")) {
                taskCompleted(command);
            }
            // adds tasks into list
            else {
                addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program when input=bye
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
        if (command.equals("bye")) {
            try {
                saveTasks(tasksFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("No file was saved due to an I/O error.\n");
            }
        }

    }
    public static void uploadTasks(String pathOfFile) throws FileNotFoundException {
        File f = new File(pathOfFile);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" ==> ");
            if (task[0].equals("T")) {
                tasks[size] = new Todo(task[2]);
            } else if (task[0].equals("D")) {
                tasks[size] = new Deadline(task[2], task[3]);
            } else {
                tasks[size] = new Event(task[2], task[3]);
            }

            if (task[1].equals("[X]")) {
                tasks[size].markAsDone();
            }
            size++;
        }
    }
    public static void saveTasks(String pathOfFile) throws IOException {
        FileWriter fw = new FileWriter(pathOfFile);
        for (int i=0; i<size; ++i) {
            fw.write(tasks[i].stringToSave());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
    public static void addTasks(String description) {
        printDash();
        System.out.println("\tGot it. I've added this task: ");
        if (description.contains("todo")) {
            description = description.replace("todo", "");
            description = description.strip();
            runTodo(description);
        } else if (description.contains("deadline")) {
            description = description.replace("deadline", "");
            description = description.strip();
            runDeadline(description);
        } else if (description.contains("event")) {
            description = description.replace("event", "");
            description = description.strip();
            runEvent(description);
        }
        System.out.println(tasks[size++].toString());
        System.out.println("\tNow you have " + size + " tasks in the list");
        printDash();
    }

    // mark and display task that is completed
    public static void taskCompleted(String command) {
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        // convert string 2 into int 2
        int count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks[count].markAsDone();
        printDash();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks[count].toString());
        printDash();
    }

    // print list as a checklist when command=list
    public static void printList() {
        printDash();
        if (size != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 1; i <= size; ++i) {
                System.out.println("\t" + i + "." + tasks[i - 1].toString());
            }
        } else {
            System.out.println("\tYou do not have any pending task.");
        }
        printDash();
    }

    public static void runDeadline(String description) {
        String[] details = description.split(" /by");
        tasks[size] = new Deadline(details[0], details[1]);
    }

    public static void runTodo(String description) {
        tasks[size] = new Todo(description);
    }

    public static void runEvent(String description) {
        String[] details = description.split(" /at");
        tasks[size] = new Event(details[0], details[1]);
    }

    public static void printDash() {
        System.out.println("-".repeat(50));
    }

}
