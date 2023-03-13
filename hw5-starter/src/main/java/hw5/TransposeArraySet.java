package hw5;

/**
 * Set implemented using plain Java arrays and transpose-sequential-search heuristic.
 *
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {

  @Override
  protected int find(T t) {
    for (int i = 0; i < numElements; i++) {
      if (t.equals(data[i])) {
        if (i != 0) {
          T temp = data[i - 1];
          data[i - 1] = data[i];
          data[i] = temp;
        }
        return i;
      }
    }
    return -1;
  }
}
