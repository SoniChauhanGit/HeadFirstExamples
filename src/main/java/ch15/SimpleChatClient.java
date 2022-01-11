package ch15;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleChatClient {
  private JTextArea incoming;
  private JTextField outgoing;
  private BufferedReader reader;
  private PrintWriter writer;

  public static void main(String[] args) {
    new SimpleChatClient().go();
  }

  public void go() {
    incoming = new JTextArea(15, 50);
    incoming.setLineWrap(true);
    incoming.setWrapStyleWord(true);
    incoming.setEditable(false);
    JScrollPane qScroller = new JScrollPane(incoming);
    qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    outgoing = new JTextField(20);

    JButton sendButton = new JButton("Send");
    sendButton.addActionListener(new SendButtonListener());

    JPanel mainPanel = new JPanel();
    mainPanel.add(qScroller);
    mainPanel.add(outgoing);
    mainPanel.add(sendButton);

    setUpNetworking();

    Thread readerThread = new Thread(new IncomingReader());
    readerThread.start();

    JFrame frame = new JFrame("Ludicrously Simple Chat Client");
    frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    frame.setSize(800, 500);
    frame.setVisible(true);
  }

  private void setUpNetworking() {
    try {
      Socket socket = new Socket("127.0.0.1", 5000);
      InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
      reader = new BufferedReader(streamReader);
      writer = new PrintWriter(socket.getOutputStream());
      System.out.println("networking established");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public class SendButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ev) {
      try {
        writer.println(outgoing.getText());
        writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      outgoing.setText("");
      outgoing.requestFocus();
    }
  }

  public class IncomingReader implements Runnable {
    public void run() {
      String message;
      try {
        while ((message = reader.readLine()) != null) {
          System.out.println("read " + message);
          incoming.append(message + "\n");
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}