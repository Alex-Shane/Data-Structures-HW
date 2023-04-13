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
  public void testInsertCausesNoRotation() {
    map.insert("5", "a");
    map.insert("6", "b");
    map.insert("4", "c");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "5:a",
            "4:c 6:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  @Test
  public void testInsertCausesLeftRotation() {
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
  public void testInsertCausesLeftRotationWithMoreElements() {
    map.insert("5", "a");
    map.insert("4", "b");
    map.insert("6", "c");
    map.insert("3", "d");
    map.insert("7", "e");
    map.insert("8", "f");
    System.out.println(map.toString());
    // must print
    /*
        5:a
        4:b 7:e
        3:d null 6:c 8:f
     */

    String [] expected = new String[] {
            "5:a",
            "4:b 7:e",
            "3:d null 6:c 8:f"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testInsertCausesRightRotation() {
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
  public void testInsertCausesRightRotationWithMoreElements() {
    map.insert("7", "a");
    map.insert("6", "b");
    map.insert("8", "c");
    map.insert("5", "d");
    map.insert("9", "e");
    map.insert("4", "f");
    System.out.println(map.toString());
    // must print
    /*
        7:a
        5:d 8:c
        4:f 6:b null 9:e
     */

    String [] expected = new String[] {
            "7:a",
            "5:d 8:c",
            "4:f 6:b null 9:e"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testInsertCausesLeftRightRotation() {
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
  public void testInsertCausesRightLeftRotation() {
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

  @Test
  public void testRemoveCausesNoRotation() {
    map.insert("7", "a");
    map.insert("5", "b");
    map.insert("8", "c");
    map.insert("9", "d");
    map.remove("9");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "7:a",
            "5:b 8:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }
  @Test
  public void testRemoveCausesRightRotation() {
    map.insert("7", "a");
    map.insert("5", "b");
    map.insert("8", "c");
    map.insert("6", "d");
    map.insert("9", "e");
    map.insert("4", "f");
    map.insert("3", "g");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "7:a",
            "5:b 8:c",
            "4:f 6:d null 9:e",
            "3:g null null null null null null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("6");
    System.out.println(map.toString());
    String[] expectedPostRemove = new String[]{
            "7:a",
            "4:f 8:c",
            "3:g 5:b null 9:e",
    };
    assertEquals((String.join("\n", expectedPostRemove) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesLeftRotation() {
    map.insert("5", "a");
    map.insert("4", "b");
    map.insert("7", "c");
    map.insert("3", "d");
    map.insert("6", "e");
    map.insert("8", "f");
    map.insert("9", "g");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "5:a",
            "4:b 7:c",
            "3:d null 6:e 8:f",
            "null null null null null null null 9:g"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("6");
    System.out.println(map.toString());
    String[] expectedPostRemove = new String[]{
            "5:a",
            "4:b 8:f",
            "3:d null 7:c 9:g"
    };
    assertEquals((String.join("\n", expectedPostRemove) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesLeftRightRotation() {
    map.insert("7", "a");
    map.insert("3", "b");
    map.insert("9", "c");
    map.insert("5", "d");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "7:a",
            "3:b 9:c",
            "null 5:d null null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("9");
    System.out.println(map.toString());
    String[] expectedPostRemoval = new String[]{
            "5:d",
            "3:b 7:a"
    };
    assertEquals((String.join("\n", expectedPostRemoval) + "\n"), map.toString());
  }

  @Test
  public void testRemoveCausesRightLeftRotation() {
    map.insert("5", "a");
    map.insert("4", "b");
    map.insert("7", "c");
    map.insert("6", "d");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "5:a",
            "4:b 7:c",
            "null null 6:d null"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());

    map.remove("4");
    System.out.println(map.toString());
    String[] expectedPostRemoval = new String[]{
            "6:d",
            "5:a 7:c"
    };
    assertEquals((String.join("\n", expectedPostRemoval) + "\n"), map.toString());
  }

  @Test
  public void testRemoveLeaf() {
    map.insert("2", "a");
    map.insert("3", "b");
    map.insert("1", "c");
    map.insert("4", "d");
    map.remove("4");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "2:a",
            "1:c 3:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveNodeWithOneChild() {
    map.insert("5", "a");
    map.insert("6", "b");
    map.remove("5");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "6:b"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }

  @Test
  public void testRemoveNodeWithTwoChildren() {
    map.insert("5", "a");
    map.insert("4", "b");
    map.insert("7", "c");
    map.remove("5");
    System.out.println(map.toString());
    String[] expected = new String[]{
            "4:b",
            "null 7:c"
    };
    assertEquals((String.join("\n", expected) + "\n"), map.toString());
  }


}
