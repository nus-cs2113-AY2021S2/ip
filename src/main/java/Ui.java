public class Ui {
    private String reply;

    public Ui(){
        reply = "";
    }


    public static String showWelcome(){
        return "Hello! I'm Duke" + "\n" + "What can I do for you?";
    }


    public void addResponse(String message){
        reply = (message + "\n");
    }

    public String getReply(){
        return reply;
    }

    public void clearReply(){
        reply = "";
    }


    public String showGoodbye(){
        return "Bye, hope to see you again!";
    }

}