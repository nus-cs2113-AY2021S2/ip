/*public class List {
    private Task[] tasks;
    private static int size;

    // list struct
    public List() {
        this.tasks = new Task[100];
        this.size = 0;
    }

    // add items to the list
    public void addTasks(String description) {
        System.out.println("Got it. I've added this task: ");
        if(description.contains("todo")){
            this.tasks[size] = new Task(description);
        }
        else if(description.contains("deadline")){
            this.tasks[size] = new Deadline(description, "baa"); //use substring to put in two arguments
        }
        else if(description.contains("event")){
            this.tasks[size] = new Event(description, "boo"); //use substring to put in two arguments
        }
        ++this.size; //increment
    }

    // mark and display task that is completed
    public void taskCompleted(String command) {
        // remove done from string
        command = command.replace("done", " ");
        command = command.strip();
        // convert string 2 into int 2
        int count = Integer.parseInt(command);
        --count; // array starts from 0
        this.tasks[count].markAsDone();
        printDash();
        System.out.println("Nice! I've marked this task as done: ");

        System.out.println(this.tasks[count].getStatusIcon());
        printDash();
    }

    // print list as a checklist when command=list
    public void printList() {
        printDash();
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + " " + this.tasks[i]);
        }
        printDash();
    }
    public void printDash() {
        System.out.println("-".repeat(50));
    }


}*/

