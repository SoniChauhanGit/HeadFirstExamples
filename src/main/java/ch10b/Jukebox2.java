package ch10b;

import java.util.Collections;
import java.util.List;

public class Jukebox2 {

  public static void main(String[] args) {
    new Jukebox2().go();
  }

  public void go() {
    MockSongs s = new MockSongs();
    List<String> songList = s.getSongStrings();
    System.out.println(songList);
    Collections.sort(songList);
    System.out.println("\n" + songList);
  }
}
