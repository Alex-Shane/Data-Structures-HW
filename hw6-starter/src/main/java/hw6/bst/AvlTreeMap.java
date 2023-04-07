package hw6.bst;

import hw6.OrderedMap;
import java.util.EmptyStackException;
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
    if (k == null) {
      throw new IllegalArgumentException();
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
    } else if (cmp < 0) {
      node.right = insert(k, v, node.right);
    } else {
      throw new IllegalArgumentException();
    }
    // update height and bf of current node
    updateNodeHeight(node);
    updateNodeBF(node);
    if (node.bf > 1 || node.bf < -1) { // balance node if needed
      node = balanceNode(k, node);
    }
    return node;
  }

  private void updateNodeHeight(Node<K, V> node) {
    if (node == null) {
      return;
    }
    if (node.left == null) {
      if (node.right == null) { // if no children, height is zero
        node.height = 0;
      } else { // if only right child, height = height right child + 1
        node.height = 1 + node.right.height;
      }
    } else if (node.right == null) { // if only left child, height = height left child + 1
      node.height = 1 + node.left.height;
    } else { // if two children, height = 1 + bigger of the two children's heights
      node.height = 1 + Math.max (node.left.height, node.right.height);
    }
  }

  private void updateNodeBF(Node<K, V> node) {
    int leftSubtreeHeight = 0;
    int rightSubTreeHeight = 0;
    if (node.left != null) {
      leftSubtreeHeight = 1 + node.left.height;
    }
    if (node.right != null) {
      rightSubTreeHeight = 1 + node.right.height;
    }
    node.bf = leftSubtreeHeight - rightSubTreeHeight;
  }

  private Node<K, V> balanceNode(K k, Node<K, V> node) {
    int nodeBF = node.bf;
    if (nodeBF < -1) { // denotes right heavy tree
      if (node.right.bf <= 0) { // if parent bf = -2 and right child bf = -1 or 0, rotate left
        node = rotateLeft(k, node);
      } else { // perform RightLeft rotation if right child bf = 1
        node = rotateRightLeft(k, node);
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
    // perform rotation
    Node<K, V> leftChild = node.left;
    node.left = leftChild.right;
    leftChild.right = node;
    //decrement heights before updating
    node.height--;
    leftChild.height--;
    // update heights and balance factors
    updateNodeHeight(node);
    updateNodeHeight(leftChild);
    updateNodeBF(node);
    updateNodeBF(leftChild);
    return leftChild;
  }

  private Node<K, V> rotateLeft(K k, Node<K, V> node) {
    // perform rotation
    Node<K, V> rightChild = node.right;
    node.right = rightChild.left;
    rightChild.left = node;
    // decrement heights before updating
    node.height--;
    rightChild.height--;
    // update heights and balance factors
    updateNodeHeight(node);
    updateNodeHeight(rightChild);
    updateNodeBF(node);
    updateNodeBF(rightChild);
    return rightChild;
  }

  private Node<K, V> rotateRightLeft(K k, Node<K, V> node) {
    node.right = rotateRight(k, node.right);// first perform right rotation on right child
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
