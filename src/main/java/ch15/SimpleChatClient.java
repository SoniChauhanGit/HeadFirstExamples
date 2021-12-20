package ch15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatClient {

  public static void main(String[] args) throws IOException {
    Socket socket = setUpNetworking();

    ClientUI clientUI = new ClientUI();
    clientUI.go(socket);

    IncomingReader incomingReader = new IncomingReader(clientUI.getIncoming(), socket);
    ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
    threadExecutor.execute(incomingReader);
    threadExecutor.shutdown();
  }

  private static Socket setUpNetworking() throws IOException {
    Socket socket = new Socket("127.0.0.1", 5000);
    System.out.println("networking established");
    return socket;
  }
}

class ClientUI {
  private JTextArea incoming;

  public void go(Socket socket) {
    JFrame frame = new JFrame("Ludicrously Simple Chat Client");
    JPanel mainPanel = new JPanel();
    incoming = new JTextArea(15, 50);
    incoming.setLineWrap(true);
    incoming.setWrapStyleWord(true);
    incoming.setEditable(false);
    JScrollPane qScroller = new JScrollPane(incoming);
    qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    JTextField outgoing = new JTextField(20);
    JButton sendButton = new JButton("Send");
    sendButton.addActionListener(new SendButtonListener(outgoing, socket));
    mainPanel.add(qScroller);
    mainPanel.add(outgoing);
    mainPanel.add(sendButton);
    frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
    frame.setSize(800, 500);
    frame.setVisible(true);
  }

  public JTextArea getIncoming() {
    return incoming;
  }

  public static class SendButtonListener implements ActionListener {
    private final JTextField outgoing;
    private final PrintWriter writer;

    public SendButtonListener(JTextField outgoingMessage, Socket socket) {
      try {
        writer = new PrintWriter(socket.getOutputStream());
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      outgoing = outgoingMessage;
    }

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
}

class IncomingReader implements Runnable {
  private final BufferedReader reader;
  private final JTextArea incomingMessageArea;

  public IncomingReader(JTextArea incomingMessageArea, Socket socket) throws IOException {
    this.incomingMessageArea = incomingMessageArea;
    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  }

  public void run() {
    processMessages();
  }

  private void processMessages() {
    String message;
    try {
      while ((message = reader.readLine()) != null) {
        System.out.println("read " + message);
        incomingMessageArea.append(message + "\n");
      } // close while
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
} // close inner class