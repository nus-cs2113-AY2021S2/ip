package Duke;

public class AddToList extends Duke {
    public static void AddToList(String taskType, String taskDescription) {
        if (taskType.equalsIgnoreCase("todo")) {
            lists[taskCount][0] = "T";
            lists[taskCount][1] = " ";
            lists[taskCount][2] = taskDescription;
            lists[taskCount][3] = " ";
            taskAddedText();

    } else if (taskDescription.contains("/")) {
            String[] taskAndTime = taskDescription.split("/", 2);
            if (taskType.equalsIgnoreCase("deadline")) {
                lists[taskCount][0] = "D";
                lists[taskCount][1] = " ";
                lists[taskCount][2] = taskAndTime[0].trim();
                lists[taskCount][3] = " (by:" + taskAndTime[1].trim() + ")";
                taskAddedText();
            } else if (taskType.equalsIgnoreCase("event")) {
                lists[taskCount][0] = "E";
                lists[taskCount][1] = " ";
                lists[taskCount][2] = taskAndTime[0].trim();
                lists[taskCount][3] = " (at:" + taskAndTime[1].trim() + ")";
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