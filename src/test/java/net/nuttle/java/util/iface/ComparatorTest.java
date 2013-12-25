package net.nuttle.java.util.iface;

import java.util.Comparator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComparatorTest {

  @Test
  public void testCompare() {
    assertEquals(0, stringComparator.compare(null, null));
    assertEquals(1, stringComparator.compare("abc", null));
    assertEquals(-1, stringComparator.compare(null, "abc"));
    assertEquals(0, stringComparator.compare("abc", "abc"));
    assertEquals(-1, stringComparator.compare("abc", "def"));
    assertEquals(1, stringComparator.compare("def", "abc"));
  }

  /**
   * This Comparator returns only 0 (equal), -1 (s1 < s2) or 1 (s1 > s2)
   */
  private static Comparator<String> stringComparator = new Comparator<String>() {
    public int compare(String s1, String s2) {
      if (s1==null && s2==null) {
        return 0;
      }
      if (s1==null) {
        return -1;
      }
      if (s2==null) {
        return 1;
      }
      if(s1.equals(s2)) {
        return 0;
      }
      return s1.compareTo(s2) > 0 ? 1 : -1;
    
    }
  };
}