package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

import org.junit.Test;

/**
 * Tests of Deque functionality.  Some methods are not tested because they are tested in 
 * CollectionTest, and behavior is same as described for Collection:
 * contains()
 * size()
 * @author dan
 *
 */
public class DequeTest {

  /**
   * Test the add(E e) method.
   * It adds e to the tail of the deque.
   */
  @Test
  public void testAdd() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("def", is(equalTo(d.getLast())));
  }

  /**
   * Test the addFirst(E e) method.
   * It adds e to the head of the deque.
   */
  @Test
  public void testAddFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.addFirst("ghi");
    assertThat("ghi", is(equalTo(d.getFirst())));
  }

  /**
   * Test the addLast(E e) method.
   * It adds e to the tail of the deque.
   */
  @Test
  public void testAddLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.addLast("ghi");
    assertThat("ghi", is(equalTo(d.getLast())));
  }

  /**
   * Test the descendingIterator() method.
   * It returns an iterator that iterates the deque from tail to head.
   */
  @Test
  public void testDescendingIterator() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.add("ghi");
    Iterator<String> it = d.descendingIterator();
    assertThat("ghi", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
    assertThat("abc", is(equalTo(it.next())));
    assertFalse(it.hasNext());
  }

  /**
   * Test the element() method.
   * It returns but does not remove the element at the head of the deque.
   */
  @Test
  public void testElement() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.element())));
    assertThat(2, is(equalTo(d.size())));
  }

  /**
   * Test the getFirst() method.
   * It returns but does not remove the element at the head of the deque.
   */
  @Test
  public void testGetFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.getFirst())));
    assertThat(2, is(equalTo(d.size())));
  }

  /**
   * Test the getLast() method.
   * It returns but does not rmeove the element at the tail of the deque.
   */
  @Test
  public void testGetLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("def", is(equalTo(d.getLast())));
    assertThat(2, is(equalTo(d.size())));
  }

  /**
   * Test the iterator() method.
   * It returns an iterator that iterates from head to tail.
   */
  @Test
  public void testIterator() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.add("ghi");
    Iterator<String> it = d.iterator();
    assertThat("abc", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
    assertThat("ghi", is(equalTo(it.next())));
    assertFalse(it.hasNext());
  }

  /**
   * Test the offer(E e) method.
   * It adds e to the tail of the deque.
   */
  @Test
  public void testOffer() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertTrue(d.offer("ghi"));
    assertThat("ghi", is(equalTo(d.getLast())));
    assertThat(3, is(equalTo(d.size())));
  }

  /**
   * Test the offerFirst(E e) method.
   * It adds e to the head of the deque.
   */
  @Test
  public void testOfferFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertTrue(d.offerFirst("ghi"));
    assertThat("ghi", is(equalTo(d.getFirst())));
    assertThat(3, is(equalTo(d.size())));
  }

  /**
   * Test the offerLast(E e) method.
   * It adds e to the tail of the deque.
   */
  @Test
  public void testOfferLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertTrue(d.offerLast("ghi"));
    assertThat("ghi", is(equalTo(d.getLast())));
    assertThat(3, is(equalTo(d.size())));
  }

  /**
   * Test the peek() method.
   * It returns but does not remove the element at the head of the deque.
   * Returns null if deque is empty.
   */
  @Test
  public void testPeek() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.peek())));
    assertThat(2, is(equalTo(d.size())));
  }

  /**
   * Test the peekFirst() method.
   * It returns but does remove the element at the head of the deque.
   * Returns null if deque is empty. 
   */
  @Test
  public void testPeekFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.peekFirst())));
    assertThat(2, is(equalTo(d.size())));
  }
  
  /**
   * Test the peekLast() method.
   * It returns but does not remove the element at the tail of the deque.
   * Returns null if deque is empty.
   */
  @Test
  public void testPeekLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("def", is(equalTo(d.peekLast())));
    assertThat(2, is(equalTo(d.size())));
  }

  /**
   * Test the poll() method.
   * It removes and returns the element at the head of the deque.
   * Returns null if deque is empty.
   */
  @Test
  public void testPoll() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.poll())));
    assertThat(1, is(equalTo(d.size())));
  }

  /**
   * Test the pollFirst() method.
   * It removes and returns the element at the head of the deque.
   * Returns null if deque is empty.
   */
  @Test
  public void testPollFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.pollFirst())));
    assertThat(1, is(equalTo(d.size())));
  }
  
  /**
   * Test the pollLast() method.
   * It removes and returns the element at the tail of the deque.
   * Returns null if the deque is empty.
   */
  @Test
  public void testPollLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("def", is(equalTo(d.pollLast())));
    assertThat(1, is(equalTo(d.size())));
  }

  /**
   * Test the pop method.
   * It removes an element from the head of the deque and returns it.
   */
  @Test
  public void testPop() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.pop())));
    assertThat(1, is(equalTo(d.size())));
  }
  
  /**
   * Test the push method.
   * It pushes an element onto the head of the queue.
   */
  @Test
  public void testPush() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.push("ghi");
    assertThat("ghi", is(equalTo(d.getFirst())));
  }

  /**
   * Test the remove() method.
   * It removes and returns the first element in the deque.
   */
  @Test
  public void testRemove() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.remove())));
    assertThat(1, is(equalTo(d.size())));
    
  }
  
  /**
   * Test the remove(Object obj) method.
   * It removes and returns the first element found that matches obj.
   */
  @Test
  public void testRemoveObject() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertTrue(d.remove("abc"));
    assertThat(1, is(equalTo(d.size())));
  }

  /**
   * Test the removeFirst method.
   * It removes and returns the element at the head of the deque. 
   */
  @Test
  public void testRemoveFirst() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("abc", is(equalTo(d.removeFirst())));
    assertThat(1, is(equalTo(d.size())));
  }

  /**
   * Test the removeFirstOccurrence(Object obj) method.
   * It removes and returns the first element in the deque that is equal to obj.
   */
  @Test
  public void testRemoveFirstOccurrence() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.add("abc");
    assertTrue(d.removeFirstOccurrence("abc"));
    assertThat("abc", is(equalTo(d.getLast())));
    assertThat(2, is(equalTo(d.size())));
  }
  
  /**
   * Test the removeLast() method.
   * It removes and returns the last element in the deque.
   */
  @Test
  public void testRemoveLast() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    assertThat("def", is(equalTo(d.removeLast())));
    assertThat(1, is(equalTo(d.size())));
  }
  
  /**
   * Test the removeLastOccurrence(Object obj) method.
   * It removes and returns the last element in the deque that is equal to obj.
   */
  @Test
  public void testRemoveLastOccurrence() {
    Deque<String> d = new ArrayDeque<String>();
    d.add("abc");
    d.add("def");
    d.add("abc");
    assertTrue(d.removeLastOccurrence("abc"));
    assertThat("abc", is(equalTo(d.getFirst())));
    assertThat(2, is(equalTo(d.size())));
  }
}
  