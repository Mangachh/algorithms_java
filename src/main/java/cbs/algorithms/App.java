package cbs.algorithms;


import cbs.algorithms.collections.*;

public class App 
{
    public static void main( String[] args )
    {
        //checkDynamicArray();
        checkList();
        
    }

    private static void checkDynamicArray(){
        DynamicArray<String> array = new DynamicArray<>(3);
        array.putAt(0, "Hola");
        array.putAt(1, "Adiós");
        array.putAt(2, "Pepote");

        array.pushAt(1, "new string");

        for(String s: array){
            System.out.println(s);
        }
    }

    private static void checkList(){
        List<String> list = new List<>(30);
        list.add("Hola");
        list.add("Adiós");
        list.add("Pepote");
        list.add("Hola");

        list.pushAt(2, "New string");
        System.out.println("Push AT!!!");
        for(String s: list){
            System.out.println(s);
        }
        System.out.println("Count: " + list.getCount());

        list.removeAt(1);
        System.out.println("\nRemoved index 1");
        for(String s: list){
            System.out.println(s);
        }
        System.out.println("Count: " + list.getCount());

        list.remove("Hola");
        System.out.println("\nRemoved first <<Hola>>");
        for(String s: list){
            System.out.println(s);
        }

        list.removeAll("Hola");
        System.out.println("\nRemoved all <<Hola>>");
        for(String s: list){
            System.out.println(s);
        }
        System.out.println("Count: " + list.getCount());

        list.clear();
        System.out.println("\nCleared the list");
        for(String s: list){
            System.out.println(s);
        }
        System.out.println("Count: " + list.getCount());
    }

    
}
