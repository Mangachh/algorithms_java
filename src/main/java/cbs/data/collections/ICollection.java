package cbs.data.collections;

/**
 * An interface for all the Collections. 
 * Extends from {@link Iterable<T>}
 * 
 */
public interface ICollection<T> extends Iterable<T> {
    
    
    /**
     * Has next value? Used for the iteration
     * @param index index to chec
     * @return has next?
     */
    boolean hasNextO(int index);

    /**
     * Creates a java array with the items of the collection
     * @param datatype type of the item
     * @return array
     */
    T[] toArray(Class<T> datatype);
    
    /**
     * Removes the firs iteration of the object, if exist
     * @param obj
     */
    void remove(final T obj);

    /**
     * Removes all the concordant objects, if exist
     * @param obj
     */
    void removeAll(final T obj);

    /**
     * Clears the collection
     */
    void clear();
}
