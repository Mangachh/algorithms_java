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
        List<String> list = new List<>(100);
        list.add("Hola");
        list.add("Adiós");
        list.add("Enga ya");

        for(String s: list){
            if(s != null){
                System.out.println(s);
            }else{
                System.out.println("null");
            }
        }
        
    }
}
