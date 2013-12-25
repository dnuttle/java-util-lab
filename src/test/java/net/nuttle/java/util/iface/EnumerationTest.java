package net.nuttle.java.util.iface;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.Vector;

import org.junit.Test;

/**
 * Test the Enumeration interface.
 * Note that this has been supplanted by the Iterator interface.
 * @author dan
 *
 */
public class EnumerationTest {

  /**
   * Test the hasMoreElements() method.
   * It returns true if the enumeration is not empty.
   */
  @Test
  public void testHasMoreElements() {
    Vector<String> v = new Vector<String>();
    v.add("abc");
    v.add("def");
    Enumeration<String> e = v.elements();
    assertTrue(e.hasMoreElements());
    e.nextElement();
    assertTrue(e.hasMoreElements());
    e.nextElement();
    assertFalse(e.hasMoreElements());
  }

  /**
   * Test the nextElement() method.
   * It returns the next element in the enumeration.
   */
  @Test
  public void testNextElement() {
    Vector<String> v = new Vector<String>();
    v.add("abc");
    v.add("def");
    Enumeration<String> e = v.elements();
    assertThat("abc", is(equalTo(e.nextElement())));
    assertThat("def", is(equalTo(e.nextElement())));
  }

  /**
   * Test the nextElement() method when the enumeration is empty.
   * It throws NoSuchElementException.
   */
  @Test(expected=NoSuchElementException.class)
  public void testNextElementThrowsNoSuchElement() {
    Vector<String> v = new Vector<String>();
    Enumeration<String> e = v.elements();
    e.nextElement();
  }

}
