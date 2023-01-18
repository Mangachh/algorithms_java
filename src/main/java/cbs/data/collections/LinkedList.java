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
    private static final String STRING_NULL = "null";

    
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
            try{
                builder.append(temp.getData().toString());
            }catch(NullPointerException e){
                builder.append(STRING_NULL);
            }
            
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
        if(index >= count || index < 0){
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
        if(index >= this.count || index < 0){
            return;
        }

        LinkedNode<T> newNode = new LinkedNode<>(obj);
        LinkedNode<T> current = this.getNodeAt(index);
        if(index == 0){
            this.head = newNode;
        }

        if(index == count - 1){
            this.tail = newNode;
        }
                
        try{
            current.getPrevious().insertAfter(newNode);
        }catch(NullPointerException e){

        }
        
        try{
            current.getNext().insertBefore(newNode);
        }catch(NullPointerException e){}
                
        
        
        //newNode.insertAfter(current.getNext());
        //newNode.insertBefore(current.getPrevious());

        current.setNext(null);
        current.setPrevious(null);
    }

    
    @Override
    public void pushAt(int index, T obj) {
        if(index >= this.count || index < 0){
            return;
        }

        if(index == 0){
            this.prepending(obj);
            return;
        }

        LinkedNode<T> current = this.getNodeAt(index);
        LinkedNode<T> newNode = new LinkedNode<>(obj);

        current.getPrevious().insertAfter(newNode);
        current.insertBefore(newNode);       
        this.count++;     
    }

    @Override
    public void removeAt(int index) {
        if(index >= this.count || index < 0){
            return;
        }        

        LinkedNode<T> current = this.getNodeAt(index);

        if(index == 0){
            try{
                this.head = current.getNext();
            }catch(NullPointerException e){

            }
            
        }

        if(index == this.count - 1){
            try{
                this.tail = current.getPrevious();
            }catch(NullPointerException e){

            }
            
        }
        this.removeNode(current);
        
    }


    private void removeNode(final LinkedNode<T> node){

        try{
            node.getPrevious().setNext(node.getNext());
        }catch(NullPointerException e){

        }

        try{
            node.getNext().setPrevious(node.getPrevious());
        }catch(NullPointerException e){

        }

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
        int target = this.count;
        for(int i = 0; i < target; i++){
            if(current.getData().equals(obj)){
                if(current == this.head){
                    try{
                        this.head = current.getNext();
                    }catch (NullPointerException e){
                        this.head = null;
                    }
                    
                }

                if(current == this.tail){
                    try{
                        this.tail = current.getPrevious();
                    }catch(NullPointerException e){
                        this.tail = null;
                    }
                }

                this.removeNode(current);
                //count--;                
            }

            current = current.getNext();
        }

        /* 
        if(this.head.getData().equals(obj)){
            this.head = this.head.getNext();
            count--;
        }

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
        }    */
        
    }

    public void insertHead(final T data){
        LinkedNode n = new LinkedNode<T>(data);
        head.insertAfter(n);
        n.setNext(head);
        this.head = n;

    }


    @Override
    public void clear() {
        
        if(head != null){
            LinkedNode<T> current = this.head.getNext();
            LinkedNode<T> previous = this.head;

            for(int i = 0; i < this.count-2; i++){                
                current.setPrevious(null);
                previous.setNext(null);
                current = current.getNext();                
            }

            count = 0;
            this.head = null;
            this.tail = null;
        }
        
    }

    public T getHead(){
        try{
            return head.getData();
        }catch(NullPointerException e){
            return null;
        }
    }

    public T getTail(){
        try{
            return this.tail.getData();
        }catch(NullPointerException e){
            return null;
        }
    }
    
}
