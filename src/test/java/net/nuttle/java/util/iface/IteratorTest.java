package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Test java.util.Iterator
 * @author dan
 *
 */
public class IteratorTest {

  /**
   * Test the hasNext() method.
   * Returns true if the iterator has any remaining elements.
   */
  @Test
  public void testHasNext() {
    List<String> l = new ArrayList<String>();
    l.add("abc");
    l.add("def");
    Iterator<String> it = l.iterator();
    assertTrue(it.hasNext());
    it.next();
    assertTrue(it.hasNext());
    it.next();
    assertFalse(it.hasNext());
  }

  /**
   * Test the next() method.
   * It returns the next element from the iterator.
   */
  @Test
  public void testNext() {
    List<String> l = new ArrayList<String>();
    l.add("abc");
    l.add("def");
    Iterator<String> it = l.iterator();
    assertThat("abc", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
  }

  /**
   * Test the next() method when iterator is empty.
   * Throws NoSuchElementException.
   */
  @Test(expected=NoSuchElementException.class)
  public void testNextThrowsNoSuchElementException() {
    List<String> l = new ArrayList<String>();
    l.add("abc");
    l.add("def");
    Iterator<String> it = l.iterator();
    while (it.hasNext()) {
      it.next();
    }
    it.next();
  }
  
  /**
   * Test the remove() method.
   * Removes the last element returned by iterator from underlying collection.
   */
  @Test
  public void testRemove() {
    List<String> l = new ArrayList<String>();
    l.add("abc");
    l.add("def");
    Iterator<String> it = l.iterator();
    it.next();
    it.remove();
    assertThat("def", is(equalTo(it.next())));
    assertFalse(it.hasNext());
    assertThat(1, is(equalTo(l.size())));
  }

  /**
   * Test the remove() method when next() has not yet been called.
   * Throws IllegalStateException.
   */
  @Test(expected=IllegalStateException.class)
  public void testRemoveThrowsIllegalStateException() {
    List<String> l = new ArrayList<String>();
    l.add("abc");
    l.add("def");
    Iterator<String> it = l.iterator();
    it.remove();
    
  }
}
