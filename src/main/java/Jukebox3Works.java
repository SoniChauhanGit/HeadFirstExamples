import java.util.ArrayList;
import java.util.Collections;

public class Jukebox3Works {

    ArrayList<SongV3> mySongs;
    MockSongV2File mockFile = new MockSongV2File();

    public static void main(String[] args) {
        new Jukebox3Works().go();
    }

    public void go() {
        mySongs = mockFile.getSongsV3();
        System.out.println(mySongs);
        Collections.sort(mySongs);
        System.out.println(mySongs);
    }
}