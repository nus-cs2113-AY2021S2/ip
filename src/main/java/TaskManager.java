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
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[numOfTasks]);
        System.out.println("Now you have " + (numOfTasks+1) + " tasks in the list.");
        System.out.println("____________________________________________________________");
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
        if (taskIndexShow > 0 && taskIndexShow <= numOfTasks) {
            this.tasks[taskIndexShow-1].setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndexShow-1]);
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("____________________________________________________________");
            System.out.println("No such task found! Please try again");
            System.out.println("____________________________________________________________");
        }
    }

    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< this.numOfTasks; i++) {
            System.out.println(i+1 + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }


}
