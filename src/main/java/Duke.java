import java.util.Scanner;
public class Duke {

    public static void main(String[] args) {

        String doneString = null;
        Task[] tasks = new Task[101];

        Greetings.welcome();

        Scanner sc = new Scanner(System.in);
        String inputCommand = sc.nextLine();

        while (!inputCommand.equals("bye")) {
            if (inputCommand.equals("list")) {
                if (Task.getListCount() > 1) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println("_______________________________________");
                    for (int i = 1; i < Task.getListCount(); i++) {
                        System.out.println(i + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
                        }
                } else {
                    System.out.println("\uD83D\uDE14\n Please add a task to the list");
                }
            } else if (inputCommand.equals("blah")) {
                System.out.println("blah");

            } else {
                String trimmed_inputCommand = inputCommand.trim();
                if (trimmed_inputCommand.length() > 4) {
                    doneString = trimmed_inputCommand.substring(0, 4);
                }
                if ("done".equals(doneString) && (trimmed_inputCommand.length() > 4)) {
                    int doneIndex = Integer.parseInt(inputCommand.substring(5));
                    tasks[doneIndex].updateDoneStatus();

                } else {
                    tasks[Task.getListCount()] = new Task(inputCommand);
                }
            }
            inputCommand = sc.nextLine();
        }
        Greetings.goodbye();
    }

}
