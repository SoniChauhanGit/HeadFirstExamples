package ch14.btc;

import javax.swing.*;
import java.awt.BorderLayout;

class InnerButton {
  private JFrame frame;
  private JButton b;

  public static void main(String[] args) {
    InnerButton gui = new InnerButton();
    gui.go();
  }

  public void go() {
    frame = new JFrame();
    frame.setDefaultCloseOperation(
            JFrame.EXIT_ON_CLOSE);

    b = new JButton("A");
//    b.addActionListener();

    frame.getContentPane().add(
            BorderLayout.SOUTH, b);
    frame.setSize(200, 100);
    frame.setVisible(true);
  }

//  class BListener extends ActionListener {
//    public void actionPerformed(ActionEvent e) {
//      if (b.getText().equals("A")) {
//        b.setText("B");
//      } else {
//        b.setText("A");
//      }
//    }
//  }
}