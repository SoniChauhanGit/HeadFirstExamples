import java.util.Collections;
import java.util.List;

public class Jukebox4 {

  List<SongV3> mySongs;
  MockSongV2File mockFile = new MockSongV2File();

  public static void main(String[] args) {
    new Jukebox4().go();
  }

  public void go() {
    mySongs = mockFile.getSongsV3();
    System.out.println(mySongs);
    Collections.sort(mySongs);
    System.out.println(mySongs);
  }
}