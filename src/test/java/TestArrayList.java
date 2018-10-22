import org.junit.jupiter.api.*;

import java.util.*;

/**
 * Set (add, clear, contains, containsAll, isEmpty, equals, remove, retainAll, size)
 * LinkedList (add, clear, contains, get, indexOf, isEmpty, lastIndexOf, remove, size, sort)
 * Stack (all methods)
 * Queue (all methods)
 * HashMap (Clear, compute, containsKey, containsValue, equals, get, isEmpty, put, remove, replace, size)
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
    public void initialize() {
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
     * Test if size method works by
     * check if it is equal to the correct size
     * check if it not equal to the correct size
     */
    @Test
    public void testSize() {
        /*** SET ***/
        Assertions.assertEquals(3, aSet.size());
        Assertions.assertFalse(aSet.size() == 2);

        /*** LINKED LIST ***/
        Assertions.assertEquals(3, aLink.size());
        Assertions.assertFalse(aLink.size() == 2);

    }

    /**
     * Test if add method works
     * Make sure that an integer is added to the list.
     * Make sure that the size of the list increases.
     * Make sure you can only add Integers.
     * Make sure you can only add initial collection into collection (can't add different collections together)
     */
    @Test
    public void testAdd() {

        ArrayList<Integer> tmpArrayList = new ArrayList<Integer>();
        Set<Integer> tmpSet = new HashSet<Integer>();
        LinkedList<Integer> tmpLink = new LinkedList<Integer>();

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
        // I decided to just build off from the previous made tmp collections in this method
        Assertions.assertTrue(aLink.add(4));
        Assertions.assertSame(STARTING_SIZE + 1, aLink.size());

        tmpLink.add(1);
        Assertions.assertTrue(aLink.addAll(tmpLink));
        Assertions.assertTrue(aLink.addAll(tmpSet)); // you can add a set to a linked list
        Assertions.assertFalse(aLink.addAll(tmpArrayList)); // you cannot add an array list to a linked list
        Assertions.assertSame(STARTING_SIZE + 4, aLink.size());


    }

    /**
     * Test if remove and isEmpty method works
     * Make sure that an integer is removed from the list.
     * Make sure that the you are able to remove an integer from an index.
     * make sure that you are able to remove all objects in list.
     * Make sure that you can't remove objects from empty list.
     */
    @Test
    public void testRemoveAndIsEmpty() {
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
        Assertions.assertFalse(aLink.remove((Integer) 1)); // already removed this value, so should fail
        Assertions.assertTrue(aLink.remove((Integer) 2));
        Assertions.assertEquals(STARTING_SIZE - 2, aLink.size());
        Assertions.assertFalse(aLink.isEmpty());
        Assertions.assertTrue(aLink.removeAll(aLink));
        Assertions.assertFalse(aLink.removeAll(aLink)); // here, aList should not have anything in it
        Assertions.assertTrue(aLink.isEmpty());

    }

    /**
     * Test if clear method works by
     * checking if elements are contained within collection
     * clear the elements
     * and then check if there are no longer any elements in the collection
     */
    @Test
    public void testClear() {
        /*** SET ***/
        Assertions.assertTrue(aSet.size() > 0);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aSet.toArray()); // can't do int
        aSet.clear();
        Assertions.assertTrue(aSet.size() == 0);

        /*** LINKED LIST ***/
        Assertions.assertTrue(aLink.size() > 0);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aLink.toArray()); // can't do int
        aLink.clear();
        Assertions.assertTrue(aLink.size() == 0);

    }

    @Test
    public void testContainsAndContainsAll() {
        /*** SET ***/
        Assertions.assertTrue(aSet.size() > 0);
        Assertions.assertTrue(aSet.contains(1));
        Assertions.assertFalse(aSet.contains("1"));

        Integer elements[] = new Integer[]{2, 3};
        Set tmpSet = new HashSet(Arrays.asList(elements));
        Assertions.assertTrue(aSet.containsAll(tmpSet));
        tmpSet.add("1");
        Assertions.assertFalse(aSet.containsAll(tmpSet));

        /*** LINKED LIST ***/
        Assertions.assertTrue(aLink.size() > 0);
        Assertions.assertTrue(aLink.contains(1));
        Assertions.assertFalse(aLink.contains("1"));

        tmpSet.remove("1"); // tmpSet should only contain 2, 3
        Assertions.assertTrue(aSet.containsAll(tmpSet));
        tmpSet.add("1");
        Assertions.assertFalse(aSet.containsAll(tmpSet));
    }

    /**
     * Check if equals method works
     * make sure equals doesn't mean the same as contains
     * check if you don't have the correct elements, it doesn't equal
     * make sure it equals if you have the correct elements
     */
    @Test
    public void testEquals() {
        /*** SET ***/
        Assertions.assertFalse(aSet.equals(1));

        Integer elements[] = new Integer[]{2, 3};
        Set tmpSet = new HashSet(Arrays.asList(elements));
        Assertions.assertFalse(aSet.equals(tmpSet));
        tmpSet.add(1);
        Assertions.assertTrue(aSet.equals(tmpSet));
        tmpSet.add(1);
        Assertions.assertTrue(aSet.equals(tmpSet));
        tmpSet.add(4);
        Assertions.assertFalse(aSet.equals(tmpSet));
    }

    @Test
    public void testRetainAll() {
        /*** SET ***/
        Integer elements[] = new Integer[]{2, 3};
        Set tmpSet = new HashSet(Arrays.asList(elements));

        Assertions.assertTrue(aSet.retainAll(tmpSet));
        tmpSet.add(4);
        Assertions.assertFalse(aSet.retainAll(tmpSet));
    }

    @Test
    public void testGet() {
        /*** LINKED LIST ***/
        Assertions.assertEquals((Integer)1, aLink.get(0));
        Assertions.assertNotEquals(2, aLink.get(0));
//        Assertions.assertNotEquals(1, aLink.get(4)); // tried to test IndexOutOfBoundsException
        aLink.remove(0);
        Assertions.assertNotEquals(1,aLink.get(0));

    }

    @Test
    public void testIndexOfAndLastIndexOf() {
        /*** LINKED LIST ***/
        Assertions.assertEquals(0, aLink.indexOf(1));
        Assertions.assertEquals(0, aLink.lastIndexOf(1));
        aLink.remove(0);
        Assertions.assertNotEquals(0, aLink.indexOf(1));
        Assertions.assertNotEquals(0, aLink.lastIndexOf(1));
    }

    @Test
    public void testSort() {
        /*** LINKED LIST ***/
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aLink.toArray());
        aLink.add(1);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 1}, aLink.toArray());
        aLink.sort(Comparator.naturalOrder()); // array sorted to be {1, 1, 2, 3}
        Assertions.assertNotEquals(new Integer[]{1, 2, 3, 1}, aLink.toArray());
    }
}
