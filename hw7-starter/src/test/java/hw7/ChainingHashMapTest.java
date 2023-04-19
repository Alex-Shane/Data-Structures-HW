package hw7;

import hw7.hashing.ChainingHashMap;
import org.junit.jupiter.api.Test;

class ChainingHashMapTest extends MapTest {
  @Override
  protected Map<String, String> createMap() {
    return new ChainingHashMap<>();
  }

}