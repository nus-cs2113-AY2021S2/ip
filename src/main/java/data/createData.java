package data;

import java.io.File;

public class createData extends Data{

    public createData(){
        super();
    }

    public createData(String path){
        super(path);
    }

    public void execute(){
        (new File(this.path)).mkdir();
        System.out.println("No Task List was found!\n"
                + "Task List file was created at: ");
        returnPath();
        System.out.println("____________________________________________________________\n");
    }
}
