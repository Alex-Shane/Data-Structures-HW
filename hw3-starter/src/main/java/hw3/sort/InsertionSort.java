package hw3.sort;


import hw3.list.IndexedList;

/**
 * The Insertion Sort algorithm, with minimizing swaps optimization.
 *
 * @param <T> Element type.
 */
public final class InsertionSort<T extends Comparable<T>>
    implements SortingAlgorithm<T> {

  @Override
  public void sort(IndexedList<T> indexedList) {
    // represents the index of the current unsorted element in the list
    int unsortedIndex = 1;
    // go through whole list so it all gets sorted
    while (unsortedIndex < indexedList.length()) {
      // value at position of unsorted element
      T curVal = indexedList.get(unsortedIndex);
      // greatest sorted element
      int greatestSortedIndex = unsortedIndex - 1;
      // loops until we find the smallest value in list or find right place to put curVal
      while (greatestSortedIndex >= 0 && indexedList.get(greatestSortedIndex).compareTo(curVal) > 0) {
        // move value at greatestSortedIndex one spot to the right to keep sorting
        indexedList.put(greatestSortedIndex + 1, indexedList.get(greatestSortedIndex));
        greatestSortedIndex--;
      }
      // put curVal in proper spot
      indexedList.put(greatestSortedIndex + 1, curVal);
      unsortedIndex++;
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
