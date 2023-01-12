package cbs.algorithms.collections;

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
    private static final String DATA_5 = "4";
    private static final int COUNT = 6;

    private static final String DATA_NEW_0 = "new string 0";
    private static final String DATA_NEW_1 = "new string 1";
    private static final String DATA_NEW_2 = "new string 2";
    private static final String DATA_NEW_3 = "new string 3";


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

        test.insertHead(data);

        assertEquals(data, test.getHead());
        assertEquals(previousHead, test.getAt(1));
    }    

    private static Stream<Arguments> testInsertHeadArguments(){
        return Stream.of(
            Arguments.of((Object)null),
            Arguments.of(DATA_NEW_0),
            Arguments.of(DATA_3)
        );
    }

    
    @Test
    void testClear() {

    }    

    @Test
    void testGetCount() {

    }

    @Test
    void testIterator() {

    }

    

    @Test
    void testPushAt() {

    }

    @Test
    void testPutAt() {

    }

    @Test
    void testRemove() {

    }

    @Test
    void testRemoveAll() {

    }

    @Test
    void testRemoveAt() {

    }
}
