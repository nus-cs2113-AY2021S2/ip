import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import Class.*;
import ErrorHandling.*;

public class Duke {
    public static String lineDivider = "    --------------------------------------------------------------------------";

    public static void addMessage(Task task, int size){
        System.out.println("     Got it. I've added this task:");
        System.out.println("     " + task.getDescription());
        System.out.println("     Now you have " + size + " tasks in this list.");
    }

    public static void takeCommand(String command,List<Task> tasks) throws UnknownCommand, EmptyDescription, OutOfBound {
        String[] subStrings = command.split(" ");
        String description = "";
        int slashIndex = command.indexOf('/');

        switch(subStrings[0]){
        case "list":
            printTasks(tasks);
            break;
        case "bye":
            System.out.println("     Bye. Hope to see you again soon!");
            break;
        case "done":
            if(subStrings.length > 1){
                int taskNo = Integer.parseInt(subStrings[1]);
                if(taskNo > tasks.size()){
                    throw new OutOfBound();
                } else {
                    Task taskDone = tasks.get(taskNo-1);
                    taskDone.markAsDone();
                    System.out.println("     Nice! I've marked this task as done:");
                    System.out.println("      " + taskDone.getDescription());
                    tasks.set(taskNo-1, taskDone);
                }
            } else {
                throw new EmptyDescription("done");
            }
            break;
        case "todo":
            if(subStrings.length > 1){
                description = command.substring(5);
                Todo todo = new Todo(description);
                tasks.add(todo);
                addMessage(todo,tasks.size());
            } else {
                throw new EmptyDescription("todo");
            }
            break;
        case "deadline":
            if(slashIndex != -1) {
                description = command.substring(9, slashIndex-1);
                String by = command.substring(slashIndex+4);
                Deadline deadline = new Deadline(description, by);
                tasks.add(deadline);
                addMessage(deadline,tasks.size());
            } else {
                throw new EmptyDescription("deadline");
            }
            break;
        case "event":
            if(slashIndex != -1) {
                description = command.substring(6, slashIndex-1);
                String at = command.substring(slashIndex+4);
                Event event = new Event(description, at);
                tasks.add(event);
                addMessage(event,tasks.size());
            } else {
                throw new EmptyDescription("event");
            }
            break;
        default:
            throw new UnknownCommand();
        }
    }

    public static void printTasks(List<Task> tasks){
        int i = 1;
        System.out.println("     Here are the tasks in your list:");
        for(Task task:tasks){
            System.out.println("     " + i + "." + task.getDescription());
            ++i;
        }
    }

    public static void readFile(List<Task> tasks) throws IOException, UnknownCommand, EmptyDescription {
        try {
            File saveFolder = new File("data");
            if(!saveFolder.exists()){
                saveFolder.mkdir();
            }
            File saveFile = new File("data/tasks.txt");
            if(!saveFile.exists()){
                saveFile.createNewFile();
            }

            Scanner s = new Scanner(saveFile);
            int isDone;

            while(s.hasNext()) {
                String input = s.nextLine();
                String description = input.substring(2, input.indexOf('|')-1);
                String time = input.substring(input.indexOf('|') + 2);
                isDone = Character.getNumericValue(input.charAt(0));
                String[] subStrings = input.split(" ");
                switch (subStrings[1]){
                case "todo":
                    Todo todo = new Todo(description);
                    if(isDone == 1){
                        todo.markAsDone();
                    }
                    tasks.add(todo);
                    break;
                case "event":
                    Event event = new Event(description, time);
                    if(isDone == 1){
                        event.markAsDone();
                    }
                    tasks.add(event);
                    break;
                case "deadline":
                    Deadline deadline = new Deadline(description, time);
                    if(isDone == 1){
                        deadline.markAsDone();
                    }
                    tasks.add(deadline);
                    break;
                default:
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred when accessing the save file.");
            e.printStackTrace();
        }
    }

    public static void writeFile(List<Task> tasks) throws IOException {
        try {
            FileWriter fw = new FileWriter("data/tasks.txt");
            for(Task task:tasks){
                fw.write(task.isDone() + " " + task.getType() + " " + task.getTaskName() + " | " + task.getTime()
                        + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred when writing to the save file.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, UnknownCommand, EmptyDescription, OutOfBound {
        Scanner in = new Scanner(System.in).useDelimiter(" ");
        String command = "";
        List<Task> tasks = new ArrayList<Task>();

        readFile(tasks);

        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        do{
            command = in.nextLine();
            System.out.println(lineDivider);
            try{
                takeCommand(command,tasks);
            } catch (UnknownCommand e) {
                System.out.println("     Oops!!! I'm sorry, but I have no idea what that means =(");
            } catch (EmptyDescription e) {
                System.out.println("     Oops!!! The description of " + e.TaskName() + " cannot be empty.");
            } catch (OutOfBound e) {
                System.out.println("     Oops!!! I think you are trying to access things that aren't even there yet!");
            }
            System.out.println(lineDivider);
        }while(!command.equals("bye"));

        writeFile(tasks);
    }
}
