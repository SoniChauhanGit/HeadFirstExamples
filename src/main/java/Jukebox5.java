import java.util.*;
public class Jukebox5 {
    ArrayList<SongV3> songList = new ArrayList<SongV3>();
    MockSongV2File mockFile = new MockSongV2File();

    public static void main(String[] args) {
        new Jukebox5Consistent().go();
    }

    public void go() {
        songList = mockFile.getSongsV3();
        System.out.println(songList);
        Collections.sort(songList);
        System.out.println(songList);
        ArtistCompare  artistCompare = new ArtistCompare();
        songList.sort(artistCompare);
        System.out.println(songList);
    }

    class ArtistCompare implements Comparator<SongV3> {
        public int compare(SongV3 one, SongV3 two) {
            return one.getArtist().compareTo(two.getArtist());
        }
    }
}
