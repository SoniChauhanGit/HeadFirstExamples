package ch10b;

import java.util.List;

public class Jukebox3 {

  List<SongV2> mySongs;

  public static void main(String[] args) {
    new Jukebox3().go();
  }

  public void go() {
    mySongs = MockSongs.getSongsV2();
    System.out.println(mySongs);
    // This line intentionally fails compilation
//        Collections.sort(mySongs);
    System.out.println(mySongs);
  }
}