package cbs.algorithms.freeCodeQuestions;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Symmetry {

    /*
     * Create a function that takes two or more arrays and returns an array of their
     * symmetric difference. The returned array must contain only unique values (no
     * duplicates).
     */

    public static <T> List<T> symmetrySet(List<T>... lists) {
        if (lists.length <= 1) {
            return lists[0];
        }

        List<T> endList = new ArrayList<T>();
        List<T> current = new ArrayList<>();
        for (int i = 0; i < lists.length - 1; i++) {
            current.clear();
            current.addAll(lists[i]);
            current.addAll(endList);
            List<T> next = lists[i + 1];
            endList = current.stream().filter(l -> next.contains(l) == false).collect(Collectors.toList());
        }
        

        return endList;
     }
}
