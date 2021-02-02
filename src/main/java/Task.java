public class Task {
    protected String description;
    protected String timestamp;
    protected boolean isDone;
    protected static int taskIndex = 0;
    protected static final int MAX_ARRAY_SIZE = 100;
    protected static Task[] taskArray = new Task[MAX_ARRAY_SIZE];

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static void printList() {
        int index = 0;
        printTaskHeader();
        for(Task task: taskArray) {
            // Empty array
            if(index >= taskIndex){
                break;
            }
            task.printTaskIndex(index);
            task.printTaskType();
            task.printCompletionStatus();
            task.printTaskItem();
            System.out.println();
            index++;
        }
        System.out.println("\n");
    }

    protected void printTaskItem() {
        String item = " " + this.description;
        System.out.print(item);
    }

    private static void printTaskHeader() {
        String tasksTemplate
                = "******************************************************\n"
                + "*                                                    *\n"
                + "*          [Objectives]-[Missions]-[Tasks]           *\n"
                + "*                                                    *\n"
                + "******************************************************\n";
        System.out.println(tasksTemplate);
    }

    private void printTaskIndex(int taskIndex) {
        System.out.print("[" + Integer.toString(taskIndex + 1) + "]");
    }

    private void printTaskType() {
        System.out.print("[" + getTaskType() + "]");
    }

    protected String getTaskType() {
        return null;
    }

    private void printCompletionStatus() {
        System.out.print("[" + getStatusIcon() + "]");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void addToTaskList(String errand, String timestamp) {
        echoInput(errand, timestamp);
        taskArray[taskIndex] = this;
        taskIndex++;
    }

    public void echoInput(String errand, String timestamp) {
        String echoBottom;
        String echoTop = "______________________________________________________\n"
                + "[Orders received]:\n";

        if (timestamp == null) { // Todo tasks
            echoBottom = " " + errand + "\n"
                    + "______________________________________________________\n";
        } else { // Deadlines and Events
            echoBottom = " " + errand + " (Timestamp: " + timestamp + ")" + "\n"
                    + "______________________________________________________\n";
        }

        System.out.println(echoTop);
        printTaskType();
        printCompletionStatus();
        System.out.println(echoBottom);
    }

    /*
    public void addList(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String getStatusIcon(Task[] listArray, int i) {
        return (listArray[i].isDone ? "\u2713" : "\u2718");
    }

    public static void printList(Task[] listArray, int listIndex){
        printTaskHeader();
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
            if (taskIndex < taskLength && taskIndex >= 0) {
                return taskIndex;
            } else {
                System.out.println("Index out of bound");
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    */
}
