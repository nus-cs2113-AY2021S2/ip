import java.util.List;

public class Utils {
    /**
     * Wraps replies with horizontal lines and indentation.
     */
    public static void reply(List<String> messages) {
        String allMessages = "";
        String indentation = "     ";
        for (String message : messages) {
            if (message.isEmpty()) {
                continue;
            }
            allMessages += indentation + message + "\n";
        }
        System.out.println(
            String.format(
                "%s\n%s%s\n",
                Constants.HORIZONTAL_LINE,
                allMessages,
                Constants.HORIZONTAL_LINE
            )
        );
        messages.clear();
    }

    /**
     * Checks if the given string is an integer. 
     */
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch (NumberFormatException e) { 
            return false; 
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
