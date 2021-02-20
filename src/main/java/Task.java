public class Task {
    protected String taskDescription;
    protected boolean isDone;
    protected String taskType;
    protected String completed;

    public Task(String inputTask) {
        this.taskDescription = inputTask;
        this.isDone = false;
        this.taskType = "[ ]";
        this.completed = "0";

    }

    public String getStatusIcon() {
        //if task is done, mark with X
        return (isDone ? "[X]" : "[ ]");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return taskType + this.getStatusIcon() + this.taskDescription;
    }

    public static Task textToTask(String text) {
        String[] task = text.split("\\|");
        Task existingTask = null;
        switch (task[0]) {
        case "T":
            existingTask = new ToDo(task[2]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "D":
            existingTask = new Deadline(task[2], task[3]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        case "E":
            existingTask = new Event(task[2], task[3]);
            if (task[1].equals("1")) {
                existingTask.markAsDone();
            }
            break;
        }
        return existingTask;
    }

    public String taskToText() {
        return "A|" + completed + "|" + taskDescription;
    }

}