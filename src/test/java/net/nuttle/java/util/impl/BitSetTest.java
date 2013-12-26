package net.nuttle.java.util.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

import java.util.BitSet;

import org.junit.Before;
import org.junit.Test;

public class BitSetTest {

  private BitSet set = null;
  @Before
  public void setUp() {
    set = getBitSet();
  }

  /**
   * Test the flip(int) method.
   * Flips the specified bit.
   */
  @Test
  public void testFlipInt() {
    assertTrue(set.get(0));
    set.flip(0);
    assertFalse(set.get(0));
    set.flip(0);
    assertTrue(set.get(0));
  }

  /**
   * Test the flip(int fromIndex, int toIndex) method.
   * Flips all the from fromIndex inclusive to toIndex exclusive
   */
  @Test
  public void testFlipIntInt() {
    set = getBitSet(false, false, false, false);
    set.flip(0,3);
    assertTrue(set.get(0));
    assertTrue(set.get(1));
    assertTrue(set.get(2));
    assertFalse(set.get(3));
  }

  /**
   * Test the set(int index) method.
   * Sets the element at index to true.
   */
  @Test
  public void testSetInt() {
    assertFalse(set.get(1));
    set.set(1);
    assertTrue(set.get(1));
  }

  /**
   * Test the set(int index, boolean value) method.
   * Sets the element at index to value.
   */
  @Test
  public void testSetIntBoolean() {
    assertTrue(set.get(0));
    set.set(0, false);
    assertFalse(set.get(0));
    set.set(0, true);
    assertTrue(set.get(0));
  }

  /**
   * Test the set(int fromIndex, int toIndex) method.
   * Sets the elements from fromIndex (inclusive) to toIndex (exclusive) to true.
   */
  @Test
  public void testSetIntInt() {
    set = getBitSet(false, false, false, false);
    set.set(0, 3);
    assertTrue(set.get(0));
    assertTrue(set.get(1));
    assertTrue(set.get(2));
    assertFalse(set.get(3));
  }

  /**
   * Test the set(int fromIndex, int toIndex, boolean value) method.
   * Sets the elements from fromIndex (inclusive) to toIndex (exclusive) to value.
   */
  @Test
  public void testSetIntIntBoolean() {
    set = getBitSet(true, true, true, true);
    set.set(0, 3, false);
    assertFalse(set.get(0));
    assertFalse(set.get(1));
    assertFalse(set.get(2));
    assertTrue(set.get(3));
  }

  /**
   * Test the clear(int index) method.
   * Sets the element at index to false.
   */
  @Test
  public void testClearInt() {
    assertTrue(set.get(0));
    set.clear(0);
    assertFalse(set.get(0));
  }

  /**
   * Test the clear(int fromIndex, int toIndex) method.
   * Sets the elements from fromIndex (inclusive) to toIndex (exclusive) to false.
   */
  @Test
  public void testClearIntInt() {
    set = getBitSet(true, true, true, true);
    set.clear(0, 3);
    assertFalse(set.get(0));
    assertFalse(set.get(1));
    assertFalse(set.get(2));
    assertTrue(set.get(3));
  }

  /**
   * Test the clear(method).
   * Sets all elements to false.
   */
  @Test
  public void testClear() {
    set = getBitSet(true, true, true, true);
    set.clear();
    assertThat(set.length(), is(equalTo(0)));
  }

  /**
   * Test the get(int index) method.
   * Returns the value of element index.
   */
  @Test
  public void testGetInt() {
    assertTrue(set.get(0));
    assertFalse(set.get(1));
  }

  /**
   * Test the get(int fromIndex, int toIndex) method.
   * Returns BitSet containing elements from fromIndex (inclusive) to toIndex (exclusive).
   */
  @Test
  public void testGetIntInt() {
    BitSet set2 = set.get(0, 3);
    assertThat(3, is(equalTo(set2.length())));
    assertTrue(set2.get(0));
    assertFalse(set2.get(1));
    assertTrue(set2.get(2));
  }

  /**
   * Test the nextSetBit(int index) method.
   * Returns the index of the next element that is set, starting with index, 
   * or -1 if none found.
   */
  @Test
  public void testNextSetBit() {
    assertThat(0, is(equalTo(set.nextSetBit(0))));
    assertThat(2, is(equalTo(set.nextSetBit(1))));
    assertThat(-1, is(equalTo(set.nextSetBit(3))));
  }

  /**
   * Test the nextClearBit(int index) method.
   * Returns the index of the next element that is cleared, starting with index,
   * or -1 if none found.
   */
  @Test
  public void testNextClearBit() {
    assertThat(1, is(equalTo(set.nextClearBit(0))));
    assertThat(1, is(equalTo(set.nextClearBit(1))));
    assertThat(3, is(equalTo(set.nextClearBit(2))));
    assertThat(4, is(equalTo(set.nextClearBit(4))));
  }

  /**
   * Test the length() method.
   * This returns the index of the highest set element, plus one,
   * or 0 if no elements are set.
   * So this is a 1-based index of the highest set element.
   */
  @Test
  public void testLength() {
    assertThat(3, is(equalTo(set.length())));
    set.clear();
    assertThat(0, is(equalTo(set.length())));
  }

  /**
   * Test the isEmpty() method.
   * This returns true if there are no set bits.
   */
  @Test
  public void testIsEmpty() {
    assertFalse(set.isEmpty());
    set.clear();
    assertTrue(set.isEmpty());
  }

  /**
   * Test the intersects(BitSet bs) method.
   * This returns true if bs has any bits that are also set in the current BitSet.
   * "Intersection" occurs only on true values; so if two bit sets both contain only
   * false values, they do *not* intersect.
   */
  @Test
  public void testIntersects() {
    BitSet set2 = getBitSet(false, true, false, true);
    assertFalse(set.intersects(set2));
    set2 = getBitSet(false, false, false, false);
    assertFalse(set.intersects(set2));
    set2 = getBitSet(true);
    assertTrue(set.intersects(set2));
    set = getBitSet(false, false);
    set2 = getBitSet(false, false);
    assertFalse(set.intersects(set2));
  }

  /**
   * Test the cardinality() method.
   * Returns the number of bits set in the BitSet.
   */
  @Test
  public void testCardinality() {
    assertThat(2, is(equalTo(set.cardinality())));
    set.clear(0);
    assertThat(1, is(equalTo(set.cardinality())));
    set.clear();
    assertThat(0, is(equalTo(set.cardinality())));
  }

  /**
   * Test the and(BitSet bs) method.
   * Modifies the underlying BitSet by an AND operation with bs.
   */
  @Test
  public void testAnd() {
    BitSet set2 = getBitSet(true, false, false, false);
    set.and(set2);
    assertThat(1, is(equalTo(set.cardinality())));
    assertTrue(set.get(0));
    set = getBitSet(false, false);
    set2 = getBitSet(false, false);
    set.and(set2);
    assertThat(0, is(equalTo(set.cardinality())));
  }

  /**
   * Test the or(BitSet bs) method.
   * Modifies the underlying BitSet with an OR operation with bs.
   */
  @Test
  public void testOr() {
    BitSet set2 = getBitSet();
    set.or(set2);
    assertThat(2, is(equalTo(set.cardinality())));
    assertTrue(set.get(0));
    assertTrue(set.get(2));
    set2 = getBitSet(false, true, false, true);
    set.or(set2);
    assertThat(4, is(equalTo(set.cardinality())));
  }

  /**
   * Test the xor(BitSet bs) method.
   * Modifies the underlying BitSet with on OR operation with bs.
   */
  @Test
  public void testXor() {
    BitSet set2 = getBitSet(true, false, true, false);
    set.xor(set2);
    assertThat(0, is(equalTo(set.cardinality())));
    set = getBitSet();
    set2 = getBitSet(false, false, true, false);
    set.xor(set2);
    assertThat(1, is(equalTo(set.cardinality())));
    assertTrue(set.get(0));
  }

  /**
   * Test the andNot(BitSet bs) method.
   * Clears all the bits in the underlying BitSet corresponding to set bits in bs.
   */
  @Test
  public void testAndNot() {
    BitSet set2 = getBitSet(false, false, false, false);
    set.andNot(set2);
    assertThat(2, is(equalTo(set.cardinality())));
    set2 = getBitSet(false, true, false, true);
    set.andNot(set2);
    assertThat(2, is(equalTo(set.cardinality())));
    set2 = getBitSet(true, true, true, true);
    set.andNot(set2);
    assertThat(0, is(equalTo(set.cardinality())));
  }

  /**
   * Test the size() method.
   * Returns the number of bits actually in use.
   * Apparently, if this runs on a 64-bit OS, then the first byte 
   * will have 64 bits.
   * So the value returned may vary based on OS.
   */
  @Test
  public void testSize() {
    assertThat(64, is(equalTo(set.size())));
    set.set(64, true);
    assertThat(128, is(equalTo(set.size())));
  }

  /**
   * Utility method returns BitSet with stock values:
   * true, false, true, false
   * @return
   */
  private BitSet getBitSet() {
    return getBitSet(true, false, true, false);
  }
  
  /**
   * Utility method returns BitSet with specified values.
   * @param arg
   * @return
   */
  private BitSet getBitSet(boolean...arg) {
    BitSet bs = new BitSet(arg.length);
    for (int i = 0; i < arg.length; i++) {
      bs.set(i, arg[i]);
    }
    return bs;
  }
}

