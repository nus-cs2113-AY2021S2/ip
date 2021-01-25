import java.util.Arrays;
import java.util.Scanner;
public class Duke {
    private static Scanner in = new Scanner(System.in);
    private static String[] orderedLists = new String[100];
    private static int taskNumber = 0;

    public static void addToList(String word){
        orderedLists[taskNumber] = (taskNumber+1)+"."+word;
        taskNumber++;
    }
    public static void printList(String [] orderedLists){
        String[] newLists = Arrays.copyOf(orderedLists,taskNumber);
        for(String newList: newLists) {
            System.out.println(newList);
        }
    }

    public static void main(String[] args) {
        System.out.println("*********************************************");
        System.out.println("Hello! I'm Julia");
        System.out.println("What can I do for you?");
        System.out.println();
        System.out.println("*********************************************");
        String input = "";
        while(true){
            input = in.nextLine();
            System.out.println("*********************************************");
            switch(input){
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println();
                System.out.println("*********************************************");
                System.exit(0);
            case "list":
                printList(orderedLists);
                System.out.println("*********************************************");
                break;
            default:
                addToList(input);
                System.out.println("added: "+input);
                System.out.println();
                System.out.println("*********************************************");
                break;
            }

        }

    }
}