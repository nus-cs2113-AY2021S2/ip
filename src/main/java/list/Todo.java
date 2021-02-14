package list;

public class Todo extends TaskList {

    private static final String TASK_TITLE = "T";

    public Todo(String line) {
        this.description = line;
        this.isDone = false;
        printAddedTask();
    }

    @Override
    public void printAddedTask() {
        System.out.println("Aight Crewmate!! I've got a new task for you to do!!! (¬‿¬): " + getTaskDescription());
    }

    @Override
    public void printTask() {
        String phrase = "[" + TASK_TITLE + "]" + "[" + getStatusIcon() + "]"
                + getTaskDescription();
        System.out.println(phrase);
    }

    @Override
    public String getTaskToPrintInFile(){
        return ("todo " + this.description + System.lineSeparator() +this.isDone);
    }
}
