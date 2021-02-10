public class Command {
    Tasks[] tasks;
    Command(Tasks[] tasks){
        this.tasks = tasks;
    }

    public static void displayLine(){
        System.out.println("____________________________________________________________");
    }

    public static void addedMessage(){
        System.out.println("Got it. I've added this task: ");
    }
}
