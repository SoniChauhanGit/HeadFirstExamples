class SongV3 implements Comparable<SongV3> {
  String title;
  String artist;
  String bpm;

  public int compareTo(SongV3 s) {
    return title.compareTo(s.getTitle());
  }

  SongV3(String t, String a, String b) {
    title = t;
    artist = a;
    bpm = b;
  }

  public String getTitle() {
    return title;
  }

  public String getArtist() {
    return artist;
  }

  public String getBPM() {
    return bpm;
  }

  public String toString() {
    return title;
  }
}
