public class TaskManager {
    private Task[] tasks;
    private int numOfTasks;

    public TaskManager(int sizeOfTasks) {
        this.tasks = new Task[sizeOfTasks];
        numOfTasks = 0;
    }

    public void addTask(String content) {
        tasks[numOfTasks] = new Task(content);
        this.numOfTasks++;
        System.out.println("____________________________________________________________");
        System.out.println("added: " + content);
        System.out.println("____________________________________________________________");
    }

    public void markTaskDone(int taskIndexShow) {
        if (taskIndexShow > 0 && taskIndexShow <= numOfTasks) {
            this.tasks[taskIndexShow-1].setDone(true);
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println("[" + tasks[taskIndexShow-1].getStatusIcon() + "] " + tasks[taskIndexShow-1].getContent());
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
            System.out.println(i+1 + ". [" + tasks[i].getStatusIcon() + "]" + tasks[i].getContent());
        }
        System.out.println("____________________________________________________________");
    }
}
