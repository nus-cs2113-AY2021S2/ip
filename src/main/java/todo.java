public class todo extends todolist {
    private listTypes[] types = new listTypes[100];
    private String[] dates = new String[100];


    public todo() {
        super();
    }

    public String displayType(listTypes type){
        String out = "";
        switch(type){
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
    public String displayDate(listTypes type, String day){
        String out = "";
        switch (type){
        case deadline:
            out = String.format("(by: %s)", day);
            break;
        case event:
            out = String.format("(at: %s)", day);
            break;
        case todo:
            break;

        };
        return out;
    }

    public String listItems(){
        String output = "";

        for(int i = 0; i < items; i++){
            if(i < items - 1) {
                output += Integer.toString(i + 1) + ". " + displayType(types[i]) + " " + displayCheck(tracker[i]) + " " + list[i] + " " + displayDate(types[i],dates[i]) +"\n";
            }else{
                output += Integer.toString(i + 1) + ". " + displayType(types[i]) + " " + displayCheck(tracker[i]) + " " + list[i] + " " + displayDate(types[i],dates[i]);
            }
        }
        return output;
    }




    public String addItems(String item, listTypes type, String day){
        tracker[this.items] = false;
        this.types[this.items] = type;
        this.dates[this.items] = day;
        return displayType(type) + " " + displayCheck(tracker[this.items]) + " " + this.addItems(item) + " " + displayDate(type, day);

    }

    public String addItems(String arg, listTypes type){
        tracker[this.items] = false;
        this.types[this.items] = type;
        String out = displayType(type) + " " + displayCheck(tracker[this.items]);
        return out + " " + this.addItems(arg);
    }



}
