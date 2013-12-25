package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.junit.Test;

/**
 * Tests java.util.Queue.
 * Implementation used is ArrayDeque, so queues are FIFO.
 * @author dan
 *
 */
public class QueueTest {

  /**
   * Test the add(E e) method.
   * It returns true if is is possible to add e.
   */
  @Test
  public void testAdd() {
    Queue<String> q = getQueue();
    assertTrue(q.add("ghi"));
    assertThat(3, is(equalTo(q.size())));
  }

  /**
   * Test the offer(E e) method.
   * It returns true if it is possible to add e.
   */
  @Test
  public void testOffer() {
    Queue<String> q = getQueue();
    assertTrue(q.offer("ghi"));
    assertThat(3, is(equalTo(q.size())));
  }

  /**
   * Test the remove() method.
   * It removes and returns the head of the queue.
   */
  @Test
  public void testRemove() {
    Queue<String> q = getQueue();
    assertThat("abc", is(equalTo(q.remove())));
  }
  
  /**
   * Test the remove() method.
   * It throws NoSuchElementException if queue is empty.
   */
  @Test(expected=NoSuchElementException.class)
  public void testRemoveThrowsNoSuchElementException() {
    Queue<String> q = getQueue();
    q.remove();
    q.remove();
    q.remove();
  }

  /**
   * Test the poll() method.
   * It removes and returns the head of the queue, or null if the queue is empty.
   */
  @Test
  public void testPoll() {
    Queue<String> q = getQueue();
    assertThat("abc", is(equalTo(q.poll())));
    assertThat("def", is(equalTo(q.poll())));
    assertNull(q.poll());
  }

  /**
   * Test the element() method.
   * It returns but does not remove the head of the queue.
   */
  @Test
  public void testElement() {
    Queue<String> q = getQueue();
    assertThat("abc", is(equalTo(q.element())));
    assertThat(2, is(equalTo(q.size())));
  }

  /**
   * Test the element() method.
   * It throws NoSuchElementException when queue is empty.
   */
  @Test(expected=NoSuchElementException.class)
  public void testElementThrowsNoSuchElementException() {
    Queue<String> q = getQueue();
    q.remove();
    q.remove();
    q.element();
  }

  /**
   * Test the peek() method.
   * It returns but does not remove the head of the queue, or null if queue is empty.  
   */
  @Test
  public void testPeek() {
    Queue<String> q = getQueue();
    assertThat("abc", is(equalTo(q.peek())));
    assertThat(2, is(equalTo(q.size())));
    q.remove();
    q.remove();
    assertNull(q.peek());
  }

  /**
   * Utility method returns Queue instance with stock values.
   * @return
   */
  private Queue<String> getQueue() {
    Queue<String> queue = new ArrayDeque<String>();
    queue.add("abc");
    queue.add("def");
    return queue;
  }
}
