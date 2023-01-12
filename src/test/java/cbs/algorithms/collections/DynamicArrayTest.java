package cbs.algorithms.collections;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class DynamicArrayTest {    
    private final int CAPACITY = 5;

    private final String DATA_0 = "0";
    private final String DATA_1 = "1";
    private final String DATA_2 = "2";
    private final String DATA_3 = "3";
    private final String DATA_4 = "4";

    private final String DATA_NEW = "new String";

    private DynamicArray<String> newArray(){
        DynamicArray<String> test = new DynamicArray<>(this.CAPACITY);
        test.putAt(0, DATA_0);
        test.putAt(1, DATA_1);
        test.putAt(2, DATA_2);
        test.putAt(3, DATA_3);
        test.putAt(4, DATA_4);

        return test;
    }

    private String[] dataToArray(){
        String[] array = {DATA_0, DATA_1, DATA_2, DATA_3, DATA_4};
        return array;
    }

    @Test
    public void testGetCapacityDefCapacity() {
        DynamicArray<String> test = new DynamicArray<>();
        assertEquals(test.getDefaultCapacity(), test.getCapacity());
    }


    @Test
    public void testGetCapacityNewCapacity() {
        DynamicArray<String> test = this.newArray();
        assertEquals(this.CAPACITY, test.getCapacity());
    }


    @Test
    public void createArrayCapacity(){
        final int capacity = 5;
        DynamicArray<String> test = new DynamicArray<>(capacity);

        assertNotEquals(test, null);
        assertEquals(capacity, test.getCapacity());
    }


    @Test
    public void createArrayNoCapacity(){
        final int capacity = 5;
        DynamicArray<String> test = new DynamicArray<>(capacity);

        assertNotEquals(test, null);
        assertTrue(test.getCapacity() > 0);        
    }


    @Test
    public void testGetAt() {
        final int index = 0;
        final String data = DATA_0;

        DynamicArray<String> test = this.newArray();

        assertEquals(data, test.getAt(index));
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testGetAtNoIndex() {
        final int index = 200;        

        DynamicArray<String> test = this.newArray();
        test.getAt(index);
    }


    @Test
    public void testPutAt() {
        final int index = 0;
        final String newString = DATA_NEW;

        DynamicArray<String> test = this.newArray();
        test.putAt(index, newString);

        assertEquals(newString, test.getAt(index));
    }


    @Test
    public void testPutAtIndexNoExists() {
        final int index = 6;
        final String data = DATA_NEW;
        
        DynamicArray<String> test = this.newArray();
        test.putAt(index, data);
        
        assertEquals(data, test.getAt(index));
    }

    @Test
    public void testRemoveNulls(){
        final int index = CAPACITY + 2;
        final String data = DATA_NEW;

        DynamicArray<String> test = this.newArray();
        test.putAt(index, data);

        for(int i = 0; i < test.getCapacity(); i++){
            System.out.println(test.getAt(i));
        }

        test.removeNulls();
        System.out.println("Removed nulls");

        for(int i = 0; i < test.getCapacity(); i++){
            System.out.println(test.getAt(i));
        }
    }


    @Test
    public void testClear() {
        DynamicArray<String> test = this.newArray();
        test.clear();

        for(int i = 0; i < test.getCapacity(); i++){
            assertTrue(test.getAt(i) == null);
        }
    }

    @Test
    public void testPushAt() {
        final int index = 2;
        final String data = this.DATA_NEW;
        final String[] oldData = this.dataToArray();

        DynamicArray<String> test = this.newArray();
        test.pushAt(index, data);

        assertEquals(data, test.getAt(index));
        for(int i = index; i < oldData.length; i++){
            assertEquals(oldData[i], test.getAt(i + 1));            
        }

        for(int i = 0; i < test.getCapacity(); i++){
            System.out.println(test.getAt(i));
        }
    }   


    @Test
    public void testPushAtHead() {
        final int index = 0;
        final String data = this.DATA_NEW;
        final String[] oldData = this.dataToArray();

        DynamicArray<String> test = this.newArray();
        test.pushAt(index, data);

        assertTrue(test.getAt(index).equals(data));
        for(int i = 0; i < oldData.length; i++){
            assertTrue(oldData[i].equals(test.getAt(i+1)));
        }
    }


    @Test
    public void testPushAtIndexSuperiorCapacty() {
        final int index = CAPACITY + 2;
        final String data = this.DATA_NEW;

        DynamicArray<String> test = this.newArray();
        test.pushAt(index, data);

        assertTrue(test.getAt(index).equals(data));

    }

    @Test
    public void testPushAtIndexEqualsCapacty() {
        final int index = CAPACITY;
        final String data = this.DATA_NEW;

        DynamicArray<String> test = this.newArray();
        test.pushAt(index, data);

        assertTrue(test.getAt(index).equals(data));
        ArrayList<String> a = new ArrayList<String>();
        a.add("Hola");
        a.add("Adios");
        a.add("Pepote");
        System.out.println(a);

    }

    // TODO: To string
    
    @Test
    public void testHasNextO() {

    }

    @Test
    public void testIterator() {

    }

    

    

    @Test
    public void testRemove() {

    }

    @Test
    public void testRemoveAll() {

    }

    @Test
    public void testRemoveAt() {

    }

    @Test
    public void testResize() {

    }

    @Test
    public void testToArray() {

    }
}
