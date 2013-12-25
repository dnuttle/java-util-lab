package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Test java.util.NavigableSet.
 * Review this, make sure all the proper methods are tested.
 * @author dan
 *
 */
public class NavigableSetTest {
  
  private NavigableSet<String> set;
  
  @Before
  public void setUp() {
    set = getSet();
  }

  /**
   * Test the iterator() method.
   * Returns iterator that returns elements in sorted order. 
   */
  @Test
  public void testIterator() {
    Iterator<String> it = set.iterator();
    assertThat("abc", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
    assertThat("ghi", is(equalTo(it.next())));
    assertThat("jkl", is(equalTo(it.next())));
    assertFalse(it.hasNext());
  }

  /**
   * Test the subSet(E fromElem, E toElem) method.
   * Returns SortedSet containing elements from fromElem, inclusive, to toElem, exclusive.
   */
  @Test
  public void testSubSetEE() {
    SortedSet<String> s2 = set.subSet("def", "jkl");
    assertThat(2, is(equalTo(s2.size())));
    assertThat("def", is(equalTo(s2.first())));
    assertThat("ghi", is(equalTo(s2.last())));
  }

  /**
   * Test the headSet(E toElement, boolean inclusive) method.
   * Returns all elements less than (or equal to) toElement
   */
  @Test
  public void testHeadSetEBoolean() {
    SortedSet<String> s2 = set.headSet("def", true);
    assertThat(2, is(equalTo(s2.size())));
    assertThat("abc", is(equalTo(s2.first())));
    assertThat("def", is(equalTo(s2.last())));
    s2 = set.headSet("def", false);
    assertThat(1, is(equalTo(s2.size())));
  }

  /**
   * Test the tailSet(E fromElement, boolean inclusive) method.
   * Returns all elements greater than (or equal to) fromElement.
   */
  @Test
  public void testTailSetEBoolean() {
    SortedSet<String> s2 = set.tailSet("def", false);
    assertThat(2, is(equalTo(s2.size())));
    assertThat("ghi", is(equalTo(s2.first())));
    assertThat("jkl", is(equalTo(s2.last())));
    s2 = set.tailSet("def", true);
    assertThat(3, is(equalTo(s2.size())));
  }

  /**
   * Test the lower(E e) method.
   * Returns greatest element that is less than e, or null if none found.
   */
  @Test
  public void testLower() {
    assertThat("abc", is(equalTo(set.lower("def"))));
    assertThat("def", is(equalTo(set.lower("defa"))));
    assertNull(set.lower("aaa"));
  }

  /**
   * Test the floor(E e) method.
   * Returns the greatest element that is less than or equal to e, or null if none found.
   */
  @Test
  public void testFloor() {
    assertThat("def", is(equalTo(set.floor("def"))));
    assertThat("def", is(equalTo(set.floor("deff"))));
    assertNull(set.floor("aaa"));
  }

  /**
   * Test the ceiling(E e) method.
   * Returns the least element of the set greater than or equal to e, or null if none found.
   */
  @Test
  public void testCeiling() {
    String val = set.ceiling("def");
    assertThat("def", is(equalTo(val)));
    val = set.ceiling("deff");
    assertThat("ghi", is(equalTo(val)));
    assertNull(set.ceiling("xyz"));
  }

  /**
   * Test the higher(E e) method.
   * Returns the least element greater/higher than e, or null if none found.
   */
  @Test
  public void testHigher() {
    String val = set.higher("def");
    assertThat("ghi", is(equalTo(val)));
    assertNull(set.higher("jkl"));
  }

  /**
   * Test the pollFirst() method.
   * Removes and returns the first element, or null if set is empty.
   */
  @Test
  public void testPollFirst() {
    assertThat("abc", is(equalTo(set.pollFirst())));
    assertThat(3, is(equalTo(set.size())));
    set = new TreeSet<String>();
    assertNull(set.pollFirst());
  }

  /**
   * Test the pollLast method.
   * Removes and returns the last element, or null if set is empty.
   */
  @Test
  public void testPollLast() {
    assertThat("jkl", is(equalTo(set.pollLast())));
    assertThat(3, is(equalTo(set.size())));
    set = new TreeSet<String>();
    assertNull(set.pollLast());
  }

  /**
   * Test the descendingSet() method.
   * Returns the set in reversed order.  This set is backed by the original set.
   */
  @Test
  public void testDescendingSet() {
    NavigableSet<String> s2 = set.descendingSet();
    assertThat("jkl", is(equalTo(s2.pollFirst())));
    assertThat("abc", is(equalTo(s2.pollLast())));
    assertThat(2, is(equalTo(set.size())));
  }

  /**
   * Test the descendingIterator() method.
   * Returns an iterator that iterates elements in reverse order.
   */
  @Test
  public void testDescendingIterator() {
    Iterator<String> it = set.descendingIterator();
    assertThat("jkl", is(equalTo(it.next())));
    assertThat("ghi", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
    assertThat("abc", is(equalTo(it.next())));
    assertFalse(it.hasNext());
  }


  /**
   * Test the subSet(E fromElem, boolean fromInclusive, E toElem, boolean toInclusive) method.
   * Returns a set containing elements from fromElem to toElem, with inclusivity at either end 
   * determined by booleans.  The returned set is backed by the original set. 
   */
  @Test
  public void testSubSetEBooleanEBoolean() {
    NavigableSet<String> s2 = set.subSet("def", false, "ghi", false);
    assertThat(0, is(equalTo(s2.size())));
    s2 = set.subSet("def",  true, "ghi", true);
    assertThat(2, is(equalTo(s2.size())));
    assertThat("def", is(equalTo(s2.pollFirst())));
    assertThat(3, is(equalTo(set.size())));
  }

  private NavigableSet<String> getSet() {
    NavigableSet<String> set = new TreeSet<String>();
    set.add("abc");
    set.add("def");
    set.add("ghi");
    set.add("jkl");
    return set;
  }
}
