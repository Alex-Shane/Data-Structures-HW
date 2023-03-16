# Discussion

## Flawed Deque
Flaw 1: FlawedDeque failed testRemoveFrontThrowsExceptionWhenEmptyDeque() and testRemoveBackThrowsExceptionWhenEmptyDeque(),
which makes me believe that in the implementation for both methods, there is a missing check for if the deque is empty, 
then throw exception. 

Flaw 2: FlawedDeque failed testBackThrowsExceptionWhenEmptyDeque() since it throws a length exception instead of the proper 
empty exception. The error here is pretty obvious that the implementation throws a length exception instead of an empty
exception. 

Flaw 3: The insertBack() method has something strange going on with it as almost every method that uses insertBack()
more than once fails. Looking more closely at one of these tests that fails, testInsertBackWithMultipleElements(), it appears
that not every element gets added properly to the back of the deque. The pattern seems to be that every other call to insertBack()
works as intended, but the 5th and 6th calls both work, however the 7th call doesn't. Thus, I believe that the error that is causing
this flaw must be in the growing of the data structure used to implement deque. Either the underlying data structure isn't growing
fast enough or it is growing at the right rate and time, but the last element that should be in the deque is not getting added,
which would make sense why it appears that an element is only added every two calls. 


## Hacking Linear Search
For LinkedSet: When remove is called in main, we first need to run find() to get the node in the set. Running find()
moves our target node to the head since we implemented move to front heuristic. Thus, the actual removal part of the 
remove() function runs in O(1) time always since our target node is always the head at removal time. 

For ArraySet: When remove is called in main, we run find() to see if the element is contained in the set. If it is, we swap
the target element with the previous element so that when it comes time to actually remove we need to traverse one less
element than we would've without the swap. However, this removal part is still O(n) time because in the worst case we
need to traverse through n-1 elements.  


## Profiling

Times with initial setup/experiment: 
    ArraySet: 0.241 ms/op
    LinkedSet: 0.876 ms/op
    MoveToFront: 0.876 ms/op
    TransposeSequence: 0.221 ms/op

To make the heuristic implementations perform faster than the original implementations, I decided to add on to the 
experiment. After adding all the numbers, I decided to go through the set again, but each time only calling the has
method for one number. For both LinkedSet and ArraySet, they would traverse through the set in its entirety each time
until they found the specific number. However, for MoveToFront, after the first has call, it would find the number in O(1)
time since that number would now be at the head. And, for TransposeSequence, each time it finds the number, it swaps with the
previous element so that with each call it would take less time to find the number. 

Times with changes to experiment and keeping setup the same:
    ArraySet: 0.429 ms/op
    LinkedSet: 1.750 ms/op
    MovetoFront: 0.890 ms/op
    TransposeSequence: 0.304 ms/op

From these results its clear that the heuristics performed faster with the new experiment (MoveToFront was almost 2x faster
than the original implementation and TransposeSequence was more than .100 ms/op faster than ArraySet).