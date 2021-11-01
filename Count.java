import javax.swing.*;
import java.math.*;
import java.awt.*;
import java.awt.event.*;

class Count {
  int tokens = 0;
  public void one() {
    tokens += 1;
  }
  public void two() {
    tokens += 2;
  }
  public int getTokens() {
    return tokens;
  }
}