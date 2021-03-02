package duke;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;


public class TaskManager {
    private ArrayList<Task> tasks;

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }
    public TaskManager(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    /**
     * Adds a TodoTask to the tasks.
     * @param content the description of the TodoTask
     * @return the added TodoTask object.
     */
    public Todo addTodo(String content) {
        Task taskAdded = new Todo(content);
        tasks.add(taskAdded);
        return (Todo) taskAdded;
    }



    /**
     * Adds a DeadlineTask to the tasks.
     * @param content the description of the DeadlineTask
     * @param by the date of the DeadlineTask
     * @return the added DeadlineTask object.
     */
    public Deadline addDeadline(String content, String by) {
        Task taskAdded = new Deadline(content, by);
        tasks.add(taskAdded);
        return (Deadline) taskAdded;
    }



    /**
     * Adds a EventTask to the tasks.
     * @param content the description of the EventTask
     * @param at the date of the EventTask
     * @return the added EventTask object.
     */
    public Event addEvent(String content, String at) {
        Task taskAdded = new Event(content, at);
        tasks.add(taskAdded);
        return (Event) taskAdded;
    }

    /**
     * Mark a certain task as done
     * @param taskIndexShow the index of the task that user input
     * @return the modified to done Task object.
     */
    public Task markTaskDone(int taskIndexShow) {
        tasks.get(taskIndexShow - 1).setDone(true);
        return tasks.get(taskIndexShow - 1);
    }

    public void listAllTasks() {
        for(int i=0; i< tasks.size() ; i++) {
            System.out.println(i+1 + ". " + tasks.get(i));
        }
    }

    /**
     * delete a certain task from tasks
     * @param taskIndexShow the index of the task that user input
     * @return deleted Task object.
     */
    public Task deleteTask(int taskIndexShow){
        Task temp = tasks.get(taskIndexShow - 1);
        tasks.remove(taskIndexShow - 1);
        return temp;
    }


    //filter by date
    public ArrayList<Task> filterByDate(LocalDate date) {
        ArrayList<Task> filteredDeadline = (ArrayList<Task>) tasks.stream()
                .filter((t) -> (t instanceof Deadline))
                .filter((s) -> ((Deadline) s).getBy().equals(date))
                .collect(toList());

        ArrayList<Task> filteredEvent = (ArrayList<Task>) tasks.stream()
                .filter((t) -> (t instanceof Event))
                .filter((s) -> ((Event) s).getAt().equals(date))
                .collect(toList());
        
        filteredDeadline.addAll(filteredEvent);
        return filteredDeadline;


    // find tasks by string
    public ArrayList<Task> filterTasksByString(String filterString) {
        ArrayList<Task> filteredList = (ArrayList<Task>) tasks.stream()
                .filter((s) -> s.getContent().toLowerCase().contains(filterString))
                .collect(toList());
        return filteredList;
    }

}
