public class todolist {
    private String[] list = new String[100];
    private int items = 0;
    private Boolean[] tracker = new Boolean[100];


    public todolist(String[] arg){
        items = arg.length;
        for(int i = 0; i < items; i++){
            list[i] = arg[i];
            tracker[i] = false;
        }
    }

    public todolist(){

    }

    public String done(Boolean arg){
        if (arg){
            return "[X]";
        }else{
            return "[ ]";
        }
    }


    public String list_items(){
        String output = "";

        for(int i = 0; i < items; i++){
            if(i < items - 1) {
                output += Integer.toString(i + 1) + ". " + done(tracker[i]) + " " + list[i] + "\n";
            }else{
                output += Integer.toString(i + 1) + ". " + done(tracker[i]) + " " + list[i];
            }
        }
        return output;
    }


    public String add_items(String arg){
        this.list[items] = arg;
        this.tracker[items] = false;
        this.items += 1;
        return "added: " + arg;
    }

    public String mark_done(String arg){
        String output = "";

        Integer task_no = Integer.parseInt(arg) - 1;

        if(tracker[task_no]){
            return "Task is already done!";
        }


        this.tracker[task_no] = true;
        output = "Nice! I've marked this task as done: \n" +
                 done(true) + " " + list[task_no];

        return output;
    }


}
