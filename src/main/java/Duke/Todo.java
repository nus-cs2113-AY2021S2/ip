package Duke;

import Duke.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getAlphabet() {
        return "T";
    }

    public String toString(){
        return this.description;
    }
}
