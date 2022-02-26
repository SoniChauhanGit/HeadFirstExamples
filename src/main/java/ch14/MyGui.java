package ch14;

import javax.swing.*;
import java.awt.event.*;

class MyGui implements ActionListener {
  private JButton colorButton;
  private JButton labelButton;
  private JFrame frame;
  private JLabel label;
  // declare a bunch of instance variables here

  public void go() {
    // build gui
    colorButton = new JButton();
    labelButton = new JButton();
    colorButton.addActionListener(this);
    labelButton.addActionListener(this);
    // more gui code here ...
  }

  public void actionPerformed(ActionEvent event) {
    if (event.getSource() == colorButton) {
      frame.repaint();
    } else {
      label.setText("That hurt!");
    }
  }
}

