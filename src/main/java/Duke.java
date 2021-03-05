//import java.io.*;
//import java.util.ArrayList;
//import java.util.Scanner;
import Duke.*;

public class Duke {
    private static TaskManager taskManager = new TaskManager();
//    public static final String FILE_PATH_TO_SAVE_TASKS = "duke.txt";

    public static void main(String[] args) {
        DukeStorage.createFileIfThereIsNone();
        //initializeData();
        taskManager = DukeStorage.loadData();
        DukeUI.printWelcomeMessage();
        DukeUI.printLine();
        while(true) {
            String[] listOfUserInputs = DukeUI.getUserInput();
            String userCommand = listOfUserInputs[0];
            String inputDetails = listOfUserInputs[1];
            if (userCommand.equalsIgnoreCase("bye")){
                DukeStorage.endDuke(taskManager);
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
            DukeUI.printLine();
        }
    }

    private static void processCommandWithException(String userCommand, String inputDetails) {
        try {
            processUserRequest(userCommand, inputDetails);
        } catch (TodoException e) {
            DukeUI.printLine();
            DukeUI.print(e.sendErrorMessage());
        }
    }

    private static boolean isValidCommand(String userCommand) {
        boolean isValid = true;
        try {
            isValidInput(userCommand);
        } catch (InvalidCommandException e) {
            DukeUI.printLine();
            DukeUI.print(e.sendErrorMessage());
            DukeUI.printLine();
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

//    private static void createFileIfThereIsNone() {
//        File fForCheck = new File(FILE_PATH_TO_SAVE_TASKS);
//        if (!fForCheck.exists()) {
//            createNewFile(fForCheck);
//        }
//    }

//    private static void emptyFileAfterInitializing() {
//        FileWriter fw = null;
//        fw = createFileWriterObject(null);
//        writeEmptyStringToFile(fw);
//    }


//    private static void createNewFile(File fForCheck) {
//        try {
//            fForCheck.createNewFile();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

//    private static FileWriter createFileWriterObject(FileWriter fw) {
//        try {
//            fw = new FileWriter(FILE_PATH_TO_SAVE_TASKS);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return fw;
//    }

//    private static void writeEmptyStringToFile(FileWriter fw) {
//        try {
//            fw.write("");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private static void processUserRequest(String userCommand, String inputDetails) throws TodoException {
        if (inputDetails.equalsIgnoreCase("filler") & userCommand.equalsIgnoreCase("todo")) {
            throw new TodoException();
        }
        DukeUI.printLine();
        DukeUI.printLine();
        Task newTask = new Task(inputDetails, userCommand);
        taskManager.addTask(newTask);
        notifyUser(newTask);
    }

//    private static void processSavedData(String userCommand, String inputDetails) {
//        Task newTask = new Task(inputDetails, userCommand);
//        taskManager.addTask(newTask);
//    }

    private static void notifyUser(Task selectedTask) {
        DukeUI.print("Got it. I've added this task:");
        DukeUI.print("  " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        DukeUI.print("Now you have " + taskManager.taskCount() + " tasks in the list.");
    }

    private static void markTaskAsDone(String s) {
        Task selectedTask = taskManager.getTask(s);
        selectedTask.markAsDone();
        DukeUI.print("Nice! Following task is now marked as done:");
        DukeUI.print("[X] " + selectedTask.getDescription());
    }

//    private static void exitDuke() throws IOException {
//        DukeUI.printLine();
//        ArrayList<Task> finalTasksList = taskManager.returnTaskList();
//        for (Task task : finalTasksList) {
//            try {
//                writeToFile(FILE_PATH_TO_SAVE_TASKS, task);
//            } catch (IOException e) {
//                DukeUI.print("Something went wrong: " + e.getMessage());
//            }
//        }
//        DukeUI.printExitingMessage();
//    }

    private static void listOutTasks() {
        DukeUI.printLine();
        int i = 0;
        while (i < taskManager.taskCount()) {
            i++;
            Task selectedTask = taskManager.getTaskWithInt(i);
            DukeUI.print(i + ". " + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        }
    }


    private static void deleteTask(String s) {
        Task selectedTask = taskManager.getTask(s);
        DukeUI.print("Noted. I've removed this task");
        DukeUI.print("\t" + selectedTask.getTaskType() + selectedTask.getStatusIcon() + " " + selectedTask.getDescription());
        taskManager.removeTask(s);
        DukeUI.print("Now you have " + taskManager.taskCount() + " tasks in the list.");
    }
//
//    private static void writeToFile(String filePath, Task tasks) throws IOException {
//        FileWriter fw = new FileWriter(filePath, true);
//        String description = tasks.getDescriptionWithoutBrackets();
//        String taskType = tasks.getTaskTypeInWords();
//        Boolean status = tasks.getStatusInWords();
//        if (status) {
//            fw.write(taskType + "done " + description + "\n");
//        } else {
//            fw.write(taskType + ' ' + description + "\n");
//        }
//        fw.close();
//    }

//    private static void initializeData() {
//        File f = new File(FILE_PATH_TO_SAVE_TASKS);
//        DukeUI.print("Initializing data");
//        try {
//            Scanner s = new Scanner(f);
//            while(s.hasNext()) {
//                String[] listOfDataFromFile = DukeParser.splitInputIntoString(s.nextLine());
//                String userCommand = listOfDataFromFile[0];
//                String inputDetails = listOfDataFromFile[1];
//                processSavedData(userCommand, inputDetails);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        emptyFileAfterInitializing();
//    }


}
