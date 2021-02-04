import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static final String lineDivider = "\t__________________________________________________________________________\n";

    static final String dukeKeywords = "\t\t Use 'todo', 'deadline' , 'event' to enter tasks!\n" +
                                       "\t\t Use 'list' to view tasks!\n";
    public static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    static final String dukeGreeting =
            String.format("%s%s\t Hello! I'm Duke - your personal task manager \n%s%s",
                    LOGO,
                    lineDivider,
                    dukeKeywords,
                    lineDivider);
    static final String dukeFarewell = String.format("%s\t Bye. Hope to see you again soon!\n%s",
            lineDivider,
            lineDivider);

    static Scanner sc = new Scanner(System.in);

    public static void echos(){
        String userInput = sc.nextLine();
        while (!userInput.toLowerCase().equals("bye")){
            String echos = String.format("%s\t %s\n%s",lineDivider,userInput.toLowerCase(),lineDivider);
            System.out.println(echos);
            userInput = sc.nextLine();
        }
    }


    public static void taskApp(){
        ArrayList<Task> taskList = new ArrayList<>();
        while (true) {

            System.out.print("Enter command: ");
            String userInput = sc.nextLine();

            String commandWord = userInput.split(" ")[0].toLowerCase();
            String content = userInput.substring(userInput.indexOf(" ") + 1);

            switch (commandWord) {
                case "todo":
                    taskList.add(new Todo(content));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this task: ");
                    break;
                case "deadline":
                    String deadlineTask = content.split("/")[0].toLowerCase();
                    String datelineDateBy = content.split("/")[1];
                    datelineDateBy = datelineDateBy.substring(datelineDateBy.indexOf(" ")+1);
                    taskList.add(new Deadline(deadlineTask,datelineDateBy ));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this deadline: ");
                    break;
                case "event":
                    String eventTask = content.split("/")[0].toLowerCase();
                    String eventDateBy = content.split("/")[1];
                    eventDateBy = eventDateBy.substring(eventDateBy.indexOf(" "));
                    taskList.add(new Event(eventTask,eventDateBy));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this Event: ");
                    break;
                case "list":
                    String listReturnString = String.format("%s%s%s",lineDivider,getList(taskList),lineDivider);
                    System.out.println(listReturnString);
                    break;
                case "done":
                    markTaskDone(taskList, userInput, content);
                    break;
                case "bye":
                    System.out.println(dukeFarewell);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Command word not recognised - please start command with " +
                            "'todo', 'deadline' or 'event'");
            }
        }
    }

    public static void markTaskDone(ArrayList<Task> taskList, String userInput, String content){
        if (userInput.matches(".*\\d.*")) { // checks if there is a number in done cmd
            int taskNumber = Integer.parseInt(content) ;
            int indexOfTaskToBeMarked = taskNumber - 1;
            if (indexOfTaskToBeMarked < taskList.size()){
                taskList.get(indexOfTaskToBeMarked).setDone(true);
                String markedTaskAsDoneMessage =
                        String.format("%s\t Nice! I've marked this task as done:\n \t %s %s \n%s",
                                lineDivider,
                                taskList.get(indexOfTaskToBeMarked).getStatusIcon(),
                                taskList.get(indexOfTaskToBeMarked).getDescription(),
                                lineDivider);
                System.out.println(markedTaskAsDoneMessage);
                } else {
                String taskDoesNotExistMessage = String.format("%s\t Task does not exist!\n %s \n", lineDivider, lineDivider);
                System.out.print(taskDoesNotExistMessage);
            }
        } else {
            String doneErrorPrompt = "Which task do you want to mark done?";
            String doneListMessage = String.format("%s\t%s\n\t%s\n%s",lineDivider,doneErrorPrompt,getList(taskList),lineDivider);
            System.out.println(doneListMessage);
        }
    }

    public static void addTaskSuccessMessage(ArrayList<Task> taskList, String s) {
        System.out.println(lineDivider);
        System.out.println(s);
        System.out.println("\t " + taskList.get(taskList.size() - 1).getStatusIcon()
                + " " + taskList.get(taskList.size() - 1).getDescription());
        System.out.println(lineDivider);
    }

    public static String getList(ArrayList<Task> taskList) {
        if(!taskList.isEmpty()) { // userInput == list
            StringBuilder sb = new StringBuilder();
            String listAsString;
            for (int i = 0; i < taskList.size(); i++) {
                sb.append("\t");
                sb.append((i + 1));
                sb.append(". ");
                sb.append(taskList.get(i).getStatusIcon());
                sb.append(" ");
                sb.append(taskList.get(i).getDescription());
                sb.append("\n");
                sb.toString();
            }
            sb.append("\n");
            sb.append("\tNow you have ");
            sb.append(taskList.size());
            sb.append(" tasks in the list. \n");
            sb.append("\tEnter \"done _\" to see mark task as done. \n");
            listAsString = sb.toString();
            return listAsString;
        }
        else {
            return "List is empty!";
        }
    }

    public static void main(String[] args) {

        // Greetings
        System.out.println(dukeGreeting);

        // Mark as done - Task Manager app - Level 3
        taskApp();

        // Farewell procedure
        System.out.println(dukeFarewell);
    }

}
