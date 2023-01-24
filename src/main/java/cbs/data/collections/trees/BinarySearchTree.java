package cbs.data.collections.trees;


public class BinarySearchTree<T extends Comparable<T>> {

    private T data;
    
    private BinarySearchTree<T> parent;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> rigth;
    

    public BinarySearchTree(final T data){
        this.data = data;
    }

    public void insert(final T newData){
        BinarySearchTree<T> newNode = new BinarySearchTree<T>(newData);

        if(this.data.compareTo(newData) < 1){
        }
    }


}
