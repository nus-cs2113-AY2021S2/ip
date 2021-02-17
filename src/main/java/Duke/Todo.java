package Duke;

import Duke.Task;

public class Todo extends Task {

    public Todo(String description)  {
        super(description);
    }

    public String getAlphabet(){
        return "T";
    }
}
