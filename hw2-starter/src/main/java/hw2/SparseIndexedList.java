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
  private Node<T> head;
  private final int length;
  private final T defaultValue;

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

  /**
   * Finds a Node object at a given index in the list if it exists.
   *
   * @param index position to be modified
   * @return Node at index if Node object exists, null otherwise
   */
  private Node<T> findNode(int index) {
    Node<T> cur = head;
    while (cur != null) {
      if (cur.index == index) {
        return cur;
      }
      cur = cur.next;
    }
    return null;
  }

  /**
   * Finds the Node previous to the target Node of a put() operation.
   *
   * @param index position of target Node
   * @return Node previous to target Node if target Node != head, null otherwise
   */
  private Node<T> findPrevNode(int index) {
    Node<T> cur = head;
    Node<T> prev = null;
    if (index == 0) {
      return null;
    }
    while (cur != null) {
      if (cur.index == index) {
        // return prev since we want Node before target index
        return prev;
      }
      prev = cur;
      cur = cur.next;
    }
    return null;
  }

  @Override
  public T get(int index) throws IndexException {
    if (index < 0 || index >= length) {
      throw new IndexException();
    }
    Node<T> target = findNode(index);
    if (target != null) {
      return target.data;
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
    Node<T> target = findNode(index);
    if (target != null) {
      putAtModifiedPosition(target,index,value);
    } else {
      if (!defaultValue.equals(value)) {
        putAtNonModifiedPosition(cur, index, value);
      }
    }
  }

  /**
   * Performs the put() operation when the target index has already been modified.
   *
   * @param index position of target Node
   * @param target Node to be modified
   * @param value data to replace current target data
   */
  private void putAtModifiedPosition(Node<T> target,int index, T value) {
    Node<T> prev = findPrevNode(index);
    if (defaultValue.equals(value)) {
      removeNode(target,prev);
    } else { // if unique data, then put it into target node
      target.data = value;
    }
  }

  /**
   * Removes a Node from the list.
   *
   * @param cur Node that we want to remove
   * @param prev Node before cur, used to link to node after cur to delete cur
   */
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

  /**
   * Performs the put() operation when the target index has not been modified.
   *
   * @param index position to add Node at
   * @param cur tracks current Node we are looking at in list throughout method. initial value is head
   * @param value data to put into Node at index
   */
  private void putAtNonModifiedPosition(Node<T> cur, int index, T value) {
    Node<T> add;
    if (head == null) { // node to add is simply head
      add = new Node<>(value, index, null);
      head = add;
    } else if (index < head.index) { // if new position is before head, add node to front of list and make it head
      add = new Node<>(value, index, head);
      head = add;
    } else if (cur.next == null && cur.index + 1 == index) { // if only one node in list, add node next to it
      add = new Node<>(value, index, null);
      head.next = add;
    } else {
      addNodeAfterHead(cur, index, value);
    }
  }

  /**
   * Adds a Node to the list when index > head.index.
   *
   * @param index position to add Node at
   * @param cur tracks current Node we are looking at in list throughout method. initial value is head
   * @param value data to put into Node at index
   */
  private void addNodeAfterHead(Node<T> cur, int index, T value) {
    Node<T> add;
    while (cur != null) { // add the node when target index is before index of next node in the list
      if (cur.next != null && cur.next.index > index) {
        add = new Node<>(value, index, cur.next);
        cur.next = add;
        break;
      } else if (cur.next == null) { // if adding Node with the largest index in list
        add = new Node<>(value,index,null);
        cur.next = add;
        break;
      }
      cur = cur.next;
    }
  }

  // method returns head node
  public Node<T> getHead() {
    return head;
  }

  // method returns data stored in head
  public T getHeadData() {
    return head.data;
  }

  public T getDefaultValue() {
    return defaultValue;
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
    private T data;
    private final int index;
    private Node<T> next;

    /**
     * Constructs a new Node object with given data, index, and Node to point to.
     *
     * @param data element value
     * @param index position within the list,
     *              pre: index >= 0 and index < length
     * @param next points to the next Node in the list, null if last Node in list
     */
    Node(T data, int index, Node<T> next) {
      this.data = data;
      this.index = index;
      this.next = next;
    }
  }

  // Iterator for SparseIndexedList
  private class SparseIndexedListIterator implements Iterator<T> {
    private Node<T> curNode;
    private int curPosition;

    /**
     * Constructs a new SparseIndexedListIterator with default values
     * for curNode and curPosition.
     */
    SparseIndexedListIterator() {
      curNode = null;
      curPosition = 0;
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
      if (head == null || curPosition <= head.index) {
        return nextIfPositionBeforeHead();
      } else {
        return nextIfPositionAfterHead();
      }
    }

    /**
     * Performs the next() operation when curPosition <= head index or if head doesn't exist.
     *
     * @return data stored in head if curPosition = head, defaultValue otherwise
     */
    private T nextIfPositionBeforeHead() {
      if (head != null && curPosition == head.index) { // set curNode to head once head is reached in iteration
        curNode = head;
        curPosition++;
        return head.data;
      } else { // if head hasn't been reached, return default
        curPosition++;
        return defaultValue;
      }
    }

    /**
     * Performs the next() operation when curPosition > head index.
     *
     * @return data stored in Node if Node exists at curPosition, defaultValue otherwise
     */
    private T nextIfPositionAfterHead() {
      // check if Node object at next position, if not return default
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
