public class TaskManager {
    private Task[] tasks = new Task[100];
    int taskCount = 0;

    public void listTask() {
        for (int i=0; i<taskCount; ++i) {
            System.out.printf("%d: %s", i+1, tasks[i]);
            System.out.println();
        }
    }

    public void addTask(String line) {
        if(line.startsWith("todo")){
            tasks[taskCount] = new Todo(line);
        }else if(line.startsWith("deadline")){
            int byIndex = line.indexOf("/by");
            String by = line.substring(byIndex+4);
            tasks[taskCount] = new Deadline(line, by);
        }else if(line.startsWith("event")){
            int atIndex = line.indexOf("/at");
            String at = line.substring(atIndex+4);
            tasks[taskCount] = new Event(line, at);
        }

        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        ++taskCount;
        System.out.println("Now you have " + taskCount + " tasks in the list.");

    }

    public void doneTask(int taskNum) {
        tasks[taskNum].setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[taskNum].getTaskName());
    }
}
