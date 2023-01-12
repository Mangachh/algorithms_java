package cbs.algorithms.collections;

public class LinkedNode<T>{
    
    private T data;
    private LinkedNode<T> previous;
    private LinkedNode<T> next;

    public LinkedNode(final T data){
        this.data = data;
    }

    public LinkedNode(final T data, final LinkedNode<T> previous, final LinkedNode<T> next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    public void insertBefore(final LinkedNode<T> newNode){
        newNode.next = this;
        this.previous = newNode;
        this.next = newNode;
    }

    public void insertBefore(final T data){
        LinkedNode<T> newNode = new LinkedNode<>(data);
        this.insertBefore(newNode);
    }

    public void insertAfter(final LinkedNode<T> newNode){
        newNode.next = this.next;
        newNode.previous = this;
        this.next = newNode;        
    }

    public void insertAfter(final T data){
        LinkedNode<T> newNode = new LinkedNode<>(data);
        this.insertAfter(newNode);
    }



    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public LinkedNode<T> getPrevious() {
        return this.previous;
    }
    public void setPrevious(LinkedNode<T> previous) {
        this.previous = previous;
    }
    public LinkedNode<T> getNext() {
        return next;
    }
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }   

}
