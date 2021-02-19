import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    //public static Task[] tasks = new Task[100];
    public static ArrayList<Task> tasks = new ArrayList<>();

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

                // marks current task as completed
            } else if (command.contains("done")) {
                taskCompleted(command);


                // adds tasks into list
            } else if (command.contains("delete")) {
                deleteTasks(command);

                // add tasks to list

            } else {
                addTasks(command);
            }
            command = myObj.nextLine();
        }
        // exit program
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
        try {
            saveTasks(tasksFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("No file was saved due to an I/O error.\n");
        }

    }
    public static void uploadTasks(String pathOfFile) throws FileNotFoundException {
        File f = new File(pathOfFile);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] task = s.nextLine().split(" ==> ");
            if (task[0].equals("T")) {
                tasks.add(new Todo(task[2]));
            } else if (task[0].equals("D")) {
                tasks.add(new Deadline(task[2], task[3]));
            } else {
                tasks.add(new Event(task[2], task[3]));
            }

            if (task[1].equals("[X]")) {
                tasks.get(tasks.size()-1).markAsDone();
            }
        }
    }
    public static void saveTasks(String pathOfFile) throws IOException {
        FileWriter fw = new FileWriter(pathOfFile);
        for (Task task : tasks) {
            fw.write(task.stringToSave());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
    public static void addTasks(String description) {
        printDash();
        if (description.contains("todo")) {
            try {
                description = description.substring(5);
                runTodo(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a todo cannot be empty.");
                printDash();
                return;
            }
        } else if (description.contains("deadline")) {
            try {
                description = description.substring(9);
                runDeadline(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a deadline cannot be empty.");
                printDash();
                return;
            }
        } else if (description.contains("event")) {
            try {
                description = description.substring(6);
                runEvent(description);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\tOOPS!!! The description of a event cannot be empty");
                printDash();
                return;
            }
        } else {
            System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
            printDash();
            return;
        }
        System.out.println("\tGot it. I've added this task: ");
        System.out.println(tasks.get(tasks.size()-1).toString());
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
        printDash();
    }
    public static void deleteTasks(String command) {

        command = command.replace("delete", " ");
        command = command.strip();

        int count = Integer.parseInt(command);
        --count; // array starts from 0
        System.out.println("\tNoted. I've removed this task:\n" + tasks.get(count).toString());

        tasks.remove(tasks.get(count));
        System.out.println("\tNow you have " + tasks.size() + " tasks in the list");
    }

    public static void taskCompleted(String command) {
        int count = 0;
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        count = Integer.parseInt(command);
        --count; // array starts from 0
        tasks.get(count).markAsDone();

        try {
            count = Integer.parseInt(command); // convert string 2 into int 2
        } catch (NumberFormatException e) {
            System.out.println("\tOOPS!!! Please indicate task number");
            printDash();
            return;
        }
        try {
            --count; // array starts from 0
            tasks.get(count).markAsDone();
            System.out.println("\tNice! I've marked this task as done: ");
            System.out.println(tasks.get(count).toString());
        } catch (NullPointerException e) {
            System.out.println("\tOOPS!!! Please enter valid task number");
            printDash();
            return;
        }
        printDash();
    }

    // print list as a checklist when command=list
    public static void printList() {
        printDash();
        if (tasks.size() != 0) {
            System.out.println("\tHere are the tasks in your list: ");
            for (int i = 1; i <= tasks.size(); ++i) {
                System.out.println("\t" + i + "." + tasks.get(i - 1).toString());
            }
        } else {
            System.out.println("\tYou do not have any pending task.");
        }
        printDash();
    }

    public static void runDeadline(String description) {
        String[] details = description.split(" /by");
        tasks.add(new Deadline(details[0], details[1]));
    }

    public static void runTodo(String description) {
        tasks.add(new Todo(description));
    }

    public static void runEvent(String description) {
        String[] details = description.split(" /at");
        tasks.add(new Event(details[0], details[1]));
    }

    public static void printDash() {
        System.out.println("-".repeat(80));
    }

}
