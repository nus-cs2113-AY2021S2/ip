
import java.io.*;


public class Duke {
    private static final String line = "____________________________________________________________\n";
    public static int index = 0;
    private static Ui ui;
    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage(new File("data/duke.txt"));



    public static void main(String[] args) {

        storage.readFile(tasks,index);

        ui = new Ui();
        ui.printGreeting();
        String input = ui.readCommand();
        while (!input.equals("bye")) {
            if (input.contains("todo")) {
                int dividerPosition = input.indexOf(" ");
                String t = input.substring(dividerPosition + 1);
                if (dividerPosition == -1 || t.equals("") || !input.contains(" ")) {
                    System.out.print(line);
                    System.out.print("I don't quite understand." + "\n" + "Please enter the command again." + "\n");
                    System.out.print(line);
                } else {
                    tasks.add(new Todo(t));
//                    tasks.add(new Todo(t));
                    ui.printTask(new Todo(t), tasks.size());
//                    System.out.print(line + "Got it. I've added this task:" + "\n" + new Todo(t) + "\n" + "Now you have " + tasks.size() + " tasks in the list." + "\n" + line);
                }
            } else if (input.contains("deadline")) {
                int dividerPosition = input.indexOf(" ");
                int dividerPosition_1 = input.indexOf("/");
                String d = input.substring(dividerPosition + 1, dividerPosition_1);
                String by = input.substring(dividerPosition_1 + 4);
                if (dividerPosition == -1 || d.equals("") || !input.contains(" ")) {
                    System.out.print(line);
                    System.out.print("I don't quite understand." + "\n" + "Please enter the command again." + "\n");
                    System.out.print(line);
                } else {
                    tasks.add(new Deadline(d, by));
                    ui.printTask(new Deadline(d,by),tasks.size());
                }

            } else if (input.contains("event")) {
                int dividerPosition = input.indexOf(" ");
                int dividerPosition_1 = input.indexOf("/");
                String e = input.substring(dividerPosition + 1, dividerPosition_1);
                String at = input.substring(dividerPosition_1 + 4);
                if (dividerPosition == -1 || e.equals("") || !input.contains(" ")) {
                    System.out.print(line);
                    System.out.print("I don't quite understand." + "\n" + "Please enter the command again." + "\n");
                    System.out.print(line);
                } else {
                    tasks.add(new Event(e, at));
                    ui.printTask(new Event(e,at), tasks.size());
                }

            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.print(line);
                    System.out.print("You have zero task at the moment." + "\n");
                    System.out.print(line);
                } else {

                    System.out.print(line);
                    System.out.print("Here are the tasks in your list: " + "\n");

                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.print(i + 1 + "." + tasks.get(i) + "\n");
                    }
                    System.out.print(line);
                }

            } else if (input.contains("done")) {

                int dividerPosition = input.indexOf(" ");
                String taskNo = input.substring(dividerPosition + 1);
                try {
                    int taskIndex = Integer.parseInt(taskNo) - 1;
                    if (taskIndex >= tasks.size()) {
                        System.out.print(line);
                        System.out.print("You have no such task." + "\n");
                        System.out.print(line);
                    } else {
                        tasks.get(taskIndex).markAsDone();
                        System.out.print(line);
                        System.out.print("Nice! I've marked this task as done:" + "\n" + tasks.get(taskIndex) + "\n");
                        System.out.print(line);
                    }
                } catch (NumberFormatException e) {
                    System.out.print(line);
                    System.out.print("I don't quite understand." + "\n" + "Please enter a number after done command." + "\n");
                    System.out.print(line);
                }
            } else if (input.contains("delete")) {
                int dividerPosition = input.indexOf(" ");
                String taskNo = input.substring(dividerPosition + 1);
                try {
                    int taskIndex = Integer.parseInt(taskNo) - 1;
                    if (taskIndex >= tasks.size()) {
                        System.out.print(line);
                        System.out.print("You have no such task." + "\n");
                        System.out.print(line);
                    } else {
                        System.out.print(line);
                        System.out.print("Noted. I've removed this task: " + "\n" + tasks.get(taskIndex) + "\n");
                        tasks.remove(taskIndex);
                        System.out.print("Now you have " + tasks.size() + " tasks in the list." + "\n");
                        System.out.print(line);
                    }
                } catch (NumberFormatException e) {
                    System.out.print(line);
                    System.out.print("I don't quite understand." + "\n" + "Please enter a number after delete command." + "\n");
                    System.out.print(line);
                }


            } else if (input.contains("find")) {
                int dividerPosition = input.indexOf(" ");
                String keyWords = input.substring(dividerPosition + 1);
                tasks.getFoundTask(keyWords,ui);

            } else {
                ui.printErrorMessage();
            }
            input = ui.readCommand();
        }

        storage.writeToFile(tasks);
        ui.printByeMessage();
    }

}
