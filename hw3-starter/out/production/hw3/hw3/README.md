# Discussion

## PART I: MEASURED IndexedList

**Discuss from a design perspective whether iterating over a `MeasuredIndexedList`should 
affect the accesses and mutation counts. Note that for the purposes of this assignment we are NOT 
asking you to rewrite the `ArrayIndexedListIterator` to do so. However, if you wanted to include 
the `next()` and/or `hasNext()` methods in the statistics measured, can you inherit 
`ArrayIndexedListIterator` from `ArrayIndexedList` and override the relevant methods, or not? 
Explain.**

Iterating over MeasuredIndexedList should not affect the accesses or the mutations
since the iterator doesn't need to call the get() method to find element at current index being traversed and
the iterator never modifies a value in the list.

You can't inherit ArrayIndexedListIterator from ArrayIndexedList and override
the relevant methods since ArrayIndexedListIterator is a private nested class,
so the subclass (MeasuredIndexedList) does not have access to it and thus can't override
its methods. 



## PART II: EXPERIMENTS

**Explain the mistake in the setup/implementation of the experiment which resulted in a discrepancy 
between the results and what is expected from each sorting algorithm.**

When running the SortingAlgorithmDriver, I noticed that there were many mutations for ascending.data when sorted
with bubble sort, whereas we would expect zero mutations with bubble swap with the stop early if no swaps optimization
on an ascending sorted list. I knew that meant that either bubble sort was implemented wrong or something was wrong
in how the experiment was carried out. After I confirmed the bubble sort implementation was correct, I knew it must be
in the experiment implementation.

That's when I looked closer at how the lists we sorted were declared, and I saw that in the driver their data type was 
string instead of the expected Integer type. Therefore, our sorts were sorting lexicographically rather than numerically. 
This makes sense since the descending data file doesn't follow a descending numerical pattern (line 11 has "999" and line 
12 has "9989") but rather a descending lexicographical pattern. Thus, the issue/error was that our sorting algorithms
were sorting lexicographically rather than numerically, which we expected. 




## PART III: ANALYSIS OF SELECTION SORT

**Determine exactly how many comparisons C(n) and assignments A(n) are performed by this 
implementation of selection sort in the worst case. Both of those should be polynomials of degree 2 
since you know that the asymptotic complexity of selection sort is O(n^2).**



