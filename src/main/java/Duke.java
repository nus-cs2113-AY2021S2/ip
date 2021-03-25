/**
 * The main code file of the project.
 */

public class Duke {
    private Storage storage;
    private UI ui;

    /**
     * Constructor that initialises two new objects
     */

    public Duke(){
        this.storage = new Storage();
        this.ui = new UI(storage);
    }

    public void run(){
        ui.welcomeMessage();
        ui.run();
        ui.goodByeMessage();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
