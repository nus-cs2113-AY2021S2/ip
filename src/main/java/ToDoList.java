import java.util.ArrayList;

public class ToDoList {
    /**
     * Total number of list items in current list.
     */
    private static int numOfListItem = 0;
    /**
     * The collection of list items in current list.
     */
    private ArrayList<ListItem> listItems;

    /**
     * Initializes a to-do-list object with an empty array list that stores list items.
     */
    public ToDoList() {
        this.listItems = new ArrayList<>();
    }

    /**
     * Returns the total number of list items.
     *
     * @return the total number of list items.
     */
    public static int getNumOfListItem() {
        return numOfListItem;
    }

    /**
     * Prints the current list with dividers.
     */
    public void printCurrentList() {
        System.out.println(Duke.DIVIDER);
        printList();
        System.out.println(Duke.DIVIDER_LINE_ONLY);
    }

    /**
     * Prints the current list (without dividers).
     */
    private void printList() {
        int listSize = listItems.size();
        System.out.println("This is your current to-do list:");
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
    }

    /**
     * Adds a new list item to the current to-do list.
     * The content of the new list will be printed.
     *
     * @param content the content of the new list item.
     */
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

    /**
     * Updates the content of a given list item.
     *
     * @param itemIndex  the index of the list item in the array list.
     * @param newContent the new content to be updated.
     */
    public void updateItemContent(int itemIndex, String newContent) {
        ListItem itemToUpdate = listItems.get(itemIndex);
        itemToUpdate.setItemContent(newContent);
    }

    /**
     * Removes a given list item from the current to-do list.
     *
     * @param itemIndex the index of the list item in the array list.
     */
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

    /**
     * Prints the list item with content and status.
     *
     * @param itemIndex the index of the list item in the array list.
     */
    public void printItem(int itemIndex) {
        ListItem currentItem = listItems.get(itemIndex);
        boolean isItemDone = currentItem.isDone();
        System.out.println("Item content: " + currentItem.getItemContent());
        System.out.println("Item status: " + (isItemDone ? "Done" : "Undone"));
    }

    /**
     * Updates the status of a given list item.
     *
     * @param itemIndex the index of the list item in the array list.
     * @param isDone the new status of the list item.
     */
    public void updateItemStatus(int itemIndex, boolean isDone) {
        ListItem currentItem = listItems.get(itemIndex);
        currentItem.setDone(isDone);
        System.out.println(Duke.DIVIDER
                + "The list item: \n"
                + " | " + currentItem.getItemContent() + " |\n"
                + "is marked as "
                + (isDone ? "done" : "undone") + ".\n"
        );
        printList();
        System.out.println(Duke.DIVIDER_LINE_ONLY);
    }
}
