public class AddToList extends Duke {
    public static void AddToList(String taskType, String task) {
        if (taskType.equalsIgnoreCase("todo")) {
            lists[listIndex][0] = "T";
            lists[listIndex][1] = " ";
            lists[listIndex][2] = task;
            lists[listIndex][3] = " ";
            TaskAddedText();
        } else {
            if (task.contains("/")) {
                String[] TaskAndTime= task.split("/", 2);
                if (taskType.equalsIgnoreCase("deadline")) {
                    lists[listIndex][0] = "D";
                    lists[listIndex][1] = " ";
                    lists[listIndex][2] = TaskAndTime[0].trim();
                    lists[listIndex][3] = " (by:" + TaskAndTime[1].trim() + ")";
                    TaskAddedText();
                } else if (taskType.equalsIgnoreCase("event")) {
                    lists[listIndex][0] = "E";
                    lists[listIndex][1] = " ";
                    lists[listIndex][2] = TaskAndTime[0].trim();
                    lists[listIndex][3] = " (at:" + TaskAndTime[1].trim() + ")";
                    TaskAddedText();
                }
            } else {
                DukeException.taskWithoutTime();
            }
        }
    }

    public static void TaskAddedText() {
    System.out.println(" Task added! ^_^");
        PrintList(listIndex, listIndex + 1);
        listIndex += 1;
    }

    public static boolean keywordCheck(String keyword){
        if (keyword.equalsIgnoreCase("deadline")||
            keyword.equalsIgnoreCase("event") ||
            keyword.equalsIgnoreCase(("todo"))){
            return true;
        } else {
            return false;
        }
    }
}