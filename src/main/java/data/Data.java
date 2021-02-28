package data;

import duke.todoList;

import java.io.File;

public class Data {

    protected static String path = System.getProperty("user.dir") + "\\data";
    protected static final File importedList = new File(path);
    protected static todoList inputList;

    public Data(){
    }

    public Data(String path){
        Data.path = path;
        File importedList = new File(path);
    }

    public static void returnPath(){
        String path = System.getProperty("user.dir") + Data.path;
        System.out.println(path);
    }

    public void setData(todoList input){
        this.inputList = input;
    }
}
