package cbs.data.collections.trees;

import java.util.function.Consumer;
import java.util.function.Function;

public class BasicTree<T> {

    private BasicNode<T> root;

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

    public BasicNode<T> getNodeByData(final T data){
        return root.getNodeByData(data);
    }

}
