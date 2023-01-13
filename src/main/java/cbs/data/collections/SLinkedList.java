package cbs.data.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An unidirectional LinkedList
 */
public class SLinkedList<T> implements ICollection<T>, IIndexable<T> {

    /**
     * Head node of the list
     */
    private SLinkedNode<T> head;

    /**
     * Tail node of the list, to use in method {@link #append}
     */
    private SLinkedNode<T> tail;

    /**
     * Count
     */
    private int count = 0;

    /**
     * Start string for {@link #toString}
     */
    private static final String STRING_START = "[";

    /**
     * End string for {@link #toString}
     */
    private static final String STRING_END = "]";

    /**
     * Separator for the element to {@link #toString}
     */
    private static final String ELEMENT_SEPARATOR = ", ";

    /**
     * Appends and item to the tail of the list
     * 
     * @param data data to append
     */
    public void append(final T data) {
        if (this.head == null) {
            this.head = new SLinkedNode<T>(data);
            this.tail = this.head;
        } else {
            this.tail.insertAfter(data);
            this.tail = this.tail.getNext();
        }

        this.count++;
    }

    /**
     * Inserts an item directly to the head to the head
     * consider {1,2,3}
     * insertHead(0)
     * result {0,1,2,3}
     * 
     * Similar to {@link #pushAt(int, Object)} but only works with the head
     * 
     * @param data data to insert
     */
    public void insertHead(final T data) {
        SLinkedNode<T> newHead = new SLinkedNode<T>(data);
        // newHead.insertAfter(this.head);
        newHead.setNextNode(head);
        this.head = newHead;

        if (tail == null) {
            tail = head;
        }

        count++;
    }

    /**
     * Gets the count of the items in the list
     * 
     * @return items
     */
    public int getCount() {
        return this.count;
    }

    @Override
    public String toString() {
        if (this.head == null) {
            return String.format("%s%s", STRING_START, STRING_END);
        }

        StringBuilder builder = new StringBuilder();
        builder.append(STRING_START);
        // todo esto se puede hacer con recursión

        SLinkedNode<T> temp = head;
        for (int i = 0; i < this.count; i++) {
            try {
                builder.append(temp.getData().toString());
            } catch (NullPointerException e) {
                builder.append("NULL");
            }

            builder.append(ELEMENT_SEPARATOR);
            temp = temp.getNext();
        }

        builder.setLength(builder.length() - ELEMENT_SEPARATOR.length());
        builder.append(STRING_END);
        return builder.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private SLinkedNode<T> current = head;
            private int i = 0;

            @Override
            public boolean hasNext() {
                return hasNextO(i);
            }

            @Override
            public T next() {
                if (hasNext() == false) {
                    throw new NoSuchElementException();
                }
                T data = current.getData();
                current = current.getNext();
                i++;
                return data;
            }
        };
    }

    /**
     * Gets an item based on its index.
     * 
     * @param index index to look for
     */
    @Override
    public T getAt(int index) {
        if (index < 0 || index >= count || index < 0) {
            return null;
        }

        SLinkedNode<T> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        return temp.getData();
    }

    /**
     * Puts an element in the correspondent index if it exists,
     * replacing the current one.
     * 
     * If the index is longer does nothing.
     * 
     * @param index index to put the item
     * @param obj   item
     */
    @Override
    public void putAt(int index, final T obj) {
        if (index < 0 || index >= this.count) {
            return;
        }
        SLinkedNode<T> newNode = new SLinkedNode<T>(obj);

        if (index == 0) {
            if (this.head != null) {
                newNode.setNextNode(this.head.getNext());
                tail = newNode;
            }

            this.head = newNode;
            return;
        } else if (index == this.count - 1) {
            tail = newNode;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> back = this.head;
        for (int i = 1; i < index; i++) {
            back = current;
            current = current.getNext();
        }

        back.setNextNode(newNode);
        newNode.setNextNode(current.getNext());
    }

    @Override
    public boolean hasNextO(int index) {
        return index < this.count;
    }

    /**
     * Converts the list into a java array
     * 
     * @param array class
     */
    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype) {
        SLinkedNode<T> current = this.head;
        T[] obj = (T[]) java.lang.reflect.Array.newInstance(datatype, this.count);
        for (int i = 0; i < this.count; i++) {
            obj[i] = (T) current.getData();
            current = current.getNext();
        }

        return obj;
    }

    /**
     * pushes an item into the desired index moving all
     * the items one position at right.
     * This method doesn't replaces an item
     * 
     * @param index index to push to
     * @param obj object to push
     */
    @Override
    public void pushAt(int index, final T obj) {
        if (index < 0 || index >= this.count) {
            return;
        }
        if (index == 0) {
            this.insertHead(obj);
            return;
        }
        SLinkedNode<T> newNode = new SLinkedNode<T>(obj);
        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> back = this.head;
        for (int i = 1; i < index; i++) {
            back = current;
            current = current.getNext();
        }

        newNode.setNextNode(current);
        back.setNextNode(newNode);
        count++;

    }

    /**
     * Removes the object at that index, if exists
     * 
     * @param index index 
     */
    @Override
    public void removeAt(int index) {
        if (this.head == null || index >= count || index < 0) {
            return;
        }

        if (index == 0) {
            this.head = this.head.getNext();
            this.count--;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> previous = this.head;

        for (int i = 1; i < index; i++) {
            previous = current;
            current = current.getNext();
        }

        previous.setNextNode(current.getNext());
        current.setNextNode(null);
        this.count--;

        if (index == this.count) {
            this.tail = previous;
        }
    }

    
    /**
     * Removes the first coincidential object
     * 
     * @param obj object
     */
    @Override
    public void remove(T obj) {
        if (this.head == null) {
            return;
        }

        if (obj.equals(this.head.getData())) {
            this.head = this.head.getNext();
            count--;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> previous = this.head;

        for (int i = 1; i < this.count; i++) {
            if (current.getData().equals(obj)) {
                previous.setNextNode(current.getNext());
                current.setNextNode(null);
                count--;

                if (i == this.count) {
                    this.tail = previous;
                }
                return;
            }

            previous = current;
            current = current.getNext();
        }
    }


    /**
     * Removes all the concordant objects
     * 
     * @param obj
     */
    @Override
    public void removeAll(T obj) {
        if (this.head == null) {
            return;
        }

        SLinkedNode<T> current = this.head;
        SLinkedNode<T> previous = null;

        for (int i = this.count - 1; i >= 0; i--) {
            if (current.getData().equals(obj)) {
                try {
                    // if previous is null, then the node is the head,
                    // so the next one will be the new head
                    previous.setNextNode(current.getNext());
                } catch (NullPointerException e) {
                    this.head = current.getNext();
                } finally {
                    SLinkedNode<T> temp = current.getNext();
                    current.setNextNode(null);
                    current = temp;
                    count--;

                    if (i == 0) {
                        tail = previous;
                    }
                }
                continue;
            }
            previous = current;
            current = current.getNext();
        }
    }

    /**
     * Clears the list
     */
    @Override
    public void clear() {
        if (this.head == null) {
            return;
        }

        SLinkedNode<T> previous = this.head;
        SLinkedNode<T> current = this.head.getNext();
        for (int i = 1; i < this.count; i++) {
            previous.setNextNode(null);
            previous = current;
            current = current.getNext();
        }

        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    /**
     * Returns the head, null if there's none
     * @return
     */
    public T getHead() {
        try {
            return this.head.getData();
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * Returns the last item of the list, null if none
     * @return
     */
    public T getTail() {
        try {
            return this.tail.getData();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
