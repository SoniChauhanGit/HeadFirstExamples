package ch10b;

import java.util.ArrayList;
import java.util.List;

public class Jukebox7 {
  List<SongV4> songList = new ArrayList<>();
  MockMoreSongs mockFile = new MockMoreSongs();

  public static void main(String[] args) {
    new Jukebox7().go();
  }

  public void go() {
    songList = mockFile.getSongs();
    System.out.println(songList);
    songList.sort((one, two) -> one.getTitle().compareTo(two.getTitle()));
    System.out.println(songList);
    songList.sort((one, two) -> one.getArtist().compareTo(two.getArtist()));
    System.out.println(songList);
  }
}
