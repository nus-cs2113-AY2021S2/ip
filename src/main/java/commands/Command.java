package commands;

import duke.todoList;

public class Command {

    protected todoList inputList;
    private int separatorIndex = -1;

    public Command(int targetIndex) {
        this.separatorIndex(targetIndex);
    }
    public Command() {
    }

    private void separatorIndex(int targetIndex) {
        this.separatorIndex = targetIndex;
    }
    public void setData(todoList input){
        this.inputList = input;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };



}
