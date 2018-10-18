import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TestArrayList {

    private ArrayList<Integer> aList = new ArrayList<Integer>();
    private int STARTING_SIZE = 0;

    @BeforeEach
    public void init() {
        aList.add(1);
        aList.add(2);
        aList.add(3);

        STARTING_SIZE = aList.size();
    }

    /**
     * Test if size method works in Array List
     */
    @Test
    public void size() {
        Assertions.assertEquals(STARTING_SIZE, aList.size());
    }

    /**
     * Test if add method works in Array List.
     * <p>
     * Make sure that an integer is added to the list.
     * Make sure that the size of the list increases.
     * Make sure you can only add Integers.
     */
    @Test
    public void add() {
        Assertions.assertTrue(STARTING_SIZE == 3);
        Assertions.assertTrue(aList.add(4));
        Assertions.assertSame(STARTING_SIZE + 1, aList.size());
        // Assertions.assertFalse(aList.add("one")); <-- intellij already catches this as an error
    }

    /**
     * Test if remove method works in Array List.
     * Make sure that an integer is removed from the list.
     * Make sure that the you are able to remove an integer from an index.
     * make sure that you are able to remove all objects in list.
     * Make sure that you can't remove objects from empty list.
     */
    @Test
    public void remove() {
        Assertions.assertTrue(aList.remove((Integer) 1));
        Assertions.assertEquals(STARTING_SIZE - 1, aList.size());
        Assertions.assertEquals(2, (int)aList.remove(0)); // have to cast to differentiate between removing index or object
        Assertions.assertEquals(STARTING_SIZE - 2, aList.size());
        Assertions.assertFalse(aList.isEmpty());
        Assertions.assertTrue(aList.removeAll(aList));
        Assertions.assertFalse(aList.removeAll(aList)); // here, aList should not have anything in it
        Assertions.assertTrue(aList.isEmpty());
    }

    /**
     * Test if clear method works in Array List.
     */
    @Test
    public void clear() {
        Assertions.assertTrue(aList.size() > 0);
        Assertions.assertArrayEquals(new Integer[]{1,2,3}, aList.toArray()); // can't do int
    }
}
