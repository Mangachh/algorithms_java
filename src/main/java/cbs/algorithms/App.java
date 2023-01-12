package cbs.algorithms;


import cbs.data.collections.*;

public class App 
{
    public static void main( String[] args )
    {
        //checkDynamicArray();
        //checkList();
        //checkSLinked();
        checkLinked();
        
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
        list.insertHead("-1");
        System.out.println(list.toString());

        System.out.println("\nsCheck iterator");
        for(String s: list){
            System.out.println(s);
        }

        list.remove("3");
        System.out.println("\nRemove 3, no index");
        System.out.println(list);        

        System.out.println("\nTo Array");
        String[] test = list.toArray(String.class);
        System.out.println(test.getClass());
        for(String t : test){
            System.out.println(t);
        }

        list.clear();
        System.out.println("\nclear list (oju, count = 2");
        System.out.println(list.getCount());
        System.out.println(list.toString());
    }

    private static void checkLinked(){
        LinkedList<String> list = new LinkedList<>();
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

        System.out.println("\nsCheck iterator");
        for(String s: list){
            System.out.println(s);
        }

        list.putAt(2, "1");
        System.out.println("\nWrite 1 on index 2");
        System.out.println(list);
        System.out.println(list.getCount());

        list.pushAt(3, "3");
        System.out.println("\nPush 3 on index 3");
        System.out.println(list);

        list.remove("3");
        System.out.println("\nRemove 3, no index");
        System.out.println(list);

        System.out.println("\nPut two new \"Cero\" and remove both");
        list.append("Cero");
        list.pushAt(2, "Cero");
        System.out.println(list);
        list.removeAll("Cero");        
        System.out.println(list);

        System.out.println("\nClear index 0");
        list.removeAt(0);
        System.out.println(list);
        System.out.println(list.getCount());

        System.out.println("\nTo Array");
        String[] test = list.toArray(String.class);
        System.out.println(test.getClass());
        for(String t : test){
            System.out.println(t);
        }

        list.clear();
        System.out.println("\nclear list (oju, count = 2");
        System.out.println(list.getCount());
        System.out.println(list.toString());
    }

    
}
