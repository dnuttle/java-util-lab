package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.TreeMap;

import org.junit.Test;

/**
 * Tests java.util.NavigableMap.
 * See also SortedMapTest and MapTest.
 * @author dan
 *
 */
public class NavigableMapTest {

  /**
   * Test the lowerEntry(Key k) method.
   * It returns Map.Entry<K, V> whose key is the greatest in the map that is less than k, or null if none exists.
   */
  @Test
  public void testLowerEntry() {
    NavigableMap<String, String> map = getMap(true);
    Map.Entry<String, String> entry = map.lowerEntry("key3");
    assertThat("key2", is(equalTo(entry.getKey())));
    entry = map.lowerEntry("key33");
    assertThat("key3", is(equalTo(entry.getKey())));
    entry = map.lowerEntry("key1");
    assertNull(entry);
  }

  /**
   * Test the lowerKey(Key k) method.
   * It returns the key that is greatest in the map that is less than k, or null if none exists.
   */
  @Test
  public void testLowerKey() {
    NavigableMap<String, String> map = getMap(true);
    String key = map.lowerKey("key3");
    assertThat("key2", is(equalTo(key)));
    key = map.lowerKey("key33");
    assertThat("key3", is(equalTo(key)));
    key = map.lowerKey("key1");
    assertNull(key);
  }

  /**
   * Test the floorEntry(Key k) method.
   * It returns the Map.Entry<K, V> whose key is the greatest in the map, and less than or equal to k, or null if none exists.
   */
  @Test
  public void testFloorEntry() {
    NavigableMap<String, String> map = getMap(true);
    Map.Entry<String, String> entry = map.floorEntry("key3");
    assertThat("key3", is(equalTo(entry.getKey())));
    entry = map.floorEntry("key33");
    assertThat("key3", is(equalTo(entry.getKey())));
    entry = map.floorEntry("key0");
    assertNull(entry);
  }

  /**
   * Test the floorKey(Key k) method.
   * It returns the key that is greatest in the map that is less than or equal to k, or null if none exists.
   */
  @Test
  public void testFloorKey() {
    NavigableMap<String, String> map = getMap(true);
    String key = map.floorKey("key3");
    assertThat("key3", is(equalTo(key)));
    key = map.floorKey("key33");
    assertThat("key3", is(equalTo(key)));
    key = map.floorKey("key0");
    assertNull(key);
  }

  /**
   * Test the ceilingEntry(Key k) method.
   * It returns the Map.Entry<K, V> whose key is the least in the map that is greater than or equal to k, or null if none exists.
   */
  @Test
  public void testCeilingEntry() {
    NavigableMap<String, String> map = getMap(true);
    Map.Entry<String, String> entry = map.ceilingEntry("key3");
    assertThat("key3", is(equalTo(entry.getKey())));
    entry = map.ceilingEntry("key33");
    assertThat("key4", is(equalTo(entry.getKey())));
    assertNull(map.ceilingKey("key9"));
  }

  /**
   * Test the ceilingKey(Key k) method.
   * It returns the least key in the map that is greater than or equal to k, or null if none exists.
   */
  @Test
  public void testCeilingKey() {
    NavigableMap<String, String> map = getMap(true);
    String key = map.ceilingKey("key3");
    assertThat("key3", is(equalTo(key)));
    key = map.ceilingKey("key33");
    assertThat("key4", is(equalTo(key)));
    assertNull(map.ceilingKey("key9"));
  }

  /**
   * Test the higherEntry(Key k) method.
   * It returns the Map.Entry<K, V> whose key is the least in the map that is greater than k, or null if none exists.
   */
  @Test
  public void testHigherEntry() {
    NavigableMap<String, String> map = getMap(true);
    Map.Entry<String, String> entry = map.higherEntry("key2");
    assertThat("key3", is(equalTo(entry.getKey())));
    entry = map.higherEntry("key22");
    assertThat("key3", is(equalTo(entry.getKey())));
    assertNull(map.higherEntry("key9"));
  }

  /**
   * Test the higherKey(Key k) method.
   * It returns the least key in the map that is greater than k, or null if none exists.
   */
  @Test
  public void testHigherKey() {
    NavigableMap<String, String> map = getMap(true);
    String key = map.higherKey("key2");
    assertThat("key3", is(equalTo(key)));
    assertNull(map.higherKey("key9"));
  }

  /**
   * Tests the firstEntry() method.
   * Returns Map.Entry<K, V> for the entry with the least key in the map, or null if map is empty.
   */
  @Test
  public void testFirstEntry() {
    NavigableMap<String, String> map = getMap();
    Map.Entry<String, String> entry = map.firstEntry();
    assertThat("key1", is(equalTo(entry.getKey())));
    map = new TreeMap<String, String>();
    assertNull(map.firstEntry());
  }

  /**
   * Tests the lastEntry() method.
   * Returns Map.Entry<K, V> for the entry with the highest key in the map, or null if map is empty.
   */
  @Test
  public void testLastEntry() {
    NavigableMap<String, String> map = getMap();
    Map.Entry<String, String> entry = map.lastEntry();
    assertThat("key2", is(equalTo(entry.getKey())));
    map = new TreeMap<String, String>();
    assertNull(map.lastEntry());
  }

  /**
   * Tests the pollFirstEntry() method.
   * Removes and returns the Map.Entry<K, V> whose key is least in the map, or null if map is empty.
   */
  @Test
  public void testPollFirstEntry() {
    NavigableMap<String, String> map = getMap();
    Map.Entry<String, String> entry = map.pollFirstEntry();
    assertThat("key1", is(equalTo(entry.getKey())));
    assertThat(1, is(equalTo(map.size())));
    map.pollFirstEntry();
    assertNull(map.pollFirstEntry());
  }

  /**
   * Tests the pollLastEntry() method.
   * Removes and returns the Map.Entry<K, V> whose key is highest in the map, or null if map is empty.
   */
  @Test
  public void testPollLastEntry() {
    NavigableMap<String, String> map = getMap();
    Map.Entry<String, String> entry = map.pollLastEntry();
    assertThat("key2", is(equalTo(entry.getKey())));
    assertThat(1, is(equalTo(map.size())));
    map.pollLastEntry();
    assertNull(map.pollLastEntry());
  }

  /**
   * Tests the descendingMap() method.
   * Returns a NavigableMap whose keys are in reverse order of the original.
   */
  @Test
  public void testDescendingMap() {
    NavigableMap<String, String> map = getMap();
    NavigableMap<String, String> reverse = map.descendingMap();
    Map.Entry<String, String> entry = reverse.pollFirstEntry();
    assertThat("key2", is(equalTo(entry.getKey())));
    entry = reverse.pollFirstEntry();
    assertThat("key1", is(equalTo(entry.getKey())));
    assertNull(reverse.firstEntry());
    assertThat(0, is(equalTo(map.size())));
  }

  /**
   * Tests the navigableKeySet() method. 
   * Returns a NavigableSet<K> view of the keys in the underlying map.
   */
  @Test
  public void testNavigableKeySet() {
    NavigableMap<String, String> map = getMap();
    NavigableSet<String> ns = map.navigableKeySet();
    String key = ns.pollFirst();
    assertThat("key1", is(equalTo(key)));
    key = ns.pollFirst();
    assertThat("key2", is(equalTo(key)));
    assertNull(ns.pollFirst());
  }

  /**
   * Tests the descendingKeySet() method.
   * Returns a NavigableSet<K> view of the keys in the underlying map, but in reverse order.
   */
  @Test
  public void testDescendingKeySet() {
    NavigableMap<String, String> map = getMap();
    NavigableSet<String> ns = map.descendingKeySet();
    String key = ns.pollFirst();
    assertThat("key2", is(equalTo(key)));
    key = ns.pollFirst();
    assertThat("key1", is(equalTo(key)));
    assertNull(ns.pollFirst());
  }

  /**
   * Tests the subMap(Key fromKey, boolean fromInclusive, Key toKey, boolean toInclusive) method.
   * Returns NaviableMap<K, V> whose keys are from fromKey to toKey.
   */
  @Test
  public void testSubMapKBooleanKBoolean() {
    NavigableMap<String, String> map = getMap(true);
    NavigableMap<String, String> map2 = map.subMap("key2", true, "key4", false);
    assertThat(2, is(equalTo(map2.size())));
    assertThat("def", is(equalTo(map2.get("key2"))));
    assertThat("ghi", is(equalTo(map2.get("key3"))));
  }

  /**
   * Tests the headMap(Key k, boolean inclusive) method.
   * Returns NavigableMap<K, V> whose keys are less than (or equal to) k. 
   */
  @Test
  public void testHeadMapKBoolean() {
    NavigableMap<String, String> map = getMap(true);
    NavigableMap<String, String> map2 = map.headMap("key3", true);
    assertThat(3, is(equalTo(map2.size())));
    map2 = map.headMap("key3", false);
    assertThat(2, is(equalTo(map2.size())));
    assertTrue(map2.keySet().contains("key1"));
    assertTrue(map2.keySet().contains("key2"));
  }

  /**
   * Tests the tailMap(Key k, boolean inclusive) method.
   * Returns NavigableMap<String, String> whose keys are greater than (or equal to) k.
   */
  @Test
  public void testTailMapKBoolean() {
    NavigableMap<String, String> map = getMap(true);
    NavigableMap<String, String> map2 = map.tailMap("key3", true);
    assertThat(2, is(equalTo(map2.size())));
    map2 = map.tailMap("key3", false);
    assertThat(1, is(equalTo(map2.size())));
    assertTrue(map2.keySet().contains("key4"));
  }

  /**
   * Utility method creates and returns NavigableMap with stock values.
   * @return
   */
  public NavigableMap<String, String> getMap() {
    return getMap(false);
  }
  
  public NavigableMap<String, String> getMap(boolean longer) {
    NavigableMap<String, String> map = new TreeMap<String, String>();
    map.put("key1", "abc");
    map.put("key2", "def");
    if(longer) {
      map.put("key3", "ghi");
      map.put("key4", "jkl");
    }
    return map;
  }
}
