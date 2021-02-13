package Duke.Functions;

import Duke.Duke;
import Duke.Exceptions.DukeException;
import Duke.Task.DeadlineTask;
import Duke.Task.EventTask;
import Duke.Task.ToDoTask;

public class AddToList extends Duke {
    public static void AddToList(String taskType, String taskDescription) {
        if (taskType.equalsIgnoreCase("todo")) {
            lists.add(new ToDoTask(taskDescription));
            taskAddedText();
    } else if (taskDescription.contains("/")) {
            String[] taskAndTime = taskDescription.split("/", 2);
            if (taskType.equalsIgnoreCase("deadline")) {
                lists.add(new DeadlineTask(taskAndTime[0], taskAndTime[1]));
                taskAddedText();
            } else if (taskType.equalsIgnoreCase("event")) {
                lists.add(new EventTask(taskAndTime[0], taskAndTime[1]));
                taskAddedText();
            }
        } else {
            DukeException.taskWithoutTime();
        }
    }

    public static void taskAddedText() {
        System.out.println(" Task added! ^_^");
        printList(taskCount, taskCount + 1);
        ++taskCount;
    }

    public static boolean keywordCheck(String keyword) {
        return keyword.equalsIgnoreCase("deadline") ||
                keyword.equalsIgnoreCase("event") ||
                keyword.equalsIgnoreCase("todo");
    }
}