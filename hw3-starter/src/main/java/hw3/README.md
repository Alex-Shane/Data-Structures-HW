# Discussion

## PART I: MEASURED IndexedList

**Discuss from a design perspective whether iterating over a `MeasuredIndexedList`should 
affect the accesses and mutation counts. Note that for the purposes of this assignment we are NOT 
asking you to rewrite the `ArrayIndexedListIterator` to do so. However, if you wanted to include 
the `next()` and/or `hasNext()` methods in the statistics measured, can you inherit 
`ArrayIndexedListIterator` from `ArrayIndexedList` and override the relevant methods, or not? 
Explain.**

From a design perspective, iterating over a MeasuredIndexedList should not affect either the mutations or accesses. It 
shouldn't affect mutations since we never change a value at any index when iterating over the list. It shouldn't affect
accesses because although the iterator retrieves the value at each index, we want the accesses count to refer to the number
of deliberate calls to access data at a specific index. For example, a user could use an enhanced for loop to change every
value in the list to some default value, in which case we wouldn't want to count any accesses from the iterator since 
the user's intention was to modify data not access it. 

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

Let's start with the comparisons. The only comparisons in this version of selection sort are on lines 3,5, and 6.
On line 3, we have the comparison i < a.length-1;, which occurs n times. On line 5, we have the comparison 
j < a.length;, which occurs n/2 times, but is repeated n-1 times (the number of times the outer for loop is run). Thus,
that comparison occurs n(n-1)/2 times. Lastly, on line 6, we have a[j] > a[max];, which, following the same logic as the 
previous comparison, occurs n(n-1)/2 times. 

Adding these comparisons up, we get n + 2n(n-1)/2 = n + n(n-1) = n^2
Thus, C(n) for worst case = n^2, which matches up with the expected polynomial of degree two for worst case.

The assignments in this code are found in lines 3,4,5,7,10,11, and 12. In line 3, we have the assignment int i = 0;, which 
only occurs once. Also in line 3, we have i++;, which occurs (n-1) because this assignment is not called after the comparison
of the for loop fails. In line 4, we have max = i;, which occurs (n-1) times since it is called as many times as the outer
for loop runs. In line 5, we have int j = i+1;, which occurs (n-1) times by the same logic as the last assignment. Also
in line 5 we have j++, which following the same logic as explained in the comparisons, occurs n(n-1)/2 times. In line 7,
we have max = j;, which also happens n(n-1)/2 times. The assignments for lines 10,11, and 12 all follow the same logic
and happen (n-1) times due to the outer loop.

Summing these totals together, we get 1+6(n-1)+2n(n-1)/2 = 1+6n-6+n^2-n = n^2 + 5n- 5
Thus, A(n) for worst case = n^2 + 5n - 5, which matches up with the expected polynomial of degree two for worst case.