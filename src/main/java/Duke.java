import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Duke.*;

public class Duke {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Task> tasksList = new ArrayList<>();
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String FILE_PATH_TO_SAVE_TASKS = "duke.txt";

    public static void main(String[] args) {
        initializeData();
        sendWelcomeMessage();
        printLine();
        while(true) {
            String[] listOfUserInputs = getUserInput();
            String userCommand = listOfUserInputs[0];
            String inputDetails = listOfUserInputs[1];
            if (userCommand.equalsIgnoreCase("bye")){
                try {
                    exitDuke();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
            if (userCommand.equalsIgnoreCase("list")){
                listOutTasks();
            } else if (userCommand.equalsIgnoreCase("done" )) {
                markTaskAsDone(inputDetails);
            } else if (userCommand.equalsIgnoreCase("delete" )) {
                deleteTask(inputDetails);
            } else  {
                if (!isValidCommand(userCommand)) {
                    continue;
                }
                //process Todo, event or deadline
                processCommandWithException(userCommand, inputDetails);
            }
            printLine();
        }
    }

    private static void processCommandWithException(String userCommand, String inputDetails) {
        try {
            processUserRequest(userCommand, inputDetails);
        } catch (TodoException e) {
            printLine();
            System.out.println(e.sendErrorMessage());
        }
    }

    private static boolean isValidCommand(String userCommand) {
        boolean isValid = true;
        try {
            isValidInput(userCommand);
        } catch (InvalidCommandException e) {
            printLine();
            System.out.println(e.sendErrorMessage());
            printLine();
            isValid = false;
        }
        return isValid;
    }

    private static void isValidInput(String userCommand) throws InvalidCommandException {
        var isValid = userCommand.equalsIgnoreCase("todo") | userCommand.equalsIgnoreCase("deadline") | userCommand.equalsIgnoreCase("event");
        if (!isValid) {
            throw new InvalidCommandException();
        }
    }

    private static void sendWelcomeMessage() {
        System.out.println("Hello from\n" + LOGO);
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private static void createFileIfThereIsNone() {
        File fForCheck = new File(FILE_PATH_TO_SAVE_TASKS);
        if (!fForCheck.exists()) {
            createNewFile(fForCheck);
        }
    }


    private static void createNewFile(File fForCheck) {
        try {
            fForCheck.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static FileWriter createFileWriterObject(String fileName, FileWriter fw) {
        try {
            fw = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;
    }

    private static void writeEmptyStringToFile(FileWriter fw) {
        try {
            fw.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processUserRequest(String userCommand, String inputDetails) throws TodoException {
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("todo")) {
            throw new TodoException();
        }
        printLine();
        printLine();
        Task newTask = new Task(inputDetails, userCommand);
        tasksList.add(newTask);
        notifyUser(newTask);
    }

    private static void processSavedData(String userCommand, String inputDetails) {
        Task newTask = new Task(inputDetails, userCommand);
        tasksList.add(newTask);
    }

    private static void notifyUser(Task selectedTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void markTaskAsDone(String s) {
        int taskNumber = Integer.parseInt(s);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        selectedTask.markAsDone();
        System.out.println("Nice! Following task is now marked as done:");
        System.out.println("[X] " + selectedTask.getDescription());
    }

    private static void exitDuke() throws IOException {
        printLine();
        String fileName = FILE_PATH_TO_SAVE_TASKS;
        for (Task task : tasksList) {
            try {
                writeToFile(fileName, task);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }


    private static void printLine() {
        System.out.println("---------------------------------------------------");
    }


    private static String[] getUserInput() {
        String userInput = scanner.nextLine();
        return splitInputIntoString(userInput);
    }

    private static String[] splitInputIntoString(String userInput) {
        String[] listOfInputs = userInput.split(" ", 2);
        if (listOfInputs.length == 1) {
            listOfInputs = new String[]{userInput, "filler"};
        }
        return listOfInputs;
    }


    private static void listOutTasks() {
        printLine();
        int i = 0;
        while (i < tasksList.size()) {
            Task selectedTask = tasksList.get(i);
            i++;
            System.out.println(i + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }
    }

    private static void deleteTask(String s) {
        int taskNumber = Integer.parseInt(s);
        taskNumber--;
        Task selectedTask = tasksList.get(taskNumber);
        System.out.println("Noted. I've removed this task");
        System.out.println("\t" + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        tasksList.remove(taskNumber);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }

    private static void writeToFile(String filePath, Task tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        String description = tasks.getDescriptionWithoutBrackets();
        String taskType = tasks.getTaskTypeInWords();
        Boolean status = tasks.getStatusInWords();
        if (status) {
            fw.write(taskType + "done " + description + "\n");
        } else {
            fw.write(taskType + ' ' + description + "\n");
        }
        fw.close();
    }

    private static void initializeData() {
        createFileIfThereIsNone();
        File f = new File(FILE_PATH_TO_SAVE_TASKS);
        System.out.println("Initializing data");
        try {
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String[] listOfDataFromFile = splitInputIntoString(s.nextLine());
                String userCommand = listOfDataFromFile[0];
                String inputDetails = listOfDataFromFile[1];
                processSavedData(userCommand, inputDetails);
                System.out.println(userCommand);
                System.out.println(inputDetails);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


}
