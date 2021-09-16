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
    public ArrayList<SongV3> getSongsV3() {
        ArrayList<SongV3> mySongs = new ArrayList<>();
        mySongs.add(new SongV3("somersault", "zero 7", "50"));
        mySongs.add(new SongV3("cassidy", "grateful dead", "65"));
        mySongs.add(new SongV3("$10", "hitchhiker", "80"));

        mySongs.add(new SongV3("havana", "cabello", "60"));
        mySongs.add(new SongV3("Cassidy", "grateful dead", "65"));
        mySongs.add(new SongV3("50 ways", "simon", "70"));
        return mySongs;
    }
}