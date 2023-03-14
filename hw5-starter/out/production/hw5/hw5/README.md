# Discussion

## Flawed Deque
Flaw 1: FlawedDeque failed testRemoveFrontThrowsExceptionWhenEmptyDeque(), which makes me believe that in the implementation
there is a missing check for if the deque is empty, throw exception. 

Flaw 2: 


## Hacking Linear Search
when you call find in the remove method, there is shifting around of values again due to the move to front 
heuristic. So the list head is now the value you want to remove, which makes it run in O(1) time after the find
method.


## Profiling

