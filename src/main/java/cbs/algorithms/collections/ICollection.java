package cbs.algorithms.collections;

/**
 * An interface for all the Collections. 
 * Extends from {@link Iterable<T>}
 * 
 */
public interface ICollection<T> extends Iterable<T> {
    
    // getAt, pushAt, putAt & removeAt -> diferent interface
    // unordered collections like hash doesn't need them

    boolean hasNextO(int index);
    T[] toArray(Class<T> datatype);
    
    void remove(final T obj);
    void removeAll(final T obj);
    void clear();
}
