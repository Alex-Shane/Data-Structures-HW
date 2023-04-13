package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 * Map implemented as a Treap.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class TreapMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'rand'. ***/
  public static Random rand;
  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int numElements;

  /**
   * Make a TreapMap.
   */
  public TreapMap() {
    rand = new Random();
  }

  /**
   * Make a TreapMap with initial seed.
   * @param seed for random number generation
   */
  public TreapMap(int seed) {
    rand = new Random(seed);
  }


  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException("Cannot insert a null key");
    }
    root = insert(k, v, root);
    numElements++;
  }

  private Node<K, V> insert(K k, V v, Node<K, V> node) {
    if (node == null) { // base case: put new node in correct spot
      return new Node<>(k,v);
    }
    // search for correct spot to put new node
    int cmp = node.key.compareTo(k);
    if (cmp > 0) {
      node.left = insert(k, v, node.left);
      if (node.left.priority < node.priority) { // fix min heap property if violated
        node = rotateRight(node);
      }
    } else if (cmp < 0) {
      node.right = insert(k, v, node.right);
      if (node.right.priority < node.priority) { // fix min heap property if violated
        node = rotateLeft(node);
      }
    } else { // keys must be unique
      throw new IllegalArgumentException();
    }
    return node;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    Node<K, V> toRemove = findForSure(k, root);
    V val = toRemove.value;
    root = remove(k, root);
    numElements--;
    return val;
  }

  private Node<K, V> remove(K k, Node<K, V> node) {
    if (node == null) {
      return null;
    }
    // search for key down the tree
    if (k.compareTo(node.key) < 0) {
      node.left = remove(k, node.left);
    } else if (k.compareTo(node.key) > 0) {
      node.right = remove(k, node.right);
    } else { // at target node to remove
      if (node.left == null && node.right == null) { // if target is leaf, just remove by making null
        return null;
      } else if (node.left == null || node.right == null) { // remove node with one child
        node = removeNodeWithOneChild(k, node);
      } else { // removing node with two children
        node = removeNodeWithTwoChildren(k, node);
      }
    }
    // make sure as we go up tree, priorities are correct
    node = checkForRotations(node);
    return node;
  }

  private Node<K, V> checkForRotations(Node<K, V> node) {
    if (node.left != null && node.priority > node.left.priority) { // if left child has smaller priority, fix
      return rotateRight(node);
    } else if (node.right != null && node.priority > node.right.priority) { // if right child has smaller priority, fix
      return rotateLeft(node);
    } else { // if priorities are ok, no rotation needed
      return node;
    }
  }

  private Node<K, V> removeNodeWithOneChild(K k, Node<K, V> node) {
    if (node.left == null) {
      node = rotateLeft(node);
      node.left = remove(k, node.left);
    } else {
      node = rotateRight(node);
      node.right = remove(k, node.right);
    }
    return node;
  }

  private Node<K, V> removeNodeWithTwoChildren(K k, Node<K, V> node) {
    node.priority = Integer.MAX_VALUE; // make max value so that it becomes a leaf at some point
    if (node.left.priority > node.right.priority) {
      node = rotateLeft(node);
      node.left = remove(k, node.left);
    } else {
      node = rotateRight(node);
      node.right = remove(k, node.right);
    }
    return node;
  }

  private Node<K, V> rotateRight(Node<K, V> node) {
    Node<K, V> leftChild = node.left;
    node.left = leftChild.right;
    leftChild.right = node;
    return leftChild;
  }

  private Node<K, V> rotateLeft(Node<K, V> node) {
    Node<K, V> rightChild = node.right;
    node.right = rightChild.left;
    rightChild.left = node;
    return rightChild;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    Node<K, V> nodeToUpdate = find(k, root);
    if (nodeToUpdate == null) { // throw exception if target key doesn't exist in tree
      throw new IllegalArgumentException();
    }
    nodeToUpdate.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    }
    Node<K, V> target = find(k, root);
    if (target == null) { // throw exception if target key doesn't exist in tree
      throw new IllegalArgumentException();
    }
    return target.value;
  }

  @Override
  public boolean has(K k) {
    return find(k, root) != null;
  }

  private Node<K, V> find(K k, Node<K, V> node) {
    if (k == null) {
      throw new IllegalArgumentException("cannot handle null key");
    }
    if (node == null) {
      return null;
    } else if (node.key.compareTo(k) > 0) {
      return find(k, node.left);
    } else if (node.key.compareTo(k) < 0) {
      return find(k, node.right);
    } else {
      return node;
    }
  }

  private Node<K, V> findForSure(K k, Node<K, V> node) {
    Node<K, V> n = find(k, node);
    if (n == null) {
      throw new IllegalArgumentException("cannot find key " + k);
    }
    return n;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }


  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   * Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers. Since this is
   * a node class for a Treap we also include a priority field.
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int priority;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null
      key = k;
      value = v;
      priority = generateRandomInteger();
    }

    // Use this function to generate random values
    // to use as node priorities as you insert new
    // nodes into your TreapMap.
    private int generateRandomInteger() {
      // Note: do not change this function!
      return rand.nextInt();
    }

    @Override
    public String toString() {
      return key + ":" + value + ":" + priority;
    }

    @Override
    public BinaryTreeNode getLeftChild() {
      return left;
    }

    @Override
    public BinaryTreeNode getRightChild() {
      return right;
    }

  }

  private class InorderIterator implements Iterator<K> {
    private final Stack<Node<K, V>> stack;

    InorderIterator() {
      stack = new Stack<>();
      pushLeft(root);
    }

    private void pushLeft(Node<K, V> curr) {
      while (curr != null) {
        stack.push(curr);
        curr = curr.left;
      }
    }

    @Override
    public boolean hasNext() {
      return !stack.isEmpty();
    }

    @Override
    public K next() {
      Node<K, V> top = stack.pop();
      pushLeft(top.right);
      return top.key;
    }
  }
}
