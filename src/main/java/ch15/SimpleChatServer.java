package ch15;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

public class SimpleChatServer {

  public static void main(String[] args) {
    new SimpleChatServer().go();
  }

  public void go() {
    try {
      ServerSocket serverSock = new ServerSocket(5000);
      Broadcaster broadcaster = new Broadcaster();
      while (true) {
        Socket clientSocket = serverSock.accept();
        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
        broadcaster.addClient(writer);
        ClientHandler clientHandler = new ClientHandler(clientSocket, broadcaster);
        Thread t = new Thread(clientHandler);
        t.start();
        System.out.println("got a connection");
      }
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  } // close go
} // close class

class ClientHandler implements Runnable {
  private BufferedReader reader;
  private Socket sock;
  private final Broadcaster broadcaster;

  public ClientHandler(Socket clientSocket, Broadcaster broadcaster) {
    this.broadcaster = broadcaster;
    try {
      sock = clientSocket;
      InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
      reader = new BufferedReader(isReader);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  } // close constructor

  public void run() {
    String message;
    try {
      while ((message = reader.readLine()) != null) {
        System.out.println("read " + message);
        broadcaster.tellEveryone(message);
      } // close while
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  } // close run

}

class Broadcaster {
  private final ArrayList<PrintWriter> clientOutputStreams = new ArrayList<>();

  void addClient(PrintWriter clientOutputStream) {
    clientOutputStreams.add(clientOutputStream);
  }

  public void tellEveryone(String message) {
    Iterator<PrintWriter> it = clientOutputStreams.iterator();
    while (it.hasNext()) {
      try {
        PrintWriter writer = it.next();
        writer.println(message);
        writer.flush();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    } // end while
  } // close tellEveryone
}