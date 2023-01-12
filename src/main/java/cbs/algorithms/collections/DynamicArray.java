package cbs.algorithms.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO: Documentation
public class DynamicArray<T> implements ICollection<T>{
    
    protected Object[] data;

    private static int DEFFAULT_CAPACITY = 10;
    private static int RESIZE_MULT= 2;

    private static String STR_OPEN = "[";
    private static String STR_CLOSE = "]";
    
    
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

    public void removeNulls(){
        List<Object> temp = new List<>(this.data.length);
        for(int i = 0; i < this.data.length; i++){
            if(this.data[i] != null){
                temp.add(data[i]);
                data[i] = null;
            }
        }

        this.data = temp.toArray(Object.class);
    }

    // because is dynamic, if the index is bigger than
    // the length, it resizes
    // TODO: optimize array
    @Override
    public void putAt(int index, final T data){
        if(index >= this.data.length){
            this.resize();
        }

        this.data[index] = data;
    }   

    @Override
    public void pushAt(int index, final T obj){
        int originalLength = this.data.length;        

        // 1 is the object that we push
        if(this.data[originalLength -1] != null || index >= originalLength){
            this.resize();
        }

        for(int i = originalLength; i > index; i--){
            this.data[i] = this.data[i-1];
        }

        this.data[index] = obj;
    }

    @Override
    public void removeAt(int index){
        this.data[index] = null;
    }

    @Override
    public void remove(final T obj){
        for(int i = 0; i < this.data.length; i++){
            if(this.data[i].equals(obj)){
                this.data[i] = null;
                return;
            }
        }
    }

    @Override
    public void removeAll(final T obj){
        for(int i = 0; i < this.data.length; i++){
            if(this.data[i].equals(obj)){
                this.data[i] = null;
            }
        }
    }

    @Override
    public void clear(){
        for(int i = 0; i < this.data.length; i++){
            this.data[i] = null;
        }
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


    @Override
    public String toString(){
        return "";
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

    public int getDefaultCapacity(){
        return DEFFAULT_CAPACITY;
    }
}
