package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit Tests for any class implementing the IndexedList interface.
 */
public abstract class IndexedListTest {
  protected static final int LENGTH = 10;
  protected static final int INITIAL = 7;
  private IndexedList<Integer> indexedList;

  public abstract IndexedList<Integer> createArray();
  public abstract IndexedList<Integer> createSizeZeroArray();
  public abstract IndexedList<Integer> createNegativeSizeArray();

  @BeforeEach
  public void setup() {
    indexedList = createArray();
  }

  @Test
  @DisplayName("get() returns the default value after IndexedList is instantiated.")
  void testGetAfterConstruction() {
    for (int index = 0; index < indexedList.length(); index++) {
      assertEquals(INITIAL, indexedList.get(index));
    }
  }

  @Test
  @DisplayName("put() adds unique value to list after IndexedList is instantiated.")
  void testPutAfterConstruction() {
    indexedList.put(0, 5);
    assertEquals(5, indexedList.get(0));
  }

  @Test
  @DisplayName("get() throws exception if index is below the valid range.")
  void testGetWithIndexBelowRangeThrowsException() {
    try {
      indexedList.get(-1);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName ("get() throws exception if index is above the valid range")
  void testGetWithIndexAboveRangeThrowsException() {
    try {
      indexedList.get(10);
      fail("IndexException was not thrown for index >= length");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName ("put() throws exception if index is below the valid range")
  void testPutWithIndexBelowRangeThrowsException() {
    try {
      indexedList.put(-1, 3);
      fail("IndexException was not thrown for index < 0");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName ("put() throws exception if index is above the valid range")
  void testPutWithIndexAboveRangeThrowsException() {
    try {
      indexedList.put(10, 3);
      fail("IndexException was not thrown for index >= length");
    } catch (IndexException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Constructor throws exception when given size = 0")
  void testConstructorWithSizeEqualToZeroThrowsException() {
    try {
      IndexedList<Integer> list = createSizeZeroArray();
      fail("Constructor didn't catch invalid size of zero");
    }
    catch (LengthException e) {
      return;
    }
  }

  @Test
  @DisplayName("Constructor throws exception when given size < 0")
  void testConstructorWithSizeLessThanZeroThrowsException() {
    try {
      IndexedList<Integer> list = createNegativeSizeArray();
      fail("Constructor didn't catch invalid size of -1");
    }
    catch (LengthException e) {
      return;
    }
  }

  @Test
  @DisplayName ("length is equal to the designated length of 10")
  void testLengthAfterConstruction() {
    assertEquals(LENGTH, indexedList.length());
  }

  @Test
  @DisplayName ("iterator traverses through list properly with for each loop")
  void testIteratorWithForEachLoop() {
    indexedList.put(0,1);
    indexedList.put(9,2);
    int counter = 0;
    for (Object element:indexedList) {
      assertEquals(element, indexedList.get(counter));
      counter++;
    }
  }

  @Test
  @DisplayName ("iterator traverses through list properly after node removals with for each loop")
  void testIteratorWithForEachLoopAfterNodeRemovals() {
    indexedList.put(0, 3);
    indexedList.put (3,5);
    indexedList.put (6, 9);
    indexedList.put (9, 4);
    indexedList.put (3,INITIAL); // removed
    indexedList.put (6,0); // removed
    int counter = 0;
    for (Object element:indexedList) {
      assertEquals(element, indexedList.get(counter));
      counter++;
    }
  }

  @Test
  @DisplayName ("iterator traverses through list properly when the object is called directly")
  void testIteratorObjectDirectlyWithCallsToNext() {
    Iterator<Integer> it = indexedList.iterator();
    indexedList.put (0,3);
    indexedList.put (4, 6);
    for (int k = 0; k < indexedList.length(); k++) {
      assertEquals(indexedList.get(k), it.next());
    }
  }

  @Test
  @DisplayName ("iterator properly identifies when list ends")
  void testIteratorHasNextIsFalseWhenAtEndOfList() {
    Iterator<Integer> it = indexedList.iterator();
    indexedList.put (0,3);
    indexedList.put (4, 6);
    for (int k = 0; k <= indexedList.length(); k++) {
      if (k == indexedList.length()) {
        assertFalse(it.hasNext());
      }
      else {
        it.next();
      }
    }
  }

  @Test
  @DisplayName("iterator throws exception when next() is called when hasNext() is false")
  void testIteratorThrowsExceptionWhenNextCalledWhenHasNextFalse() {
    Iterator<Integer> it = indexedList.iterator();
    indexedList.put (0,6);
    indexedList.put (7, 2);
    for (int k = 0; k <= indexedList.length(); k++) {
      if (k == indexedList.length()) {
        try {
          it.next();
          fail("iterator did not catch that we have reached end of list");
        } catch (NoSuchElementException ex) {
          return;
        }
      }
      else {
        it.next();
      }
    }
  }

  @Test
  @DisplayName("put() does add not multiple nodes to same index but rather replaces the value at index")
  void testPutReplacesValueAtAlreadyModifiedPosition() {
    indexedList.put(4, 3);
    indexedList.put(4, 2);
    assertEquals(2, indexedList.get(4));
  }

  @Test
  @DisplayName("put() properly adds a node between two nodes")
  void testPutAddsNodeInBetweenTwoNodes() {
    indexedList.put (3, 4);
    indexedList.put (5, 9);
    indexedList.put(4, 8);
    assertEquals(indexedList.get(4), 8);
  }

  @Test
  @DisplayName("put() properly adds a node to the left of head")
  void testPutAddsNodeBeforeHeadPosition() {
    indexedList.put (2, 5); // head
    indexedList.put (1, 2);
    assertEquals(indexedList.get(1), 2);
  }

  @Test
  @DisplayName("put() properly adds a node to the right of head")
  void testPutAddsNodeAfterHeadPosition() {
    indexedList.put(2,5);
    indexedList.put(3,6);
    assertEquals(indexedList.get(3), 6);
  }

  @Test
  @DisplayName("put() can handle adding multiple nodes after head")
  void testPutAddingMultipleNodesAfterHead() {
    indexedList.put (0,3);
    indexedList.put (5, 25);
    indexedList.put(7, 49);
    assertEquals(indexedList.get(7), 49);
  }

  @Test
  @DisplayName("get() works for getting position between two nodes")
  void testGetBetweenTwoNodes() {
    indexedList.put (2,4);
    indexedList.put(6, 9);
    assertEquals(indexedList.get(4), INITIAL);
  }

  @Test
  @DisplayName("get() works after Node has been removed")
  void testGetAfterNodeRemoval() {
    indexedList.put(3,5);
    indexedList.put(3,INITIAL);
    assertEquals(indexedList.get(3), INITIAL);
  }

  @Test
  @DisplayName("get() works if value to retrieve is null")
  void testGetRetrieveNull() {
    indexedList.put(4, null);
    assertEquals(indexedList.get(4), null);
  }

}
