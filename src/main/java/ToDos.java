public class ToDos extends Task{

    public ToDos(String description) {
        super(description);
    }

    @Override
    public void printTask() {
        System.out.printf(".[T][%s] %s\n",super.getStatusIcon(), super.getDescription());
    }
}
