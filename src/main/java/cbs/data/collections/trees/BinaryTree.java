package cbs.data.collections.trees;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public void setRoot(final BinaryNode<T> newRoot){
        this.root = newRoot;
    }

    public int count(){
        return root.count();
    }

    
    
}
