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

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static String extractTaskMessage(String userInput) throws DukeExceptionNoTaskMessage {
        if (userInput.indexOf(" ") > 0) {
            return userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            String errMessage = String.format("%s\tOOPS!!! The description of a task cannot be empty.\n%s",
                    lineDivider,
                    lineDivider);
            throw new DukeExceptionNoTaskMessage(errMessage);
        }
    }

    public static String extractDateBy(String content) throws DukeExceptionNoDateBy {
        if (content.contains("//")) {

            // using the limit parameter limits the number outputs the string splits
            String taskAndDate = content.split("//", 2)[1].strip();

            if (taskAndDate.contains("by") || taskAndDate.contains("at")) {
                return taskAndDate.substring(taskAndDate.indexOf(" ")).strip();
            } else {
                return taskAndDate;
            }
        } else {
            String errMessage = String.format("%s\t OOPS!!! For deadlines and events, " +
                            "there must be a dateby denoted using\n" +
                            "\t  the '//' symbol (e.g \"deadline task 1 // by Tuesday 2pm\"\n " +
                            "\t  or \"event task 1 // at 10/2/2021\" ) \n %s",
                    lineDivider,
                    lineDivider);
            throw new DukeExceptionNoDateBy(errMessage);

        }
    }

    public static void taskApp() throws DukeExceptionNoTaskMessage, DukeExceptionNoDateBy {

        while (true) {

            System.out.print("Enter command: ");
            String userInput = sc.nextLine();

            String commandWord = userInput.split(" ")[0].toLowerCase();
            String content = null;

            switch (commandWord) {
                case "todo":
                    try {
                        content = extractTaskMessage(userInput.strip());
                    } catch (DukeExceptionNoTaskMessage ex) {
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    taskList.add(new Todo(content));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this task: ");

                    break;
                case "deadline":
                    String datelineDateBy = null;
                    String deadlineTask = null;

                    try {
                        content = extractTaskMessage(userInput.strip());
                        datelineDateBy = extractDateBy(content);
                        deadlineTask = content.split("//")[0].toLowerCase();

                    } catch (DukeExceptionNoTaskMessage | DukeExceptionNoDateBy ex) {
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    taskList.add(new Deadline(deadlineTask.strip(), datelineDateBy.strip()));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this deadline: ");
                    break;

                case "event":
                    String eventTask = null;
                    String eventDateBy = null;

                    try {
                        content = extractTaskMessage(userInput.strip());
                        eventDateBy = extractDateBy(content);
                        eventTask = content.split("//")[0].toLowerCase();
                    } catch (DukeExceptionNoTaskMessage | DukeExceptionNoDateBy ex) {
                        System.out.println(ex.getMessage());
                        continue;
                    }
                    taskList.add(new Event(eventTask.strip(), eventDateBy.strip()));
                    addTaskSuccessMessage(taskList, "\tGot it. I've added this Event: ");
                    break;
                case "list":
                    String listReturnString = String.format("%s%s%s", lineDivider, getList(taskList), lineDivider);
                    System.out.println(listReturnString);
                    break;
                case "done":
                    markTaskDone(taskList, userInput);
                    break;
                case "delete":
                    deleteTask(taskList, userInput);
                    break;
                case "bye":
                    System.out.println(dukeFarewell);
                    System.exit(0);
                    break;
                case "save":
//                    save(taskList);
                    break;
                default:
                    String defaultMessage =
                            String.format("%s \t Command word not recognised - please start command with\n " +
                                            " \t 'todo', 'deadline' or 'event' \n%s",
                                    lineDivider,
                                    lineDivider);

                    System.out.println(defaultMessage);
            }
        }
    }

    public static void markTaskDone(ArrayList<Task> taskList, String userInput) {
        if (userInput.matches(".*\\d.*")) { // checks if there is a number in done cmd
            int taskNumber = Integer.parseInt(userInput.replaceAll("\\D+",""));
            int indexOfTaskToBeMarked = taskNumber - 1;
            if (indexOfTaskToBeMarked < taskList.size()) {
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
            // empty list ! 
            String doneErrorPrompt = "OOPS!!! Unable to mark task as done as your input is not in the right format!\n " +
                    "Which task do you want to mark done?";
            String doneListMessage = String.format("%s%s\n\t%s\n%s", lineDivider, doneErrorPrompt, getList(taskList), lineDivider);
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
        if (!taskList.isEmpty()) { // userInput == list
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
            sb.append("\tEnter \"delete _\" to see delete task. \n ");
            listAsString = sb.toString();
            return listAsString;
        } else {
            return "\tList is empty!\n";
        }
    }

    public static void deleteTask(ArrayList<Task> taskList, String userInput) {
        if (userInput.matches(".*\\d.*")) { // checks if there is a number in delete cmd
            int taskNumber = Integer.parseInt(userInput.replaceAll("\\D+", ""));
            int indexOfTaskToBeDeleted = taskNumber - 1;

            if (indexOfTaskToBeDeleted < taskList.size()) {
                String taskDeletedMessage =
                        String.format("%s\t Alright! I've removed task %s: \n \t\t%s%s \n\t " +
                                        "Now you have %d tasks remaining in the list! \n%s",
                                lineDivider,
                                indexOfTaskToBeDeleted,
                                taskList.get(indexOfTaskToBeDeleted).getStatusIcon(),
                                taskList.get(indexOfTaskToBeDeleted).getDescription(),
                                taskList.size() - 1,
                                lineDivider);
                System.out.println(taskDeletedMessage);
                taskList.remove(indexOfTaskToBeDeleted);
            } else {
                String taskDoesNotExistMessage = String.format("%s\t Task does not exist!\n %s \n", lineDivider, lineDivider);
                System.out.print(taskDoesNotExistMessage);
            }
        } else {
            // empty list !
            String doneErrorPrompt = "OOPS!!! Unable to delete task as your input is not in the right format!\n " +
                    "Which task do you want to mark done?";
            String printList = String.format("%s%s\n\t%s\n%s", lineDivider, doneErrorPrompt, getList(taskList), lineDivider);
            System.out.println(printList);
        }

    }

    public static void main(String[] args) throws DukeExceptionNoDateBy, DukeExceptionNoTaskMessage {

        // Greetings
        System.out.println(dukeGreeting);

        // Mark as done - Task Manager app - Level 3
        taskApp();

        // Farewell procedure
        System.out.println(dukeFarewell);
    }

}
