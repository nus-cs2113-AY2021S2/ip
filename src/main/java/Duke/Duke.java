package Duke;

public class Duke {

    public static void main(String[] args) {
        String storagePath = "database.txt";
        Logic logic = new Logic(storagePath);
        logic.run();
    }

}