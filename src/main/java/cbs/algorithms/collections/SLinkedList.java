package cbs.algorithms.collections;

import java.util.Iterator;

public class SLinkedList<T> implements ICollection<T>{

    private SLinkedNode<T> head;
    private SLinkedNode<T> tail;
    private int count = 0;

    private static final String STRING_START = "[";
    private static final String STRING_END = "]";
    private static final String ELEMENT_SEPARATOR = ", ";


    public void append(final T data){
        if(head == null){
            this.head = new SLinkedNode<T>(data);
            this.tail = this.head;
        }else{
            this.tail.insertAfter(data);
            this.tail = this.tail.getNext();
        }
        this.count++;
    }

    public void prepending(final T data){
        SLinkedNode<T> newHead = new SLinkedNode<T>(data);
        newHead.setNextNode(head);
        this.head = newHead;

        if(tail == null){
            tail = head;
        }

        count++;
    }

    public int getCount(){
        return this.count;
    }

    @Override
    public String toString(){
        if(this.head == null){
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(STRING_START);
        // todo esto se puede hacer con recursión
        
        SLinkedNode<T> temp = head;
        for(int i = 0; i < this.count; i++){
            builder.append(temp.getData().toString());
            builder.append(ELEMENT_SEPARATOR);
            temp = temp.getNext();
        }

        builder.setLength(builder.length() - ELEMENT_SEPARATOR.length());
        builder.append( STRING_END);        
        return builder.toString();
    }

    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public T getAt(int index) {
        if(index > count){
            return null;
        }
        
        SLinkedNode<T> temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.getNext();
        }

        return temp.getData();
    }



    @Override
    public void putAt(int index, final T obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public boolean tryReplaceAt(int index, final T obj) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public boolean hasNextO(int index) {
        // TODO Auto-generated method stub
        return false;
    }
    @Override
    public T[] toArray(Class<T> datatype) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void pushAt(int index, final T obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void removeAt(int index) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void remove(Object obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void removeAll(Object obj) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }



    
}
