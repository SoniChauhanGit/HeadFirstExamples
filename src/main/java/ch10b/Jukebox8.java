package ch10b;

import java.util.*;

public class Jukebox8 {
  private final MockMoreSongs mockFile = new MockMoreSongs();

  public static void main(String[] args) {
    new Jukebox8().go();
  }

  public void go() {
    List<SongV4> songList = mockFile.getSongs();
    System.out.println(songList);
    songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
    System.out.println(songList);
    Set<SongV4> songSet = new HashSet<>(songList);
    System.out.println(songSet);
  }
}
