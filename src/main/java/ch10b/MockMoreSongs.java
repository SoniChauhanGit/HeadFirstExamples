package ch10b;

import java.util.ArrayList;
import java.util.List;

class MockMoreSongs {

  public List<SongV3> getSongs() {
    List<SongV3> mySongs = new ArrayList<>();
    mySongs.add(new SongV3("somersault", "zero 7", "50"));
    mySongs.add(new SongV3("cassidy", "grateful dead", "65"));
    mySongs.add(new SongV3("$10", "hitchhiker", "80"));
    mySongs.add(new SongV3("havana", "cabello", "60"));
    mySongs.add(new SongV3("$10", "hitchhiker", "80"));
    mySongs.add(new SongV3("cassidy", "grateful dead", "65"));
    mySongs.add(new SongV3("50 ways", "simon", "70"));
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