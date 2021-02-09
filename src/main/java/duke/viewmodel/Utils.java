package duke.viewmodel;

import java.util.List;

public class Utils {

    /**
     * Wraps replies with horizontal lines and indentation.
     * @param messages Dukes' replies to be seen by user.
     */
    public static void reply(List<String> messages) {
        if (messages.isEmpty()) {
            return;
        }
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
     * Wraps error messages with horizontal lines and indentation.
     * @param errorMessage Dukes' report to user regarding invalid outcomes.
     */
    public static void notifyError(String errorMessage) {
        String output = "     " + errorMessage;
        System.out.println(
            String.format(
                "%s\n%s\n%s\n",
                Constants.HORIZONTAL_LINE,
                output,
                Constants.HORIZONTAL_LINE
            )
        );
    }
}
