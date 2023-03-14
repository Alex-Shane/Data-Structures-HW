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

  public static void main(String[] args) {
    LinkedSet<Integer> set = new MoveToFrontLinkedSet<Integer>();
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
