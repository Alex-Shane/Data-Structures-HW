package hw7;

import hw7.hashing.ChainingHashMap;
import org.junit.jupiter.api.Test;

class ChainingHashMapTest extends MapTest {
  @Override
  protected Map<String, String> createMap() {
    return new ChainingHashMap<>();
  }

  @Test
  public void testRehashWorks() {
    map.insert("apple", "pie");
    map.insert("key-lime", "pie");
    map.insert("pecan", "pie");
    map.insert("chocolate", "pie");
    for (String s: map) {
      System.out.println(s);
    }
  }

}