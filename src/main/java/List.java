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
        System.out.println("-".repeat(50));
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(this.list[count].getStatusIcon());
        System.out.println("-".repeat(50));
    }

    // print list as a checklist when command=list
    public void printList() {
        System.out.println("-".repeat(50));
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < size; ++i) {
            System.out.println((i + 1) + "." + " " + this.list[i].getStatusIcon());
        }
        System.out.println("-".repeat(50));
    }
}
           /* while (!command.equals("bye") && !command.equals("list")) {
                // add task into array
                tasks[count] = command;
                count++;
                // echo task
                System.out.println(dash.repeat(50));
                System.out.println("added: " + command);
                System.out.println(dash.repeat(50));
                command = myObj.nextLine();
            }*/
 /*else {
            tasks[value - 1].markAsDone();
            System.out.println(dash.repeat(50));
            System.out.println("Task " + value + " does not exit!");
            System.out.println(dash.repeat(50));
        }
    } else {
        System.out.println(dash.repeat(50));
        System.out.println("added: " + command);
        System.out.println(dash.repeat(50));
        Task t = new Task(command);
        tasks[count] = t;
        count++;

    }
}*/
