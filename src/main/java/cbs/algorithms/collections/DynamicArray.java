package cbs.algorithms.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<T> implements ICollection<T>{
    
    protected Object[] data;

    private static int DEFFAULT_CAPACITY = 10;
    private static int RESIZE_MULT= 2;
    
    
    public DynamicArray(int capacity){
        this.data = new Object[capacity];
    }

    public DynamicArray(){
        this.data = new Object[DEFFAULT_CAPACITY];
    }

    // get and put

    @SuppressWarnings("unchecked")
    public T getAt(int index){        
        return (T)this.data[index];
    }

    @Override
    public void putAt(int index, final Object data){
        this.data[index] = data;
    }

    @Override
    public boolean tryReplaceAt(int index, final Object data){
        if (index < this.data.length){
            this.data[index] = data;
            return true;
        }

        return false;
    }

    public void pushAt(int index){
        // pone en el index y empuja lo demás
        // chequeamos si el último tiene valor, si es
        // así entoces hacemos un resize
    }

    public void resize(){
        Object[] newO = new Object[this.data.length * RESIZE_MULT];

        for(int i = 0; i < this.data.length; i++){
            newO[i] = this.data[i];
        }

        this.data = newO;
    }

    public int getCapacity(){
        return this.data.length;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {
           private int i = 0;

           @Override
           public boolean hasNext(){
                return hasNextO(i);
           }

           @Override
           public T next(){
            if (hasNext() == false){
                throw new NoSuchElementException();
            }

            return (T)data[i++];
           }
        };
    }

    @Override
    public boolean hasNextO(int index){
        return (index < data.length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype){
        
        T[] obj = (T[])java.lang.reflect.Array.newInstance(datatype, this.data.length);
        for(int i = 0; i < this.data.length; i++){
            obj[i] = (T)this.data[i];
        }

        return obj;
    }
}
