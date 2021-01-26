import java.util.ArrayList;

public class ToDoList {
    private static int numOfListItem = 0;
    private ArrayList<ListItem> listItems;

    public ToDoList() {
        this.listItems = new ArrayList<>();
    }

    public static int getNumOfListItem() {
        return numOfListItem;
    }

    public void printList() {
        int listSize = listItems.size();
        System.out.println(Duke.DIVIDER
                + "This is your current to-do list:"
        );
        if (listSize == 0) {
            System.out.println(" - The list is empty!");
            System.out.println(" - You can try add list item by input what\n"
                    + "   you want to do.\n"
                    + " - e.g. read book"
            );
        } else {
            for (int i = 0; i < listSize; i++) {
                ListItem currentItem = listItems.get(i);
                System.out.println((i + 1) + ". "
                        + "[" + (currentItem.isDone() ? "☑️" : "✖️") + "] "
                        + currentItem.getItemContent()
                );
            }
        }
        System.out.println(Duke.DIVIDER_LINE_ONLY);
    }

    public void addListItem(String content) {
        ListItem newItem = new ListItem(content);
        listItems.add(newItem);
        numOfListItem++;
        System.out.println(Duke.DIVIDER
                + "A new list item is added: "
                + newItem.getItemContent() + "\n"
                + Duke.DIVIDER_LINE_ONLY
        );

    }

    public void updateListItem(int itemIndex, String newContent) {
        ListItem itemToUpdate = listItems.get(itemIndex);
        itemToUpdate.setItemContent(newContent);
    }

    public void removeListItem(int itemIndex) {
        String itemContent = listItems.get(itemIndex).getItemContent();
        listItems.remove(itemIndex);
        numOfListItem--;
        System.out.println(Duke.DIVIDER
                + "A list item is removed: "
                + itemContent + "\n"
                + Duke.DIVIDER_LINE_ONLY
        );
    }

    public void checkItem(int itemIndex) {
        ListItem currentItem = listItems.get(itemIndex);
        boolean isItemDone = currentItem.isDone();
        System.out.println("Item content: " + currentItem.getItemContent());
        System.out.println("Item status: " + (isItemDone ? "Finished" : "Unfinished"));
    }

    public void updateItemStatus(int itemIndex, boolean isDone) {
        ListItem currentItem = listItems.get(itemIndex);
        currentItem.setDone(isDone);
    }

}
