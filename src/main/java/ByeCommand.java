public class ByeCommand extends Command {

    public ByeCommand() {
        super(CommandType.BYE);
    }

    @Override
    public void execute(Task[] tasks) {
        Menu.printBye();
        System.exit(0);
    }
}
