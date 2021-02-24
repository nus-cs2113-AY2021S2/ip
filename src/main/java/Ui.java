public class Ui {

    public static final String LINE_STRING = "____________________________________________________________\n";

    void welcomeMessage() {
        String welcome = LINE_STRING +
                " Hello! I'm Bob 😀\n" +
                " If you need anything hit me up fam 😌\n" +
                LINE_STRING;
        System.out.print(welcome);
    }

    void goodbyeMessage() {
        String goodbye = LINE_STRING + " Chao 👌\n" + LINE_STRING;
        System.out.print(goodbye);
    }

    void printNoCommandFormat(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                " 😥 You gotta use the time marker for " + commandName + "\n" +
                "and the time which it happens\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printNoCommandLabel(Command commandType) {
        String commandName = commandType.name().toLowerCase();
        String exceptionMessage = LINE_STRING +
                " 😥 You gotta tell me what is the task for " + commandName + "\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printNoSuchMethod() {
        String exceptionMessage = LINE_STRING +
                " 😥 I don't quite get what the command means\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printInputMismatch() {
        String exceptionMessage = LINE_STRING +
                " 😥 There is some issue with getting you input\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printIOException() {
        String exceptionMessage = LINE_STRING +
                " 😥 IO issue encountered! Unable to read file\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printNumberFormatException() {
        String exceptionMessage = LINE_STRING +
                " 😥 Number format exception encountered! Input may be corrupted\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    void printNoTaskSpecified() {
        String exceptionMessage = LINE_STRING +
                " 😥 Tell me what task are you referring to\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }

    public void printIndexOutOfBounds() {
        String exceptionMessage = LINE_STRING +
                " 😥 Index out of bounds\n" +
                LINE_STRING;
        System.out.println(exceptionMessage);
    }
}
