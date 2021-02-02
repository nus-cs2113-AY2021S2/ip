public class Task {

    //Store each task into the tasks array
    protected static String[] tasks = new String[100];
    //track status of each task
    protected static boolean[] isDone = new boolean[100];
    //keeps track of number of tasks
    protected static int count = 0;

    /**
     * Returns a string that prints the added task
     *
     * @param description is the current task to be added
     */
    public void addTask(String description) {
        //store into the string arr
        tasks[count] = description;
        //set the particular task to false initially
        isDone[count] = false;
        //print out the added task
        System.out.println("added: "+tasks[count]);
        count++;
    }

    /**
     * Prints a list of strings with all the past and present tasks
     * and showing their status whether it is done or not
     */
    public void printList(){

        for(int i=0;i<count;i++){
            System.out.println((i+1)+". " +"["+getStatusIcon(i)+"]"+tasks[i]);
        }

    }

    /**
     * Returns a string to indicate whether the task is done
     *
     * @param index is the current the task to be inquiry
     */
    public String getStatusIcon(int index) {
        return (isDone[index] ? "\u2713" : "\u2718");
    }

    /**
     * set the status of a task to be done
     *
     * @param index is the current task to be set done.
     */
    public void MarkAsDone(int index){
        //mark as done
        isDone[index] = true;
        System.out.println("Nice! I've marked this task as done:\n"
                +"["+getStatusIcon(index)+"]"+" "+tasks[index]);
    }
}