package ch15;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;

public class SimpleChatClient {
  private JTextArea incoming;
  private JTextField outgoing;
  private BufferedReader reader;
  private PrintWriter writer;

  public void go() {
    setUpNetworking();

    incoming = new JTextArea(15, 30);
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

    ExecutorService executor = Executors.newSingleThreadExecutor();
    executor.execute(new IncomingReader());

    JFrame frame = new JFrame("Ludicrously Simple Chat Client");
    frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    frame.setSize(400, 350);
    frame.setVisible(true);
  }

  private void setUpNetworking() {
    try {
      SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 5000));

      reader = new BufferedReader(Channels.newReader(socketChannel, StandardCharsets.UTF_8));
      writer = new PrintWriter(Channels.newWriter(socketChannel, StandardCharsets.UTF_8));

      System.out.println("Networking established. Client running at: " + socketChannel.getLocalAddress());
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public class SendButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent ev) {
      writer.println(outgoing.getText());
      writer.flush();
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
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    new SimpleChatClient().go();
  }
}