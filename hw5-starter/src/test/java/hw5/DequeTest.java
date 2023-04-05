package hw5;

import exceptions.EmptyException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class DequeTest {

  private Deque<String> deque;

  @BeforeEach
  public void setUp() {
    this.deque = createDeque();
  }

  protected abstract Deque<String> createDeque();

  @Test
  @DisplayName("Deque is empty after construction.")
  public void testConstructor() {
    assertTrue(deque.empty());
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque empty() returns false when elements in deque")
  public void testEmptyOnNonEmptyDeque() {
    deque.insertFront("hi");
    assertEquals(deque.length(), 1);
    assertFalse(deque.empty());
  }

  @Test
  @DisplayName("Deque length() works properly with multiple elements in deque")
  public void testLengthWithMultipleElements() {
    deque.insertFront("hi");
    deque.insertFront("bye");
    deque.insertFront("yo");
    assertEquals(deque.length(), 3);
  }

  @Test
  @DisplayName ("Deque length() works properly after removal")
  public void testLengthAfterRemoval() {
    deque.insertFront("hi");
    assertEquals(1, deque.length());
    deque.removeFront();
    assertEquals(0, deque.length());
  }

  @Test
  @DisplayName("Deque front() throws exception when deque is empty")
  public void testFrontThrowsExceptionWhenEmptyDeque() {
    try {
      deque.front();
      fail("front() didn't catch exception for empty deque");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Deque front() works with one element in deque")
  public void testFrontWithOneElementInDeque() {
    deque.insertFront("hi");
    assertEquals("hi", deque.front());
  }

  @Test
  @DisplayName("Deque front() works with multiple elements in deque")
  public void testFrontWithMultipleElementsInDeque() {
    deque.insertFront("hi");
    deque.insertFront("yo");
    deque.insertFront("bye");
    deque.insertFront("hey");
    assertEquals("hey", deque.front());
  }

  @Test
  @DisplayName("Deque back() throws exception when deque is empty")
  public void testBackThrowsExceptionWhenEmptyDeque() {
    try {
      deque.back();
      fail("back() didn't catch exception for empty deque");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Deque back() works with one element in deque")
  public void testBackWithOneElementInDeque() {
    deque.insertBack("hi");
    assertEquals("hi", deque.back());
  }

  @Test
  @DisplayName("Deque back() works with multiple elements in deque")
  public void testBackWithMultipleElementsInDeque() {
    deque.insertBack("hi");
    assertEquals(deque.length(), 1);
    deque.insertBack("yo");
    assertEquals("yo", deque.back());
  }

  @Test
  @DisplayName("Deque insertFront() works with no elements in deque")
  public void testInsertFrontWithEmptyDeque() {
    deque.insertFront("hi");
    assertEquals("hi", deque.front());
  }

  @Test
  @DisplayName("Deque insertFront() works with multiple elements in deque")
  public void testInsertFrontWithMultipleElementsInDeque() {
    deque.insertFront("hi");
    deque.insertFront("hello");
    assertEquals("hello", deque.front());
  }

  @Test
  @DisplayName("Deque insertBack() works with no elements in deque")
  public void testInsertBackWithEmptyDeque() {
    deque.insertBack("hi");
    assertEquals("hi", deque.back());
  }

  @Test
  @DisplayName("Deque insertBack() works with multiple elements in deque")
  public void testInsertBackWithMultipleElementsInDeque() {
    deque.insertBack("hi");
    deque.insertBack("hello");
    deque.insertBack("hurt");
    deque.insertBack("roof");
    deque.insertBack("noot");
    deque.insertBack("back");
    deque.insertBack("back2");
    assertEquals("back2", deque.back());
  }

  @Test
  @DisplayName("Deque removeFront() throws exception when empty deque")
  public void testRemoveFrontThrowsExceptionWhenEmptyDeque() {
    try {
      deque.removeFront();
      fail("deque didn't catch exception with empty deque");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Deque removeBack() throws exception when empty deque")
  public void testRemoveBackThrowsExceptionWhenEmptyDeque() {
    try {
      deque.removeBack();
      fail("deque didn't catch exception with empty deque");
    } catch (EmptyException ex) {
      return;
    }
  }

  @Test
  @DisplayName("Deque removeFront() works with one element in deque")
  public void testRemoveFrontWithOneElementInDeque() {
    deque.insertFront("hi");
    deque.removeFront();
    assertEquals(deque.length(), 0);
    assertTrue(deque.empty());
  }

  @Test
  @DisplayName("Deque removeFront() works with multiple elements in deque")
  public void testRemoveFrontWithMultipleElementsInDeque() {
    deque.insertFront("hi");
    deque.insertFront("hello");
    deque.removeFront();
    assertEquals(1, deque.length());
    assertEquals("hi", deque.front());
  }

  @Test
  @DisplayName("Deque removeFront() works with null element")
  public void testRemoveFrontWithNullElement() {
    deque.insertFront("hi");
    deque.insertFront(null);
    deque.removeFront();
    assertEquals(1, deque.length());
    assertEquals("hi", deque.front());
  }

  @Test
  @DisplayName("Deque removeBack() works with one element in deque")
  public void testRemoveBackWithOneElementInDeque() {
    deque.insertBack("hi");
    deque.removeBack();
    assertEquals(deque.length(), 0);
    assertTrue(deque.empty());
  }

  @Test
  @DisplayName("Deque removeBack() works with multiple elements in deque")
  public void testRemoveBackWithMultipleElementsInDeque() {
    deque.insertBack("hi");
    deque.insertBack("hello");
    deque.removeBack();
    assertEquals(1, deque.length());
    assertEquals("hi", deque.back());
  }

  @Test
  @DisplayName("Deque removeBack() works with null element")
  public void testRemoveBackWithNullElement() {
    deque.insertBack("hi");
    deque.insertBack(null);
    deque.removeBack();
    assertEquals(1, deque.length());
    assertEquals("hi", deque.back());
  }

  @Test
  @DisplayName("Deque works like stack when only calling front methods")
  public void testDequeWorksAsStackWithOnlyFrontMethods() {
    deque.insertFront("hi");
    deque.insertFront("bye");
    assertEquals(deque.front(), "bye");
    deque.removeFront();
    deque.removeFront();
    assertEquals(deque.length(), 0);
  }

}
