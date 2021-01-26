public class Task {
    private String description;
    private boolean isDone;

    private static String tasksTemplate
            = "******************************************************\n"
            + "*                                                    *\n"
            + "*          [Objectives]-[Missions]-[Tasks]           *\n"
            + "*                                                    *\n"
            + "******************************************************\n"
            + "\n"
            + "[Status] [S/N]\n";

    public Task(){
        description = null;
        isDone = false;
    }

    public void addList(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String getStatusIcon(Task[] listArray, int i) {
        return (listArray[i].isDone ? "\u2713" : "\u2718");
    }

    public static void printList(Task[] listArray, int listIndex){
        System.out.println(tasksTemplate);
        for (int i = 0; i < listIndex; i++) {
            System.out.println("[  " + getStatusIcon(listArray, i) + "  ]"
                    + " [ " + (i+1) + " ] " // i+1 to make it user readable
                    + listArray[i].description);
        }
        System.out.println(); // For a neater UI
    }

    public static String markDone(Task[] tasksArray, String serialNumber, int taskLength) {
        int taskIndex = serialNumberCheck(serialNumber, taskLength);
        if (taskIndex >= 0) {
            tasksArray[taskIndex].isDone = true;
            return tasksArray[taskIndex].description;
        } else {
            // No item in list to return
            return null;
        }
    }

    private static int serialNumberCheck(String serialNumber, int taskLength) {
        try {
            int taskIndex = Integer.parseInt(serialNumber);
            taskIndex--; // Users will key in +1 of index due to S/N
            if (taskIndex < taskLength && taskLength != 0) {
                return taskIndex;
            } else {
                System.out.println("Index out of bound");
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
