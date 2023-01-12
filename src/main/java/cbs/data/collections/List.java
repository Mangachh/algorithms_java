package cbs.data.collections;


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

    @Override
    public void pushAt(int index, final T obj){
        // if index is greater, do nothing
        if(index >= this.count){
            return;
        }

        // put in variable in case the resize is needed
        int originalCount = this.count;

        // check if the last has value
        if(this.count + 1 >= this.data.length){
            this.resize();
        }

        for(int i = originalCount; i > index; i--){
            this.data[i] = this.data[i-1];
        }

        this.data[index] = obj;
        this.count++;
    }


    @Override
    public void removeAt(int index){
        for(int i = index; i < this.count - 1; i++){
            super.data[i] = super.data[i +1];
        }

        super.data[this.count - 1] = null;
        this.count--; 
    }

    @Override
    public void remove(final T obj){
        for(int i = 0; i < this.count; i++){
            if(super.data[i].equals(obj)){
                this.removeAt(i);
                return;
            }
        }
    }

    @Override
    public void removeAll(final T obj){
        for(int i = 0; i < this.count; i++){
            if(super.data[i].equals(obj)){

                // se puede optimizar.
                // de esta manera recorremos y organizamos el array
                // a cada encuentro y, dependiendo de las veces que salga
                // reordenamos demasiado, aún así, si ponemos los indexs 
                // en una lista y quitamos a partir de allí, quizás
                // podamos hacerlo más rápido
                this.removeAt(i);
            }
        }
    }    

    @Override
    public void clear(){
        for(int i = 0; i < this.count; i++){
            super.data[i] = null;
        }
        count = 0;
    }
    
}
