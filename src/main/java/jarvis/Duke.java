package jarvis;

import jarvis.exception.InvalidCommandException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final Jarvis jarvis = new Jarvis();

    // MAIN program
    public static void main(String[] args) throws InterruptedException {
        jarvis.startJarvis();

        try {
            printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            System.out.println("\tUnfortunately, I could not detect any files in the database!");
            System.out.println("\tBut don't worry sir.");
            System.out.println("\tI will create the files you might be needing later.");
            jarvis.printDivider();
        }

        while (true) {
            try {
                writeToFile(jarvis.performTask());
            } catch (InvalidCommandException exception) {
                System.out.println("\tSorry, sir. I do not recognise this command.");
                jarvis.printDivider();
            } catch (IOException exception) {
                System.out.println("\tSomething went wrong: " + exception.getMessage());
                jarvis.printDivider();
            }
        }
    }

    // Writing into a text file
    private static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter("jarvis.txt");
        for (Task task : tasks) {
            fileWriter.write(task.toString() + System.lineSeparator());
        }
        fileWriter.close();
    }

    // Reading contents in the text file
    private static void printFileContents() throws FileNotFoundException {
        File file = new File("jarvis.txt");
        Scanner scanner = new Scanner(file);
        if (scanner.hasNext()) {
            System.out.println("\tHere's the tasks in your list, sir: ");
        } else {
            System.out.println("\tYou do not have any pending task, sir.");
        }
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            System.out.println("\t\t" + line);
            toTaskList(line);
        }
    }

    // Method to convert a string to a Task object and add it to the ArrayList
    private static void toTaskList(String line) {
        int taskIndex = 1;
        int statusIndex = 4;
        int descriptionStartIndex = 7;
        char taskType = line.charAt(taskIndex);
        switch (taskType) {
        case 'T':
            String descriptionTodo = line.substring(descriptionStartIndex);
            Task todo = new Todo(descriptionTodo);
            todo.setTaskStatus(line.charAt(statusIndex) != ' ');
            jarvis.tasks.add(todo);
            break;
        case 'D':
            String taskDeadline = line.substring(descriptionStartIndex);
            String[] detailsDeadline = taskDeadline.split("\\(by:", 2);
            String descriptionDeadline = detailsDeadline[0];
            String by = detailsDeadline[1].replace(")", "");
            Task deadline = new Deadline(descriptionDeadline, by);
            deadline.setTaskStatus(line.charAt(statusIndex) != ' ');
            jarvis.tasks.add(deadline);
            break;
        case 'E':
            String taskEvent = line.substring(descriptionStartIndex);
            String[] detailsEvent = taskEvent.split("\\(at:", 2);
            String descriptionEvent = detailsEvent[0];
            String at = detailsEvent[1].replace(")", "");
            Task event = new Event(descriptionEvent, at);
            event.setTaskStatus(line.charAt(statusIndex) != ' ');
            jarvis.tasks.add(event);
            break;
        }
    }
}