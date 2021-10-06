import java.util.List;

class MockSongFile {

  public List<SongV3> getSongsV3() {
    return List.of(new SongV3("somersault", "zero 7", "50"),
                   new SongV3("cassidy", "grateful dead", "65"),
                   new SongV3("$10", "hitchhiker", "80"),
                   new SongV3("havana", "cabello", "60"),
                   new SongV3("Cassidy", "grateful dead", "65"),
                   new SongV3("50 ways", "simon", "70"));
  }
}