package cbs.algorithms;


import cbs.algorithms.collections.*;

public class App 
{
    public static void main( String[] args )
    {
        //checkDynamicArray();
        //checkList();

        checkSLinked();
        
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


    private static void checkSLinked(){
        SLinkedList<String> list = new SLinkedList<>();
        System.out.println(list.toString());
        list.append("Cero");
        list.append("Uno");
        list.append("Dos");
        list.append("Tres");
        list.append("Cuatro");

        System.out.println("\nTo String para toda la lista y el count");
        System.out.println(list.toString());
        System.out.println(list.getCount());

        System.out.println("\nPillamos el índice 2");
        System.out.println(list.getAt(2));

        System.out.println("\nPonemos -1 como cabeza de cartel");
        list.prepending("-1");
        System.out.println(list.toString());
    }

    
}
