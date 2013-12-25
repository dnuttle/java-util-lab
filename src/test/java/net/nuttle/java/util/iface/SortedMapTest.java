package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Test;

public class SortedMapTest {

  /**
   * Test the keySet() method.
   * It returns the keys by order of value.
   */
  @Test
  public void testKeySet() {
    SortedMap<String, String> map = getMap();
    Set<String> keys = map.keySet();
    String[] keysArray = keys.toArray(new String[0]);
    assertThat(2, is(equalTo(keysArray.length)));
    assertThat("key1", is(equalTo(keysArray[0])));
    assertThat("key2", is(equalTo(keysArray[1])));
  }

  /**
   * Test the values() method.
   * It returns the values of the set, in order of their keys.
   */
  @Test
  public void testValues() {
    SortedMap<String, String> map = getMap();
    Collection<String> values = map.values();
    String[] valuesArray = values.toArray(new String[0]);
    assertThat(2, is(equalTo(valuesArray.length)));
    assertThat("abc", is(equalTo(valuesArray[0])));
    assertThat("def", is(equalTo(valuesArray[1])));
  }

  /**
   * Test the entrySet() method.
   * Returns a Set of Map.Entry instances in order of the key values.
   */
  @Test
  @SuppressWarnings("unchecked")
  public void testEntrySet() {
    SortedMap<String, String> map = getMap();
    Set<Map.Entry<String, String>> entries = map.entrySet();
    Object[] entriesArray = entries.toArray();
    //Testing is difficult; no apparent way to call enries.toArray(T[] t) because you can't instantiate Map.Entry directly
    //So need case each element in entriesArray to Map.Entry<String, String>.
    assertThat("key1", is(equalTo(((Map.Entry<String, String>)entriesArray[0]).getKey())));
    assertThat("key2", is(equalTo(((Map.Entry<String, String>)entriesArray[1]).getKey())));
  }

  /**
   * Test the comparator() method.
   * This returns the Comparator used internally.
   * In this simple example, it is null.
   */
  @Test
  public void testComparatorNull() {
    SortedMap<String, String> map = getMap();
    assertNull(map.comparator());
  }

  /**
   * Test comparator() method.
   * A comparator is used that ignores "key" at the start of a key,
   * so that for instance, "keya" should appear before "b"
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
    SortedMap<String, String> map = new TreeMap<String, String>(comp);
    map.put("keya", "abc");
    map.put("b", "def");
    assertNotNull(map.comparator());
    String[] valuesArray = map.values().toArray(new String[0]);
    assertThat("abc", is(equalTo(valuesArray[0])));
    assertThat("def", is(equalTo(valuesArray[1])));
  }

  /**
   * Test the subMap(K fromKey, K toKey) method.
   * This returns a sorted map containing some of the elements in the original map, 
   * starting with fromKey, and up to, but not including, toKey.
   */
  @Test
  public void testSubMap() {
    SortedMap<String, String> map = getMap();
    map.put("key3", "ghi");
    map.put("key4", "jkl");
    SortedMap<String, String> submap = map.subMap("key2", "key4");
    String[] keys = submap.keySet().toArray(new String[0]);
    assertThat(2, is(equalTo(keys.length)));
    assertThat("key2", is(equalTo(keys[0])));
    assertThat("key3", is(equalTo(keys[1])));
  }

  /**
   * Test the headMap(Key k) method.
   * This returns a sorted map whose keys are all less than k.
   */
  @Test
  public void testHeadMap() {
    SortedMap<String, String> map = getMap();
    map.put("key3", "ghi");
    map.put("key4", "jkl");
    SortedMap<String, String> headMap = map.headMap("key3");
    String[] keys = headMap.keySet().toArray(new String[0]);
    assertThat(2, is(equalTo(keys.length)));
    assertThat("key1", is(equalTo(keys[0])));
    assertThat("key2", is(equalTo(keys[1])));
  }

  /**
   * Test the tailMap(Key k) method.
   * This returns a new sorted map whose keys are all greater than or equal to k. 
   */
  @Test
  public void testTailMap() {
    SortedMap<String, String> map = getMap();
    map.put("key3", "ghi");
    map.put("key4", "jkl");
    SortedMap<String, String> tailMap = map.tailMap("key3");
    String[] keys = tailMap.keySet().toArray(new String[0]);
    assertThat(2, is(equalTo(keys.length)));
    assertThat("key3", is(equalTo(keys[0])));
    assertThat("key4", is(equalTo(keys[1])));
  }

  /**
   * Test the firstKey() method.
   * This returns the lowest key in the map.
   */
  @Test
  public void testFirstKey() {
    SortedMap<String, String> map = getMap();
    assertThat("key1", is(equalTo(map.firstKey())));
  }

  /**
   * Test the lastKey() method.
   * This returns the highest key in the map.
   */
  @Test
  public void testLastKey() {
    SortedMap<String, String> map = getMap();
    assertThat("key2", is(equalTo(map.lastKey())));
  }

  /**
   * Utility method returns a SortedMap with two elements:
   * key1=abc
   * key2=def
   * @return
   */
  public SortedMap<String, String> getMap() {
    SortedMap<String, String> map = new TreeMap<String, String>();
    map.put("key1", "abc");
    map.put("key2", "def");
    return map;
  }
}
