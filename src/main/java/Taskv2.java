public class Taskv2 extends Task {
    private listTypes type;
    private String date;

    public Taskv2(String desc, listTypes type, String date){
        super(desc);
        this.type = type;
        this.date = date;
    }
    public Taskv2(String desc, listTypes type){
        super(desc);
        this.type = type;
        this.date = "";
    }

    public String displayType(){
        String out = "";
        switch(this.type){
        case todo:
            out = "[T]";
            break;
        case deadline:
            out = "[D]";
            break;
        case event:
            out = "[E]";
            break;
        };

        return  out;
    }

    public String displayDate(){
        String out = "";
        switch (this.type){
        case deadline:
            out = String.format("(by: %s)", this.date);
            break;
        case event:
            out = String.format("(at: %s)", this.date);
            break;
        case todo:
            break;
        };
        return out;
    }

    public String displayDescription(){
        return description;
    }
    public String displayResolved(){

        if (this.resolved) {
            return "[X]";
        } else {
            return "[ ]";
        }

    }







}
