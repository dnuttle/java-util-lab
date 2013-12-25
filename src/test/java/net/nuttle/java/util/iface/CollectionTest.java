package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.junit.Test;

/**
 * Tests/demonstration of java.util.Collection, JRE 1.7
 * @author dan
 *
 */
public class CollectionTest {

  /**
   * Test Collection.add
   */
  @Test
  public void testAdd() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    //A list allows adding the same value more than once (still returns true)
    assertTrue(c.add("abc"));
    c = new HashSet<String>();
    c.add("abc");
    //A set returns false if same value is added a second time
    assertFalse(c.add("abc"));
    //But other values can be added
    assertTrue(c.add("def"));
  }
  
  @Test
  public void testAddAll() {
    Collection<String> c = new HashSet<String>();
    c.add("abc");
    Collection<String> newVals = new ArrayList<String>();
    newVals.add("abc");
    newVals.add("def");
    //An attempt is made to add abc a second time,
    //but addAll still returns true because def was added, and so 
    //the underlying collection was modified
    assertTrue(c.addAll(newVals));
    //Confirm that the collection contains only two values
    assertThat(2, is(equalTo(c.size())));
  }
  
  @Test
  public void testClear() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    c.clear();
    assertThat(0, is(equalTo(c.size())));
  }
  
  @Test
  public void testContains() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    assertTrue(c.contains("abc"));
    assertFalse(c.contains("Abc"));
  }
  
  @Test
  public void testContainsAll() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    c.add("ghi");
    Collection<String> test = new ArrayList<String>();
    c.add("abc");
    c.add("ghi");
    assertTrue(c.containsAll(test));
    test.add("xyz");
    assertFalse(c.containsAll(test));
  }
  
  @Test
  public void testIsEmpty() {
    Collection<String> c = new ArrayList<String>();
    assertTrue(c.isEmpty());
    c.add("abc");
    assertFalse(c.isEmpty());
  }
  
  @Test
  public void testIterator() {
    Collection<String> c = new ArrayList<String>();
    HashSet<String> values = new HashSet<String>();
    values.add("abc");
    values.add("def");
    values.add("ghi");
    for (String s : values) {
      c.add(s);
    }
    int count = 0;
    Iterator<String> it = c.iterator();
    while (it.hasNext()) {
      String val = it.next();
      count++;
      assertTrue(values.contains(val));
    }
    assertThat(values.size(), is(equalTo(count)));
  }
  
  @Test
  public void testRemove() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    assertFalse(c.remove("ghi"));
    assertTrue(c.remove("abc"));
    assertThat(1, is(equalTo(c.size())));
  }

  @Test
  public void testRemoveAll() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    Collection<String> remove = new ArrayList<String>();
    remove.add("abc");
    remove.add("ghi");
    //The value abc is removed, so the underlying collection is modifed,
    //and the method returns true, even though ghi is not present and therefore
    //not removed.
    assertTrue(c.removeAll(remove));
    //Confirm that size is now 1 (def remains)
    assertThat(1, is(equalTo(c.size())));
  }
  
  @Test
  public void testSize() {
    Collection<String> c = new ArrayList<String>();
    assertThat(0, is(equalTo(c.size())));
    c.add("abc");
    assertThat(1, is(equalTo(c.size())));
    c.add("def");
    assertThat(2, is(equalTo(c.size())));
    c.remove("abc");
    assertThat(1, is(equalTo(c.size())));
  }

  @Test
  public void testToArray() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    Object[] values = c.toArray();
    assertThat(2, is(equalTo(values.length)));
    assertThat("abc", is(equalTo((String) values[0])));
    assertThat("def", is(equalTo((String) values[1])));
  }

  @Test
  public void testToArrayByType() {
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    String[] values = c.toArray(new String[0]);
    assertThat(2, is(equalTo(values.length)));
    assertThat("abc", is(equalTo(values[0])));
    assertThat("def", is(equalTo(values[1])));
  }
}
