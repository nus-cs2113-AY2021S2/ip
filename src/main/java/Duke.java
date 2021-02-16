import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {


    public static String command_parser(todo list, String command){
        String[] tokens = command.split(" ");
        int itemStartIndex = command.indexOf(" ") + 1;
        int itemEndIndex;
        int dateStartIndex;
        String out = "";
        String desc = "";
        String date = "";
        switch(tokens[0]){
        case "list": // If command is list, print all tasks in list
            out = list.listItems();
            break;
        case "deadline": // If command is deadline, parse the task and by date, print confirmation
            itemEndIndex = command.indexOf("/by") - 1;
            dateStartIndex = itemEndIndex + 5;
            try {
                desc = command.substring(itemStartIndex, itemEndIndex);
                date = command.substring(dateStartIndex);
            }catch(StringIndexOutOfBoundsException e) { // if task or by date was not entered, catch error
                out = "Invalid deadline task format!"
                    + " Try the following: deadline [task] /by [date] ";
                break;
            }

            out = list.addTask(desc, listTypes.deadline, date);
            break;
        case "event": // If command is event, parse the task and at date, print confirmation
            itemEndIndex = command.indexOf("/at") - 1;
            dateStartIndex = itemEndIndex + 5;
            try { // if task or at date was not entered, catch error
                desc = command.substring(itemStartIndex, itemEndIndex);
                date = command.substring(dateStartIndex);
            }catch(StringIndexOutOfBoundsException e) {
                out = "Invalid command format!"
                    + " Try the following: event [task] /at [date] ";
                break;
            }
            out = list.addTask(desc, listTypes.event, date);
            break;
        case "todo": // If command is todo, parse the task, print confirmation

            desc = command.substring(itemStartIndex);
            if(desc.equals("todo")){ // if task was not entered, catch error
                out = "Invalid todo task!";
                break;
            }
            out = list.addTask(desc,listTypes.todo);
            break;
        case "done": // If command is done, mark task as done, print confirmation
            try {
                out = list.resolveTask(tokens[1]);
            }catch(ArrayIndexOutOfBoundsException e){
                out = "Invalid selection!";
                break;
            }
            break;
        case "delete": // If command is  delete, delete task entry in list, print confirmation
            try {
                out = list.deleteTask(tokens[1]);
            }catch(IndexOutOfBoundsException e){ // If task number is invalid, catch error
                out = "Invalid selection!";
                break;
            }catch(NumberFormatException e){ // If string is entered instead of task number, catch error
            out = "Please enter the number of the task!";
            }
            break;

        default: // If command entered is any other unrecognised command, print invalid command
            out = "Invalid task command!";
        }


        return out;

    }




    public static void dukeResponse(String output){
        System.out.println("____________________________________________________________\n" +
                output + "\n" +
                "____________________________________________________________\n");

    }
    public static void dukeStartup(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n" );

    }


    public static void dukeInitialisation(todov2 list) throws IOException {
        File importedList = new File("data/duke.txt");
        Scanner s = new Scanner(importedList);
        while (s.hasNext()) {
            String input = s.nextLine();
            String[] tokens = input.split("/");
            String command = "";
            switch(tokens[0]){
            case "D":
                command = "deadline " + tokens[2] + " /by " + tokens[3];
                break;
            case "E":
                command = "event " + tokens[2] + " /at " + tokens[3];
                break;
            case "T":
                command = "todo " + tokens[2];
                break;
            }
            command_parser(list, command);
            if (tokens[1].equals("X")){
                String  temp = command_parser(list, "done " + list.tasksTotal());
            }
        }
        System.out.println("Task list has been imported! Here are your tasks: \n" + command_parser(list, "list"));

    }


    public static void writeToFile(String filePath, String addText) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(addText);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

/*    public static void addToList(String type, String resolved, String description, String date){
        String file = "data/duke.txt";
        try {
            appendToFile(file, type + "|" + resolved + "|" + description + "|" + date + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }*/

    public static void updateList(todov2 list) throws IOException{
        String file = "data/duke.txt";
        writeToFile(file,"");
        String out = list.tasksUpdate();
        try {
            appendToFile(file, out);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }








    public static void main(String[] args) throws IOException {

        Boolean continueChat = true;
        Scanner in = new Scanner(System.in);
        String output = "Nothing.";
        todo todoList = new todo();


        dukeStartup();
        dukeInitialisation(todoList);

        while(continueChat){
            String input = in.nextLine();
            if(input.equals("bye")){
                output = "Bye. Hope to see you again soon!";
                continueChat = false;
            }else {
                output = command_parser(todoList,input);
                updateList(todoList);
            }
            dukeResponse(output);

        }



    }
}
