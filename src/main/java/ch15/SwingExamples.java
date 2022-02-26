package ch15;

import javax.swing.*;
import java.awt.*;

public class SwingExamples {
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    JButton button = new JButton("click me");

    Box box = new Box(BoxLayout.Y_AXIS);
    box.add(button);
    box.add(new JCheckBox("choose me"));

    frame.getContentPane().add(BorderLayout.EAST, box);

    Box textBox = new Box(BoxLayout.X_AXIS);
    textBox.add(new JTextField("this is a text field"));
    frame.getContentPane().add(BorderLayout.NORTH, textBox);

    frame.setSize(300,300);
    frame.setVisible(true);
  }
}
