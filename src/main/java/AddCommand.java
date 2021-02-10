public class AddCommand extends Command {

    private String input;
    private int counter;
    private int type;

    AddCommand(Tasks[] tasks, String input, int counter, int type) {
        super(tasks);
        this.input = input;
        this.counter = counter;
        this.type = type;
    }

    public Tasks[] addTask() {
        if (type == 0) {
            int startIndex = input.indexOf("n");
            int endIndex = input.indexOf("\\at");
            String preciseInput = input.substring(startIndex+3,endIndex-1).trim();
            Deadline temp = new Deadline(preciseInput,input.split(" ")[0],input.substring(endIndex+4));
            tasks[counter]=temp;
            displayLine();
            addedMessage();
            System.out.println("[ " +tasks[counter].displayTaskType()+ " ]" + " [" +tasks[counter].setDisplay()+ "] " +
                    tasks[counter].getDescription() + tasks[counter].extraDescription);
            counter++;
            displayCount(counter);
            displayLine();
        } else if (type == 1) {
            int startIndex = input.indexOf("o");
            ToDo temp = new ToDo(input.substring(startIndex+4),input.split(" ")[0]);
            tasks[counter]=temp;
            displayLine();
            addedMessage();
            System.out.println("[ " +tasks[counter].displayTaskType()+ " ]" + " [" +tasks[counter].setDisplay()+
                    "] " + tasks[counter].getDescription() + tasks[counter].extraDescription);
            counter++;
            displayCount(counter);
            displayLine();
        } else {
            int startIndex = input.indexOf("t");
            int endIndex = input.indexOf("\\by");
            String preciseInput = input.substring(startIndex+2,endIndex-1).trim();
            Event temp = new Event(preciseInput,input.split(" ")[0],input.substring(endIndex+4));
            tasks[counter]=temp;
            displayLine();
            addedMessage();
            System.out.println("[ " +tasks[counter].displayTaskType()+ " ]" + " [" +tasks[counter].setDisplay()+
                    "] " + tasks[counter].getDescription() + tasks[counter].extraDescription);
            counter++;
            displayCount(counter);
            displayLine();
        }

        return tasks;
    }

    public static void displayCount(int counter){
        System.out.println("Now you have " + counter + " tasks in the list");
    }
}
