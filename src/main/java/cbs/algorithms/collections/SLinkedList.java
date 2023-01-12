package cbs.algorithms.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLinkedList<T> implements ICollection<T>, IIndexable<T>{

    private SLinkedNode<T> head;
    private SLinkedNode<T> tail;
    private int count = 0;

    private static final String STRING_START = "[";
    private static final String STRING_END = "]";
    private static final String ELEMENT_SEPARATOR = ", ";


    public void append(final T data){
        if(this.head == null){
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
        newHead.insertAfter(this.head);
        //newHead.setNextNode(head);
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
            return String.format("%s%s", STRING_START, STRING_END);
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
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private SLinkedNode<T> current = head;     
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
             T data = current.getData();
             current = current.getNext();
             i++;
             return data;
            }
         };
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
        if(index >= this.count){
            return;
        }
        SLinkedNode<T> newNode = new SLinkedNode<T>(obj);

        if(index == 0){            
            try{
                newNode.setNextNode(this.head.getNext());
            }catch(NullPointerException e){

            }
            
            this.head = newNode;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> back = this.head;
        for(int i = 1; i < index; i++){
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

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype) {
        SLinkedNode<T> current = this.head;
        T[] obj = (T[])java.lang.reflect.Array.newInstance(datatype, this.count);
        for(int i = 0; i < this.count; i++){
            obj[i] = (T)current.getData();
            current = current.getNext();
        }

        return obj;
    }    
    

    @Override
    public void pushAt(int index, final T obj) {
        if(index == 0){
            this.prepending(obj);
            return;
        }
        SLinkedNode<T> newNode = new SLinkedNode<T>(obj);
        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> back = this.head;
        for(int i = 1; i < index; i++){
            back = current;
            current = current.getNext();
        }

        newNode.setNextNode(current);
        back.setNextNode(newNode);
        count++;
        
    }


    @Override
    public void removeAt(int index) {
        if(this.head == null){
            return;
        }

        if(index == 0){
            this.head = this.head.getNext();
            this.count--;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> previous = this.head;

        for(int i = 1; i < index; i++){
            if(i == index){
                previous.setNextNode(current.getNext());
                current.setNextNode(null);
                count--;
                return;
            }

            previous = current;
            current = current.getNext();
        }
        
    }
    @Override
    public void remove(T obj) {
        if(this.head == null){
            return;
        }

        if(obj.equals(this.head.getData())){
            this.head = this.head.getNext();
            count--;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> previous = this.head;

        for(int i = 1; i < this.count; i++){
            if(current.getData().equals(obj)){
                previous.setNextNode(current.getNext());
                current.setNextNode(null);
                count--;
                if(i == this.count -1){
                    this.tail = previous;
                }
                return;
            }

            previous = current;
            current = current.getNext();
        }        
    }

    @Override
    public void removeAll(T obj) {
        if(this.head == null){
            return;
        }

        if(obj.equals(this.head.getData())){
            this.head = this.head.getNext();
            count--;
            return;
        }

        SLinkedNode<T> current = this.head.getNext();
        SLinkedNode<T> previous = this.head;

        for(int i = this.count - 1; i > 0; i--){
            if(current.getData().equals(obj)){
                previous.setNextNode(current.getNext());
                count--;                
                current = current.getNext();
                continue;
            }

            previous = current;
            current = current.getNext();
        }    
        
    }
    @Override
    public void clear() {
        if(this.head == null){
            return;
        }

        SLinkedNode<T> previous = this.head;
        SLinkedNode<T> current = this.head.getNext();
        for(int i = 1; i < this.count; i++){
            previous.setNextNode(null);
            previous = current;
            current = current.getNext();                
        }

        this.head = null;
        this.tail = null;
        this.count = 0;      
    }
    
}
