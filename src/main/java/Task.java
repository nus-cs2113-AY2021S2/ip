public class Task {

    protected static String[] tasks = new String[100];//track tasks
    protected static boolean[] status = new boolean[100]; //track status
    protected static int count = 0; //keep track of number

    public void addTask(String description) { //initialization
        tasks[count] = description; //store into the string arr
        status[count] = false;  //set the count to false initially
        System.out.println("added: "+tasks[count]);
        count++;
    }

    public void printList(){

        for(int i=0;i<count;i++){
            System.out.println((i+1)+". " +"["+getStatusIcon(i)+"]"+tasks[i]);
        }

    }

    public String getStatusIcon(int index) {
        return (status[index] ? "\u2713" : "\u2718"); //return tick or X
    }

    public void MarkAsDone(int index){
        status[index] = true; //mark as done
        System.out.println("Nice! I've marked this task as done:\n"+"["+getStatusIcon(index)+"]"+" "+tasks[index]);
    }
}