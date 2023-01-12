package cbs.algorithms.collections;

/**
 * An interface for the collections that 
 * allow add and remove from index
 */
public interface IIndexable<T>{
    T getAt(int index);
    void putAt(int index, final T obj);
    void pushAt(int index, final T obj);
    void removeAt(int index);
}
