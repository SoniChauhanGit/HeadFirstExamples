package ch14;

import javax.swing.*;
import java.awt.event.*;

class MyGui implements ActionListener {
  private JFrame frame;
  private JLabel label;
  // lots of code here and then:

  public void actionPerformed(ActionEvent event) {
    frame.repaint();
  }

//  public void actionPerformed(ActionEvent event) {
//    label.setText("That hurt!");
//  }
}
