package cbs.algorithms.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DynamicArrayTest {
    private final static int CAPACITY = 5;

    private final static String DATA_0 = "0";
    private final static String DATA_1 = "1";
    private final static String DATA_2 = "2";
    private final static String DATA_3 = "3";
    private final static String DATA_4 = "4";

    private final static String DATA_NEW = "new String";

    private DynamicArray<String> newArray() {
        DynamicArray<String> test = new DynamicArray<>(CAPACITY);
        test.putAt(0, DATA_0);
        test.putAt(1, DATA_1);
        test.putAt(2, DATA_2);
        test.putAt(3, DATA_3);
        test.putAt(4, DATA_4);

        return test;
    }

    private static String[] dataToArray() {
        String[] array = { DATA_0, DATA_1, DATA_2, DATA_3, DATA_4 };
        return array;
    }

    @Test
    public void testGetCapacityDefCapacity() {
        DynamicArray<String> test = new DynamicArray<>();
        assertEquals(DynamicArray.getDefaultCapacity(), test.getCapacity());
    }

    @Test
    public void testGetCapacityNewCapacity() {
        DynamicArray<String> test = this.newArray();
        assertEquals(CAPACITY, test.getCapacity());
    }

    @ParameterizedTest
    @MethodSource("capacityArguments")
    public void createArrayCapacity(int capacity, int expected) throws Exception {

        DynamicArray<String> test = new DynamicArray<>(capacity);

        assertNotEquals(test, null);
        assertEquals(expected, test.getCapacity());
    }

    // value, expected
    private static Stream<Arguments> capacityArguments() {
        return Stream.of(
                Arguments.of("2", 2),
                Arguments.of("10", "10"),
                Arguments.of("0", Integer.toString(DynamicArray.getDefaultCapacity())),
                Arguments.of(Integer.toString(DynamicArray.getDefaultCapacity()),
                        Integer.toString(DynamicArray.getDefaultCapacity()))

        );
    }

    @ParameterizedTest
    @MethodSource("getAtArguments")
    public void testGetAt(int index, String data) {

        DynamicArray<String> test = this.newArray();

        assertEquals(data, test.getAt(index));
    }

    private static Stream<Arguments> getAtArguments() {
        return Stream.of(
                Arguments.of(0, DATA_0),
                Arguments.of(3, DATA_3),
                Arguments.of(2, DATA_2)

        );
    }

    @ParameterizedTest
    @MethodSource("getAtNoIndexArguments")
    public void testGetAtNoIndex(int index, Class<? extends Exception> e) {

        DynamicArray<String> test = this.newArray();
        assertThrows(e, () -> test.getAt(index));
    }

    private static Stream<Arguments> getAtNoIndexArguments() {
        return Stream.of(
                Arguments.of(-1, ArrayIndexOutOfBoundsException.class),
                Arguments.of(CAPACITY + 5, ArrayIndexOutOfBoundsException.class));
    }

    @ParameterizedTest
    @MethodSource("testPutAtArguments")
    public void testPutAt(int index, String data) {

        DynamicArray<String> test = this.newArray();
        test.putAt(index, data);

        assertEquals(data, test.getAt(index));
    }

    private static Stream<Arguments> testPutAtArguments() {
        return Stream.of(
                Arguments.of(0, DATA_NEW),
                Arguments.of(4, DATA_NEW),
                Arguments.of(12, DATA_NEW));
    }

    @Test
    public void testRemoveNulls() {
        final int index = CAPACITY + 2;
        final String data = DATA_NEW;

        DynamicArray<String> test = this.newArray();
        test.putAt(index, data);

        test.removeNulls();
        System.out.println("Removed nulls");

        for (int i = 0; i < test.getCapacity(); i++) {
            assertNotEquals(test.getAt(i), null);
        }
    }

    @Test
    public void testClear() {
        DynamicArray<String> test = this.newArray();
        test.clear();

        for (int i = 0; i < test.getCapacity(); i++) {
            assertTrue(test.getAt(i) == null);
        }
    }

    @ParameterizedTest
    @MethodSource("testResizeArguments")
    public void testResize(int capacity, int expectedCapacity, String firstValue, String lastValue) {
        DynamicArray<String> test = this.newArray();
        test.resize(capacity);

        assertEquals(expectedCapacity, test.getCapacity());
        assertEquals(firstValue, test.getAt(0));
        assertEquals(lastValue, test.getAt(test.getCapacity() - 1));

    }

    private static Stream<Arguments> testResizeArguments() {
        return Stream.of(
                Arguments.of(10, 10, DATA_0, null),
                Arguments.of(2, 2, DATA_0, DATA_1),
                Arguments.of(-5, CAPACITY, DATA_0, DATA_4),
                Arguments.of(5, 5, DATA_0, DATA_4),
                Arguments.of(0, CAPACITY, DATA_0, DATA_4));
    }

    @ParameterizedTest
    @MethodSource("testPushArguments")
    public void testPushAt(int index, String[] oldData, String newData) {

        DynamicArray<String> test = this.newArray();
        test.pushAt(index, newData);

        assertEquals(newData, test.getAt(index));
        if (index < oldData.length) {
            for (int i = index; i < oldData.length; i++) {
                assertEquals(oldData[i], test.getAt(i + 1));
            }
        } else {
            // in case that the index overflowed the array and needed a resize
            for (int i = index + 1; i > test.getCapacity(); i++) {
                assertEquals(test.getAt(i), null);
            }
        }
    }

    private static Stream<Arguments> testPushArguments() {
        return Stream.of(
                Arguments.of(2, dataToArray(), DATA_NEW),
                Arguments.of(8, dataToArray(), DATA_NEW),
                Arguments.of(CAPACITY, dataToArray(), DATA_NEW),
                Arguments.of(0, dataToArray(), DATA_NEW));
    }

    // TODO: To string

    @Test
    public void testIterator() {
        DynamicArray<String> test = this.newArray();
        int counter = 0;

        for (String t : test) {
            assertEquals(test.getAt(counter), t);
            counter++;
        }
    }

    private final static String DATA_REM_0 = "0";
    private final static String DATA_REM_1 = "1";
    private final static String DATA_REM_2 = "2";
    private final static String DATA_REM_3 = "1";
    private final static String DATA_REM_4 = "4";
    private final static String DATA_REM_5 = "4";
    private final static String DATA_REM_6 = "4";

    private final static int CAPACITY_REM = 7;

    private DynamicArray<String> arrayForRemoveTests() {
        DynamicArray<String> test = new DynamicArray<>(CAPACITY_REM);
        test.putAt(0, DATA_REM_0);
        test.putAt(1, DATA_REM_1);
        test.putAt(2, DATA_REM_2);
        test.putAt(3, DATA_REM_3);
        test.putAt(4, DATA_REM_4);
        test.putAt(5, DATA_REM_5);
        test.putAt(6, DATA_REM_6);
        return test;
    }

    @ParameterizedTest
    @MethodSource("testRemoveArguments")
    public void testRemove(String toRemove, boolean exists) {
        DynamicArray<String> test = this.arrayForRemoveTests();
        test.remove(toRemove);

        // when we remove we put the array[index] = null
        // so we check if there is ONLY one null
        for (String s : test) {
            if (s == null) {
                return;
            }
        }

        assertTrue("Not null value found", !exists);
    }

    private static Stream<Arguments> testRemoveArguments() {
        return Stream.of(
                Arguments.of(DATA_REM_0, true),
                Arguments.of(DATA_REM_4, true),
                Arguments.of("Invented", false));
    }

    @ParameterizedTest
    @MethodSource("testRemoveAllArguments")
    public void testRemoveAll(String toRemove, int expectedRemoves) {
        DynamicArray<String> test = this.arrayForRemoveTests();
        test.removeAll(toRemove);
        int removed = 0;

        // when we remove we put the array[index] = null
        // so we check if there is ONLY one null
        for (String s : test) {
            if (s == null) {
                removed++;
            }
        }

        assertEquals(removed, expectedRemoves);
    }

    private static Stream<Arguments> testRemoveAllArguments() {
        return Stream.of(
                Arguments.of(DATA_REM_0, 1),
                Arguments.of(DATA_REM_4, 3),
                Arguments.of(DATA_REM_1, 2),
                Arguments.of("Invented", 0));
    }

    @ParameterizedTest
    @MethodSource("testRemoveAtArguments")
    public void testRemoveAt(int index, boolean removed) {
        DynamicArray<String> test = this.arrayForRemoveTests();
        test.removeAt(index);

        if(removed){
            assertEquals(test.getAt(index), null);
        }

    }

    private static Stream<Arguments> testRemoveAtArguments() {
        return Stream.of(
            Arguments.of(2, true),
            Arguments.of(-2, false),
            Arguments.of(4, true),
            Arguments.of(12, false)
        );
    }

    @Test
    public void testToArray() {
        DynamicArray<String> test = this.newArray();
        String[] array = test.toArray(String.class);

        assertEquals(test.getCapacity(), array.length);
        for (int i = 0; i < array.length; i++) {
            assertEquals(test.getAt(i), array[i]);
        }
    }
}
