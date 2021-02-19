package duke;

import static duke.listTypes.todo;
import static duke.listTypes.deadline;
import static duke.listTypes.event;

public class improvedTask extends task {
    private listTypes type;
    private String date;

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







}
