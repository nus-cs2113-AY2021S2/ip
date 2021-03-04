public class Ui {
    protected String filepath;
    public Ui (String filepath) {
        this.filepath = filepath;
    }

    public String getFilePath() {
        return filepath;
    }

    /**
     * print welcome message
     */
    static void printGreetMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        Duke.printDash();
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        Duke.printDash();
    }
    /**
     * print loading error message
     */
    public void showLoadingError() {
        System.out.println("\tUnable to access file at "+ getFilePath());
    }

}
