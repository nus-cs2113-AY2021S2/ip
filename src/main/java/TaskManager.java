public class TaskManager {
    private Task[] tasks;
    private int numOfTasks;

    public TaskManager(int sizeOfTasks) {
        this.tasks = new Task[sizeOfTasks];
        numOfTasks = 0;
    }

    public int getNumOfTasks() {
        return numOfTasks;
    }

    public void showAddResult(int numOfTasks) {
        Duke.showExecuteResult("Got it. I've added this task:\n" + tasks[numOfTasks] + "\nNow you have " + (numOfTasks+1) + " tasks in the list.");
    }

    public void addTodo(String content) {
        tasks[numOfTasks] = new Todo(content);
        showAddResult(numOfTasks);
        this.numOfTasks++;
    }

    public void addDeadline(String content, String by)
    {
        tasks[numOfTasks] = new Deadline(content, by);
        showAddResult(numOfTasks);
        this.numOfTasks++;
    }

    public void addEvent(String content, String at) {
        tasks[numOfTasks] = new Event(content, at);
        showAddResult(numOfTasks);
        this.numOfTasks++;
    }

    public void markTaskDone(int taskIndexShow) {
        this.tasks[taskIndexShow-1].setDone(true);
        Duke.showExecuteResult("Nice! I've marked this task as done:\n" + tasks[taskIndexShow-1]);
    }

    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< this.numOfTasks; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }


}
