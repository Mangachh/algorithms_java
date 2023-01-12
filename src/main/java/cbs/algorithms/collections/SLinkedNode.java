package cbs.algorithms.collections;

/**
 * Nodes for the {@link SLinkedList} class.
 * Because it is a simple node it only has a reference for the next node (or child node)
 */
public class SLinkedNode<T> {
    
    /**
     * Data attached to the node
     */
    private T data;

    /**
     * Next node, child
     */
    private SLinkedNode<T> next;

    /**
     * constructor
     * @param data data to attach to the node
     */
    public SLinkedNode(final T data){
        this.data = data;
    }

    /**
     * Returns the next node (child node)
     * @return next node, null if not exists
     */
    public SLinkedNode<T> getNext(){
        return next;
    }

    /**
     * Insterts a node after this node
     * @param newNode new node to insert
     */
    public void insertAfter(final SLinkedNode<T> newNode){
        newNode.next = this.next;
        this.next = newNode;        
    }

    /**
     * Creates and inserts a node after this one
     * @param data data to attach to the new node
     */
    public void insertAfter(final T data){
        SLinkedNode<T> newNode = new SLinkedNode<>(data);
        newNode.next = this.next;
        this.next = newNode;
    }

    /**
     * Set's the next node
     * @param node next node
     * @deprecated
     */
    public void setNextNode(final SLinkedNode<T> node){
        this.next = node;
    }

    /**
     * Returns the data attached to this node
     * @return this data
     */
    public T getData(){
        return this.data;
    }

    /**
     * Sets the data attached to this node
     * @param data
     */
    public void setData(final T data){
        this.data = data;
    }

    
}
