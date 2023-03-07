package hw3;

import exceptions.IndexException;
import hw3.list.MeasuredIndexedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MeasuredIndexedListTest {

  private static final int LENGTH = 15;
  private static final int DEFAULT_VALUE = 3;

  private MeasuredIndexedList<Integer> measuredIndexedList;

  @BeforeEach
  void setup() {
    measuredIndexedList = new MeasuredIndexedList<>(LENGTH, DEFAULT_VALUE);
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero reads")
  void zeroReadsStart() {
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList starts with zero writes")
  void zeroWritesStart() {
    assertEquals(0, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("MeasuredIndexedList handles single access properly")
  void testSingleAccessInList() {
    measuredIndexedList.get(3);
    assertEquals(1, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList handles multiple accesses properly")
  void testMultipleAccessesInList() {
    measuredIndexedList.get(4);
    measuredIndexedList.get(5);
    measuredIndexedList.get(2);
    assertEquals(3, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("count() works properly when list unchanged after instantiation")
  void testUnmodifiedListWithCount() {
    assertEquals(15, measuredIndexedList.count(3));
  }

  @Test
  @DisplayName("MeasuredIndexedList count() works properly for one non null value")
  void testCountWithSingleNonNullValue() {
    measuredIndexedList.put(1, 4);
    int count = measuredIndexedList.count(4);
    assertEquals(1, count);
  }

  @Test
  @DisplayName("MeasuredIndexedList count() works properly for multiple occurrences of a non null value")
  void testCountWithMultipleOccurrencesOfNonNullValue() {
    measuredIndexedList.put(1, 4);
    measuredIndexedList.put(8, 4);
    int count = measuredIndexedList.count(4);
    assertEquals(2, count);
  }

  @Test
  @DisplayName("MeasuredIndexedList count() works properly for one null value")
  void testCountWithOneNullValue() {
    measuredIndexedList.put(1,null);
    int count = measuredIndexedList.count(null);
    assertEquals(1, count);
  }

  @Test
  @DisplayName("MeasuredIndexedList count() works properly for multiple null values")
  void testCountWithMultipleNullValues() {
    measuredIndexedList.put(1,null);
    measuredIndexedList.put(4, null);
    int count = measuredIndexedList.count(null);
    assertEquals(2, count);
  }

  @Test
  @DisplayName("MeasuredIndexedList handles accesses properly with count method")
  void testAccessesWithCountMethodInList() {
    measuredIndexedList.put(1, 4);
    int count = measuredIndexedList.count(4); // count should call get() 15 times
    assertEquals(15, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList doesn't increase accesses for get() with negative index")
  void testAccessesAfterGetWithNegativeIndex() {
    try {
      measuredIndexedList.get(-1);
      fail("get() didn't catch invalid index");
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.accesses());
    }
  }

  @Test
  @DisplayName("MeasuredIndexedList doesn't increase accesses for get() with index >= length")
  void testAccessesAfterGetWithTooLargeIndex() {
    try {
      measuredIndexedList.get(15);
      fail("get() didn't catch invalid index");
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.accesses());
    }
  }

  @Test
  @DisplayName("MeasuredIndexedList doesn't increase mutations for put() with index >= length")
  void testMutationsAfterPutWithTooLargeIndex() {
    try {
      measuredIndexedList.put(15, 6);
      fail("put() didn't catch invalid index");
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.mutations());
    }
  }

  @Test
  @DisplayName("MeasuredIndexedList doesn't increase mutations for put() with negative index")
  void testMutationsAfterPutWithNegativeIndex() {
    try {
      measuredIndexedList.put(-1, 6);
      fail("put() didn't catch invalid index");
    } catch (IndexException ex) {
      assertEquals(0, measuredIndexedList.mutations());
    }
  }

  @Test
  @DisplayName("MeasuredIndexedList handles single mutation properly")
  void testMutationsAfterSinglePutCall() {
    measuredIndexedList.put(4, 39);
    assertEquals(1, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("MeasuredIndexedList handles multiple mutations properly")
  void testMutationsAfterMultiplePutCalls() {
    measuredIndexedList.put(5, 38);
    measuredIndexedList.put (8,27);
    assertEquals(2, measuredIndexedList.mutations());
  }

  @Test
  @DisplayName("MeasuredIndexedList properly resets accesses")
  void testResetWithAccesses() {
    measuredIndexedList.get(4);
    measuredIndexedList.reset();
    assertEquals(0, measuredIndexedList.accesses());
  }

  @Test
  @DisplayName("MeasuredIndexedList properly resets mutations")
  void testResetWithMutations() {
    measuredIndexedList.put(8,47);
    measuredIndexedList.reset();
    assertEquals(0, measuredIndexedList.mutations());
  }



}
