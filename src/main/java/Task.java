public class Task {
    protected static final int MAX_TASK_NUM = 100;
    protected static final String MARK_DONE = "X";
    protected static final String MARK_UNDONE = " ";
    protected static final String TASK_SYMBOL_NA = "?";

    protected static Task[] listOfTask;
    protected String completionStatus;
    protected static int taskAmt;
    protected String taskDescription;

    public Task() {
        listOfTask = new Task[MAX_TASK_NUM];
        taskAmt = 0;
    }

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.completionStatus = MARK_UNDONE;
    }

    public void addTask() {
        listOfTask[taskAmt] = this;
        taskAmt++;
        System.out.println("Got it. I've added this task:");
        this.printTaskDetails();
        System.out.println();
        System.out.println("Now you have " + taskAmt + " tasks in the list.");
    }

    public static void listTask() {
        int i = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task currentTask : listOfTask) {
            if(i == taskAmt) {
                break;
            }
            printIndexForPrinting(i+1);
            currentTask.printTaskDetails();
            System.out.println();
            i++;
        }
    }

    public void printTaskDetails() {
        this.printTaskSymbol();
        this.printTaskCompletionStatus();
        System.out.print(" ");
        this.printTaskDescription();
    }

    private void printTaskSymbol() {
        System.out.print("[");
        System.out.print(this.getTaskSymbol());
        System.out.print("]");
    }

    public String getTaskSymbol() {
        return TASK_SYMBOL_NA;
    }

    private void printTaskCompletionStatus() {
        System.out.print("[");
        this.getTaskCompletionStatus();
        System.out.print("]");
    }

    private void getTaskCompletionStatus() {
        System.out.print(this.completionStatus);
    }

    private void printTaskDescription() {
        this.getTaskDescription();
    }

    private void getTaskDescription() {
        System.out.print(this.taskDescription);
    }

    public static void printIndexForPrinting(int index) {
        System.out.print(index + ".");
    }

    public void markDone(int index) {
        Task currentTask = listOfTask[index-1];
        if (index > taskAmt) {
            System.out.println("There is no task number " + index + ".");
            System.out.println("Please try again!");
        } else {
            if (currentTask.isDone() == true) {
                System.out.println("You have already completed this task.");
            } else {
                currentTask.completionStatus = MARK_DONE;
                System.out.println("Nice! I've marked this task as done:");
                currentTask.printTaskDetails();
                System.out.println();
            }
        }
    }

    public boolean isDone() {
        if (this.completionStatus == MARK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}
