package hw2;


public class ArrayIndexedListTest extends IndexedListTest {

  @Override
  public IndexedList<Integer> createArray() {
    return new ArrayIndexedList<>(LENGTH, INITIAL);
  }
  public IndexedList<Integer> createSizeZeroArray() { return new ArrayIndexedList<> (0, INITIAL);}
  public IndexedList<Integer> createNegativeSizeArray() { return new ArrayIndexedList<> (-1, INITIAL);}

}
