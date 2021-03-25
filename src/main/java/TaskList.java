

import java.util.ArrayList;

/**
 * This is task array class and we use this class to update new tasks onto the list.
 */

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor initializes arraylist with a list of tasks
     *
     * @param tasks the list of the tasks will be provided.
     */

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    /**
     * This will update and add one more task to the tasklist array.
     *
     * @param task gets added into the tasklist.
     */

    public void update(Task task){
        tasks.add(task);
    }

    /**
     * Get a task from the given index in the tasklist
     *
     * @param i index to be pulled
     * @return Task object
     */

    public Task get(int i){
        return tasks.get(i - 1);
    }

    /**
     * This method will delete the particular task at the given index.
     *
     * @param i index to be deleted
     */

    public void delete(int i){
        tasks.remove(i - 1);
    }

    /**
     * This method will update the status of a task to done
     *
     * @param i index to be updated to done
     */

    public void updateStatus(int i){
        tasks.get(i - 1).updateStatus();
    }

    /**
     * This methods will let us get the size of the tasks array.
     *
     * @return the size of the tasks array.
     */

    public int getSize(){
        return tasks.size();
    }

    /**
     * The tasklist gets converted into a String object format and it gets saved.
     *
     * @return formatted String
     */

    public String save(){
        StringBuilder line = new StringBuilder();
        for (Task task : tasks){
            if (!task.istodo()){
                String append = task.description() + task.getWork() + "|" + task.getDate() + "\n";
                line.append(append);
                continue;
            }
            String append = task.toString() + "\n";
            line.append(append);
        }
        return line.toString();
    }

    /**
     * This method overrides the default method.
     *
     * @return the string that has the tasklist.
     */

    public String toString(){
        StringBuilder line = new StringBuilder();
        for(int i =0;i< tasks.size();i++){
            line.append(tasks.get(i).toString());
            line.append('\n');
        }
        return line.toString();
    }
}

