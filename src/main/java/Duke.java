import java.util.ArrayList;
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

//    // Level 2
//    public static void addList(){
//        System.out.print("Enter task: ");
//        String userInput = sc.nextLine();
//        ArrayList<String> taskList = new ArrayList<String>();
//
//        while (!userInput.toLowerCase().equals("bye")) {
//            if (!userInput.toLowerCase().equals("list")) { // userInput != list
//                if (taskList.contains(userInput)){ // handle duplicate task
//                    String taskExistMessage = String.format("%s\t \"%s\" already exists. \n%s", lineDivider, userInput, lineDivider);
//                    System.out.println(taskExistMessage);
//                }else{ // new task
//                    taskList.add(userInput);
//
//                    String addedMessage = String.format("%s\t added: %s\n%s", lineDivider, userInput, lineDivider);
//
//                    System.out.println(addedMessage + "\tEnter \"list\" to see full list. \n");
//                }
//            }else if(!taskList.isEmpty()) { // userInput == list
//                ListIterator<String> it = taskList.listIterator();
//                System.out.print(lineDivider);
//                while (it.hasNext()) {
//                    System.out.println("\t" + (it.nextIndex() + 1) + ". " + it.next());
//                }
//                System.out.println(lineDivider);
//            } else {
//                String emptyListMessage = String.format("%s\t List is empty!\n %s \n", lineDivider, lineDivider);
//                System.out.println(emptyListMessage);
//            }
//
//            System.out.print("Enter task: ");
//            userInput = sc.nextLine();
//        }
//
//    }
    // Level 3
    public static void taskApp(){

        System.out.print("Enter task: ");
        String userInput = sc.nextLine();
        ArrayList<Task> taskList = new ArrayList<>();

        while (!userInput.toLowerCase().equals("bye")) {
            if (userInput.toLowerCase().equals("list")) { // userInput == list
                if(!taskList.isEmpty()) { // userInput == list
                    System.out.print(lineDivider);

                    for(int i = 0; i < taskList.size(); i++ ){
                        System.out.println("\t" + (i+1) + ". " + taskList.get(i).getStatusIcon() + " " + taskList.get(i).getDescription() );
                    }
                    System.out.print(lineDivider);
                    System.out.println("\tEnter \"done _\" to see mark task as done. \n");
                } else {
                    String emptyListMessage = String.format("%s\t List is empty!\n %s \n", lineDivider, lineDivider);
                    System.out.print(emptyListMessage);
                }

            } else if (userInput.toLowerCase().contains("done")){ // userInput == done to mark task as done
                if (userInput.matches(".*\\d.*")) { // checks if there is a number in done cmd
                    int taskNumber = Integer.parseInt(userInput.replaceAll("\\D+", "")) ;
                    int indexOfTaskCompleted = taskNumber - 1;
                    if (indexOfTaskCompleted < taskList.size()){
                        taskList.get(indexOfTaskCompleted).setDone(true);
                        String markedTaskAsDoneMessage =
                                String.format("%s\t Nice! I've marked this task as done:\n %s %s \n%s \n",
                                        lineDivider,
                                        taskList.get(indexOfTaskCompleted).getStatusIcon(),
                                        taskList.get(indexOfTaskCompleted).getDescription(),
                                        lineDivider);
                        System.out.println(markedTaskAsDoneMessage);
                    } else {
                        String taskDoesNotExistMessage = String.format("%s\t Task does not exist!\n %s \n", lineDivider, lineDivider);
                        System.out.print(taskDoesNotExistMessage);
                    }
                } else {
                    String invalidInputMessage = String.format("%s\t Invalid input!\n %s \n", lineDivider, lineDivider);
                    System.out.print(invalidInputMessage);
                }
            } else { // regular adding of task
                taskList.add(new Task(userInput));

                String addedMessage = String.format("%s\t added: %s\n%s", lineDivider, userInput, lineDivider);

                System.out.println(addedMessage + "\tEnter \"list\" to see full list. \n");

            }
            System.out.print("Enter task: ");
            userInput = sc.nextLine();
        }

    }



    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


        // Greetings
        System.out.println(dukeGreeting);

        // Do echo function - Level 1
//        echos();

        // Do Add, List function - Level 2
//        addList();

        // Mark as done - Task Manager app - Level 3
        taskApp();

        // Farewell procedure
        System.out.println(dukeFarewell);
    }



}
