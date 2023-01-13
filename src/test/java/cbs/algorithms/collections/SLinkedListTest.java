package cbs.algorithms.collections;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import cbs.data.collections.SLinkedList;


public class SLinkedListTest {

    private static final String DATA_0 = "0";
    private static final String DATA_1 = "1";
    private static final String DATA_2 = "2";
    private static final String DATA_3 = "3";
    private static final String DATA_4 = "4";
    private static final String DATA_5 = "5";
    private static final int COUNT = 6;

    private static final String DATA_NEW_0 = "new string 0";
    private static final String DATA_NEW_1 = "new string 1";
    private static final String DATA_NEW_2 = "new string 2";
    private static final String DATA_NEW_3 = "new string 3";

    private static void print(final String mess, final SLinkedList<?> list){
        System.out.println(mess + ": " + list.toString());
    }

    private static void print(final String mess, final Object ress){
        System.out.println(mess + ": " + ress.toString());
    }

    private static void printSep(){
        System.out.println("----------------------");
    }

    public SLinkedList<String> newList(){
        SLinkedList<String> test = new SLinkedList<>();
        test.append(DATA_0);
        test.append(DATA_1);
        test.append(DATA_2);
        test.append(DATA_3);
        test.append(DATA_4);
        test.append(DATA_5);

        return test;
    }    

    public static String[] dataToArray(){
        return new String[] {DATA_0, DATA_1, DATA_2, DATA_3, DATA_4, DATA_5};
    }

    @ParameterizedTest
    @MethodSource("testAppendArguments")
    void testAppend(final String[] data) {
        SLinkedList<String> list = new SLinkedList<>();
        int counter = 0;

        for(int i = 0; i < data.length; i++){
            list.append(data[i]);
        }

        for(String s: list){
            assertEquals(data[counter], s);
            counter++;
        }
    }

    private static Stream<Arguments> testAppendArguments(){
        return Stream.of(
            Arguments.of((Object)new String[]{DATA_0, DATA_1}),
            Arguments.of((Object)new String[]{DATA_0, DATA_1, DATA_2}),
            Arguments.of((Object)SLinkedListTest.dataToArray()),
            Arguments.of((Object)new String[]{null, null})
        );
    }

    @ParameterizedTest
    @MethodSource("testGetAtArguments")
    void testGetAt(int index, final String expected) {
        SLinkedList<String> test = this.newList();
        String actual = test.getAt(index);

        assertEquals(expected, actual);
        System.out.println(test.toString());
    }

    private static Stream<Arguments> testGetAtArguments(){
        return Stream.of(
            Arguments.of(2, DATA_2),
            Arguments.of(4, DATA_4),
            Arguments.of(-1, null),
            Arguments.of(COUNT + 5, null)
        );
    }

    @ParameterizedTest
    @MethodSource("testInsertHeadArguments")
    void testInsertHead(final String data) {
        SLinkedList<String> test = this.newList();
        String previousHead = test.getHead();
        System.out.println("Original: " + test.toString());
        test.insertHead(data);


        assertEquals(data, test.getHead());
        assertEquals(previousHead, test.getAt(1));
        System.out.println(test.toString());
    }    

    private static Stream<Arguments> testInsertHeadArguments(){
        return Stream.of(
            Arguments.of((Object)null),
            Arguments.of(DATA_NEW_0),
            Arguments.of(DATA_NEW_3)
        );
    }

    @Test
    void testGetCount() {
        SLinkedList<String> test = this.newList();
        assertEquals(COUNT, test.getCount());
    }

    
    @Test
    void testClear() {
        SLinkedList<String> test = this.newList();
        assertEquals(COUNT, test.getCount());
        test.clear();

        assertEquals(test.getCount(), 0);
        assertEquals(test.getHead(), null);
        assertEquals(test.getTail(), null);

    }    

    
    @Test
    void testIterator() {
        SLinkedList<String> test = new SLinkedList<>();
        int counter = 0;
        for(String s: test){
            counter++;
        }

        assertEquals(test.getCount(), counter);
    }

    

    @ParameterizedTest
    @MethodSource("testPushAtArguments")
    void testPushAt(int index, final String data, final String expected) {
        SLinkedList<String> test = this.newList();
        test.pushAt(index, data);

        assertEquals(expected, test.getAt(index));
    }

    private static Stream<Arguments> testPushAtArguments(){
        return Stream.of(
            Arguments.of(1, DATA_NEW_1, DATA_NEW_1),
            Arguments.of(3, DATA_NEW_3, DATA_NEW_3),
            Arguments.of(0, DATA_NEW_0, DATA_NEW_0),
            Arguments.of(COUNT + 5, "Pepote", null),
            Arguments.of(-4, "Pepote", null)
        );
    }
    

    @ParameterizedTest
    @MethodSource("testPutAtArguments")
    void testPutAt(int index, final String data, final String expected) {
        SLinkedList<String> test = this.newList();
        print("-----", "-----");
        print("Original", test);
        test.putAt(index, data);

        assertEquals(expected, test.getAt(index));
        print("Done", test);
        print("tail", test.getTail());
        
    }

    

    private static Stream<Arguments> testPutAtArguments(){
        return Stream.of(
            Arguments.of(1, DATA_NEW_1, DATA_NEW_1),
            Arguments.of(0, DATA_NEW_0, DATA_NEW_0),
            Arguments.of(COUNT-1, DATA_NEW_2, DATA_NEW_2),
            Arguments.of(-5, DATA_0, null),
            Arguments.of(COUNT + 2, DATA_0, null)
        );
    }

    public SLinkedList<String> removeList(){
        SLinkedList<String> test = new SLinkedList<>();
        test.append(DATA_0);
        test.append(DATA_0);
        test.append(DATA_1);
        test.append(DATA_2);
        test.append(DATA_1);
        test.append(DATA_3);

        return test;
    }

    @ParameterizedTest
    @MethodSource("testRemoveArguments")
    void testRemove(final String data, int expectedCount, int firstIndex) {
        SLinkedList<String> test = this.newList();
        print("-----------", "");
        print("Original", test);
        test.remove(data);

        assertEquals(expectedCount, test.getCount());
        if(firstIndex >= 0 && firstIndex < COUNT){
            assertNotEquals(data, test.getAt(firstIndex));            
        }
        print("Done", test);
        print("tail", test.getTail());
    }

    private static Stream<Arguments> testRemoveArguments(){
        return Stream.of(
            Arguments.of(DATA_0, COUNT-1, 0),
            Arguments.of(DATA_1, COUNT-1, 2),
            Arguments.of(DATA_3, COUNT-1, 5),
            Arguments.of(DATA_4, COUNT-1, 6),
            Arguments.of(DATA_5, COUNT-1, COUNT-1)
        );
    }

    @ParameterizedTest
    @MethodSource("testRemoveAllArguments")
    void testRemoveAll(final String data, int expectedCount, int firstIndex) {
        SLinkedList<String> test = this.removeList();
        printSep();
        print("Original", test);
        test.removeAll(data);

        assertEquals(expectedCount, test.getCount());
        if(firstIndex >= 0 && firstIndex < COUNT){
            assertNotEquals(data, test.getAt(firstIndex));            
        }

        print("Done", test);
        print("Head", test.getHead());
        print("Tail", test.getTail());
    }

    private static Stream<Arguments> testRemoveAllArguments(){
        return Stream.of(
            Arguments.of(DATA_0, COUNT-2, 0),
            Arguments.of(DATA_1, COUNT-2, 2),
            Arguments.of(DATA_3, COUNT-1, 5),
            Arguments.of(DATA_4, COUNT, 6)
        );
    }

    @ParameterizedTest
    @MethodSource("testRemoveAtArguments")
    void testRemoveAt(int index, final String expected, int expectedCount) {
        SLinkedList<String> test = this.newList();
        test.removeAt(index);

        if(expectedCount <= COUNT - 1){
            assertEquals(expected, test.getAt(index));
        }
        assertEquals(expectedCount, test.getCount());
        
    }   

    private static Stream<Arguments> testRemoveAtArguments(){
        return Stream.of(
            Arguments.of(0, DATA_1, COUNT-1),
            Arguments.of(1, DATA_2, COUNT-1),
            Arguments.of(3, DATA_4, COUNT-1),
            Arguments.of(-5, DATA_4, COUNT),
            Arguments.of(COUNT+7, DATA_2, COUNT)
        );
    }
}
