package duke.task;

public class Validator {
    public static int validateIndex(String index) {
        int indexOfTask = -1;

        try {
            indexOfTask = Integer.parseInt(index) - 1;
            if (indexOfTask < 0 || TaskList.taskArray.size() == 0) {
                return -1;
            } else if (indexOfTask >= TaskList.taskArray.size()) {
                return -1;
            }
        } catch (NumberFormatException e) {
            return -2;
        }

        return indexOfTask;
    }
}
