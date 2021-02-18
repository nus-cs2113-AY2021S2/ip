import myexceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {



    static ArrayList<Task> tasks = new ArrayList<Task>();
    static int taskPosition = 0;
    static String description;
    static String by;
    static int indexOfBy;


    private static void writeToFile(String filePath, ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);

        for(Task t:tasks) {
            fw.write( t.getStatusIcon() + " " + t.getDescription() + "\n");

        }
        fw.close();
    }

    private static void restoreFileContents(String filePath) throws FileNotFoundException, BlankDescriptionException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            inputToTasks(s.nextLine());
        }
    }

    private static void inputToTasks(String nextLine) throws BlankDescriptionException {
        int taskStatusIndex = 1;
        int taskDescriptionIndex = 7;

        char taskType = nextLine.charAt(1);
        String taskStatus = nextLine.substring(4,5);
        String taskDescription = nextLine.substring(7);
        try {
            switch (taskType) {
            case 'T':
                description = taskDescription;
                Todo todoTask = new Todo(description);
                if (taskStatus.equalsIgnoreCase("1")) {
                    todoTask.isDone = true;
                }
                storeTask(todoTask);
                break;
            case 'E':
                int indexOfBy = taskDescription.indexOf("--");
                description = taskDescription.substring(0, indexOfBy - 1);
                by = taskDescription.substring(indexOfBy + 1, taskDescription.lastIndexOf("--"));
                Event eventTask = new Event(description, by);
                if (taskStatus.equalsIgnoreCase("1")) {
                    eventTask.isDone = true;
                }
                storeTask(eventTask);
                break;
            case 'D':
                indexOfBy = taskDescription.indexOf("--");
                description = taskDescription.substring(0, indexOfBy - 1);
                by = taskDescription.substring(indexOfBy + 2, taskDescription.lastIndexOf("--"));
                Deadline deadlineTask = new Deadline(description, by);
                if (taskStatus.equalsIgnoreCase("1")) {
                    deadlineTask.isDone = true;
                }
                storeTask(deadlineTask);
                break;
            }
        } catch (BlankDescriptionException e) {
        }




    }

    public static void storeTask(Task t) throws BlankDescriptionException{
        if (t.description.isBlank()) {
            throw new BlankDescriptionException();
        }
        else {
            tasks.add(t);
            System.out.println("Got it! I've added this task!");
            //System.out.println(t.description);
            System.out.println(t.getStatusIcon() + " " + t.getDescription());
            System.out.println("Now you have " + tasks.size() + " tasks!");
        }
    }

    public static void markAsDone(int taskIndex){
        tasks.get(taskIndex - 1).isDone = true;
        System.out.println("Nice! I've marked this task as done: ");
        listArray(tasks);
    }

    public static void listArray(ArrayList<Task> tasks){
        int textNumber = 1;
        for(Task t:tasks){
            System.out.println(textNumber + "." + t.getStatusIcon() + " " + t.getDescription());
            textNumber++;
        }

    }

    public static void main(String[] args) throws InvalidCommandException, InvalidDateException, FileNotFoundException, BlankDescriptionException {
        System.out.println("Hello! I'm 343 Guilty Spark! You may call me Spark!");
        System.out.println("What can I do for you?");
        String text = "hi";

        try{
            restoreFileContents("Duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        while(!text.equalsIgnoreCase("Bye")){
            Scanner in = new Scanner(System.in);
            text = in.nextLine();

            //Task t = new Task(text);
            try {
                String[] arr = text.split(" "); //split command input into words
                String command = arr[0];


                switch (command) {
                case "deadline":
                case "Deadline":
                case "DEADLINE":
                    try {
                        if(!text.contains("/") && !text.substring(9).isBlank()){
                            throw new MissingDateException();
                        }
                        int indexOfBy = text.indexOf('/');
                        description = text.substring(9, indexOfBy - 1);
                        by = text.substring(indexOfBy + 1);
                        Deadline deadlineTask = new Deadline(description, by);

                        storeTask(deadlineTask);
                    } catch (StringIndexOutOfBoundsException | BlankDescriptionException e) {
                        System.out.println("Description cannot be blank!");
                    } catch (MissingDateException e){
                        System.out.println("Missing date! Utilise '/' to key in your date!");
                    }
                    break;
                case "todo":
                case "Todo":
                case "TODO":

                    try {
                        if(text.substring(4).isBlank()){
                            throw new BlankDescriptionException();
                        }
                        description = text.substring(5);
                        Todo todoTask = new Todo(description);
                        storeTask(todoTask);
                        break;
                    } catch (BlankDescriptionException e) {
                        System.out.println("Todo cannot be empty");
                        break;
                    }


                case "event":
                case "Event":
                case "EVENT":

                    try {
                        if(!text.contains("/") && !text.substring(6).isBlank()){
                            throw new MissingDateException();
                        }
                        indexOfBy = text.indexOf('/');

                        String description = text.substring(6, indexOfBy - 1);
                        String by = text.substring(indexOfBy + 1);
                        Event eventTask = new Event(description, by);
                        storeTask(eventTask);
                    } catch (IndexOutOfBoundsException | BlankDescriptionException e) {
                        System.out.println("Event description cannot be empty! Try again!");
                    } catch (MissingDateException e){
                        System.out.println("Event must have a date! Try again!");
                    }
                    break;
                case "List":
                case "list":
                case "LIST":
                    listArray(tasks);
                    break;
                case "Done":
                case "done":
                case "DONE":
                    try {
                        Integer taskIndex = Integer.parseInt(arr[1]);
                        markAsDone(taskIndex);
                        break;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("What do you want to mark as done?");
                        break;
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("That's not in the list! Try again!");
                        break;
                    }
                case "Delete":
                case "delete":
                case "DELETE":
                    //try {
                        Integer taskIndex = Integer.parseInt(arr[1]);
                        markAsDeleted(taskIndex);
                        break;
                    //} catch (ArrayIndexOutOfBoundsException e) {
                      //  System.out.println("What do you want to mark as done?");
                        //break;
                    //}


                default:
                    throw new InvalidCommandException();
                }
            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("No input detected. What did you want to do?");
            } catch (InvalidCommandException e){
                System.out.println("Invalid command detected! What do you want to do?");
            } catch (IndexOutOfBoundsException e){
                System.out.println("There are only " + tasks.size() + " number of tasks in the list! Try again!");
            }
            String file2 = "Duke.txt";
            try {
                writeToFile(file2, tasks);
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        System.out.println("Bye! Hope to see you again soon!");
    }

    private static void markAsDeleted(Integer taskIndex) {
        if (taskIndex > tasks.size() || taskIndex < 0) {
            throw new IndexOutOfBoundsException();
        }
        Task t = tasks.get(taskIndex - 1);
        System.out.println("Noted! I have deleted this task for you: ");
        System.out.println(taskIndex + "." + t.getStatusIcon() + " " + t.getDescription());
        tasks.remove(taskIndex - 1);
        System.out.println("Now you have " + tasks.size() + " tasks in the list!");
    }
}
