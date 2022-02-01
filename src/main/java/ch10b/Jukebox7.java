package ch10b;

import java.util.*;

public class Jukebox7 {
  private final MockMoreSongs mockFile = new MockMoreSongs();

  public static void main(String[] args) {
    new Jukebox7().go();
  }

  public void go() {
    List<SongV4> songList = mockFile.getSongs();
    System.out.println(songList);
    songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
    System.out.println(songList);
    songList.sort((one, two) -> one.getArtist().compareTo(two.getArtist()));
    System.out.println(songList);
  }
}
