package hw7.hashing;

import hw7.Map;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ChainingHashMap<K, V> implements Map<K, V> {
  private LinkedList<Pair<K,V>> [] map;
  private int numElements;
  private int capacity;
  private final int[] primes = {3, 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437,102877, 205759, 411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeIndex;

  public ChainingHashMap() {
    numElements = 0;
    primeIndex = 1; // want first rehash to be size 5
    capacity = primes[0];
    map = (LinkedList<Pair<K,V>>[]) Array.newInstance(LinkedList.class, capacity);
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    } else if (getLoadFactor() >= 0.8) {
      rehash();
    }
    int index = k.hashCode() % capacity;
    LinkedList<Pair<K, V>> chain;
    if (map[index] == null) {
      map[index] = new LinkedList<>();
    }
    chain = map[index];
    if (findPairInChain(k, chain) != null) { // ie the key is already mapped
      throw new IllegalArgumentException();
    }
    chain.add(new Pair<>(k,v));
    numElements++;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = k.hashCode() % capacity;
    LinkedList<Pair<K, V>> chain = map[index];
    Pair<K, V> targetPair = findPairInChain(k, chain);
    if (targetPair != null) {
      chain.remove(targetPair);
      if (chain.size() == 0) {
        map[index] = null;
      }
      numElements--;
      return targetPair.getValue();
    }
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
    Pair<K, V> targetPair = findPair(k);
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
    return map[index] != null;
  }

  private Pair<K, V> findPairInChain(K k, LinkedList<Pair<K, V>> chain) {
    for (Pair<K, V> pair : chain) {
      if (pair.getKey().equals(k)) {
        return pair;
      }
    }
    return null;
  }

  private Pair<K, V> findPair(K k) {
    int index = k.hashCode() % capacity;
    LinkedList<Pair<K, V>> chain = map[index];
    return findPairInChain(k, chain);
  }

  private double getLoadFactor() {
    return (numElements * 1.0) / capacity;
  }

  private void rehash() {
    LinkedList<Pair<K,V>> [] newMap = map;
    if (primeIndex <= 22) {
      capacity = primes[primeIndex];
    } else {
      capacity = capacity * 2;
    }
    map = (LinkedList<Pair<K,V>>[]) Array.newInstance(LinkedList.class, capacity);
    /*final LinkedList<Pair<K,V>> [] temp = map;
    if (primeIndex <= 22) {
      capacity = primes[primeIndex];
    } else {
      capacity *= 2;
    }*/
    primeIndex++;
    numElements = 0;
    for (LinkedList<Pair<K, V>> list: newMap) {
      if (list == null) {
        continue;
      }
      for (Pair<K, V> pair: list) {
        insert(pair.getKey(), pair.getValue());
      }
    }
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
      curChain = map[0];
    }

    public boolean hasNext() {
      if (numElements == 0) {
        return false;
      }
      return curMapIndex < capacity;
    }

    public K next() {
      if (curChain == null) {
        curMapIndex++;
        curChainIndex = 0;
        curChain = map[curMapIndex];
        return next();
      }
      Pair<K, V> pair = curChain.get(curChainIndex);
      if (curChainIndex + 1 == curChain.size()) {
        curMapIndex++;
        curChainIndex = 0;
        if (curMapIndex >= capacity) {
          curChain = null;
        } else {
          curChain = map[curMapIndex];
        }
      } else {
        curChainIndex++;
      }
      return pair.getKey();
    }
  }
}
