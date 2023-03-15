# Discussion

## Flawed Deque
Flaw 1: FlawedDeque failed testRemoveFrontThrowsExceptionWhenEmptyDeque() and testRemoveBackThrowsExceptionWhenEmptyDeque(),
which makes me believe that in the implementation for both methods, there is a missing check for if the deque is empty, 
then throw exception. 

Flaw 2: FlawedDeque failed testBackThrowsExceptionWhenEmptyDeque() since it throws a length exception instead of the proper 
empty exception. The error here is pretty obvious that the implementation throws a length exception instead of an empty
exception. 

Flaw 3: 


## Hacking Linear Search
For LinkedSet: When remove is called in main, we first need to run find() to get the node in the set. Running find()
moves our target node to the head since we implemented move to front heuristic. Thus, the actual removal part of the 
remove() function runs in O(1) time always since our target node is always the head at removal time. 

For ArraySet: When remove is called in main, we run find() to see if the element is contained in the set. If it is, we swap
the target element with the previous element so that when it comes time to actually remove we need to traverse one less
element than we would've without the swap. However, this removal part is still O(n) time because in the worst case we
need to traverse through n-1 elements.  


## Profiling

