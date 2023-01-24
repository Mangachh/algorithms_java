package cbs.data.collections.trees;

import java.util.function.Consumer;

public class BinaryTree<T> {

    private BinaryNode<T> root;

    public void setRoot(final BinaryNode<T> newRoot){
        this.root = newRoot;
    }

    public int count(){
        return root.count();
    }

    public void preOrder(Consumer<BinaryNode<T>> func){
        this.root.preOrder(func);
    }

    public void postOrder(Consumer<BinaryNode<T>> func){
        this.root.postOrder(func);
    }

    public void inOrder(Consumer<BinaryNode<T>> func){
        this.root.inOrder(func);
    }
    
}
