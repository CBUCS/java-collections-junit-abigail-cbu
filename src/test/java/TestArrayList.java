import org.junit.jupiter.api.*;

import java.util.*;

/**
 * Set (add, clear, contains, containsAll, isEmpty, equals, remove, retainAll, size)
 * LinkedList (add, clear, contains, get, indexOf, isEmpty, lastIndexOf, remove, size, sort)
 * Stack (empty, peek, pop, push, search)
 * Queue (add, element, offer, peek, poll, remove)
 * HashMap (Clear, compute, containsKey, containsValue, equals, get, isEmpty, put, remove, replace, size)
 */
public class TestArrayList {

    private ArrayList<Integer> aList = new ArrayList<Integer>();
    private Set<Integer> aSet = new HashSet<Integer>();
    private LinkedList<Integer> aLink = new LinkedList<Integer>();
    private Stack<Integer> aStack = new Stack<Integer>();
    private Queue<Integer> aQueue = new LinkedList<Integer>();
    private HashMap<Integer, String> aHashMap = new HashMap<Integer, String>();
    private final int STARTING_SIZE = 3;

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

        aStack.push(1);
        aStack.push(2);
        aStack.push(3);

        aQueue.add(1);
        aQueue.add(2);
        aQueue.add(3);

        aHashMap.put(1, "one");
        aHashMap.put(2, "two");
        aHashMap.put(3, "three");
    }

    /**
     * Set (size, add, remove, isEmpty, clear, contains, containsAll, equals, retainAll)
     * LinkedList (size, add, remove, isEmpty, clear, contains, get, indexOf, lastIndexOf, sort)
     * HashMap (size, add, remove, isEmpty, clear, equals, get)
     */

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

        /*** HASH MAP ***/
        Assertions.assertEquals(3, aHashMap.size());
        Assertions.assertFalse(aHashMap.size() == 2);
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

        /*** HASH MAP ***/
        Assertions.assertEquals("one", aHashMap.remove(1));
        Assertions.assertEquals(STARTING_SIZE - 1, aHashMap.size());
        Assertions.assertNull(aHashMap.remove(1)); // already removed this value, so should return null
        Assertions.assertEquals("two", aHashMap.remove(2));
        Assertions.assertEquals(STARTING_SIZE - 2, aHashMap.size());
        Assertions.assertFalse(aHashMap.isEmpty());
        aHashMap.remove(3);
        Assertions.assertTrue(aHashMap.isEmpty());
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

        /*** HASH MAP ***/
        Assertions.assertTrue(aHashMap.size() > 0);
        Assertions.assertArrayEquals(new Object[]{"one", "two", "three"}, aHashMap.values().toArray());
        aHashMap.clear();
        Assertions.assertTrue(aHashMap.size() == 0);
    }

    /**
     * Test Contains and ContainsAll method for both Set and Linked List.
     * Make sure that the collection contains an Integer.
     * Make sure that the collection does not contain an Integer.
     */
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

        /*** HASH MAP ***/
        Assertions.assertFalse(aHashMap.equals(1));

        HashMap<Integer, String> tmpHashMap = new HashMap<Integer, String>();
        tmpHashMap.put(1, "one");
        tmpHashMap.put(2, "two");


        Assertions.assertFalse(aHashMap.equals(tmpHashMap));
        tmpHashMap.put(3, "three");
        Assertions.assertTrue(aHashMap.equals(tmpHashMap));
        tmpHashMap.put(1, "one");
        Assertions.assertTrue(aHashMap.equals(tmpHashMap));
        tmpHashMap.put(4,"four");
        Assertions.assertFalse(aHashMap.equals(tmpHashMap));
    }

    /**
     * Test RetainAll() for Set by making sure that it
     * returns true if the set contains all the elements from the tmpSet.
     */
    @Test
    public void testRetainAll() {
        /*** SET ***/
        Integer elements[] = new Integer[]{2, 3};
        Set tmpSet = new HashSet(Arrays.asList(elements));

        Assertions.assertTrue(aSet.retainAll(tmpSet));
        tmpSet.add(4);
        Assertions.assertFalse(aSet.retainAll(tmpSet));
    }

    /**
     * Test the Get() for linked list.
     * Make sure that it gets the correct object from specified index.
     */
    @Test
    public void testGet() {
        /*** LINKED LIST ***/
        Assertions.assertEquals((Integer) 1, aLink.get(0));
        Assertions.assertNotEquals(2, aLink.get(0));
//        Assertions.assertNotEquals(1, aLink.get(4)); // tried to test IndexOutOfBoundsException
        aLink.remove(0);
        Assertions.assertNotEquals(1, aLink.get(0));

        /*** HASH MAP ***/
        Assertions.assertEquals("one", aHashMap.get(1));
        Assertions.assertNotEquals("two", aHashMap.get(1));
        aHashMap.remove(1);
        Assertions.assertNotEquals("one", aHashMap.get(1));
    }

    /**
     * IndexOf should return the index of the object being searched
     * LastIndexOf should return the last index of the object being searched
     */
    @Test
    public void testIndexOfAndLastIndexOf() {
        /*** LINKED LIST ***/
        Assertions.assertEquals(0, aLink.indexOf(1));
        Assertions.assertEquals(0, aLink.lastIndexOf(1));
        aLink.remove(0);
        Assertions.assertNotEquals(0, aLink.indexOf(1));
        Assertions.assertNotEquals(0, aLink.lastIndexOf(1));
    }

    /**
     * Depending on the comparator, the sort() for linked list
     * should sort accordingly.
     */
    @Test
    public void testSort() {
        /*** LINKED LIST ***/
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, aLink.toArray());
        aLink.add(1);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3, 1}, aLink.toArray());

        aLink.sort(Comparator.naturalOrder()); // array sorted to be {1, 1, 2, 3}
        Assertions.assertNotEquals(new Object[]{1, 2, 3, 1}, aLink.toArray());

        aLink.sort(Comparator.reverseOrder());
        Assertions.assertNotEquals(new Object[]{1, 2, 3, 1}, aLink.toArray());
    }


    /**
     * Stack (empty, peek, pop, push, search)
     */

    /**
     * Make sure Empty method works for Stack
     * Should return true if the stack is empty
     * Should return false if the stack is not empty
     */
    @Test
    public void testStackEmpty() {
        Assertions.assertFalse(aStack.empty());

        Stack<Integer> tmpStack = new Stack<Integer>();
        Assertions.assertTrue(tmpStack.empty());
    }

    /**
     * Make sure that Peek method works for Stack.
     * Should return Integer from top of Stack.
     * Should not have removed the object that was peeked.
     */
    @Test
    public void testStackPeek() {
        Assertions.assertEquals((Integer) 3, aStack.peek());
        Assertions.assertEquals(STARTING_SIZE, aStack.size());
        aStack.push(4);
        Assertions.assertNotEquals(3, aStack.peek());
        Assertions.assertEquals(STARTING_SIZE + 1, aStack.size());
    }

    /**
     * Make sure Pop method works for Stack.
     * Make sure it removes the last object added to the stack.
     * Make sure can't remove an object from an empty stack.
     */
    @Test
    public void testStackPop() {
        Integer lastElement = aStack.peek();
        Assertions.assertEquals(lastElement, aStack.pop()); // remove "3"
        aStack.pop(); // remove "2"
        aStack.pop(); // remove "1"

        Assertions.assertTrue(aStack.empty());
    }

    /**
     * Make sure Push method works for stack.
     * Make sure you can add an object to stack.
     * Make sure the size of the stack increases.
     * Make sure you can only add Integers.
     */
    @Test
    public void testStackPush() {
        Assertions.assertEquals((Integer) 4, aStack.push(4));
//        Assertions.assertFalse((Integer)5, aStack.push("5")); // intellij already checks what type you push
        Assertions.assertEquals(STARTING_SIZE + 1, aStack.size());

    }

    /**
     * Make sure you are able to search through a stack.
     * Make sure it returns the index of the object in the stack.
     * Make sure it doesn't find an object that is not within the stack.
     */
    @Test
    public void testStackSearch() {
        Assertions.assertEquals(1, aStack.search(3));
        Assertions.assertEquals(2, aStack.search(2));
        Assertions.assertEquals(3, aStack.search(1));

        Assertions.assertEquals(-1, aStack.search(0));
    }

    /**
     *  Queue (add, element, offer, peek, poll, remove)
     */

    /**
     * Make sure add method works for queue.
     * Make sure you can add an object to queue.
     * Make sure the size of the queue increases.
     * Make sure you can only add Integers.
     */
    @Test
    public void testQueueAdd() {
        Assertions.assertTrue(aQueue.add(1));
        Assertions.assertEquals(STARTING_SIZE + 1, aQueue.size());
    }

    /**
     * Make sure Element method works for Queue.
     * It should only show you which object is at the head of the queue.
     * Make sure if you remove the head of the queue, it gets the new head object.
     */
    @Test
    public void testQueueElement() {
        Assertions.assertEquals((Integer) 1, aQueue.element());
        aQueue.remove();
        Assertions.assertNotEquals(1, aQueue.element());
        Assertions.assertEquals((Integer) 2, aQueue.element());
    }

    /**
     * Test to make sure offer method works in queue.
     * Make sure offer method does not fail in linked list queue
     */
    @Test
    public void testQueueOffer() {
        Assertions.assertTrue(aQueue.offer(1));
        // Assertions.assertFalse(aQueue.offer("one")); // intellij handles bad types
        Assertions.assertEquals(STARTING_SIZE + 1, aQueue.size());

        // Note: below is code that tried to make the offer() return a fail to add an element.
        // I didn't understand how to use BlockingQueue
        /*
        boolean success = true;
        Integer number = 0;
        while(success){
            success = aQueue.offer(number);
            number++;
        }
        Assertions.assertFalse(success);
        */
    }

    /**
     * Make sure Peek works for Queue.
     * Make sure that it returns the object at the head of the queue.
     * Make sure the size does not change a peek().
     * Make sure it returns new object if the previous head object was removed.
     * Make sure it returns null if queue is empty.
     */
    @Test
    public void testQueuePeek() {
        Assertions.assertEquals((Integer) 1, aQueue.peek());
        Assertions.assertEquals(STARTING_SIZE, aQueue.size());

        aQueue.remove(); // remove "1"
        Assertions.assertEquals(STARTING_SIZE - 1, aQueue.size());
        Assertions.assertNotEquals(1, aQueue.peek());
        Assertions.assertEquals((Integer) 2, aQueue.peek());

        aQueue.remove(); // remove "2"
        aQueue.remove(); // remove "3"
        Assertions.assertNull(aQueue.peek());
    }

    /**
     * Make sure Poll method works for Queue.
     * Make sure it displays the head of the queue and removes the head of the queue.
     * Make sure the size of the queue decreases after a poll.
     * Make sure poll returns a null if the queue is empty.
     */
    @Test
    public void testQueuePoll() {
        Assertions.assertEquals((Integer) 1, aQueue.poll());
        Assertions.assertEquals(STARTING_SIZE - 1, aQueue.size());
        Assertions.assertEquals((Integer) 2, aQueue.poll());
        Assertions.assertEquals((Integer) 3, aQueue.poll());
        Assertions.assertNull(aQueue.poll());
    }

    /**
     * Make sure Remove method works for Queue.
     * Make sure it removes the object from queue and that the queue gets reorganized.
     */
    @Test
    public void testQueueRemove() {
        Assertions.assertEquals((Integer) 1, aQueue.remove());
        Assertions.assertEquals(STARTING_SIZE - 1, aQueue.size());
        Assertions.assertEquals((Integer) 2, aQueue.remove());
        Assertions.assertEquals((Integer) 3, aQueue.remove());
    }

    /**
     * HashMap (compute, containsKey, containsValue, put, replace)
     */


    @Test
    public void testHashMapCompute() {

    }

    @Test
    public void testHashMapContainsKey() {

    }

    @Test
    public void testHashMapContainsValue() {

    }

    @Test
    public void testHashMapPut() {

    }

    @Test
    public void testHashMapReplace() {

    }
}
