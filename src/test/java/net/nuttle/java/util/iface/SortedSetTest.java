package net.nuttle.java.util.iface;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class SortedSetTest {

  /**
   * Test the comparator() method.
   * Returns the comparator used, or null if only natural ordering is used.
   */
  @Test
  public void testComparatorNull() {
    SortedSet<String> s = getSet();
    assertNull(s.comparator());
  }

  /**
   * Test the comparator() method.
   */
  @Test
  public void testComparator() {
    //Sample comparator:  if value starts with "key", it's ignored.
    //This means "keya" is compared as "a", and so it appears before "b".
    Comparator<String> comp = new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        if(s1==null && s2!=null) return 1;
        if(s1!=null && s2==null) return -1;
        if(s1==null && s2==null) return 0;
        String key1 = s1;
        String key2 = s2;
        if(s1.startsWith("key")) {
          key1 = s1.substring(3, s1.length());
        }
        if(s2.startsWith("key")) {
          key2 = s2.substring(3, s2.length());
        }
        if(key1.equals(key2)) return 0;
        return key1.compareTo(key2) < 0 ? -1 : 1;
      }
    };
    SortedSet<String> set2 = new TreeSet<String>(comp);
    set2.add("abc");
    set2.add("def");
    set2.add("ghi");
    set2.add("jkl");
    set2.add("keyaaa");
    assertThat("keyaaa", is(equalTo(set2.first())));
    set2.remove("keyaaa");
    assertThat("abc", is(equalTo(set2.first())));
  }
  
  /**
   * test the subSet(E fromElement, E toElement) method.
   * Returns SortedSet that includes elements from fromElement, inclusive, to toElement, exclusive.
   */
  @Test
  public void testSubSet() {
    SortedSet<String> s = getSet();
    SortedSet<String> s2 = s.subSet("def", "jkl");
    assertThat(2, is(equalTo(s2.size())));
    assertThat("def", is(equalTo(s2.first())));
    assertThat("ghi", is(equalTo(s2.last())));
  }

  /**
   * Test the headSet(E e) method.
   * Returns SortedSet of all elements less than e.
   */
  @Test
  public void testHeadSet() {
    SortedSet<String> s = getSet();
    SortedSet<String> s2 = s.headSet("def");
    assertThat(1, is(equalTo(s2.size())));
    s2 = s.headSet("deff");
    assertThat(2, is(equalTo(s2.size())));
  }

  /**
   * Test the tailSet(E e) method.
   * Returns SortedSet of all elements greater than or equal to e.
   */
  @Test
  public void testTailSet() {
    SortedSet<String> s = getSet();
    SortedSet<String> s2 = s.tailSet("def");
    assertThat(3, is(equalTo(s2.size())));
    s2 = s.tailSet("deff");
    assertThat(2, is(equalTo(s2.size())));
  }

  /**
   * Test the first() method.
   * Returns the first/lowest element in the set.
   */
  @Test
  public void testFirst() {
    SortedSet<String> s = getSet();
    assertThat("abc", is(equalTo(s.first())));
  }

  /**
   * Test the last() method.
   * Returns the last/highest element in the set.
   */
  @Test
  public void testLast() {
    SortedSet<String> s = getSet();
    assertThat("jkl", is(equalTo(s.last())));
  }

  private SortedSet<String> getSet() {
    SortedSet<String> set = new TreeSet<String>();
    set.add("abc");
    set.add("def");
    set.add("ghi");
    set.add("jkl");
    return set;
  }
  
}
