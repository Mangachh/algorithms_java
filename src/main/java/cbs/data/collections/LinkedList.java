package cbs.data.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements ICollection<T>, IIndexable<T>{
    
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int count = 0;
    

    private static final String STRING_START = "[";
    private static final String STRING_END = "]";
    private static final String ELEMENT_SEPARATOR = ", ";

    
    public void append(final T data){
        if(this.head == null){
            this.head = new LinkedNode<>(data);
            this.tail = this.head;
        }else{
            this.tail.insertAfter(data);
            this.tail = this.tail.getNext();
        }

        this.count++;
    }

    public void prepending(final T data){
        LinkedNode<T> newHead = new LinkedNode<T>(data);
        if(this.head != null){
            this.head.insertBefore(newHead);
            /*
            newHead.setNext(this.head);
            this.head.setPrevious(newHead);*/
        }

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
        
        LinkedNode<T> temp = this.head;
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
            private LinkedNode<T> current = head;     
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

    
    public boolean hasNextO(int index) {
        return index < this.count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype) {
        LinkedNode<T> current = this.head;
        T[] obj = (T[])java.lang.reflect.Array.newInstance(datatype, this.count);
        for(int i = 0; i < this.count; i++){
            obj[i] = (T)current.getData();
            current = current.getNext();
        }

        return obj;
    }

    @Override
    public T getAt(int index) {
        if(index > count){
            return null;
        }

        LinkedNode<T> temp = this.getNodeAt(index);
        return temp.getData();
    }


    private LinkedNode<T> getNodeAt(int index){
        if(index >= this.count){
            return null;
        }

        LinkedNode<T> temp = null;
        
        if(index <  this.count / 2){
            temp = this.head;
            for(int i = 0; i < index; i++){
                temp = temp.getNext();
            }
        }else{
            temp = this.tail;
            for(int i = this.count - 1; i > index; i--){
                temp = temp.getPrevious();
            }
        }

        return temp;

    }
    @Override
    public void putAt(int index, final T obj) {
        if(index >= this.count){
            return;
        }

        LinkedNode<T> newNode = new LinkedNode<>(obj);
        if(index == 0){
            newNode.setNext(this.head);           

            try{
                this.head.setPrevious(newNode);
            }catch(NullPointerException e){

            }

            this.head = newNode;
            return;            
        }

        LinkedNode<T> current = this.getNodeAt(index);

        newNode.setNext(current.getNext());
        newNode.getNext().setPrevious(newNode);
        newNode.setPrevious(current.getPrevious());
        newNode.getPrevious().setNext(newNode);        
    }

    
    @Override
    public void pushAt(int index, T obj) {
        if(index == 0){
            this.prepending(obj);
            return;
        }

        LinkedNode<T> current = this.getNodeAt(index);
        LinkedNode<T> newNode = new LinkedNode<>(obj);
        LinkedNode<T> previoys = current.getPrevious();
        previoys.setNext(newNode);
        //current.getPrevious().setNext(newNode);
        newNode.setPrevious(current.getPrevious());
        current.setPrevious(newNode);
        newNode.setNext(current);
        
        this.count++;     
    }

    @Override
    public void removeAt(int index) {
        if(index >= this.count){
            return;
        }

        LinkedNode<T> current = this.getNodeAt(index);
        this.removeNode(current);
        
    }


    private void removeNode(final LinkedNode<T> node){

        try{
            node.getPrevious().setNext(node.getNext());
        }catch(NullPointerException e){

        }

        try{
            node.getNext().setPrevious(node.getPrevious());
        }catch(NullPointerException e){}

        this.count--;
    }

    @Override
    public void remove(T obj) {
        if(this.head == null){
            return;
        }
        
        LinkedNode<T> current = this.head;
        if(this.head.getData().equals(obj)){
            this.head = this.head.getNext();
            count--;
            return;
        }
        
        current = this.head.getNext();

        for(int i = 1; i < this.count; i++){
            if(current.getData().equals(obj)){
                this.removeNode(current);

                if(i == this.count - 1){
                    this.tail = this.tail.getPrevious();
                }
                return;
            }
            current = current.getNext();
        }
        
    }

    @Override
    public void removeAll(T obj) {
        if(this.head == null){
            return;
        }
        
        LinkedNode<T> current = this.head;
        if(this.head.getData().equals(obj)){
            this.head = this.head.getNext();
            count--;
        }

        // al revés, tio!
        current = this.tail;
        for(int i = this.count - 1; i > 0; i--){
            if(current.getData().equals(obj)){
                this.removeNode(current);
                this.count--;
                
                if(i == this.count - 1){
                    this.tail = this.tail.getPrevious();
                }                
            }

            current = current.getPrevious();
        }    
        
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }
    
}
