
package duke;

import static duke.listTypes.todo;
import static duke.listTypes.deadline;
import static duke.listTypes.event;

/**Stores the type of task, if the task is resolved, the description of the task and the date of the task if applicable*/
public class improvedTask extends task {
    protected listTypes type;
    protected String date;

    public improvedTask(String desc, listTypes type, String date){
        super(desc);
        this.type = type;
        this.date = date;
    }
    public improvedTask(String desc, listTypes type){
        super(desc);
        this.type = type;
        this.date = "";
    }
    /**Method displays the type of the task*/
    public String displayType(){ // method to show the type of task
        String out = "";
        switch(this.type){
        case todo:
            out = "[T]";
            break;
        case deadline:
            out = "[D]";
            break;
        case event:
            out = "[E]";
            break;
        };

        return  out;
    }
    /**Method displays the date of the task*/
    public String displayDate(){ // method to show formatted date
        String out = "";
        switch (this.type){
        case deadline:
            out = String.format("(by: %s)", this.date);
            break;
        case event:
            out = String.format("(at: %s)", this.date);
            break;
        case todo:
            break;
        };
        return out;
    }
    /**Method displays the description of the task*/
    public String displayDescription(){ // method to access description
        return description;
    }
    public String displayResolved(){ // method to show if task is resolved

        if (this.resolved) {
            return "[X]";
        } else {
            return "[ ]";
        }

    }
    /**Method displays the resolution status of the task*/
    public boolean isResolved(){
        return this.resolved;
    }







}
