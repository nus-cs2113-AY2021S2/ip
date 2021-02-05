public class Task {
    protected String description;
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
        System.out.print("[" + (taskIndex + 1) + "]");
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
        return (isDone ? "X" : " ");
    }

    protected void printTaskItem() {
        String item = " " + this.description;
        System.out.print(item);
    }

    public void addToTaskList(String errand, String timestamp) {
        echoInput(errand, timestamp);
        taskArray[taskIndex] = this;
        taskIndex++;
    }

    public void echoInput(String errand, String timestamp) {
        String taskString;
        String echoTop = "______________________________________________________\n"
                + "[Orders received]:\n";
        String echoBottom = "______________________________________________________\n";

        if (timestamp == null) { // Todo tasks
            taskString = " " + errand + "\n";
        } else { // Deadlines and Events
            taskString = " " + errand + " (Timestamp: " + timestamp + ")" + "\n";
        }

        System.out.println(echoTop);
        printTaskType();
        printCompletionStatus();
        System.out.println(taskString);
        printTaskCount();
        System.out.println(echoBottom);
    }

    private void printTaskCount() {
        String taskCountMessage = "There are now " + (taskIndex + 1)
                + " mission objectives, Commander.";
        System.out.println(taskCountMessage);
    }

    public static void markDone(String errand) { // In this case, errand is the index of the item
        int taskIndex = Integer.parseInt(errand) - 1;
        taskArray[taskIndex].isDone = true;
        printMarkDonePrompt(taskIndex);
    }

    private static void printMarkDonePrompt(int taskIndex) {
        String doneTextTop = "______________________________________________________\n"
                + "[Objective Completed]:\n";
        String doneTextBottom = "______________________________________________________\n";

        System.out.println(doneTextTop);
        taskArray[taskIndex].printTaskType();
        taskArray[taskIndex].printCompletionStatus();
        taskArray[taskIndex].printTaskItem();
        System.out.println();
        System.out.println(doneTextBottom);
    }
}
