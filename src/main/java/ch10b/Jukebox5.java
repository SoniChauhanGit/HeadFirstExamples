package ch10b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Jukebox5 {
  List<SongV3> songList = new ArrayList<>();
  MockSongV3File mockFile = new MockSongV3File();

  public static void main(String[] args) {
    new Jukebox5().go();
  }

  public void go() {
    songList = mockFile.getSongsV3();
    System.out.println(songList);
    Collections.sort(songList);
    System.out.println(songList);
    ArtistCompare artistCompare = new ArtistCompare();
    songList.sort(artistCompare);
    System.out.println(songList);
  }
}

class ArtistCompare implements Comparator<SongV3> {
  public int compare(SongV3 one, SongV3 two) {
    return one.getArtist().compareTo(two.getArtist());
  }
}
