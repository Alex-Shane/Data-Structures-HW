package hw7.hashing;

import hw7.Map;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class OpenAddressingHashMap<K, V> implements Map<K, V> {
  private Pair<K,V>[] map;
  private int numElements;
  private int capacity;
  private final int[] primes = {3, 5, 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853,
                                25717, 51437,102877, 205759, 411527, 823117, 1646237,3292489, 6584983, 13169977};
  private int primeIndex;

  public OpenAddressingHashMap() {
    numElements = 0;
    capacity = primes[0];
    primeIndex = 1;
    map = (Pair<K, V>[]) new Pair[capacity];
  }

  @Override
  public void insert(K k, V v) throws IllegalArgumentException {
    if (k == null) {
      throw new IllegalArgumentException();
    } else if (getLoadFactor() >= 0.65) {
      rehash();
    }
    int index = k.hashCode() % capacity;
    if (map[index] != null) {
      index = probe(k);
      if (index == -1) {
        throw new IllegalArgumentException();
      }
    }
    map[index] = new Pair<>(k, v);
    numElements++;
  }

  @Override
  public V remove(K k) throws IllegalArgumentException {
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = findTrueIndex(k);
    map[index].makeTS();
    numElements--;
    return map[index].getValue();
  }

  @Override
  public void put(K k, V v) throws IllegalArgumentException { // check for if element isn't at expected index
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = findTrueIndex(k);
    map[index].setValue(v);
  }

  @Override
  public V get(K k) throws IllegalArgumentException { // need to add in check for if element isn't at expected index
    if (k == null || !has(k)) {
      throw new IllegalArgumentException();
    }
    int index = findTrueIndex(k);
    return map[index].getValue();
  }

  @Override
  public boolean has(K k) { // need to add in check for if element couldn't get added at correct index
    if (k == null) {
      return false;
    }
    int index = findTrueIndex(k);
    return index != -1;
  }

  private int findTrueIndex(K k) {
    for (int i = 0; i < capacity; i++) {
      int index = (k.hashCode() + i) % capacity;
      if (map[index] != null && map[index].getKey().equals(k) && !map[index].isTS()) {
        return index;
      }
    }
    return -1; // table is full or k not mapped
  }

  private int probe(K k) {
    for (int i = 0; i < capacity; i++) {
      int index = (k.hashCode() + i) % capacity;
      if (map[index] != null && map[index].getKey().equals(k)) {
        return -1;
      }
      if (map[index] == null || map[index].isTS()) {
        return index;
      }
    }
    return -2; // table is full
  }

  private double getLoadFactor() {
    return (numElements * 1.0) / capacity;
  }

  private void rehash() {
    if (primeIndex <= 22) {
      capacity = primes[primeIndex];
    } else {
      capacity = capacity * 2;
    }
    primeIndex++;
    numElements = 0;
    Pair<K, V>[] newMap = map;
    map = new Pair[capacity];
    for (Pair<K, V> pair: newMap) {
      if (pair == null || pair.isTS()) {
        continue;
      }
      insert(pair.getKey(), pair.getValue());
    }
  }

  @Override
  public int size() {
    return numElements;
  }

  @Override
  public Iterator<K> iterator() {
    return new OpenAddressingHashMapIterator();
  }

  private static class Pair<K, V> {
    private final K key;
    private V value;
    private boolean ts;

    Pair(K k, V v) {
      key = k;
      value = v;
      ts = false;
    }

    public K getKey() {
      return key;
    }

    public V getValue() {
      return value;
    }

    public void setValue(V v) {
      value = v;
    }

    public boolean isTS() {
      return ts;
    }

    public void makeTS() {
      ts = true;
    }
  }

  private class OpenAddressingHashMapIterator implements Iterator<K> {
    private int curMapIndex;
    private int elementsTraversed;

    OpenAddressingHashMapIterator() {
      elementsTraversed = 0;
      curMapIndex = 0;
    }

    public boolean hasNext() {
      return elementsTraversed < numElements;
    }

    public K next() throws NoSuchElementException {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }
      Pair<K, V> curPair = map[curMapIndex];
      if (curPair == null || curPair.isTS()) {
        curMapIndex++;
        return next();
      }
      curMapIndex++;
      elementsTraversed++;
      return curPair.getKey();
    }
  }
}
