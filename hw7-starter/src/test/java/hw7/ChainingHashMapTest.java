package hw7;

import hw7.hashing.ChainingHashMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashMapTest extends MapTest {
  @Override
  protected Map<String, String> createMap() {
    return new ChainingHashMap<>();
  }

  @Test
  public void insertMultipleElement() {
    map.insert("key1", "value1");
    map.insert("key2", null);
    assertTrue(map.has("key1"));
    assertTrue(map.has("key2"));
    assertEquals(2, map.size());
    int counter = 0;
    for (String s: map) {
      counter++;
    }
    assertEquals(2, counter);
  }

}