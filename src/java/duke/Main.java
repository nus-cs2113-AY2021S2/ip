package duke;

import storage.Storage;
import ui.TextUi;
import parser.Parser;


public class Main {

    private TextUi ui ;
    private Storage storage;


    public static void main(String... args){
        new Main().run();
    }

    private void run(){
        start();
        runCommandLoopUntilByeCommand();
        exit();
    }

    private void start(){
        ui = new TextUi();
        storage = new Storage();
        ui.showWelcomeMessage();
        storage.readFile();
    }

    private void runCommandLoopUntilByeCommand() {

        while(true) {
            String userCommandText = ui.getUserCommand();
            Parser.parseCommand(userCommandText);
            if(userCommandText.equals("bye")){
            break;
            }
        }
    }

    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }
}
