public class TaskManager {
    private Task[] tasks = new Task[100];
    int taskCount = 0;

    public void listTask() {
        for (int i=0; i<taskCount; ++i) {
            System.out.print(i+1 + ". [");
            System.out.print((tasks[i].isDone()) ? "X" : " ");
            System.out.println("] " + tasks[i].getTaskName());
        }
    }

    public void addTask(String line) {
        tasks[taskCount] = new Task(line);
        ++taskCount;
        System.out.println("added: " + line);
    }

    public void doneTask(int taskNum) {
        tasks[taskNum].setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[taskNum].getTaskName());
    }
}
