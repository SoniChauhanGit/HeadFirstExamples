package ch10b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Jukebox1 {

  // QU: Should this be a List or ArrayList? Have we covered this?
  List<String> songList = new ArrayList<>();

  public static void main(String[] args) {
    new Jukebox1().go();
  }

  public void go() {
    Songs s = new Songs();
    songList = s.getSongs();
    System.out.println(songList);
    // Next step adds the following
    Collections.sort(songList);
    System.out.println(songList);
  }
}

// Below is the "mock" code. A stand in for the actual
// I/O code that the other programmer will provide later

class Songs {
  public List<String> getSongs() {
    ArrayList<String> songDB = new ArrayList<>();
    songDB.add("somersault");
    songDB.add("cassidy");
    songDB.add("$10");
    songDB.add("havana");
    songDB.add("Cassidy");
    songDB.add("50 Ways");
    return songDB;
  }
}