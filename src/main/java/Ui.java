/**
 * ui of duke
 */
public class Ui {

    private String reply;

    public Ui(){
        reply = "";
    }

    /**
     * welcome ui of duke
     * @return the welcome message
     */
    public static String showWelcome(){
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }

    public void addResponse(String message){
        reply = (message + "\n");
    }

    public String getReply(){
        return reply;
    }

    /**
     * empty a reply
     */
    public void clearReply(){
        reply = "";
    }

    /**
     * exit message of duke
     * @return the exit message
     */
    public String showGoodbye(){
        return "Bye, hope to see you again!";
    }

}