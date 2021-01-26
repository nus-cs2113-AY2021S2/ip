import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! Im Duke\n" + logo + "What can I do for you?");
        Duke dk = new Duke();
        dk.run();

    }

    public void run() {
        Task[] t = new Task[100];
        int taskNo = 0;
        System.out.println("Please enter you choice!");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while(choice != 4){
            switch (choice) {
            case 1:
                t[taskNo] = new Task();
                System.out.println("Please enter you task!");
                sc.nextLine();
                t[taskNo].setDescription(sc.nextLine());
                taskNo++;
                break;
            case 2:
                System.out.println("Select this to print description");
                for(int i = 0; i<taskNo;i++){
                    System.out.println(t[i].getStatusIcon()+t[i].getDescription());
                }
                break;

            case 3:
                System.out.println("Please enter the task name you want to mark as done");
                sc.nextLine();
                for(int i = 0; i < taskNo; i++){
                    if(t[i].getDescription().equals(sc.nextLine())){
                        t[i].setDone(true);
                        break;
                    }
                }
                System.out.println("Mark task as done");
                break;
            }
            System.out.println("Please enter you choice!");
            choice = sc.nextInt();
        }
    }
}
