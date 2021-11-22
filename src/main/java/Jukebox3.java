import java.util.List;

public class Jukebox3 {

  List<SongV2> mySongs;
  MockSongV2File mockFile = new MockSongV2File();

  public static void main(String[] args) {
    new Jukebox3().go();
  }

  public void go() {
    mySongs = mockFile.getSongsV2();
    System.out.println(mySongs);
    // This line intentionally fails compilation
//        Collections.sort(mySongs);
    System.out.println(mySongs);
  }
}