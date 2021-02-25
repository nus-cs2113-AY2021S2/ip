package storage;

import commands.AddTodo;
import myexceptions.BlankDescriptionException;
import tasklist.Event;
import tasklist.Task;
import tasklist.Todo;

public class taskDecoder {

    public static Task taskDecoder(String nextLine) throws BlankDescriptionException {

        char taskType = nextLine.charAt(1);
        String taskStatus = nextLine.substring(4,5);
        String taskDescription = nextLine.substring(7);
        Task task = new Task(taskDescription);
        switch (taskType) {
        case 'T':
            task = new Todo(taskDescription);
            if (taskStatus.equalsIgnoreCase("1")) {
                task.isDone = true;
            }
            //AddTodo.execute(taskDescription);
            break;
        case 'E':
            int indexOfBy = taskDescription.indexOf("--");
            String description = taskDescription.substring(0,indexOfBy - 1);
            String by = taskDescription.substring(indexOfBy + 1, taskDescription.lastIndexOf("--"));
            task = new Event(description,by);
            if (taskStatus.equalsIgnoreCase("1")) {
                task.isDone = true;
            }
            break;
        }
        return task;
    }
}
