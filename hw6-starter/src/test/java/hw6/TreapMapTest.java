package hw6;

import hw6.bst.TreapMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to Treap.
 */
@SuppressWarnings("All")
public class TreapMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new TreapMap<>(5);
  }

  /*
  first 10 numbers generated from seed:
-1157408321
758500184
379066948
-1667228448
2099829013
-236332086
1983575708
-745993913
1926715444
1836354642
   */

  @Test
  public void testInsertCausingRightRotation() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");
    System.out.println(map.toString());
  }

}