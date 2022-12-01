package cbs.algorithms.collections;

public interface ICollection<T> extends Iterable<T> {
    
    T getAt(int index);
    void putAt(int index, final Object obj);
    boolean tryReplaceAt(int index, final Object obj);
    boolean hasNextO(int index);
    T[] toArray(Class<T> datatype);
}
