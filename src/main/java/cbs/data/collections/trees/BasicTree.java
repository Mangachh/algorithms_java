package cbs.data.collections.trees;

import java.util.function.Consumer;

public class BasicTree<T> {

    protected BasicNode<T> root;

    public int count() {
        return this.root.count();
    }

    public void setRoot(final T newRoot){
        this.root = new BasicNode<T>(newRoot, null);
    }

    public void setRoot(final BasicNode<T> newRoot){
        this.root = newRoot;
    }

    public T getRoot(){
        return this.root.getData();
    }

    public void preOrder(Consumer<BasicNode<T>> func) {
        this.root.preOrder(func);
    }

    public void postOrder(Consumer<BasicNode<T>> func){
        this.root.postOrder(func);
    }

    public BasicNode<T> getNodeByData(final T data){
        return root.getNodeByData(data);
    }

}
