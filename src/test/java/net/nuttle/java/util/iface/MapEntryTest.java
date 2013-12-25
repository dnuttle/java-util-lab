package net.nuttle.java.util.iface;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class MapEntryTest {

  /**
   * Test getKey() and getValue() methods.
   */
  @Test
  public void testGetKeyValue() {
    Map<String, String> map = getMap();
    Set<Entry<String, String>> es = map.entrySet();
    Iterator<Entry<String, String>> it = es.iterator();
    Map.Entry<String, String> entry = it.next();
    assertThat(map.get(entry.getKey()), is(equalTo(entry.getValue())));
    entry = it.next();
    assertThat(map.get(entry.getKey()), is(equalTo(entry.getValue())));
    assertFalse(it.hasNext());
  }

  @Test
  public void testSetValue() {
    Map<String, String> map = getMap();
    Set<Entry<String, String>> es = map.entrySet();
    Iterator<Entry<String, String>> it = es.iterator();
    Map.Entry<String, String> entry = it.next();
    String key = entry.getKey();
    entry.setValue("new");
    assertThat("new", is(equalTo(map.get(key))));
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
