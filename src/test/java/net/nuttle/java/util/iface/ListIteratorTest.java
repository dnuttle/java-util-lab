package net.nuttle.java.util.iface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

/**
 * Test java.util.ListIterator.
 * No tests for next() or hasNext() as these are the same as in a standard Iterator.
 * @author dan
 *
 */
public class ListIteratorTest {

  /**
   * Test the remove() method.
   * It removes the current element in the list after the last next() or previous() call.
   */
  @Test
  public void testRemove() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    lit.next();
    lit.remove();
    assertThat(1, is(equalTo(list.size())));
    lit.next();
    assertFalse(lit.hasNext());
    lit.previous();
    lit.remove();
    assertThat(0, is(equalTo(list.size())));
  }

  /**
   * Test the hasPrevious() method.
   * It returns true if the current position of the iterator is not at the head of the list.
   */
  @Test
  public void testHasPrevious() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    assertFalse(lit.hasPrevious());
    lit.next();
    assertTrue(lit.hasPrevious());
  }

  /**
   * Test the previous() method.
   * It returns the element in the list just before the current element referenced by the iterator.
   */
  @Test
  public void testPrevious() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    lit.next();
    lit.next();
    assertThat("def", is(equalTo(lit.previous())));
  }

  /**
   * Test the nextIndex() method.
   * It returns the index of the element in the list that would be referenced by the next call to next().
   */
  @Test
  public void testNextIndex() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    assertThat(0, is(equalTo(lit.nextIndex())));
    lit.next();
    assertThat(1, is(equalTo(lit.nextIndex())));
    lit.next();
    assertThat(2, is(equalTo(lit.nextIndex())));
  }

  /**
   * Test the previousIndex() method.
   * It returns the index of the element in the list that would be referenced by the next call to previous(), or -1 
   * if ListIterator is at head of list.
   */
  @Test
  public void testPreviousIndex() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    assertThat(-1, is(equalTo(lit.previousIndex())));
    lit.next();
    assertThat(0, is(equalTo(lit.previousIndex())));
    lit.next();
    assertThat(1, is(equalTo(lit.previousIndex())));
  }

  /**
   * Test the set(E e) method.
   * It sets the current element of the ListIterator (after the most recent call to next or previous) with e.
   * It throws IllegalStateException if add or remove has been called after the last call to next or previous.
   */
  @Test
  public void testSet() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    lit.next();
    lit.set("ghi");
    assertThat("ghi", is(equalTo(list.get(0))));
  }

  /**
   * Test the add(E e) method.
   * It adds e to the list just before the element that would be referred to by the next call to next, or just after the 
   * element that would be referred to by the next call to previous.  If the list is empty, e becomes the sole element in the list.
   */
  @Test
  public void testAdd() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    //ghi added as first element, index=0
    lit.add("ghi");
    assertThat("ghi", is(equalTo(list.get(0))));
    lit = list.listIterator();
    lit.next();
    lit.next();
    //jkl inserted as third element, index=2
    lit.add("jkl");
    assertThat("jkl", is(equalTo(list.get(2))));
  }

  /**
   * Returns a List<String> with two elements, "abc" and "def", in that order.
   * @return
   */
  private List<String> getList() {
    List<String> list = new ArrayList<String>();
    list.add("abc");
    list.add("def");
    return list;
  }
}
