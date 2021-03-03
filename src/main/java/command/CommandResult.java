package command;

import task.Task;
import task.TaskList;

import java.util.Iterator;
import java.util.List;

public class CommandResult {
    private String feedback;
    private TaskList relevantTasks;

    public CommandResult(String feedback){
        this.feedback = feedback;
        this.relevantTasks = null;
    }

    public CommandResult(String feedback, TaskList tasks){
        this.feedback = feedback;
        this.relevantTasks = tasks;
    }

    public String getFeedback(){
        return feedback;
    }

    public TaskList getRelevantTasks(){
        return relevantTasks;
    }

}
