package cbs.algorithms.collections;

public class List<T> extends DynamicArray<T>{


    private int count;

    public List() {
        super();        
    }

    public List(int capacity){
        super(capacity);
    }
    {
        this.count = 0;
    }

    public void add(final Object data){
        if(this.count >= super.data.length){
            super.resize();
        }

        super.data[this.count] = data;
        this.count++;

        
    }

    public List<T> getReversedList(){
        List<T> temp = new List<>(this.count);

        for(int i = this.count - 1; i >= 0; i-- ){
            temp.add(super.data[i]);
        }

        return temp;
    }

    // oju! ugly
    public void reverse(){
        this.data = this.getReversedList().data;
    }

    public int getCount(){
        return this.count;
    }

    @Override
    protected boolean hasNextO(int index){
        return index < count;
    }
    
    
}
