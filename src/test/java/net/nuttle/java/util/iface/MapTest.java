package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Test java.util.Map
 * Some methods are not tested because they are covered in CollectionTest:
 * 
 * @author dan
 *
 */
public class MapTest {

  /**
   * Test containsKey(Object key).
   * It returns true if the underlying map's key set contains key.
   */
  @Test
  public void testContainsKey() {
    Map<String, String> map = getMap();
    assertTrue(map.containsKey("key1"));
    assertFalse(map.containsKey("not_exist"));
  }

  /**
   * Test containsValue(Object value) method.
   * Returns true if value is is one of the values in the map.
   */
  @Test
  public void testContainsValue() {
    Map<String, String> map = getMap();
    assertTrue(map.containsValue("abc"));
    assertFalse(map.containsValue("not_exist"));
  }


  /**
   * Test get(Object key) method.
   * Returns value from map that matches key, if found, else null.
   */
  @Test
  public void testGet() {
    Map<String, String> map = getMap();
    assertThat("abc", is(equalTo(map.get("key1"))));
    assertNull(map.get("not_exist"));
  }

  /**
   * Test put(Key k, Value v) method.
   */
  @Test
  public void testPut() {
    Map<String, String> map = getMap();
    map.put("key1", "newvalue");
    assertThat("newvalue", is(equalTo(map.get("key1"))));
  }

  /**
   * Test remove(Key k) method.
   * It removes the value with key k, if it exists, and returns it (else returns null).
   */
  @Test
  public void testRemove() {
    Map<String, String> map = getMap();
    assertThat("abc", is(equalTo(map.remove("key1"))));
    assertNull(map.remove("notexist"));
  }

  /**
   * Test putAll(Map<? extends K, ? extends V>) method.
   */
  @Test
  public void testPutAll() {
    Map<String, String> map = getMap();
    Map<String, String> map2 = new HashMap<String, String>();
    map2.put("key3", "ghi");
    map2.put("key4", "jkl");
    map.putAll(map2);
    assertThat(4, is(equalTo(map.keySet().size())));
    assertThat("ghi", is(equalTo(map.get("key3"))));
  }

  /**
   * Test keySet() method.
   */
  @Test
  public void testKeySet() {
    Map<String, String> map = getMap();
    assertTrue(map.keySet().contains("key1"));
    assertTrue(map.keySet().contains("key2"));
    assertThat(2, is(equalTo(map.keySet().size())));
  }

  /**
   * Test values() method.
   */
  @Test
  public void testValues() {
    Map<String, String> map = getMap();
    assertThat(2, is(equalTo(map.values().size())));
    assertTrue(map.values().contains("abc"));
    assertTrue(map.values().contains("def"));
  }

  /**
   * Test entrySet() method.
   */
  @Test
  public void testEntrySet() {
    Map<String, String> map = getMap();
    Set<Map.Entry<String, String>> es = map.entrySet();
    Iterator<Map.Entry<String, String>>  it = es.iterator();
    Map.Entry<String, String> entry = it.next();
    assertThat(map.get(entry.getKey()), is(equalTo(entry.getValue())));
    entry = it.next();
    assertThat(map.get(entry.getKey()), is(equalTo(entry.getValue())));
    assertFalse(it.hasNext());
  }
  
  /**
   * Utility method returns a Map<String, String> with the key-value pairs:
   * key1=abc
   * key2=def
   * @return
   */
  private Map<String, String> getMap() {
    Map<String, String> map = new HashMap<String, String>();
    map.put("key1", "abc");
    map.put("key2", "def");
    return map;
  }

}
