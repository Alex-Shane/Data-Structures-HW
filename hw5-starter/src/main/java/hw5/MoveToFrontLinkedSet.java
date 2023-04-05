package hw5;

/**
 * Set implemented using a doubly linked list and move-to-front heuristic.
 *
 * @param <T> Element type.
 */
public class MoveToFrontLinkedSet<T> extends LinkedSet<T> {

  @Override
  protected Node<T> find(T t) {
    for (Node<T> n = head; n != null; n = n.next) {
      if (n.data.equals(t)) {
        if (n == tail && n != head) { // move tail to head and adjust pointers
          moveTail(n);
        } else if (n != head) { // adjust pointers accordingly
          Node<T> prev = n.prev;
          Node<T> next = n.next;
          prev.next = next;
          next.prev = prev;
          n.next = head;
          head.prev = n;
          n.prev = null;
          head = n;
        }
        return n;
      }
    }
    return null;
  }

  private void moveTail(Node<T> target) {
    Node<T> prev = target.prev;
    prev.next = null;
    tail = prev;
    target.next = head;
    target.prev = null;
    head.prev = target;
    head = target;
  }


  /**
   * main method for MoveToFrontLinkedSet that performs a series of operations on a set.
   *
   * @param args takes input as a string but we don't use in this method.
   */
  public static void main(String[] args) {
    LinkedSet<Integer> set = new MoveToFrontLinkedSet<>();
    set.insert(5);
    set.insert(8);
    set.insert(9);
    System.out.println(set.has(5));
    System.out.println(set.has(8));
    System.out.println(set.has(9));
    set.remove(8);
    System.out.println(set.has(8));
    set.remove(9);
    System.out.println(set.has(9));
    set.remove(5);
    System.out.println(set.has(5));
  }
}
