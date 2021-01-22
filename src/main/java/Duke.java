import java.util.concurrent.TimeUnit;

public class Duke {
    public static void main(String[] args) throws InterruptedException {
        // JARVIS initialisation (6 seconds waiting time)
        System.out.println("------------------------------------------------");
        System.out.println("Importing all preferences from home interface.");
        TimeUnit.SECONDS.sleep(1);  // waits 1 second
        System.out.println("Systems are now fully operational.");
        TimeUnit.SECONDS.sleep(1);  // waits 1 second
        System.out.print("LOADING");
        TimeUnit.SECONDS.sleep(1);  // waits 1 second
        for (int i = 0; i <= 2; i++) {
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);  // waits 1 second
        }
        System.out.println();
        // JARVIS greetings
        System.out.println("------------------------------------------------");
        System.out.println("    Hello, sir. J.A.R.V.I.S at your service.");
        System.out.println();
        System.out.println("------------------------------------------------");
        System.out.println("    Goodbye, sir.");
        System.out.println();
        System.out.println("------------------------------------------------");
    }
}
