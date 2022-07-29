package cbs.algorithms;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cbs.algorithms.sort.BubbleSort;
import cbs.algorithms.sort.SelectionSort;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        List<Integer> list = Arrays.asList(2, 5, 6, 7, 8, 9, 6, 1, 7);

        System.out.println(list.toString());
        System.out.println("Sorteando: ");
        BubbleSort.sort(list);
        SelectionSort.sort(list);
        System.out.println(list.toString());
        
    }
}
