package cbs.algorithms.collections;

/**
 * An interface for all the Collections. 
 * Extends from {@link Iterable<T>}
 * 
 */
public interface ICollection<T> extends Iterable<T> {
    
    // getAt, pushAt, putAt & removeAt -> diferent interface
    // unordered collections like hash doesn't need them

    @Deprecated
    T getAt(int index);

    @Deprecated
    void putAt(int index, final T obj);

    @Deprecated
    void pushAt(int index, final T obj);
    @Deprecated
    void removeAt(int index);


    boolean hasNextO(int index);
    T[] toArray(Class<T> datatype);
    
    void remove(final T obj);
    void removeAll(final T obj);
    void clear();
}
