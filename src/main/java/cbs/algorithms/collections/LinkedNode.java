package cbs.algorithms.collections;

/**
 * Node for the {@link LinkedList}
 * Has reference for the next node (child) and the previous (parent)
 */
public class LinkedNode<T>{
    
    /**
     * Data attached to this node
     */
    private T data;


    /**
     * Previous node (parent)
     */
    private LinkedNode<T> previous;

    /**
     * Next node (child)
     */
    private LinkedNode<T> next;

    /**
     * Simple Constructor
     * Only creates the node
     * @param data data to attach
     */
    public LinkedNode(final T data){
        this.data = data;
    }

    /**
     * Constructor that creates the node and assign the previous and next node
     * @param data data to attach
     * @param previous previous node (parent)
     * @param next next node (child)
     */
    public LinkedNode(final T data, final LinkedNode<T> previous, final LinkedNode<T> next){
        this.data = data;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Inserts a node before this one. It handles the newNode.next to
     * be this node
     * @param newNode node to attach to previous
     */
    public void insertBefore(final LinkedNode<T> newNode){
        newNode.next = this;
        newNode.previous = this.previous;
        this.previous = newNode;
    }

    /**
     * Creates and inserts a node before this one. It handles the newNode.next to
     * be this node
     * @param data
     */
    public void insertBefore(final T data){
        LinkedNode<T> newNode = new LinkedNode<>(data);
        this.insertBefore(newNode);
    }

    /**
     * Inserts a node after this one. It handles the newNode.previous to
     * be this node
     * @param newNode node to attach to previous
     */
    public void insertAfter(final LinkedNode<T> newNode){
        newNode.next = this.next;
        newNode.previous = this;
        this.next = newNode;        
    }

    /**
     * Creates and inserts a node after this one. It handles the newNode.previous to
     * be this node
     * @param data
     */
    public void insertAfter(final T data){
        LinkedNode<T> newNode = new LinkedNode<>(data);
        this.insertAfter(newNode);
    }


    /**
     * Returns the data attached to the node
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data to this node
     * @param data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Returns the previous (parent) node
     * @return
     */
    public LinkedNode<T> getPrevious() {
        return this.previous;
    }

    /**
     * Set's the previous node and handles the previous.setNext 
     * to this node. 
     * TODO: with insertAfter-insertBefore methods don't think i need this
     * @param previous
     * @deprecated
     */
    public void setPrevious(LinkedNode<T> previous) {
        this.previous = previous;
        previous.setNext(this);
    }

    /**
     * Gets the next node (child)
     * @return next node, null if not exist
     */
    public LinkedNode<T> getNext() {
        return next;
    }

    /**
     * @deprecated
     * @param next
     */
    public void setNext(LinkedNode<T> next) {
        this.next = next;
    }   

}
