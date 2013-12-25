package net.nuttle.java.util.iface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Tests java.util.Set.
 * Most functionality is covered in CollectionTest.
 * Tests here focus on the key aspect of a Set: it is a collection of unique values.
 * @author dan
 *
 */
public class SetTest {

  /**
   * Tests the add(E e) method.
   * Adds e, if it is not already in the set.  Returns true if item is added.
   */
  @Test
  public void testAdd() {
    Set<String> set = getSet();
    assertFalse(set.add("abc"));
    assertTrue(set.add("ghi"));
  }

  /**
   * Tests the addAll(Collection<? extends E> c) method.
   * Adds all items in c that are not already in set.  Returns true if underlying set is changed.
   */
  @Test
  public void testAddAll() {
    Set<String> set = getSet();
    Collection<String> c = new ArrayList<String>();
    c.add("abc");
    c.add("def");
    assertFalse(set.addAll(c));
    c.add("ghi");
    assertTrue(set.addAll(c));
  }

  /**
   * Utility method returns Set with stock values.
   * @return
   */
  private Set<String> getSet() {
    Set<String> set = new HashSet<String>();
    set.add("abc");
    set.add("def");
    return set;
  }
}
