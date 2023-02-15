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
  Node<T> head;
  int length;
  T defaultValue;

  /**
   * Constructs a new SparseIndexedList of length size
   * with default value of defaultValue.
   *
   * @param size Length of list, expected: size > 0.
   * @param defaultValue Default value to store in each slot.
   * @throws LengthException if size <= 0.
   */
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
    // check if modified
    // if not, return default value
    // if it has, return value of node at index
    if (index < 0 || index >= length) {
      throw new IndexException();
    }
    if (positionHasBeenModified(index)) {
      Node<T> cur = head;
      while (cur != null) {
        if (cur.index == index) {
          return cur.data;
        }
        cur = cur.next;
      }
    }
    // return defaultValue if given index hasn't been modified
    return defaultValue;
  }

  @Override
  public void put(int index, T value) throws IndexException {
    if (index < 0 || index >= length) {
      throw new IndexException();
    }
    Node<T> cur = head;
    if (positionHasBeenModified(index)) {
      putAtModifiedPosition(cur, index, value);
    } else {
      if (value != defaultValue) {
        putAtNonModifiedPosition(cur, index, value);
      }
      // regardless of if value == defaultValue, increment length of list
      length++;
    }
  }

  private void putAtModifiedPosition(Node<T> cur, int index, T value) {
    Node<T> prev = null;
    while (cur != null) {
      if (cur.index == index) {
        if (value == defaultValue) {
          removeNode(cur, prev);
        } else {
          cur.data = value;
        }
      }
      prev = cur;
      cur = cur.next;
    }
  }

  private void removeNode(Node<T> cur, Node<T> prev) {
    // if prev node exists, skip over default value node to "delete" it
    if (prev != null) {
      prev.next = cur.next;
    } else { // if no prev node, then cur at head, so make head point to next node if it exists
      if (cur.next == null) {
        head = null;
      } else {
        head = cur.next;
      }
    }
  }

  private void putAtNonModifiedPosition(Node<T> cur, int index, T value) {
    Node<T> add;
    if (head == null) {
      add = new Node<>(value, index, null);
      head = add;
    } else {
      if (index < head.index) { // if new position is before head, add node to front of list and make it head
        add = new Node<>(value, index, head);
        head = add;
      }
      while (cur != null) { // add the node when target index is before index of next node in the list
        if (cur.next != null && cur.next.index > index) {
          add = new Node<>(value, index, cur.next);
          cur.next = add;
        }
        cur = cur.next;
      }
    }
  }

  /**
   * Helper function for put that checks if a Node object
   * exists for a given index in the list.
   *
   * @param index position to be modified, expected: index >= 0 and index < length.
   * @return true if Node object exists at index, false otherwise
   */
  private boolean positionHasBeenModified(int index) {
    Node<T> cur = head;
    while (cur != null) {
      if (cur.index == index) {
        return true;
      }
      cur = cur.next;
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    return new SparseIndexedListIterator();
  }

  /**
   * An object to store and link unique data in sparse list.
   *
   * @param <T> Element type.
   */
  private static class Node<T> {
    T data;
    int index;
    Node<T> next;

    /**
     * Constructs a new Node object with given data, index, and Node to point to.
     *
     * @param data element value
     * @param index position within the list,
     *              pre: index >= 0 and index < length
     */
    Node(T data, int index, Node<T> next) {
      this.data = data;
      this.index = index;
      this.next = next;
    }
  }

  private class SparseIndexedListIterator implements Iterator<T> {
    private Node<T> curNode;
    private int curPosition;

    SparseIndexedListIterator() {
      curNode = null;
    }

    @Override
    public boolean hasNext() {
      return curPosition < length;
    }

    @Override
    public T next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      if (curPosition == head.index) { // set curNode to head once head is reached in iteration
        curNode = head;
        curPosition++;
        return head.data;
      } else if (curPosition < head.index) { // if head hasn't been reached, return default
        curPosition++;
        return defaultValue;
      } else { // check if Node object at curPosition, if not return default
        if (curNode.next != null && curNode.next.index == curPosition) {
          curPosition++;
          curNode = curNode.next;
          return curNode.data;
        } else {
          curPosition++;
          return defaultValue;
        }
      }
    }
  }
}
