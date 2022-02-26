package ch15.ex;

import javax.swing.*;
import java.awt.*;

public class Fragments {
  void fragmentD() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button = new JButton("tesuji");
    JButton buttonTwo = new JButton("watari");
    frame.getContentPane().add(BorderLayout.NORTH, panel);
    panel.add(buttonTwo);
    frame.getContentPane().add(BorderLayout.CENTER, button);
  }

  void fragmentB() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button = new JButton("tesuji");
    JButton buttonTwo = new JButton("watari");
    panel.add(buttonTwo);
    frame.getContentPane().add(BorderLayout.CENTER, button);
    frame.getContentPane().add(BorderLayout.EAST, panel);
  }

  void fragmentC() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button = new JButton("tesuji");
    JButton buttonTwo = new JButton("watari");
    panel.add(buttonTwo);
    frame.getContentPane().add(BorderLayout.CENTER, button);
  }

  void fragmentA() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button = new JButton("tesuji");
    JButton buttonTwo = new JButton("watari");
    panel.add(button);
    frame.getContentPane().add(BorderLayout.NORTH, buttonTwo);
    frame.getContentPane().add(BorderLayout.EAST, panel);
  }

  void fragmentE() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setBackground(Color.darkGray);
    JButton button = new JButton("tesuji");
    JButton buttonTwo = new JButton("watari");
    frame.getContentPane().add(BorderLayout.SOUTH, panel);
    panel.add(buttonTwo);
    frame.getContentPane().add(BorderLayout.NORTH, button);
  }

}
