import java.util.ArrayList;

class MockSongV2File {

    public ArrayList<SongV2> getSongsV2() {
        ArrayList<SongV2> mySongs = new ArrayList<SongV2>();
        mySongs.add(new SongV2("somersault", "zero 7", "50"));
        mySongs.add(new SongV2("cassidy", "grateful dead", "65"));
        mySongs.add(new SongV2("$10", "hitchhiker", "80"));

        mySongs.add(new SongV2("havana", "cabello", "60"));
        mySongs.add(new SongV2("Cassidy", "grateful dead", "65"));
        mySongs.add(new SongV2("50 ways", "simon", "70"));
        return mySongs;
    }
}