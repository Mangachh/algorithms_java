package cbs.algorithms.freeCodeQuestions;
import java.util.HashMap;

public class UpdateInventory {
    /*
     * Compare and update the inventory stored in a 2D array (dictionary) against a
     * second 2D
     * array of a fresh delivery. Update the current existing inventory item
     * quantities (in arr1). If an item cannot be found, add the new item and
     * quantity into the inventory array. The returned inventory array should be in
     * alphabetical order by item.
     * 
     * dict = item, quantity
     * 
     * https://www.freecodecamp.org/learn/coding-interview-prep/algorithms/inventory
     * -update
     */

    /*
     * HashMap<String, Integer> inventory;
     * HashMap<String, Integer> newInv;
     */

    public static HashMap<String, Integer> updateInventory(final HashMap<String, Integer> current,
            final HashMap<String, Integer> newInv) {
        HashMap<String, Integer> updated = new HashMap<>(current);

        for (String item : newInv.keySet()) {
            if (current.containsKey(item)) {
                updated.put(item, current.get(item) + newInv.get(item));
            } else {
                updated.put(item, newInv.get(item));
            }
        }

        return updated;
    }

}
