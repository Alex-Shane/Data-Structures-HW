package hw6;

import hw6.bst.AvlTreeMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * In addition to the tests in BinarySearchTreeMapTest (and in OrderedMapTest & MapTest),
 * we add tests specific to AVL Tree.
 */
@SuppressWarnings("All")
public class AvlTreeMapTest extends BinarySearchTreeMapTest {

  @Override
  protected Map<String, String> createMap() {
    return new AvlTreeMap<>();
  }

  @Test
  public void insertLeftRotation() {
    map.insert("1", "a");
    System.out.println(map.toString());
    // must print
    /*
        1:a
     */

    map.insert("2", "b");
    System.out.println(map.toString());
    // must print
    /*
        1:a,
        null 2:b
     */

    map.insert("3", "c"); // it must do a left rotation here!
    System.out.println(map.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
        "2:b",
        "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightRotation() {
    map.insert("3", "c");
    System.out.println(map.toString());
    // must print
    /*
        3:c
     */

    map.insert("2", "b");
    System.out.println(map.toString());
    // must print
    /*
        3:c,
        2:b null
     */

    map.insert("1", "a");
    System.out.println(map.toString());
    // must print
    /*
        2:b,
        1:a 3:c
     */

    String[] expected = new String[]{
            "2:b",
            "1:a 3:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertLeftRightRotation() {
    map.insert("7", "c");
    System.out.println(map.toString());
    // must print
    /*
        7:c
     */

    map.insert("3", "b");
    System.out.println(map.toString());
    // must print
    /*
        7:c,
        3:b null
     */

    map.insert("5", "a");
    System.out.println(map.toString());
    // must print
    /*
        5:a
        3:b 7:c
     */

    String[] expected = new String[]{
            "5:a",
            "3:b 7:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void insertRightLeftRotation() {
    map.insert("3", "c");
    System.out.println(map.toString());
    // must print
    /*
        3:c
     */

    map.insert("7", "b");
    System.out.println(map.toString());
    // must print
    /*
        3:c,
        null 7:b
     */

    map.insert("5", "a");
    System.out.println(map.toString());
    // must print
    /*
        5:a
        3:c 7:b
     */

    String[] expected = new String[]{
            "5:a",
            "3:c 7:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

}
