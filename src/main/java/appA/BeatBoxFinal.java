package appA;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class BeatBoxFinal {
  private JList<String> incomingList;
  private JTextField userMessage;
  private ArrayList<JCheckBox> checkboxList;
  private int nextNum;
  private final Vector<String> listVector = new Vector<>();
  private String userName;
  private ObjectOutputStream out;
  private ObjectInputStream in;
  private final HashMap<String, boolean[]> otherSeqsMap = new HashMap<>();

  private Sequencer sequencer;
  private Sequence sequence;
  private final Sequence mySequence = null;
  private Track track;

  String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat", "Open Hi-Hat", "Acoustic Snare", "Crash Cymbal",
          "Hand Clap", "High Tom", "Hi Bongo", "Maracas", "Whistle", "Low Conga", "Cowbell", "Vibraslap", "Low-mid Tom",
          "High Agogo", "Open Hi Conga"};

  int[] instruments = {35, 42, 46, 38, 49, 39, 50, 60, 70, 72, 64, 56, 58, 47, 67, 63};

  public static void main(String[] args) {
    new BeatBoxFinal().startUp(args[0]);  // args[0] is your user ID/screen name
  }

  public void startUp(String name) {
    userName = name;
    // open connection to the server
    try {
      Socket sock = new Socket("127.0.0.1", 4242);
      out = new ObjectOutputStream(sock.getOutputStream());
      in = new ObjectInputStream(sock.getInputStream());
      Thread remote = new Thread(new RemoteReader());
      remote.start();
    } catch (Exception ex) {
      System.out.println("Couldn't connect-you'll have to play alone.");
    }
    setUpMidi();
    buildGUI();
  }

  public void buildGUI() {
    JFrame theFrame = new JFrame("Cyber BeatBox");
    BorderLayout layout = new BorderLayout();
    JPanel background = new JPanel(layout);
    background.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    checkboxList = new ArrayList<>();

    Box buttonBox = new Box(BoxLayout.Y_AXIS);
    JButton start = new JButton("Start");
    start.addActionListener(new MyStartListener());
    buttonBox.add(start);

    JButton stop = new JButton("Stop");
    stop.addActionListener(new MyStopListener());
    buttonBox.add(stop);

    JButton upTempo = new JButton("Tempo Up");
    upTempo.addActionListener(new MyUpTempoListener());
    buttonBox.add(upTempo);

    JButton downTempo = new JButton("Tempo Down");
    downTempo.addActionListener(new MyDownTempoListener());
    buttonBox.add(downTempo);

    JButton sendIt = new JButton("sendIt");
    sendIt.addActionListener(new MySendListener());
    buttonBox.add(sendIt);

    userMessage = new JTextField();

    buttonBox.add(userMessage);

    incomingList = new JList<>();
    incomingList.addListSelectionListener(new MyListSelectionListener());
    incomingList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    JScrollPane theList = new JScrollPane(incomingList);
    buttonBox.add(theList);
    incomingList.setListData(listVector); // no data to start with

    Box nameBox = new Box(BoxLayout.Y_AXIS);
    for (int i = 0; i < 16; i++) {
      JLabel instrumentName = new JLabel(instrumentNames[i]);
      instrumentName.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
      nameBox.add(instrumentName);
    }

    background.add(BorderLayout.EAST, buttonBox);
    background.add(BorderLayout.WEST, nameBox);

    theFrame.getContentPane().add(background);
    GridLayout grid = new GridLayout(16, 16);
    grid.setVgap(1);
    grid.setHgap(2);
    JPanel mainPanel = new JPanel(grid);
    background.add(BorderLayout.CENTER, mainPanel);

    for (int i = 0; i < (16*16); i++) {
      JCheckBox c = new JCheckBox();
      c.setSelected(false);
      checkboxList.add(c);
      mainPanel.add(c);
    }

    theFrame.setBounds(50, 50, 300, 300);
    theFrame.pack();
    theFrame.setVisible(true);
  }

  public void setUpMidi() {
    try {
      sequencer = MidiSystem.getSequencer();
      sequencer.open();
      sequence = new Sequence(Sequence.PPQ, 4);
      track = sequence.createTrack();
      sequencer.setTempoInBPM(120);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void buildTrackAndStart() {
    ArrayList<Integer> trackList; // this will hold the instruments for each
    sequence.deleteTrack(track);
    track = sequence.createTrack();

    for (int i = 0; i < 16; i++) {
      trackList = new ArrayList<>();

      for (int j = 0; j < 16; j++) {
        JCheckBox jc = checkboxList.get(j + (16 * i));
        if (jc.isSelected()) {
          int key = instruments[i];
          trackList.add(key);
        } else {
          trackList.add(null);  // because this slot should be empty in the track
        }
      } // close inner loop
      makeTracks(trackList);
    } // close outer loop
    track.add(makeEvent(192, 9, 1, 0, 15)); // - so we always go to full 16 beats
    try {
      sequencer.setSequence(sequence);
      sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
      sequencer.start();
      sequencer.setTempoInBPM(120);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public class MyStartListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      buildTrackAndStart();
    }
  }

  public class MyStopListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      sequencer.stop();
    }
  }

  public class MyUpTempoListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      float tempoFactor = sequencer.getTempoFactor();
      sequencer.setTempoFactor((float) (tempoFactor * 1.03));
    }
  }

  public class MyDownTempoListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      float tempoFactor = sequencer.getTempoFactor();
      sequencer.setTempoFactor((float) (tempoFactor * .97));
    }
  }

  public class MySendListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      // make an arraylist of just the STATE of the checkboxes
      boolean[] checkboxState = new boolean[256];
      for (int i = 0; i < 256; i++) {
        JCheckBox check = checkboxList.get(i);
        if (check.isSelected()) {
          checkboxState[i] = true;
        }
      }
      try {
        out.writeObject(userName + nextNum++ + ": " + userMessage.getText());
        out.writeObject(checkboxState);
      } catch (Exception ex) {
        System.out.println("Sorry dude. Could not send it to the server.");
      }
      userMessage.setText("");
    }
  }

  public class MyListSelectionListener implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent le) {
      if (!le.getValueIsAdjusting()) {
        String selected = incomingList.getSelectedValue();
        if (selected != null) {
          // now go to the map, and change the sequence
          boolean[] selectedState = otherSeqsMap.get(selected);
          changeSequence(selectedState);
          sequencer.stop();
          buildTrackAndStart();
        }
      }
    }
  }

  public class RemoteReader implements Runnable {
    boolean[] checkboxState = null;
    Object obj = null;

    public void run() {
      try {
        while ((obj = in.readObject()) != null) {
          System.out.println("got an object from server");
          System.out.println(obj.getClass());
          String nameToShow = (String) obj;
          checkboxState = (boolean[]) in.readObject();
          otherSeqsMap.put(nameToShow, checkboxState);
          listVector.add(nameToShow);
          incomingList.setListData(listVector);
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }

  public class MyPlayMineListener implements ActionListener {
    public void actionPerformed(ActionEvent a) {
      if (mySequence != null) {
        sequence = mySequence;
      }
    }
  }

  public void changeSequence(boolean[] checkboxState) {
    for (int i = 0; i < 256; i++) {
      JCheckBox check = checkboxList.get(i);
      check.setSelected(checkboxState[i]);
    }
  }

  public void makeTracks(ArrayList<Integer> list) {
    Iterator<Integer> it = list.iterator();
    for (int i = 0; i < 16; i++) {
      Integer num = it.next();
      if (num != null) {
        track.add(makeEvent(144, 9, num, 100, i));
        track.add(makeEvent(128, 9, num, 100, i + 1));
      }
    }
  }

  public MidiEvent makeEvent(int comd, int chan, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage a = new ShortMessage();
      a.setMessage(comd, chan, one, two);
      event = new MidiEvent(a, tick);
    } catch (Exception ignored) {
    }
    return event;
  }

}


        

             

          

          

          