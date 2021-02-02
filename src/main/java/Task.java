public class Task {

    String description;
    protected boolean isDone;
    private static int listCount = 1;

    public Task(String description) {
        this.description = description;
        this.isDone = false;

        listCount++;
        System.out.println("Added: " + description);

    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]":"[ ]");
    }

    public void updateDoneStatus(){
        isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + description);
    }

    public static int getListCount() {
        return listCount;
    }



}
