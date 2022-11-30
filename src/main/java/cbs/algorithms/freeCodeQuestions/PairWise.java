package cbs.algorithms.freeCodeQuestions;

import java.util.ArrayList;
import java.util.List;

public class PairWise {
    /**
     * Given an array arr, find element pairs whose sum equal the second argument
     * arg and return the sum of their indices.
     * 
     * You may use multiple pairs that have the same numeric elements but different
     * indices. Each pair should use the lowest possible available indices. Once an
     * element has been used it cannot be reused to pair with another element. For
     * instance, pairwise([1, 1, 2], 3) creates a pair [2, 1] using the 1 at index 0
     * rather than the 1 at index 1, because 0+2 < 1+2.
     */

    public static int pairwise(final List<Integer> list, int sum) {
        List<Integer> usedIndex = new ArrayList<>();

        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (i != j && list.get(i) + list.get(j) == sum &&
                        usedIndex.contains(i) == false && usedIndex.contains(j) == false) {
                    usedIndex.add(i);
                    usedIndex.add(j);
                }
            }
        }
        return usedIndex.stream().reduce(0, Integer::sum);
    }

}
