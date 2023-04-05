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
    int compare = node.key.compareTo(k);
    if (compare > 0) {
      if (node.right == null) {

      }
    }
  }

  private Node<K, V> rebalanceNode(K k, Node<K, V> node) {
    int nodeBF = node.bf;
    if (nodeBF >= -1 && nodeBF <= 1) {
      return null;
    }
    if (nodeBF < -1) { // denotes right heavy tree
      if (node.right.bf <= 0) { // if parent bf = -2 and right child bf = -1 or 0, rotate left
        node = rotateLeft(k, node);
      } else { // perform RightLeft rotation if right child bf = 1
        node = rotateLeftRight(k, node);
      }
    } else { // denotes left heavy
      if (node.left.bf >= 0) { // if parent bf = -2 and left child bf = 0 or 1
        node = rotateRight(k, node);
      } else {
        node = rotateLeftRight(k, node);
      }
    }
    return node;
  }

  private Node<K, V> rotateRight(K k, Node<K, V> node) {
    Node<K, V> leftChild = node.left; // leftChild is root of rotated tree
    node.left = leftChild.right; // put keys larger than new root and smaller than old root to the left of old root
    leftChild.right = node; // move original root to right of new root
    updateHeight(k, node); // update old root height
    updateHeight(k, leftChild); // update new root height
    return leftChild;
  }

  private Node<K, V> rotateLeft(K k, Node<K, V> node) {
    Node<K, V> rightChild = node.right; // rightChild is new root
    node.right = rightChild.left; // move keys between old root and new root to the right of old root
    rightChild.left = node; // move old root to the left of new root
    updateHeight(k, node); // update old root height
    updateHeight(k, rightChild); // update new root height
    return rightChild;
  }

  private Node<K, V> rotateRightLeft(K k, Node<K, V> node) {
    node.right = rotateRight(k, node.right); // first perform right rotation on right child
    return rotateLeft(k, node); // then perform left rotation on original root, returning new root after rotation
  }

  private Node<K, V> rotateLeftRight(K k, Node<K, V> node) {
    node.left = rotateLeft(k, node.left); // first perform left rotation on left child
    return rotateRight(k, node); // // then perform right rotation on original root, returning new root after rotation
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
