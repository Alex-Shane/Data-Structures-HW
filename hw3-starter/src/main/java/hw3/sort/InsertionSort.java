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
    int curIndex = 1;
    while (curIndex < indexedList.length()) {
      T curVal = indexedList.get(curIndex);
      int prevIndex = curIndex - 1;
      while (prevIndex >= 0 && indexedList.get(prevIndex).compareTo(curVal) > 0) {
        indexedList.put(prevIndex + 1, indexedList.get(prevIndex));
        prevIndex--;
      }
      indexedList.put(prevIndex + 1, curVal);
      curIndex++;
    }
  }

  @Override
  public String name() {
    return "Insertion Sort";
  }
}
