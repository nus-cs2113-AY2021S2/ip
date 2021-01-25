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
        System.out.println("added: "+content);
        System.out.println("____________________________________________________________");
    }
    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< this.numOfTasks; i++) {
            System.out.println(i+1 + ". "+tasks[i].getContent());
        }
        System.out.println("____________________________________________________________");
    }
}
