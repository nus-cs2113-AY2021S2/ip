import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.printf("Hello! I'm Duke\n" +
                "What can I do for you?\n\n");

        System.out.printf("--------User Menu--------\n" +
                "list: list current tasks and completion status\n" +
                "done: Mark a task as completed\n" +
                "bye: Exit\n" +
                "any other words: The word will be recorded as a task\n");

        String[] items = new String[100];
        char[] itemsStatus = new char[100];
        int itemIndex = 0;
        int taskIndex = 0;
        String line;
        Scanner sc = new Scanner(System.in);
        line = sc.nextLine();
        while(!line.equals("bye")){
            switch(line){
            case "list":
                for(int i=0; i<itemIndex; i++){
                    System.out.println(i+1 + ".["
                            + itemsStatus[i] + "] "
                            + items[i]);
                }
                line = sc.nextLine();
                break;
            case "done":
                for(int i=0; i<itemIndex; i++){
                    System.out.println(i+1 + ".["
                            + itemsStatus[i] + "] "
                            + items[i]);
                }
                System.out.println("Enter the task number to mark as complete");
                taskIndex = Integer.parseInt(sc.nextLine());
                itemsStatus[taskIndex-1] = 'X';
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("["
                        + itemsStatus[taskIndex-1] + "] "
                        + items[taskIndex-1]);
                line = sc.nextLine();
                break;
            default:
                items[itemIndex] = line;
                itemsStatus[itemIndex] = ' ';
                System.out.println("added: " + items[itemIndex]);
                itemIndex++;
                line = sc.nextLine();
            }
        }
        System.out.printf("Bye. Hope to see you again soon!\n");
    }
}
