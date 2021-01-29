import java.util.*;
public class Duke{
    Scanner sc = new Scanner(System.in);
    Task[] t = new Task[100];
    int taskNo = 0;

    public void run() {

        String choice;
        do {
            Ui.printMenu();
            choice = sc.nextLine();
            switch (choice) {

            case "1":
                enterTask();
                break;
            case "2":
               printDescription();
                break;
            case "3":
                markDone();
                break;
            }
        } while (!choice.equals("bye"));
    }
    private void enterTask() {
        t[taskNo] = new Task();
        System.out.println("Please enter you task!");
        t[taskNo].setDescription(sc.nextLine());
        t[taskNo].setTaskNo(taskNo);
        taskNo++;
    }

    private void printDescription(){
        System.out.println("Select this to print description");
        for (int i = 0; i < taskNo; i++) {
            System.out.println((t[i].getTaskNo() + 1) + "." + t[i].getStatusIcon() + t[i].getDescription());
        }
    }

    private void markDone(){
        System.out.println("Please enter then task number you want to mark as done ");
        String value = sc.nextLine().replaceAll("[^0-9]", "");
        int newValue = Integer.parseInt(value);
        t[newValue - 1].setDone(true);
        System.out.println("Task sucessfully marked as done!");
    }


    public static void main(String[] args) {
        Duke dk = new Duke();
        Ui ui = new Ui();
        dk.run();
    }
}