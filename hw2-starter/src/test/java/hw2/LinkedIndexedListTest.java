package hw2;

public class LinkedIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new LinkedIndexedList<>(LENGTH, INITIAL);
  }
  public IndexedList<Integer> createSizeZeroArray() { return new LinkedIndexedList<> (0, INITIAL);}
  public IndexedList<Integer> createNegativeSizeArray() { return new LinkedIndexedList<> (-1, INITIAL);}

}
