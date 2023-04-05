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
        if (i == 0) {
          return i;
        } else { // perform swapping that the target element is moved closer to the front of the list
          T temp = data[i - 1];
          data[i - 1] = data[i];
          data[i] = temp;
          return i - 1;
        }
      }
    }
    return -1;
  }

  /**
   * main method for TransposeArraySet that performs a series of operations on a set.
   *
   * @param args takes input as a string but we don't use in this method.
   */
  public static void main(String[] args) {
    ArraySet<Integer> set = new TransposeArraySet<>();
    set.insert(4);
    set.insert(6);
    set.insert(7);
    System.out.println(set.has(4));
    System.out.println(set.has(6));
    System.out.println(set.has(7));
    set.remove(0);
    System.out.println(set.has(6));
    set.remove(1);
    System.out.println(set.has(7));
    set.remove(0);
    System.out.println(set.has(4));
  }
}
