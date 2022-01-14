package appA;

import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;

public class BeatBoxFinal {
  public static final int NUMBER_OF_BEATS = 16;
  private final List<BeatInstrument> instruments;

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
  private Track track;

  public static void main(String[] args) {
    new BeatBoxFinal().startUp(args[0]);  // args[0] is your user ID/screen name
  }

  public BeatBoxFinal() {
    instruments = List.of(
            new BeatInstrument("Bass Drum", 35),
            new BeatInstrument("Closed Hi-Hat", 42),
            new BeatInstrument("Open Hi-Hat", 46),
            new BeatInstrument("Acoustic Snare", 38),
            new BeatInstrument("Crash Cymbal", 49),
            new BeatInstrument("Hand Clap", 39),
            new BeatInstrument("High Tom", 50),
            new BeatInstrument("Hi Bongo", 60),
            new BeatInstrument("Maracas", 70),
            new BeatInstrument("Whistle", 72),
            new BeatInstrument("Low Conga", 64),
            new BeatInstrument("Cowbell", 56),
            new BeatInstrument("Vibraslap", 58),
            new BeatInstrument("Low-mid Tom", 47),
            new BeatInstrument("High Agogo", 67),
            new BeatInstrument("Open Hi Conga", 63));
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
    for (BeatInstrument instrument : instruments) {
      JLabel instrumentName = new JLabel(instrument.getInstrumentName());
      instrumentName.setBorder(BorderFactory.createEmptyBorder(4, 1, 4, 1));
      nameBox.add(instrumentName);
    }

    background.add(BorderLayout.EAST, buttonBox);
    background.add(BorderLayout.WEST, nameBox);

    theFrame.getContentPane().add(background);
    int numberOfInstruments = instruments.size();
    GridLayout grid = new GridLayout(numberOfInstruments, NUMBER_OF_BEATS);
    grid.setVgap(1);
    grid.setHgap(2);
    JPanel mainPanel = new JPanel(grid);
    background.add(BorderLayout.CENTER, mainPanel);

    for (int i = 0; i < (numberOfInstruments * NUMBER_OF_BEATS); i++) {
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
    List<Integer> trackList; // this will hold the instruments for each beat
    sequence.deleteTrack(track);
    track = sequence.createTrack();

    int numberOfInstruments = instruments.size();
    for (int i = 0; i < numberOfInstruments; i++) {
      trackList = new ArrayList<>();

      for (int j = 0; j < NUMBER_OF_BEATS; j++) {
        JCheckBox jc = checkboxList.get(j + (NUMBER_OF_BEATS * i));
        if (jc.isSelected()) {
          int key = instruments.get(i).getMidiValue();
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

  public void changeSequence(boolean[] checkboxState) {
    for (int i = 0; i < 256; i++) {
      JCheckBox check = checkboxList.get(i);
      check.setSelected(checkboxState[i]);
    }
  }

  public void makeTracks(List<Integer> list) {
    Iterator<Integer> it = list.iterator();
    for (int i = 0; i < NUMBER_OF_BEATS; i++) {
      Integer num = it.next();
      if (num != null) {
        track.add(makeEvent(ShortMessage.NOTE_ON, 9, num, 100, i));
        track.add(makeEvent(ShortMessage.NOTE_OFF, 9, num, 100, i + 1));
      }
    }
  }

  public MidiEvent makeEvent(int command, int channel, int one, int two, int tick) {
    MidiEvent event = null;
    try {
      ShortMessage midiMessage = new ShortMessage(command, channel, one, two);
      event = new MidiEvent(midiMessage, tick);
    } catch (InvalidMidiDataException ignored) {
    }
    return event;
  }

  private static class BeatInstrument {
    private final String instrumentName;
    private final int midiValue;

    BeatInstrument(String instrumentName, int midiValue) {
      this.instrumentName = instrumentName;
      this.midiValue = midiValue;
    }

    public String getInstrumentName() {
      return instrumentName;
    }

    public int getMidiValue() {
      return midiValue;
    }
  }
}


        

             

          

          

          