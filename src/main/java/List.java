public class List {
    private String description;
    private boolean isDone;

    public void addList(String description) {
        this.description = description;
        this.isDone = false;
    }

    public static String getStatusIcon(List[] listArray, int i) {
        return (listArray[i].isDone ? "\u2713" : "\u2718");
    }

    public static void printList(List[] listArray, int listIndex){
        for (int i = 0; i < listIndex; i++) {
            //System.out.println("Print list");
            System.out.println("[  " + getStatusIcon(listArray, i) + "  ]" + " [ " + (i+1) + " ] "
                    + listArray[i].description); // +1 to make it user readable
        }
        System.out.println(); // To look UI look better
    }

    public static String markDone(List[] listArray, String serialNumber, int listLength) {
        int listIndex = serialNumberCheck(serialNumber, listLength);
        if (listIndex >= 0) {
            listArray[listIndex].isDone = true;
            return listArray[listIndex].description;
        } else {
            return null;
        }
    }

    private static int serialNumberCheck(String serialNumber, int listLength) {
        try {
            int listIndex = Integer.parseInt(serialNumber);
            listIndex--; // Users will key in +1 of index due to S/N
            if (listIndex < listLength && listLength != 0) {
                return listIndex;
            } else {
                System.out.println("Index out of bound");
                return -1;
            }
        } catch (NumberFormatException e) {
            return -1;
        }
    }


}
