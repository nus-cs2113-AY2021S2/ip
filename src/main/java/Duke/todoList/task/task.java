package Duke.todoList.task;

import Duke.Duke;

public class task {
    protected String description;
    protected boolean resolved;

    public task(String desc){
        this.description = desc;
        this.resolved = false;
    }

    public void resolve(){
        this.resolved = true;
    }

}
