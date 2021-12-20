package ch15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleChatClient {

  public static void main(String[] args) throws IOException {
    SocketChannel socket = setUpNetworking();

    ClientUI clientUI = new ClientUI();
    clientUI.go(socket);

    IncomingReader incomingReader = new IncomingReader(clientUI.getIncoming(), socket);
    ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
    threadExecutor.execute(incomingReader);
    threadExecutor.shutdown();
  }

  private static SocketChannel setUpNetworking() throws IOException {
    SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 5000));
    System.out.println("Networking established. Local address: " + socketChannel.getLocalAddress());
    return socketChannel;
  }
}

class ClientUI {
  private JTextArea incoming;

  public void go(SocketChannel socket) {
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
    private final SocketChannel channel;

    public SendButtonListener(JTextField outgoingMessage, SocketChannel channel) {
      outgoing = outgoingMessage;
      this.channel = channel;
    }

    public void actionPerformed(ActionEvent ev) {
      // pretty sure this is doing what I think it's doing
      byte[] message = outgoing.getText().getBytes();
      ByteBuffer buffer = ByteBuffer.wrap(message);
      try {
        channel.write(buffer);
      } catch (Exception ex) {
        ex.printStackTrace();
      }
      outgoing.setText("");
      outgoing.requestFocus();
    }
  }
}

class IncomingReader implements Runnable {
  private final SocketChannel channel;
  private final JTextArea incomingMessageArea;

  public IncomingReader(JTextArea incomingMessageArea, SocketChannel channel) {
    this.incomingMessageArea = incomingMessageArea;
    this.channel = channel;
  }

  public void run() {
    processMessages();
  }

  private void processMessages() {
    ByteBuffer buffer = ByteBuffer.allocate(80);

    try {
      while (channel.read(buffer) != -1) {
        buffer.flip();
        byte[] bytes = new byte[buffer.remaining()];
        buffer.get(bytes);
        String message = new String(bytes, StandardCharsets.UTF_8);

        System.out.println("read " + message);
        incomingMessageArea.append(message + "\n");
        buffer.clear();
      }
      channel.close();
      System.out.println("Connection closed");
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
} // close inner class