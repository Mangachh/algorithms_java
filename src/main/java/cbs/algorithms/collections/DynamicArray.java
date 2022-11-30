package cbs.algorithms.collections;


public class DynamicArray<T>  {
    
    private Object[] data;
    private int count;

    private static int DEFFAULT_CAPACITY = 10;
    
    
    public DynamicArray(int capacity){
        this.data = new Object[capacity];
    }

    public DynamicArray(Class<T> c){
        this.data = new Object[DEFFAULT_CAPACITY];
    }

    public T getAt(int index){
        //@SuppressWarnings("unchecked")
        return (T)this.data[index];
    }

    public void putAt(int index, Object data){
        this.data[index] = data;
    }


}
