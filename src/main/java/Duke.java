import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void showHello() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Soul, your truly soul mate");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

    }

    public static void showBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        showHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        TaskManager tasks = new TaskManager(99);

        while (!line.equals("bye")) {
            if(line.equals("list")) {
                tasks.listAllTasks();
                line = in.nextLine();
            } else if(line.matches("^\\ *(done)\\ *[1-9][0-9]*")){
                int taskIndexShow = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                tasks.markTaskDone(taskIndexShow);
                line = in.nextLine();
            } else if(line.matches("^\\ *(todo).*$")){
                String[] content = line.split("todo",2);
                tasks.addTodo(content[1].trim());
                line = in.nextLine();
            } else if(line.matches("^\\ *(deadline).*(/by).*")){
                String[] typeContentBy = line.trim().split("deadline", 2);
                String[] contentBy = typeContentBy[1].trim().split("/by", 2);
                tasks.addDeadline(contentBy[0].trim(), contentBy[1].trim());
                line = in.nextLine();
            } else if(line.matches("^\\ *(event).*(/at).*")){
                    String[] typeContentAt= line.trim().split("event", 2);
                    String[] contentAt = typeContentAt[1].trim().split("/at", 2);
                    tasks.addEvent(contentAt[0].trim(), contentAt[1].trim());
                    line = in.nextLine();
            } else
            {
                System.out.println("Undefined!");
                line = in.nextLine();

            }
        }

        showBye();
    }
}
