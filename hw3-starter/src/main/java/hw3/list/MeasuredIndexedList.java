package hw3.list;

import exceptions.IndexException;

/**
 * An ArrayIndexedList that is able to report the number of
 * accesses and mutations, as well as reset those statistics.
 *
 * @param <T> The type of the array.
 */
public class MeasuredIndexedList<T> extends ArrayIndexedList<T>
    implements Measured<T> {
  private int numAccesses;
  private int numModifications;

  /**
   * Constructor for a MeasuredIndexedList.
   *
   * @param size         The size of the array.
   * @param defaultValue The initial value to set every object to in the array.
   */
  public MeasuredIndexedList(int size, T defaultValue) {
    super(size, defaultValue);
    numAccesses = 0;
    numModifications = 0;
  }

  @Override
  public int length() {
    return super.length();
  }

  @Override
  public T get(int index) throws IndexException {
    try {
      numAccesses++;
      return super.get(index);
    } catch (IndexException ex) {
      numAccesses--; // if exception, counteract access counter that was previously incremented
      throw ex;
    }
  }

  @Override
  public void put(int index, T value) throws IndexException {
    try {
      numModifications++;
      super.put(index,value);
    } catch (IndexException ex) {
      numModifications--; // if exception, counteract mutation counter that was previously incremented
      throw ex;
    }
  }

  @Override
  public void reset() {
    numAccesses = 0;
    numModifications = 0;
  }

  @Override
  public int accesses() {
    return numAccesses;
  }

  @Override
  public int mutations() {
    return numModifications;
  }

  @Override
  public int count(T value) {
    int count = 0;
    if (value == null) {
      return countNulls();
    } else {
      for (int k = 0; k < length(); k++) {
        if (value.equals(get(k))) {
          count++;
        }
      }
    }
    return count;
  }

  // method used to count occurrences of nulls in a list
  private int countNulls() {
    int count = 0;
    for (int k = 0; k < length(); k++) {
      if (get(k) == null) {
        count++;
      }
    }
    return count;
  }

}
