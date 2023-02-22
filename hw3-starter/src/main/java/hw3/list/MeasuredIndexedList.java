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

  /**
   * Constructor for a MeasuredIndexedList.
   *
   * @param size         The size of the array.
   * @param defaultValue The initial value to set every object to in the array..
   */
  public MeasuredIndexedList(int size, T defaultValue) {
    super(size, defaultValue);
    // TODO: Implement me
  }

  @Override
  public int length() {
    return super.length();
  }

  @Override
  public T get(int index) throws IndexException {
    // TODO: Implement me
    return null;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    // TODO: Implement me
  }

  @Override
  public void reset() {
    // TODO: Implement me
  }

  @Override
  public int accesses() {
    return 0; // TODO: Implement me
  }

  @Override
  public int mutations() {
    return 0; // TODO: Implement me
  }

  @Override
  public int count(T value) {
    return 0; // TODO: Implement me
  }

}
