package cbs.algorithms.collections;


public class List<T> extends DynamicArray<T>{


    private int count;

    public List() {
        super();        
    }

    public List(int capacity){
        super(capacity);
    }

    public List(final DynamicArray<T> array){
        super.data = new Object[array.getCapacity()];
        this.copyArray(array.data);
    }

    public List(final List<T> list){
        super.data = new Object[list.count];
        this.copyArray(list.data);
    }


    public void copyArray(final Object[] array){
        for(int i = 0; i < array.length; i++){
            super.data[i] = array[i];            
        }

        this.count = array.length;
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
    public boolean hasNextO(int index){
        return index < count;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T[] toArray(Class<T> datatype){
        
        T[] obj = (T[])java.lang.reflect.Array.newInstance(datatype, this.count);
        for(int i = 0; i < this.count; i++){
            obj[i] = (T)super.data[i];
        }

        return obj;
    }
    
    
}
