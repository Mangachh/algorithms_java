package cbs.algorithms.sort;

import java.util.List;

/**
 
 */
public class BubbleSort {

    /**
     * The buble sort algorithm pushes compare each element with the element in its right and,
     * if it's bigger, swaps them.
     * So, in each itineration, the objective is to push the bigger element to the end of the array - the number of itineration.
     * In first itineration, we put the biggest element in the last position. (size - 1)
     * In second itineration, we put the biggest one remaingin in the last previos position (size- 2)
     * In third itineration, we put the biggest one remaining in the last previous previous position (size - 3)
     * 
     * Pseudo
     * bubbleSort(array, size)
     *      for unsortedIndex in range(size - 1):
     *          for element in range(size - 1 - unsortedIndex) 
     *              if element > rightElement
     *      swap leftElement and rightElement
     * end bubbleSort
     * 
     * @param <T> : Class that extend Comparable
     * @param list : list to sort
     */
    public static <T extends Comparable<? super T>> void sort(final List<T> list) {

        int limit = list.size();

        for (int i = 0; i < limit - 1; i++) {
            for (int j = 0; j < limit - i - 1; j++) {
                T firstElement = list.get(j);
                T lastElement = list.get(j + 1);

                if (lastElement.compareTo((T) firstElement) < 0) {
                    swap(list, firstElement, lastElement, j);
                }
            }
        }
    }

    private static <T> void swap(List<T> list, T firstElement, T lastElement, int index) {
        list.set(index, lastElement);
        list.set(index + 1, firstElement);
    }

}
