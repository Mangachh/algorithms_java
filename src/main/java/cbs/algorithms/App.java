package cbs.algorithms;


import cbs.algorithms.collections.*;

public class App 
{
    public static void main( String[] args )
    {
        List<String> list = new List<String>(5);
        list.add("Hola");
        list.add("Adiós");
        list.add("Enga ya");
        String[] array = list.toArray(String.class);

        for(String s : array){
            System.out.println(s);
        }
        
    }
}
