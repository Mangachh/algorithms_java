package cbs.algorithms.collections;

public class SLinkedNode<T> {
    
    private T data;
    private SLinkedNode<T> next;

    public SLinkedNode(final T data){
        this.data = data;
    }

    public SLinkedNode<T> getNext(){
        return next;
    }

    
    public void insertAfter(final SLinkedNode<T> newNode){
        newNode.next = this.next;
        this.next = newNode;        
    }

    public void insertAfter(final T data){
        SLinkedNode<T> newNode = new SLinkedNode<>(data);
        newNode.next = this.next;
        this.next = newNode;
    }

    public void setNextNode(final SLinkedNode<T> node){
        this.next = node;
    }

    public T getData(){
        return this.data;
    }

    
}
