public class Ui {
    private static String logo =
                    "\n"
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n"
                    + "░░░████████░░░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                    + "░░░█░░░░░░░██░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                    + "░░░█░░░░░░░░█░░█░░░█░░█░█░░██████░░░░░\n"
                    + "░░░█░░░░░░░░█░░█░░░█░░██░░░█░░░░░░░░░░\n"
                    + "░░░█░░░░░░░░█░░█░░░█░░█░█░░█████░░░░░░\n"
                    + "░░░█░░░░░░░██░░██░██░░█░█░░█░░░░░░░░░░\n"
                    + "░░░█░░░░░██░░░░░███░░░█░░█░█████░░░░░░\n"
                    + "░░░███████░░░░░░░░░░░░█░░█░░░░░░░░░░░░\n"
                    + "░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░\n";


    private static String lowerLine = "\n(¯`·._.·(¯`·._.· HI I'M DUKE ·._.·´¯)·._.·´¯)\n"
                                + "How can I help you?\n";

    private String upperLine = "\n(¯`·._.·(¯`·._.· (^ v ^) ·._.·´¯)·._.·´¯)\n";
    private String goodbye = " __   __   __   __   __       ___ \n"
            + "/ _` /  \\ /  \\ |  \\ |__) \\ / |__  \n" + "\\__> \\__/ \\__/ |__/ |__)  |  |___ \n"
            + "                                  ";
    private String response;

    /**
     * Ui constructor.
     */
    public Ui() {
        response = "";
    }

    /**
     * Gets Duke's welcome.
     *
     * @return duke's welcome.
     */
    public static String getWelcome() {
        return logo + lowerLine;
    }

    /**
     * Adds message to response.
     *
     * @param message message to be added.
     */
    public void addMessage(String message) {
        response += message + '\n';
    }

    /**
     * Gets the current response.
     *
     * @return the current response.
     */
    public String getResponse() {
        return upperLine + '\n' + response + lowerLine;
    }

    /**
     * Clears the current response.
     */
    public void clearResponse() {
        response = "";
    }

    /**
     * Sets response to be Duke's goodbye.
     */
    public void addGoodbye() {
        response = goodbye;
    }
}
