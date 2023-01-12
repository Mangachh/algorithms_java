package cbs.algorithms.collections;

public interface ICollection<T> extends Iterable<T> {
    
    // getAt, pushAt, putAt & removeAt -> diferent interface
    // unordered collections like hash doesn't need them
    T getAt(int index);
    void putAt(int index, final T obj);
    boolean hasNextO(int index);
    T[] toArray(Class<T> datatype);
    void pushAt(int index, final T obj);
    void removeAt(int index);
    void remove(final T obj);
    void removeAll(final T obj);
    void clear();
}
