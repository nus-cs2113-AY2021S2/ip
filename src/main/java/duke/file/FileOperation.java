package duke.file;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FileOperation {
    public static String relativeFileName = "duke.txt";
    public static final String SEPARATOR = "|";

    public static void readFile(ArrayList<Task> userTasks){
        char taskSelection;
        try {
            File myObj = new File(relativeFileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            }
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                StringTokenizer tokens = new StringTokenizer(data , SEPARATOR);
                tokens.nextToken();
                taskSelection = data.toLowerCase().charAt(0);
                switch(taskSelection){
                case 't':
                    createTodoTask(userTasks, tokens);
                    break;
                case 'd':
                    createDeadlineTask(userTasks, tokens);
                    break;
                case 'e':
                    createEventTask(userTasks, tokens);
                    break;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static void createEventTask(ArrayList<Task> userTasks, StringTokenizer tokens) {
        boolean isDone = Boolean.parseBoolean(tokens.nextToken());
        String description = tokens.nextToken();
        String eventLocation = tokens.nextToken();
        userTasks.add(new Event(description, eventLocation));
        userTasks.get(userTasks.size()-1).setTaskStatus(isDone);
    }

    private static void createDeadlineTask(ArrayList<Task> userTasks, StringTokenizer tokens) {
        boolean isDone = Boolean.parseBoolean(tokens.nextToken());
        String description = tokens.nextToken();
        String deadlineDate = tokens.nextToken();
        userTasks.add(new Deadline(description, deadlineDate));
        userTasks.get(userTasks.size()-1).setTaskStatus(isDone);
    }

    private static void createTodoTask(ArrayList<Task> userTasks, StringTokenizer tokens) {
        boolean isDone = Boolean.parseBoolean(tokens.nextToken());
        String description = tokens.nextToken();
        userTasks.add(new Todo(description));
        userTasks.get(userTasks.size()-1).setTaskStatus(isDone);
    }

    public static void writeFile(ArrayList<Task> userTasks){
        String newLineToBeInserted = null;
        try {
            FileWriter myWriter = new FileWriter(relativeFileName);
            for(Task userTask : userTasks){
                switch(userTask.getTaskType()){
                case 't':
                case 'T':
                    newLineToBeInserted = convertTodoDetailsToString(userTask);
                    break;
                case 'd':
                case 'D':
                    newLineToBeInserted = convertDeadlineDetailsToString(userTask);
                    break;
                case 'e':
                case 'E':
                    newLineToBeInserted = convertEventDetailsToString(userTask);
                    break;
                }
                myWriter.write(newLineToBeInserted + "\n");
            }
            myWriter.close();
            System.out.println("Successfully updated the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private static String convertEventDetailsToString(Task userTask) {
        Event event = (Event) userTask;
        return event.getTaskType() + "|" + event.getTaskStatus() + "|" +
                event.getDescription() + "|" + event.getEventLocation();
    }

    private static String convertDeadlineDetailsToString(Task userTask) {
        Deadline deadline = (Deadline) userTask;
        return deadline.getTaskType() + "|" + deadline.getTaskStatus() + "|" +
                deadline.getDescription() + "|" + deadline.getDeadlineDate();
    }

    private static String convertTodoDetailsToString(Task userTask) {
        return userTask.getTaskType() + "|" + userTask.getTaskStatus() + "|" + 
                userTask.getDescription();
    }
}
