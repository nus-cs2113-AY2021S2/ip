package duke.task;

import duke.Duke;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class TaskManager {
    private ArrayList<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    // add
    public Todo addTodo(String content) {
        Task taskAdded = new Todo(content);
        tasks.add(taskAdded);
        return (Todo) taskAdded;
    }

    public Deadline addDeadline(String content, String by) {
        Task taskAdded = new Deadline(content, by);
        tasks.add(taskAdded);
        return (Deadline) taskAdded;
    }

    public Event addEvent(String content, String at) {
        Task taskAdded = new Event(content, at);
        tasks.add(taskAdded);
        return (Event) taskAdded;
    }
    // done
    public void markTaskDone(int taskIndexShow) {
        tasks.get(taskIndexShow - 1).setDone(true);
        Duke.showExecuteResult("Nice! I've marked this task as done:\n" + tasks.get(taskIndexShow - 1));
    }

    // list
    public void listAllTasks() {
        System.out.println("____________________________________________________________");
        for(int i=0; i< tasks.size() ; i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
        System.out.println("____________________________________________________________");
    }

    //delete
    public void deleteTask(int taskIndexShow){
        Task temp = tasks.get(taskIndexShow - 1);
        tasks.remove(taskIndexShow - 1);
        Duke.showExecuteResult("Noted. I've removed this task: \n" + temp + "\nNow you have " + getNumOfTasks() + " tasks in the list.");
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
        for(Task t : tasks) {
            fw.write(t.strAddToTxt());
            fw.write("\n");
        }
        fw.close();
    }
}
