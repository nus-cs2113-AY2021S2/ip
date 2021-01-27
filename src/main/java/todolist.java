public class todolist {
    private String[] list = new String[100];
    private int items = 0;

    public todolist(String[] arg){
        items = arg.length;
        for(int i = 0; i < items; i++){
            list[i] = arg[i];
        }
    }

    public todolist(){

    }




    public String list_items(){
        String output = "";

        for(int i = 0; i < items; i++){
            if(i < items - 1) {
                output += Integer.toString(i + 1) + ". " + list[i] + "\n";
            }else{
                output += Integer.toString(i + 1) + ". " + list[i];
            }
        }
        return output;
    }


    public String add_items(String arg){
        this.list[items] = arg;
        this.items += 1;
        return "added: " + arg;
    }





}
