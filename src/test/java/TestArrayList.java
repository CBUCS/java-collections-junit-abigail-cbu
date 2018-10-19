import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 ArrayList
 Set
 LinkedList
 Stack
 Queue
 HashMap
 */
public class TestArrayList {

    private ArrayList<Integer> aList = new ArrayList<Integer>();
    private Set<Integer> aSet = new HashSet<Integer>();
    private LinkedList<Integer> aLink = new LinkedList<Integer>();
    private int STARTING_SIZE = 0;

    /**
     * Start each test with 3 values each collection.
     */
    @BeforeEach
    public void Initialize() {
        aList.add(1);
        aList.add(2);
        aList.add(3);

        aSet.add(1);
        aSet.add(2);
        aSet.add(3);

        aLink.add(1);
        aLink.add(2);
        aLink.add(3);

        STARTING_SIZE = aList.size();
    }

    /**
     * Test if size method works in Array List
     */
    @Test
    public void TestSize() {
        /*** ARRAY LIST ***/
        Assertions.assertEquals(STARTING_SIZE, aList.size());

        /*** SET ***/
        Assertions.assertEquals(3, aSet.size());

    }

    /**
     * Test if add method works
     * Make sure that an integer is added to the list.
     * Make sure that the size of the list increases.
     * Make sure you can only add Integers.
     * Make sure you can only add initial collection into collection (can't add different collections together)
     */
    @Test
    public void TestAdd() {

        ArrayList<Integer> tmpArrayList = new ArrayList<Integer>();
        Set<Integer> tmpSet = new HashSet<Integer>();
        LinkedList<Integer> tmpLink = new LinkedList<Integer>();

        /*** ARRAY LIST ***/
        Assertions.assertTrue(STARTING_SIZE == 3);
        Assertions.assertTrue(aList.add(4));
        Assertions.assertSame(STARTING_SIZE + 1, aList.size());
        // Assertions.assertFalse(aList.add("one")); <-- intellij already catches this as an error

        tmpArrayList.add(1);
        tmpArrayList.add(2);
        Assertions.assertTrue(aList.addAll(tmpArrayList)); // can add same int in array list
        Assertions.assertFalse(aList.addAll(tmpSet)); // can't add different collection into array list
        Assertions.assertSame(STARTING_SIZE + 3, aList.size());

        /*** SET ***/
        Assertions.assertTrue(aSet.add(4));
        Assertions.assertSame(STARTING_SIZE + 1, aSet.size());

        tmpSet.add(1);
        tmpSet.add(2);
        Assertions.assertFalse(aSet.addAll(tmpSet)); // can't add array list into set
        Assertions.assertFalse(aSet.addAll(tmpArrayList)); // can't add same int in set

        tmpSet.clear();
        tmpSet.add(5);
        tmpSet.add(6);
        Assertions.assertTrue(aSet.addAll(tmpSet));
        Assertions.assertSame(STARTING_SIZE + 3, aSet.size());

        /*** LINKED LIST ***/
        Assertions.assertTrue(aLink.add(4));
        Assertions.assertSame(STARTING_SIZE + 1, aLink.size());

        tmpLink.add(1);
        Assertions.assertTrue(aLink.addAll(tmpLink));
        Assertions.assertTrue(aLink.addAll(tmpSet)); // you can add a set to a linked list
        Assertions.assertTrue(aLink.addAll(tmpArrayList)); // you can add an array list to a linked list
        Assertions.assertSame(STARTING_SIZE + 6, aLink.size());

    }

    /**
     * Test if remove method works
     * Make sure that an integer is removed from the list.
     * Make sure that the you are able to remove an integer from an index.
     * make sure that you are able to remove all objects in list.
     * Make sure that you can't remove objects from empty list.
     */
    @Test
    public void TestRemove() {
        /*** ARRAY LIST ***/
        Assertions.assertTrue(aList.remove((Integer) 1));
        Assertions.assertEquals(STARTING_SIZE - 1, aList.size());
        Assertions.assertEquals(2, (int) aList.remove(0)); // have to cast to differentiate between removing index or object
        Assertions.assertEquals(STARTING_SIZE - 2, aList.size());
        Assertions.assertFalse(aList.isEmpty());
        Assertions.assertTrue(aList.removeAll(aList));
        Assertions.assertFalse(aList.removeAll(aList)); // here, aList should not have anything in it
        Assertions.assertTrue(aList.isEmpty());

        /*** SET ***/
        Assertions.assertTrue(aSet.remove((Integer) 1));
        Assertions.assertEquals(STARTING_SIZE - 1, aSet.size());
        Assertions.assertFalse(aSet.remove(1)); // already removed this value, so should fail
        Assertions.assertTrue(aSet.remove((Integer) 2));
        Assertions.assertEquals(STARTING_SIZE - 2, aSet.size());
        Assertions.assertFalse(aSet.isEmpty());
        Assertions.assertTrue(aSet.removeAll(aSet));
        Assertions.assertFalse(aSet.removeAll(aSet)); // here, aList should not have anything in it
        Assertions.assertTrue(aSet.isEmpty());

        /*** LINKED LIST ***/
        Assertions.assertTrue(aLink.remove((Integer) 1));
        Assertions.assertEquals(STARTING_SIZE - 1, aLink.size());

    }

    /**
     * Test if clear method works
     */
    @Test
    public void TestClear() {
        /*** ARRAY LIST ***/
        Assertions.assertTrue(aList.size() > 0);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aList.toArray()); // can't do int
        aList.clear();
        Assertions.assertTrue(aList.size() == 0);

        /*** SET ***/
        Assertions.assertTrue(aSet.size() > 0);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aSet.toArray()); // can't do int
        aSet.clear();
        Assertions.assertTrue(aSet.size() == 0);


    }
}
