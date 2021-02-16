package duke.task;

import duke.Duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileWriter;
import java.io.IOException;

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

    public Todo addTodo(String content) {
        tasks[numOfTasks] = new Todo(content);
        this.numOfTasks++;
        return (Todo) tasks[numOfTasks-1];
    }

    public Deadline addDeadline(String content, String by)
    {
        tasks[numOfTasks] = new Deadline(content, by);
        this.numOfTasks++;
        return (Deadline) tasks[numOfTasks-1];
    }

    public Event addEvent(String content, String at) {
        tasks[numOfTasks] = new Event(content, at);
        this.numOfTasks++;
        return (Event) tasks[numOfTasks-1];
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

    public void addFromTXT(String line) {
        String[] tasksInTXT = line.split("\\|");
        String taskType = tasksInTXT[0].trim();
        String taskDone = tasksInTXT[1].trim();
        String taskDescription = tasksInTXT[2].trim();
        switch (taskType) {
        case "T":
            Todo t = addTodo(taskDescription);
            if(taskDone.equals("1")) {
                t.setDone(true);
            }
            return;
        case "D":
            Deadline d = addDeadline(taskDescription,tasksInTXT[3].trim());
            if(taskDone.equals("1")) {
                d.setDone(true);
            }
            return;
        case "E":
            Event e = addEvent(taskDescription,tasksInTXT[3].trim());
            if(taskDone.equals("1")) {
                e.setDone(true);
            }
            return;
        }
    }

    public void writeToTxt(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for(int i=0; i< this.numOfTasks; i++) {
            fw.write(tasks[i].strAddToTxt());
            fw.write("\n");
        }
        fw.close();
    }




}
