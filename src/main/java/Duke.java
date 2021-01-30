import java.util.*;

public class Duke{
    Scanner sc = new Scanner(System.in);
    Task[] t = new Task[100];
    private static int taskNo = 0;

    public void run() {

        String input;
        String[] switchChoice;
        do {
            Ui.printMenu();
            input = sc.nextLine().trim().toLowerCase();
            switchChoice = input.toLowerCase().split(" ", 2);
            switch (switchChoice[0]){
            case "todo":
                enterToDo(switchChoice[1]);
                break;
            case "deadline":
                enterDeadline(switchChoice[1]);
                break;
            case "event":
                enterEvent(switchChoice[1]);
                break;
            case "list":
               printDescription();
                break;
            case "done":
                markDone(switchChoice[1]);
                break;
            }
        } while (!input.equals("bye"));
    }


    private void enterDeadline(String input) {
        String[] inputSplit = input.split("/by");
        t[taskNo] = new Deadline(inputSplit[0], inputSplit[1]);
        t[taskNo].setTaskNo(taskNo);
        taskNo++;
    }

    private void enterEvent(String input) {
        String[] inputSplit = input.split("/at");
        t[taskNo] = new Event(inputSplit[0], inputSplit[1]);
        t[taskNo].setTaskNo(taskNo);
        taskNo++;
    }

    private void enterToDo(String input) {
        t[taskNo] = new Todo(input);
        t[taskNo].setTaskNo(taskNo);
        taskNo++;
    }

    private void printDescription(){
        System.out.println("Select this to print description");
        for (int i = 0; i < taskNo; i++) {
            System.out.println((t[i].getTaskNo() + 1) + ". " + t[i].toString());
        }
        System.out.println("You have " + (taskNo+1) + " task(s) in the list." );

    }

    private void markDone(String input){
        int newValue = Integer.parseInt(input);
        t[newValue - 1].setDone(true);
        System.out.println("Task sucessfully marked as done!");
    }
}