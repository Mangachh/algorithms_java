package cbs.data.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

// TODO: Documentation
/*
 * A Dynamic Array.
 * It resizes automatically when needed. Also the class has methods to resize it.
 * It works like and array, so if nothing is in the selected index, then returns null.
 */
public class DynamicArray<T> implements ICollection<T>, IIndexable<T>{
    
    /**
     * The data array
     */
    protected Object[] data;

    /**
     * Default Capacity of the Dynamic Array
     */
    private final static int DEFFAULT_CAPACITY = 10;

    /**
     * Resize multiplicator. 
     * new Length = data.length * RESIZE_MULT
     */
    private final static int RESIZE_MULT= 2;

    /*
     * Open key for the toString Method.
     */
    private final static String STR_OPEN = "[";

    /*
     * Close key for the toString Method.
     */
    private final static String STR_CLOSE = "]";

    /**
     * Separator between values.
     */
    private final static String STR_SEPARATOR = ", ";

    /**
     * Printed when a position is null
     */
    private final static String STR_NULL = "NULL";
    
    /**
     * Constructor. It let's choose a custom capacity to begin with. 
     * If capacity <= 0, then uses {@link DEFFAULT_CAPACITY}
     * @param capacity Initial capacity
     */
    public DynamicArray(int capacity){
        if(capacity <= 0){
            capacity = DEFFAULT_CAPACITY;
        }
        
        this.data = new Object[capacity];
    }

    /**
     * Constructor. Uses {@link DEFFAULT_CAPACITY} as initial capacity
     */
    public DynamicArray(){
        this.data = new Object[DEFFAULT_CAPACITY];
    }

    
    /**
     * Gets a value in the selected index. 
     * If there's no value, returns null.
     * If index is bigger than the capacity of the array,
     * then throws an exception.
     * 
     * TODO: maybe return null if no index?     * 
     * @return object if exist, null if not
     */
    @SuppressWarnings("unchecked")
    public T getAt(int index){                
        return (T)this.data[index];
    }

    /**
     * Remove all the nulls from the array at resizes it at 
     * the minimum possible.
     * So, if the array is {2, null, null, 5, null, null}
     * it converst to an array of {2, 5}
     */
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
    /**
     * Puts a value into the selected index.
     * If the index is bigger than the capacity of the array, 
     * the array resizes at capacity = index
     * @param index index to put the value in
     * @param data the data to put 
     */
    @Override
    public void putAt(int index, final T data){
        // just in case?
        if(index < 0){
            return;
        }
        if(index >= this.data.length){
            this.resize(index + 1);
        }

        this.data[index] = data;
    }   

    /**
     * Pushes a value to the selected index
     * and move all the other values one position
     * right the array.
     * So, in the array {1, 2, 3, 4}
     * if we push the value 69 to the index 2
     * the resultant array will be
     * {1, 2, 69, 3, 4}
     * 
     * @param index the index to push to
     * @param obj the object to push at
     */
    @Override
    public void pushAt(int index, final T obj){

        if(index < 0){
            return;
        }

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

    /**
     * Removes an objec, if exists, from the 
     * selected index.
     * If other objects exists beyond the first encounter, they will remain
     * in there.
     * Removing = null
     * @param index index to remove from 
     */
    @Override
    public void removeAt(int index){

        if(index < 0 || index >= this.data.length){
            return;
        }

        this.data[index] = null;
    }

    /**
     * Removes the first object found, if exists
     * If other objects exists beyond the first encounter, they will remain
     * in there.
     * Removing = null
     * @param obj object to remove
     */
    @Override
    public void remove(final T obj){
        for(int i = 0; i < this.data.length; i++){
            if(this.data[i].equals(obj)){
                this.data[i] = null;
                return;
            }
        }
    }

    /**
     * Removes all the objects found in the array.
     * Removing = null
     * @param index index to remove from 
     */
    @Override
    public void removeAll(final T obj){
        for(int i = 0; i < this.data.length; i++){
            if(this.data[i].equals(obj)){
                this.data[i] = null;
            }
        }
    }

    /**
     * Clears the array maintaining the capacity.
     * Each position = null
     */
    @Override
    public void clear(){
        for(int i = 0; i < this.data.length; i++){
            this.data[i] = null;
        }
    }

    /**
     * Resizes the array based on:
     * {@link array.getCapacity()} * {@link RESIZE_MUL}
     */
    public void resize(){
        this.resize(this.data.length * RESIZE_MULT);
    }

    /**
     * Resizes the array based on a custom capacity.
     * If the new capacity is bigger than the capacity of the array,
     * it will exapand it. 
     * If the capacity is smaller, then 
     * it will shrink removing the values.
     * before: 
     *      {4, 7, 12, 1, 5, 4}
     * resize(4);
     * after:
     *      {4, 7, 12, 1} 
     */
    public void resize(int capacity){

        if(capacity <= 0 || capacity == this.data.length){
            return;
        }

        Object[] newO = new Object[capacity];
        int limit;
        
        if(capacity > this.data.length){
            limit = this.data.length;
        }else{
            limit = capacity;
        }

        for(int i = 0; i < limit; i++){
            newO[i] = this.data[i];
        }
        

        this.data = newO;
    }

    /**
     * Returns the capacity of the array
     * @return capacity
     */
    public int getCapacity(){
        return this.data.length;
    }

    /**
     * Iterator method. 
     * Creates a nuew Iterator and works with
     * "HasNextO" method
     */
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

    /**
     * Simple method for the {@link DynamicArray.iterator}
     * If the index is less than the capacity of the array, returns
     * true, 
     * else it returns false
     * @return is the index smaller than the array capacity?
     */
    @Override
    public boolean hasNextO(int index){
        return (index < data.length);
    }

    /**
     * convert the array into string.
     * The string starts with {@link STR_OPEN} and ends with {@link STR_CLOSE}.
     * Each value is separated with {@link STR_SEPARATOR}
     * If a position doesn't have an object, then {@link STR_NULL} is passed as string
     * [value, value, value, NULL, value]
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();

        builder.append(STR_OPEN);
        for(int i = 0; i < this.data.length; i++){
            if(this.data != null){
                builder.append(this.data[i].toString());
            }else{
                builder.append(STR_NULL);
            }
            builder.append(STR_SEPARATOR);
        }

        builder.replace(builder.length() - STR_SEPARATOR.length(), builder.length(), STR_CLOSE);

        return builder.toString();
    }

    /**
     * Turns this DynamicArray into a Java array
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype){
        
        T[] obj = (T[])java.lang.reflect.Array.newInstance(datatype, this.data.length);
        for(int i = 0; i < this.data.length; i++){
            obj[i] = (T)this.data[i];
        }

        return obj;
    }

    /**
     * Gets the default capacity of the array
     * @return
     */
    public static int getDefaultCapacity(){
        return DEFFAULT_CAPACITY;
    }
}
