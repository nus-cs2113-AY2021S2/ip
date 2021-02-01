public class List {
    private Task[] list;
    private static int size;

    // list struct
    public List() {
        this.list = new Task[100];
        this.size = 0;
    }

    // add items to the list
    public void addTasks(String description) {
        this.list[size] = new Task(description);
        ++this.size;
    }

    // mark and display task that is completed
    public void taskCompleted(String command) {
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        // convert string 2 into int 2
        int count = Integer.parseInt(command);
        --count; // array starts from 0
        this.list[count].markAsDone();
        printDash();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.list[count].getStatusIcon());
        printDash();
    }

    // print list as a checklist when command=list
    public void printList() {
        printDash();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + " " + this.list[i].getStatusIcon());
        }
        printDash();
    }
    public void printDash() {
        System.out.println("-".repeat(50));
    }
}

