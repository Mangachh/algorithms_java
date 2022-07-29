package cbs.algorithms.sort;

import java.util.List;

public class SelectionSort {

    /**
     * The selection array looks for each position the minimun value in the array.
     * Starting from the position 0, the algorithm looks for the minimum value an
     * swaps
     * position array[0] to array[minimu].
     * Then it does the same for the position array[i] and so on.
     * We look for each position except the last one.
     * 
     * Pseudo:
     * selectionSort(array, size):
     * for unsortedIndex in range(size - 1):
     * minimum = array[unsortedIndex]
     * for (unsortedIndex + 1) in range(size):
     * if element < currentMinimum:
     * minimum = element
     * swap minimum with unsortedIndex
     * end selectionSort
     *
     * @param <T>  : class that extends Comparable
     * @param list : list to sort
     */
    public static <T extends Comparable<? super T>> void sort(final List<T> list) {
        T min;
        int indexMin;

        for (int i = 0; i < list.size() - 1; i++) {
            min = list.get(i);
            indexMin = i;
            for (int j = i + 1; j < list.size(); j++) {

                T element = list.get(j);

                if (min.compareTo(element) > 0) {
                    min = element;
                    indexMin = j;
                }
            }

            list.set(indexMin, list.get(i));
            list.set(i, min);
        }
    }
}
