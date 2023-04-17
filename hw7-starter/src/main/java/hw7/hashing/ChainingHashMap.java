package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.LinkedList;

public class ChainingHashMap<K, V> implements Map<K, V> {
  private LinkedList<Pair<K,V>> [] map;
  private int numElements;
  private int capacity;
  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    // TODO Implement Me!
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    // TODO Implement Me!
    return null;
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = k.hashCode() % capacity;
    LinkedList<Pair<K, V>> chain = map[index];
    Pair<K, V> targetPair = findPairInChain(k, chain);
    int indexInChain = chain.indexOf(targetPair);
    chain.set(indexInChain, new Pair<>(k,v));
  }

  @Override
  public V get(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = k.hashCode() % capacity;
    LinkedList<Pair<K, V>> chain = map[index];
    Pair<K, V> targetPair = findPairInChain(k, chain);
    if (targetPair != null) {
      return targetPair.getValue();
    }
    return null;
  }

  @Override
  public boolean has(K k) {
    if (k == null) {
      return false;
    }
    int index = k.hashCode() % capacity;
    return map[index].size() != 0;
  }

  private Pair<K, V> findPairInChain(K k, LinkedList<Pair<K, V>> chain) {
    for (Pair<K, V> pair : chain) {
      if (pair.getKey().equals(k)) {
        return pair;
      }
    }
    return null;
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new ChainingHashMapIterator();
  }

  private static class Pair<K, V> {
    private final K key;
    private final V value;

    Pair(K k, V v) {
      key = k;
      value = v;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }
  }

  private class ChainingHashMapIterator implements Iterator<K> {
    private int curMapIndex;
    private int curChainIndex;
    private LinkedList<Pair<K, V>> curChain;

    ChainingHashMapIterator() {
      curMapIndex = 0;
      curChainIndex = 0;
    }

    public boolean hasNext() {
      return curMapIndex < capacity;
    }

    public K next() {
      Pair<K, V> pair = curChain.get(curChainIndex);
      if (curChainIndex + 1 == curChain.size()) {
        curMapIndex++;
        curChainIndex = 0;
        curChain = map[curMapIndex];
      } else {
        curChainIndex++;
      }
      return pair.getKey();
    }
  }
}
