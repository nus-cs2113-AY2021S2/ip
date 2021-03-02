package storage;

import commands.AddTodo;
import myexceptions.BlankDescriptionException;

import myexceptions.FileModifiedException;
import tasklist.Deadline;
import tasklist.Event;
import tasklist.Task;
import tasklist.Todo;

/**
 * The task decoder converts the tasks in the text file, into a
 * parsable from in the GuiltySpark program
 */
public class taskDecoder {

    /**
     * The task decoder converts the tasks in the text file, into a
     * parsable from in the GuiltySpark program
     * @param nextLine This is the next line of text in the text file
     * @return returns the task
     * @throws BlankDescriptionException if description fields are blank
     */
    public static Task taskDecoder(String nextLine) throws BlankDescriptionException, FileModifiedException {
        Boolean isCorrupted = FileIntegrityChecker.isCorrupted(nextLine);
        if(isCorrupted) {
            System.out.println("Task detected to be corrupted...Marked for deletion.");
            Task task = new Task("Corrupted. Please delete.");
            task.isCorrupted = -1;
            return task;
        }

        Character taskStatus = nextLine.charAt(4);
        char taskType = nextLine.charAt(1);
        //String taskStatus = nextLine.substring(4,5);
        String taskDescription = nextLine.substring(7);
        Task task = new Task(taskDescription);
        switch (taskType) {
        case 'T':
            task = new Todo(taskDescription);
            if (taskStatus.equals('1')) {
                task.isDone = true;
            }
            break;
        case 'E':
            int indexOfBy = taskDescription.indexOf("<<");
            String description = taskDescription.substring(0,indexOfBy - 1);
            String by = taskDescription.substring(indexOfBy + 1, taskDescription.lastIndexOf(">>"));
            task = new Event(description,by);
            if (taskStatus.equals('1')) {
                task.isDone = true;
            }
            break;
        case 'D':
            indexOfBy = taskDescription.indexOf("<<");
            description = taskDescription.substring(0,indexOfBy - 1);
            by = taskDescription.substring(indexOfBy + 1, taskDescription.lastIndexOf(">>"));
            task = new Deadline(description,by);
            if (taskStatus.equals('1')) {
                task.isDone = true;
            }
            break;
        }
        return task;
    }
}
