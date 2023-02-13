package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * An implementation of an IndexedList designed for cases where
 * only a few positions have distinct values from the initial value.
 *
 * @param <T> Element type.
 */
public class SparseIndexedList<T> implements IndexedList<T> {
  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
  Node<T> head;
  int length;
  T defaultValue;
  public SparseIndexedList(int size, T defaultValue) throws LengthException {
    if (size <= 0) {
      throw new LengthException();
    }
    length = size;
    this.defaultValue = defaultValue;
    head = null;
  }

  @Override
  public int length() {
    return this.length;
  }

  @Override
  public T get(int index) throws IndexException {
    // TODO
    return null;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    // TODO
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  private class Node<T> {
    T data;
    int index;
    Node<T> next;
    Node (T data, int index, Node<T> next) {
      this.data = data;
      this.index = index;
      this.next = next;
    }
  }
  private class SparseIndexedListIterator implements Iterator<T> {
    private Node<T> current;
    int elementsTraversed;
    SparseIndexedListIterator() { current = head; }
    @Override
    public boolean hasNext() {
      return elementsTraversed < length;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      return null;
    }
  }
}
