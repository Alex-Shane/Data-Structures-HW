package hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SparseIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new SparseIndexedList<>(LENGTH, INITIAL);
  }
  public IndexedList<Integer> createSizeZeroArray() { return new SparseIndexedList<> (0, INITIAL);}
  public IndexedList<Integer> createNegativeSizeArray() { return new SparseIndexedList<> (-1, INITIAL);}

  @Test
  @DisplayName("Constructor properly sets head to null during list instantiation")
  void testConstructorSetsHeadToNull() {
    SparseIndexedList<Integer> list = new SparseIndexedList<>(5, 3);
    assertEquals(null, list.getHead());
  }

  @Test
  @DisplayName("Constructor properly sets default value during list instantiation")
  void testConstructorSetsDefaultValue() {
    SparseIndexedList<Integer> list = new SparseIndexedList<>(LENGTH, INITIAL);
    assertEquals(list.getDefaultValue(), INITIAL);
  }

  @Test
  @DisplayName("put(index,value) does not add Node if value == defaultValue")
  void testIfPutDoesNotAddNodeIfValueToAddIsDefaultValue() {
    SparseIndexedList<Integer> list = new SparseIndexedList<>(LENGTH,INITIAL);
    list.put(1, 4); // so that we know what value head should store
    list.put(0, INITIAL); // if node were added, head value would be INITIAL
    assertEquals(list.getHeadData(), 4);
  }

  @Test
  @DisplayName("put() removes Node if value to add is default value")
  void testPutRemovesNodeIfDefaultValueIsAttemptedToBeAdded() {
    SparseIndexedList<Integer> list = new SparseIndexedList<>(LENGTH,INITIAL);
    list.put(9, 3);
    list.put(5, 2);
    list.put(5, INITIAL);
    assertEquals(3, list.getHeadData());
  }

}