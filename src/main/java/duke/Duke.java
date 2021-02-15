package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {

    public static final Path dataDirectory = Path.of(System.getProperty("user.dir") + "\\data");

    public static final Path dataFileDirectory = Path.of(System.getProperty("user.dir") + "\\data" + "\\duke.txt");

    public static final String LINE = "____________________________________________________________";

    public static void writeToDisk(ArrayList<Task> tasks) throws IOException{
        //Each time I want to write to the Disk, I clear away the entire contents of the data file and write everything
        //Done by deleting the file and creating it again
        File clear = new File(String.valueOf(dataFileDirectory));
        clear.delete();
        createNewFile();

        //To write to the new file, I will append 1 task from the ArrayList at a time
        FileWriter fw = new FileWriter(String.valueOf(dataFileDirectory), true);
        for(Task task : tasks){
            String temp = "";

            if(task.getDoneStatus()){
                temp += "1|";
            } else {
                temp += "0|";
            }
            temp += task.getType() + "|" + task.getDescription() + "|" + task.getSeparator() + "|";
            if(task instanceof Event) {
                temp += ((Event) task).getAt();
            } else if(task instanceof Deadline){
                temp += ((Deadline) task).getBy();
            } else {
                temp += "";
            }
            temp += "\n";
            fw.write(temp);
        }
        fw.close();
    }

    public static void wrapWriteToDisk(ArrayList<Task> tasks){
        try{
            writeToDisk(tasks);
        } catch (IOException e) {
            System.out.println("Issues with writing to .txt file");
        }
    }

    public static void loadDataFromDisk(ArrayList<Task> tasks) throws FileNotFoundException {
        File dataFile = new File(String.valueOf(dataFileDirectory));
        Scanner s = new Scanner(dataFile);

        while(s.hasNext()){
            //format I am using is, to use index 0 to store the status.
            //E.g:
            //1|event|memes   r|/at|   next Friday
            String temp = s.nextLine();
            String[] command = temp.split("\\|");
            Task tempTask = populateArrayList(Arrays.copyOfRange(command,1, command.length), tasks);
            if (command[0].equals("1")){
                tempTask.setAsDone();
            }
        }
        s.close();
    }

    public static void createNewFile() {
        try{
            // If directory does not exist, then create the directory
            // If directory exists already, just does nothing
            checkForDirectory();
        } catch (IOException e) {
            System.out.println("A serious error has occurred");
        }

        try{
            // If file does not exist, then create the file
            checkForFile();
        } catch (IOException e) {
            System.out.println("A serious error has occurred");
        }
    }

    public static void checkForFile() throws IOException {
        if(!Files.exists(dataFileDirectory)){
            File newFile = new File(String.valueOf(dataFileDirectory));
            newFile.createNewFile();
        }
    }

    public static void checkForDirectory() throws IOException {
        if(!Files.exists(dataDirectory)){
            Files.createDirectory(dataDirectory);
        }
    }

    public static void runProgram() {
        Scanner in = new Scanner(System.in);
        String userInput;

        ArrayList<Task> tasks = new ArrayList<>();

        // Check if there is such a file. Otherwise, create a new file.
        try{
            loadDataFromDisk(tasks);
        } catch (FileNotFoundException e){
            createNewFile();
        }

        do {
            userInput = in.nextLine();

            String[] command = userInput.split(" ");

            if(isSpecialCharacterPresent(userInput)){
                continue;
            }

            Task newItem = populateArrayList(command, tasks);
            if(newItem != null){
                newItemMessage(tasks, newItem);
                wrapWriteToDisk(tasks);
            }
        } while (!userInput.equals("bye"));
    }

    public static Task populateArrayList(String[] command, ArrayList<Task> tasks){
        Task newItem = null;
        switch(command[0]){
        case "list":
            try{
                listCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("The list command can only contain 1 word.");
            }
            catch (Exception e){
                badUserInputMessage();
            }
            break;
        case "done":
            try{
                doneCommand(command, tasks);
                wrapWriteToDisk(tasks);
            }
            catch (DukeException e){
                System.out.println("The done command consists of the word done, and an integer.");
            }
            catch (Exception e){
                badUserInputMessage();
            }
            break;
        case "todo":
            try{
                newItem = todoCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("The description of a todo cannot be empty!");
            }
            catch (Exception e){
                badUserInputMessage();
            }
            break;
        case "event":
            try{
                newItem = eventCommand(command, tasks);
            }
            catch (DukeException e){
                System.out.println("Your input should contain /at separated by spaces, " +
                        "followed by the event time.");
            }
            catch(Exception e){
                badUserInputMessage();
            }
            break;
        case "deadline":
            try{
                newItem = deadlineCommand(command, tasks);
            }
            catch(DukeException e){
                System.out.println("Your input should contain /by separated by spaces, " +
                        "followed by the deadline.");
            }
            catch (Exception e){
                badUserInputMessage();
            }
            break;
        case "bye":
            if(command.length>1){
                System.out.println("I have no such feature!");
            }
            break;
        case "delete":
            try{
                deleteCommand(command, tasks);
                wrapWriteToDisk(tasks);
            } catch (DukeException e) {
                System.out.println("The delete command consists of the word delete, and an integer.");
            } catch (Exception e) {
                badUserInputMessage();
            }
            break;
        default:
            System.out.println("I have no such feature!");
            break;
        }
        return newItem;
    }

    public static void listCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 1){
            int index = 1;
            System.out.println(LINE);
            for (Task task : tasks) {
                System.out.println(index + "." + task.toString());
                index += 1;
            }
            System.out.println(LINE);
        } else {
            throw new DukeException();
        }
    }

    public static void doneCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 2 && checkIfInteger(command[1])){
            int index = Integer.parseInt(command[1]) - 1;
            if (0 <= index && index < tasks.size()) {
                // If the given value to set as done is an existing index
                tasks.get(index).setAsDone();
                markTaskDoneMessage(tasks, index);
            } else {
                System.out.println("The input index that you have selected to indicate as done, "+
                        "is out of the range of existing indexes!");
            }
        } else {
            throw new DukeException();
        }
    }

    public static void deleteCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length == 2 && checkIfInteger(command[1])){
            int index = Integer.parseInt(command[1]) - 1;
            if (0 <= index && index < tasks.size()) {
                // If the given value to delete is an existing index, allow deletion
                deleteTaskMessage(tasks, index);
                tasks.remove(index);
            } else {
                System.out.println("The input index that you have selected to delete, "+
                        "is out of the range of existing indexes!");
            }
        } else {
            throw new DukeException();
        }
    }

    public static Task todoCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(command.length > 1) {
            Task newItem = new Todo(String.join(" ", Arrays.copyOfRange(command, 1, command.length)));
            tasks.add(newItem);
            return newItem;
        } else {
            throw new DukeException();
        }
    }

    public static Task eventCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(checkForSubstring(command, "/at")){
            int separatorIndex = indexOfSubstring(command, "/at");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String at = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Event(description, at);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public static Task deadlineCommand(String[] command, ArrayList<Task> tasks) throws DukeException{
        if(checkForSubstring(command, "/by")){
            int separatorIndex = indexOfSubstring(command, "/by");
            String description = String.join(" ", Arrays.copyOfRange(command,
                    1, separatorIndex));
            String by = String.join(" ",Arrays.copyOfRange(command,
                    separatorIndex + 1, command.length));

            Task newItem = new Deadline(description, by);
            tasks.add(newItem);
            return newItem;
        } else{
            throw new DukeException();
        }
    }

    public static boolean checkForSubstring(String[] input, String substring){
        for(String string : input){
            if(string.equals(substring)){
                return true;
            }
        }
        return false;
    }

    public static int indexOfSubstring(String[] input, String substring){
        int index = 0;
        for(String string : input){
            if(string.equals(substring)){
                return index;
            }
            index++;
        }
        return -1;
    }

    public static boolean checkIfInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isSpecialCharacterPresent(String s){
        if(s.contains("|")){
            System.out.println("Your input cannot contain the special character '|'");
            return true;
        }
        return false;
    }

    public static void welcomeMessage(){
        String logo = " _                          \n"
                + "| |                         \n"
                + "| |     _   _   ___   _ __  \n"
                + "| |    | | | | / _ \\ | '_ \\ \n"
                + "| |____| |_| || (_) || | | |\n"
                + "\\_____/ \\__, | \\___/ |_| |_|\n"
                + "         __/ |              \n"
                + "        |___/              \n";
        System.out.println("Hello from\n" + logo);

        System.out.println();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Lyon");
        System.out.println("What can I do for you?");
    }

    public static void badUserInputMessage(){
        System.out.println(LINE);
        System.out.println("I'm sorry, your input does not comply with the available features I have.");
        System.out.println("Kindly try again!");
    }

    public static void markTaskDoneMessage(ArrayList<Task> tasks, int index){
        System.out.println(LINE);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    public static void deleteTaskMessage(ArrayList<Task> tasks, int index){
        System.out.println(LINE);
        System.out.println("Noted! I have removed this task:");
        System.out.println(tasks.get(index).toString());
        System.out.println(LINE);
    }

    public static void newItemMessage(ArrayList<Task> tasks, Task newItem){
        System.out.println(LINE);
        System.out.println("Got it. I've added this task:");
        System.out.println(newItem.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(LINE);
    }

    public static void goodbyeMessage(){
        System.out.println(LINE);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);
    }

    public static void main(String[] args) {
        welcomeMessage();
        runProgram();
        goodbyeMessage();
    }
}
