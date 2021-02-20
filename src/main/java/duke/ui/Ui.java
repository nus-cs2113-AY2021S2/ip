package duke.ui;

import java.util.List;

import duke.common.Strings;

public class Ui {

    /**
     * Wraps replies with horizontal lines and indentation.
     * @param messages Dukes' replies to be seen by user.
     */
    public static void reply(List<String> messages) {
        if (messages.isEmpty()) {
            return;
        }
        StringBuilder allMessages = new StringBuilder();
        String indentation = "     ";
        for (String message : messages) {
            if (message.isEmpty()) {
                continue;
            }
            allMessages.append(indentation).append(message).append("\n");
        }
        System.out.println(
            String.format(
                "%s\n%s%s\n",
                Strings.HORIZONTAL_LINE,
                    allMessages.toString(),
                Strings.HORIZONTAL_LINE
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
                Strings.HORIZONTAL_LINE,
                output,
                Strings.HORIZONTAL_LINE
            )
        );
    }
}
