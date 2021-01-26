import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Duke {
    static final String lineDivider = "\t____________________________________________________________\n";
    static final String dukeGreeting = lineDivider + "\t Hello! I'm Duke\n\t What can I do for you?\n" + lineDivider;
    static final String dukeFarewell =  lineDivider + "\t Bye. Hope to see you again soon!\n" + lineDivider;

    static Scanner sc = new Scanner(System.in);

    public static void echos(){

        String userInput = sc.nextLine();

        while (!userInput.toLowerCase().equals("bye")){
//            String echos = lineDivider + "\t" + userInput.toLowerCase() + "\n" + lineDivider;
            String echos = String.format("%s\t %s\n%s",lineDivider,userInput.toLowerCase(),lineDivider);
            System.out.println(echos);
            userInput = sc.nextLine();
        }


    }

    public static void addList(){
        System.out.print("Enter task: ");
        String userInput = sc.nextLine();
        ArrayList<String> taskList = new ArrayList<String>();

        while (!userInput.toLowerCase().equals("bye")) {
            if (!userInput.toLowerCase().equals("list")) { // userInput != list
                if (taskList.contains(userInput)){ // handle duplicate task
                    String taskExistMessage = String.format("%s\t \"%s\" already exists. \n%s", lineDivider, userInput, lineDivider);
                    System.out.println(taskExistMessage);
                }else{ // new task
                    taskList.add(userInput);

                    String addedMessage = String.format("%s\t added: %s\n%s", lineDivider, userInput, lineDivider);

                    System.out.println(addedMessage + "\tEnter \"list\" to see full list. \n");
                }
            }else if(!taskList.isEmpty()) { // userInput == list
                ListIterator<String> it = taskList.listIterator();
                System.out.print(lineDivider);
                while (it.hasNext()) {
                    System.out.println("\t" + (it.nextIndex() + 1) + ". " + it.next());
                }
                System.out.println(lineDivider);
            } else {
                String emptyListMessage = String.format("%s\t List is empty!\n %s \n", lineDivider, lineDivider);
                System.out.println(emptyListMessage);
            }

            System.out.print("Enter task: ");
            userInput = sc.nextLine();
        }

    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


        // Greetings
        System.out.println(dukeGreeting);

        // Do echo function - Level 1
//        echos();

        // Do Add, List function - Level 2
        addList();

        // Farewell procedure
        System.out.println(dukeFarewell);
    }



}
