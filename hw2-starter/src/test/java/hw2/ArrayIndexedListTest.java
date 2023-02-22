package hw2;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new ArrayIndexedList<>(LENGTH, INITIAL);
  }
  public IndexedList<Integer> createSizeZeroArray() { return new ArrayIndexedList<> (0, INITIAL);}
  public IndexedList<Integer> createNegativeSizeArray() { return new ArrayIndexedList<> (-1, INITIAL);}

  @Test
  @DisplayName("constructor properly sets default value for list")
  void testConstructorSetsDefaultValueProperly() {
    ArrayIndexedList<Integer> list = new ArrayIndexedList<>(LENGTH, INITIAL);
    assertEquals(INITIAL,list.get(0));
  }

}
