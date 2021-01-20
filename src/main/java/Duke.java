public class Duke {
    public static void printGreetings() {
        System.out.println(
                "__________________________________________\n" +
                        " Welcome. I am your assistant Duke.\n" +
                        " Just FYI, I am developed by Song Yu.\n" +
                        " May I know what I can help you?\n" +
                        "__________________________________________\n"
        );
    }

    public static void printExitGreetings() {
        System.out.println(
                "__________________________________________\n" +
                        " Thank you for getting in touch.\n" +
                        " See you next time. :)\n" +
                        "__________________________________________\n"
        );
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printGreetings();
        printExitGreetings();
    }
}
