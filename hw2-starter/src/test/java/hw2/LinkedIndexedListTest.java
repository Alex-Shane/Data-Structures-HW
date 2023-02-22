package hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinkedIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new LinkedIndexedList<>(LENGTH, INITIAL);
  }
  public IndexedList<Integer> createSizeZeroArray() { return new LinkedIndexedList<> (0, INITIAL);}
  public IndexedList<Integer> createNegativeSizeArray() { return new LinkedIndexedList<> (-1, INITIAL);}

  @Test
  @DisplayName("Constructor properly sets default value of list")
  void testConstructorSetsDefaultValueCorrectly() {
    LinkedIndexedList<Integer> list = new LinkedIndexedList<>(LENGTH, INITIAL);
    assertEquals(INITIAL, list.get(0));
    assertEquals(INITIAL, list.get(9));
  }

}
