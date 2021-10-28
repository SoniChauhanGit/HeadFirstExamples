import java.util.*;
public class Jukebox5Consistent {
    List<SongV3> songList = new ArrayList<SongV3>();
    MockSongV3File mockFile = new MockSongV3File();

    public static void main(String[] args) {
        new Jukebox5Consistent().go();
    }

    public void go() {
        songList = mockFile.getSongsV3();
        System.out.println(songList);
        TitleCompare titleCompare = new TitleCompare();
        songList.sort(titleCompare);
        System.out.println(songList);
        ArtistCompare  artistCompare = new ArtistCompare();
        songList.sort(artistCompare);
        System.out.println(songList);
    }

    class TitleCompare implements Comparator<SongV3> {
        public int compare(SongV3 one, SongV3 two) {
            return one.getTitle().compareTo(two.getTitle());
        }
    }
    class ArtistCompare implements Comparator<SongV3> {
        public int compare(SongV3 one, SongV3 two) {
            return one.getArtist().compareTo(two.getArtist());
        }
    }
}
