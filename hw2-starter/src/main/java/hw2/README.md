# Discussion

The `Roster` class uses `IndexedList` to store a list of students. The
`Roster.find` implements the binary search algorithm. Which
implementation of the `IndexedList` should be used to implement the
`Roster` class? (It could be one or more of `ArrayIndexedList`,
`LinkedIndexList`, `SparseIndexedList`). And why?
   
--------------- Write your answers below this line ----------------

First off, we should not use SparseIndexedList since we have unique
emails and sparse list is only good when implemented in a list with a few unique items (ie most items have common/default value). When thinking about binary search, it quicker to find the middle element
using ArrayIndexedList rather than LinkedIndexedList (get is O(1) for array and O(N) for linked). Since the array
implementation is on average faster than linked list for find, we should use ArrayIndexList for Roster class. 