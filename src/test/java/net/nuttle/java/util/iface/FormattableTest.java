package net.nuttle.java.util.iface;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.Locale;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormattableTest {

  /**
   * Test the formatTo method.
   * Much I still don't understand about this yet.
   * But it allows you to create a custom formatter that can "interpret" the formatting string.
   */
  @Test
  public void testFormatTo() {
    FormattableImpl fi = new FormattableImpl("this is a message");
    StringBuilder sb = new StringBuilder();
    Formatter fmt = new Formatter(sb, Locale.US);
    fmt.format("%S", fi);
    assertThat("THIS IS A MESSAGE", is(equalTo(sb.toString())));
    fmt.close();
  }
  
  private static class FormattableImpl implements Formattable {
    private String val;
    public FormattableImpl(String val) {
      this.val = val;
    }
    public void formatTo(Formatter fm, int flags, int width, int precision) {
      StringBuilder sb = new StringBuilder();
      if((flags & FormattableFlags.UPPERCASE)==FormattableFlags.UPPERCASE) {
          sb.append(val.toUpperCase());
      } else {
        sb.append(val);
      }
      fm.format(sb.toString());
    }
  }

}
