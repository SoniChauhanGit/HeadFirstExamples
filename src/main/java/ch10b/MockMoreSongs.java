package ch10b;

import java.util.ArrayList;
import java.util.List;

class MockMoreSongs {

  public List<SongV4> getSongs() {
    List<SongV4> mySongs = new ArrayList<>();
    mySongs.add(new SongV4("somersault", "zero 7", "50"));
    mySongs.add(new SongV4("cassidy", "grateful dead", "65"));
    mySongs.add(new SongV4("$10", "hitchhiker", "80"));
    mySongs.add(new SongV4("havana", "cabello", "60"));
    mySongs.add(new SongV4("$10", "hitchhiker", "80"));
    mySongs.add(new SongV4("cassidy", "grateful dead", "65"));
    mySongs.add(new SongV4("50 ways", "simon", "70"));
    return mySongs;
  }
}

// "text file" version
//somersault, zero 7, 50
//cassidy, grateful dead, 65
//$10, hitchhiker, 80
//havana, cabello, 60
//$10, hitchhiker, 80
//cassidy, grateful dead, 65
//50 ways, simon, 70