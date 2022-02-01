package ch10b;

import java.util.ArrayList;
import java.util.List;

class MockSongs {
  public static List<String> getSongStrings() {
    List<String> songs = new ArrayList<>();
    songs.add("somersault");
    songs.add("cassidy");
    songs.add("$10");
    songs.add("havana");
    songs.add("Cassidy");
    songs.add("50 Ways");
    return songs;
  }

  public static List<SongV2> getSongsV2() {
    List<SongV2> mySongs = new ArrayList<>();
    mySongs.add(new SongV2("somersault", "zero 7", "50"));
    mySongs.add(new SongV2("cassidy", "grateful dead", "65"));
    mySongs.add(new SongV2("$10", "hitchhiker", "80"));

    mySongs.add(new SongV2("havana", "cabello", "60"));
    mySongs.add(new SongV2("Cassidy", "grateful dead", "65"));
    mySongs.add(new SongV2("50 ways", "simon", "70"));
    return mySongs;
  }

}
