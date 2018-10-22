Each method has sort of pseudocode on how I decided to tested each method.
 
I decided to do all my testing in JUnitCollectionTesting.java.

I initialized all the collections and initialized each collection with 3 elements to each collection.
So, for each @Test method, I knew I had 3 elements to work with.

The first 10 test methods contain testing for Set, Linked List, and Hash Map methods that contain the same method name
(i.e. size, remove, etc.).

And then, I separated the testing methods for Stack, Queue, and any Hash Map testing that I did not do before.

For each method, I tested if the method returns or excutes the method according to the description in the Java docs.
Then, I tried to break the method by testing things that shouldn't happen
 (i.e. return an element that does not exist in the collection)
 
 There is a total of 26 Test methods.