public class Task {
    static final int MAX_TASK_NUM = 100;
    static final String MARK_DONE = "x";
    static final String MARK_UNDONE = " ";

    private String[] list;
    private String[] completionStatus;
    private int taskAmt;

    public Task() {
        list = new String[MAX_TASK_NUM];
        completionStatus = new String[MAX_TASK_NUM];
        taskAmt = 0;
    }

    public Task(String[] list, int taskAmt, String[] completionStatus) {
        this.list = list;
        this.taskAmt = taskAmt;
        this.completionStatus = completionStatus;
    }

    public void addTask(String task) {
        list[taskAmt] = task;
        completionStatus[taskAmt] = MARK_UNDONE;
        taskAmt++;
        System.out.println("added: " + task);
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; list[i] != null; i++) {
            System.out.println((i+1) + ".[" + completionStatus[i] + "] " + list[i]);
        }
    }

    public void markDone(int index) {
        if (index > taskAmt) {
            System.out.println("There is no task number " + index + " , Please try again");
        } else {
            if (isDone(index) == true) {
                System.out.println("You have already completed this task.");
            } else {
                completionStatus[index - 1] = MARK_DONE;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + completionStatus[index - 1] + "] " + list[index - 1]);
            }
        }
    }

    public boolean isDone(int index) {
        if (completionStatus[index-1] == MARK_DONE) {
            return true;
        } else {
            return false;
        }
    }
}
