import java.util.Arrays;

public class List {
    private static int numItems = 0;
    private Task TaskList[] = new Task[100];

    public void addTask(String task) {
        Task myTask = new Task(task);
        TaskList[numItems] = myTask;
        //System.out.println(myTask);
        //System.out.println(Arrays.toString(TaskList));
        addTaskNum();
    }

    public void addTaskNum() {
        numItems++;
    }

    public void printList() {
        if (numItems == 0) {
            System.out.println("whoops nothing to see here");
            return;
        }
        for (int i = 0; i < numItems; i++) {
            System.out.println( i+1 + ". " + TaskList[i] );
        }
    }
}
