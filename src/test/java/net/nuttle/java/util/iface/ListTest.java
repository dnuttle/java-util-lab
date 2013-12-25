package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

/**
 * Test java.util.List.
 * Many of the methods tested in java.util.Collection are skipped.
 * Tests here concentrate on 1) the fact that a List is an ordered Collection
 * and 2) List-specific functionality.
 * 
 * Frequent use is made of the getList method, which returns List<String> with values in this order:
 * abc
 * def
 * 
 * @author dan
 *
 */
public class ListTest {

  /**
   * Test the iterator() method to confirm that elements are returned in order.
   */
  @Test
  public void testIterator() {
    List<String> l = getList();
    Iterator<String> it = l.iterator();
    //Confirm expected order
    assertThat("abc", is(equalTo(it.next())));
    assertThat("def", is(equalTo(it.next())));
  }

  /**
   * Test the add(E e) method to confirm that elements are added/stored in order.
   */
  @Test
  public void testAddE() {
    List<String> l = getList();
    assertThat(l.get(0), is(equalTo("abc")));
    assertThat(l.get(1), is(equalTo("def")));
  }

  /**
   * Test the get(int index) method.
   * Returns the element at the specified index.
   */
  @Test
  public void testGet() {
    List<String> l = getList();
    assertThat(l.get(0), is(equalTo("abc")));
    assertThat(l.get(1), is(equalTo("def")));
  }

  /**
   * Test the get(int index) method when index is out of bounds.
   * Throws IndexOutOfBoundsException.
   */
  @Test(expected=IndexOutOfBoundsException.class)
  public void testGetThrowsIndexOutOfBoundsException() {
    List<String> l = getList();
    l.get(2);
  }

  /**
   * Test the set(int index, E e) method.
   * Sets the index-th element with e, assuming that index &le; list.size().
   */
  @Test
  public void testSet() {
    List<String> l = getList();
    l.set(1, "ghi");
    assertThat("ghi", is(equalTo(l.get(1))));
  }

  /**
   * Test the add(int index, E e) method.
   * Inserts e at index, shifting any existing elements at index or greater to the right.
   */
  @Test
  public void testAddIntE() {
    List<String> l = getList();
    l.add(1, "new");
    assertThat(3, is(equalTo(l.size())));
    assertThat("new", is(equalTo(l.get(1))));
    assertThat("def", is(equalTo(l.get(2))));
  }

  /**
   * Test the remove(int index) method.
   * Removes the element at index, assuming it exists.
   */
  @Test
  public void testRemoveInt() {
    List<String> l = getList();
    l.add("new");
    l.remove(1);
    assertThat(2, is(equalTo(l.size())));
    assertThat("abc", is(equalTo(l.get(0))));
    assertThat("new", is(equalTo(l.get(1))));
  }

  /**
   * Test the indexOf(Object o) method.
   * It returns the index of the first occurrence of o if found, or else -1.
   */
  @Test
  public void testIndexOf() {
    List<String> l = getList();
    l.add("abc"); //List now has two occurrences of "abc"
    assertThat(0, is(equalTo(l.indexOf("abc"))));
    assertThat(1, is(equalTo(l.indexOf("def"))));
    l.remove(1);
    assertThat(-1, is(equalTo(l.indexOf("def"))));
  }

  /**
   * Test the lastIndexOf(Object o) method.
   * It returns the last index of an instance of o in the list, or -1 if not found.
   */
  @Test
  public void testLastIndexOf() {
    List<String> l = getList();
    l.add("abc"); //List now has two occurrences of "abc"
    assertThat(2, is(equalTo(l.lastIndexOf("abc"))));
    assertThat(1, is(equalTo(l.lastIndexOf("def"))));
    l.remove(l.lastIndexOf("abc"));
    assertThat(0, is(equalTo(l.lastIndexOf("abc"))));
  }

  /**
   * Test the listIterator() method.
   * It returns a ListIterator of the underlying collection.
   * Note that the iterator() method returns an Iterator, but its order
   * will be the same as the ListIterator returned here.
   */
  @Test
  public void testListIterator() {
    List<String> list = getList();
    ListIterator<String> lit = list.listIterator();
    assertThat("abc", is(equalTo(lit.next())));
    assertThat("def", is(equalTo(lit.next())));
    assertFalse(lit.hasNext());
  }

  /**
   * Test the listIterator(int index) method.
   * It returns a ListIterator that includes those elements in the underlying
   * collection starting at index.
   */
  @Test
  public void testListIteratorInt() {
    List<String> list = getList();
    list.add("ghi");
    ListIterator<String> lit = list.listIterator(1);
    assertThat("def", is(equalTo(lit.next())));
    assertThat("ghi", is(equalTo(lit.next())));
    assertFalse(lit.hasNext());
  }

  /**
   * Test the subList(int fromIndex, int toIndex) method.
   * This returns a new List containing elements from the original list
   * starting at fromIndex and continuing to, but not including, toIndex.
   */
  @Test
  public void testSubList() {
    List<String> list = getList();
    list.add("ghi");
    list.add("jkl");
    List<String> subList = list.subList(1,  3);
    ListIterator<String> lit = subList.listIterator();
    assertThat("def", is(equalTo(lit.next())));
    assertThat("ghi", is(equalTo(lit.next())));
    assertFalse(lit.hasNext());
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
