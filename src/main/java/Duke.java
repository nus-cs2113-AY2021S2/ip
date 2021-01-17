public class Duke {

    public static void printHorizontalLine() {
        String hLine = "_".repeat(60);
        System.out.println("\t" + hLine);
    }

    private static void printStatement(String statement) {
        String[] fragments = statement.split("\\R");
        for (String fragment : fragments) {
            System.out.println("\t " + fragment);
        }
    }

    public static void printStatements(boolean printHead, boolean printFoot, String[] statements) {
        if (printHead) {
            printHorizontalLine();
        }
        for (String statement : statements) {
            printStatement(statement);
        }
        if (printFoot) {
            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?";
        String exitMessage = "Bye. Hope to see you again soon!";

        printStatements(true, true, new String[]{logo, greeting});
        printStatements(false, true, new String[]{exitMessage});
    }
}
