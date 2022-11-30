package cbs.algorithms;


import cbs.algorithms.collections.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        DynamicArray<String> list = new DynamicArray<String>(20);
        list.putAt(0, "hola");
        System.out.println(list.getAt(0));
        
    }
}
