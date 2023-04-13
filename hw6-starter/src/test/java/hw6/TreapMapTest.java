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

    String[] expected = new String[]{
            "3:c:-1157408321",
            "1:a:379066948 null",
            "null 2:b:758500184 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testInsertCausingLeftRotation() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("3", "c");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "1:a:-1157408321",
            "null 3:c:379066948",
            "null null 2:b:758500184 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testInsertCausingMultipleRotations() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("4", "d");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "4:d:-1667228448",
            "1:a:-1157408321 null",
            "null 3:c:379066948 null null",
            "null null 2:b:758500184 null null null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testInsertCausingNoRotations() {
    map.insert("5", "a");
    map.insert ("4", "b");
    map.insert("6", "c");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "5:a:-1157408321",
            "4:b:758500184 6:c:379066948"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesNoRotation() {
    map.insert("5", "a");
    map.insert ("4", "b");
    map.insert("6", "c");
    map.remove("6");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "5:a:-1157408321",
            "4:b:758500184 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesLeftRotation() {
    map.insert("5", "a");
    map.insert ("6", "b");
    map.remove("5");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "6:b:758500184"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesRightRotation() {
    map.insert("6", "a");
    map.insert ("5", "b");
    map.remove("6");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "5:b:758500184"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesMultipleRotations() {
    map.insert("3", "a");
    map.insert("2", "b");
    map.insert("4", "c");
    map.remove("3");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "4:c:379066948",
            "2:b:758500184 null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveLeaf() {
    map.insert("3", "c");
    map.insert("2", "b");
    map.insert("1", "a");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "3:c:-1157408321",
            "1:a:379066948 null",
            "null 2:b:758500184 null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());


    map.remove("2");
    System.out.println(map.toString());
    String[] expectedPostRemoval = new String[]{
            "3:c:-1157408321",
            "1:a:379066948 null",
    };
    assertEquals((String.join("\n", expectedPostRemoval) + "\n"), map.toString());
  }

  @Test
  public void testRemoveNodeWithOneChild() {
    map.insert("1", "a");
    map.insert("2", "b");
    map.insert("3", "c");
    map.insert("4", "d");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "4:d:-1667228448",
            "1:a:-1157408321 null",
            "null 3:c:379066948 null null",
            "null null 2:b:758500184 null null null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("3");
    System.out.println(map.toString());
    String[] expectedPostRemoval = new String[]{
            "4:d:-1667228448",
            "1:a:-1157408321 null",
            "null 2:b:758500184 null null",
    };
    assertEquals((String.join("\n", expectedPostRemoval) + "\n"), map.toString());
  }

  @Test
  public void testRemoveNodeWithTwoChildren() {
    map.insert("5", "a");
    map.insert ("4", "b");
    map.insert("6", "c");
    System.out.println(map.toString());

    String[] expected = new String[]{
            "5:a:-1157408321",
            "4:b:758500184 6:c:379066948"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("5");
    System.out.println(map.toString());
    String[] expectedPostRemoval = new String[]{
            "6:c:379066948",
            "4:b:758500184 null"
    };
    assertEquals((String.join("\n", expectedPostRemoval) + "\n"), map.toString());
  }

}