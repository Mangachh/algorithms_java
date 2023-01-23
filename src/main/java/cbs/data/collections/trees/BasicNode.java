package cbs.data.collections.trees;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class BasicNode<T> {

    private T data;
    // we use java collections
    private BasicNode<T> parent;
    private ArrayList<BasicNode<T>> children;

    public BasicNode(final T data, final BasicNode<T> parent){
        this.data = data;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public BasicNode(final T data){
        this.data = data;
        this.children = new ArrayList<>();
    }

    public void addChild(final BasicNode<T> child){
        this.children.add(child);
        child.setParent(this);
    }

    public void addChild(final T data){
        this.children.add(new BasicNode<T>(data, this));
    }

    public void removeChildren(final BasicNode<T> toRemove){
        this.children.removeIf(node -> node.equals(toRemove));
    }

    public void removeChildren(final T data){
        for(int i = this.children.size() - 1; i > 0; i--){
            if(this.children.get(i).getData().equals(data)){
                this.children.get(i).destroy();
                this.children.remove(i);
            }
        }
    }

    // nullify everything
    public void destroy(){
        for(int i = 0; i < this.children.size(); i++){
            this.children.get(i).destroy();
        }
        this.parent = null;
        this.data = null;
        this.children.clear();
    }

    public T getData(){
        return this.data;
    }

    public void setData(final T data){
        this.data = data;
    }

    public void setParent(final BasicNode<T> newParent){
        this.parent = newParent;
    }

    public int count(){
        int count = 1;
        for(int i = 0; i < this.children.size(); i++){
            count += this.children.get(i).count();
        }

        return count;
    }

    public void preOrder(Consumer<BasicNode<T>> func){
        func.accept(this);
        for(int i = 0; i < this.children.size(); i++){
            this.children.get(i).preOrder(func);
        }
    }

    public void postOrder(Consumer<BasicNode<T>> func){        
        for(int i = 0; i < this.children.size(); i++){
            this.children.get(i).preOrder(func);
        }
        func.accept(this);
    }

    public BasicNode<T> getNodeByData(final T data){
        if(this.getData().equals(data)){
            return this;
        }

        BasicNode<T> temp = null;
        for(int i = 0; i < this.children.size(); i++){
            temp = this.children.get(i).getNodeByData(data);
            if(temp != null){
                return temp;
            }
        }

        return null;
    }
}
