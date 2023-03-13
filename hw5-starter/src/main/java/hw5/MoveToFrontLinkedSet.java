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
        if (n == tail && n != head) {
          moveTail(n);
        } else if (n != head) {
          Node<T> prev = n.prev;
          Node<T> next = n.next;
          prev.next = next;
          next.prev = prev;
          n.next = head;
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
    head = target;
  }
}
