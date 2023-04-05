package hw6.bst;

import hw6.OrderedMap;
import java.util.Iterator;
import java.util.Stack;

/**
 * Map implemented as an AVL Tree.
 *
 * @param <K> Type for keys.
 * @param <V> Type for values.
 */
public class AvlTreeMap<K extends Comparable<K>, V> implements OrderedMap<K, V> {

  /*** Do not change variable name of 'root'. ***/
  private Node<K, V> root;
  private int numElements;

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // perform normal insertion for BST
    root = insertPair(k, v, root);
    // update heights and balance factors
    // search for imbalanced node, and rebalance the deepest node
  }

  private Node<K, V> insertPair(K k, V v, Node<K, V> node) {
    if (node == null) {
      return new Node<K,V>(k,v);
    }
    if (node.key.compareTo(k) > 0) {
      node.left = insertPair(k, v, node.left);
    } else if (node.key.compareTo(k) < 0) {
      node.right = insertPair(k, v, node.right);
    }
    return node;
  }

  private int updateHeight(K k, Node<K, V> node) {
    if (node == null) { // node that doesn't exist has height of -1
      return -1;
    }
    
  }


  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    Node<K, V> nodeToUpdate = find(k, root);
    if (nodeToUpdate == null) { // throw exception if target key doesn't exist in tree
      throw new IllegalArgumentException();
    }
    nodeToUpdate.value = v;
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
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

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new InorderIterator();
  }

  private Node<K, V> find(K k, Node<K, V> node) {
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

  /*** Do not change this function's name or modify its code. ***/
  @Override
  public String toString() {
    return BinaryTreePrinter.printBinaryTree(root);
  }

  /**
   * Feel free to add whatever you want to the Node class (e.g. new fields).
   * Just avoid changing any existing names, deleting any existing variables,
   * or modifying the overriding methods.
   *
   * <p>Inner node class, each holds a key (which is what we sort the
   * BST by) as well as a value. We don't need a parent pointer as
   * long as we use recursive insert/remove helpers.</p>
   **/
  private static class Node<K, V> implements BinaryTreeNode {
    Node<K, V> left;
    Node<K, V> right;
    K key;
    V value;
    int height;
    int bf;

    // Constructor to make node creation easier to read.
    Node(K k, V v) {
      // left and right default to null and height and bf default to zero
      key = k;
      value = v;
      height = 0;
      bf = 0;
    }

    @Override
    public String toString() {
      return key + ":" + value;
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
