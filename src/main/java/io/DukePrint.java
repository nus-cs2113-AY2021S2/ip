package io;

public class DukePrint {
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printDivider() {
        System.out.println("~____________________________________________________________~");
    }

    public static void printEndDivider() {
        System.out.println("~____________________________________________________________~\n");
    }
}
