package cbs.data.collections.trees;

import java.util.function.Consumer;

public class BinaryNode<T>{
    private T data;

    private BinaryNode<T> parent;
    private BinaryNode<T> left;
    private BinaryNode<T> rigth;

    public BinaryNode(final T data){
        this.data = data;
    }        

    public int count(){
        int count = 1;

        if(left != null){
            count += left.count();
        }

        if(rigth != null){
            count += rigth.count();
        }

        return count;
    }

    public void preOrder(Consumer<BinaryNode<T>> func){
        func.accept(this);

        if(this.left != null){
            this.left.preOrder(func);
        }

        if(this.rigth != null){
            this.rigth.preOrder(func);
        }
    }

    public void postOrder(Consumer<BinaryNode<T>> func){
        
        if(this.left != null){
            this.left.postOrder(func);
        }

        if(this.rigth != null){
            this.rigth.postOrder(func);
        }
        func.accept(this);
    }

    public void inOrder(Consumer<BinaryNode<T>> func){
        if(this.left != null){
            this.left.inOrder(func);
        }

        func.accept(this);

        if(this.rigth != null){
            this.rigth.inOrder(func);
        }        
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public void setRigth(BinaryNode<T> rigth) {
        this.rigth = rigth;
    }

    public T getData() {
        return data;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public BinaryNode<T> getRigth() {
        return rigth;
    }
}
