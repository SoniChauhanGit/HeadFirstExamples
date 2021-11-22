class SongV2 {
  String title;
  String artist;
  String bpm;

  SongV2(String t, String a, String b) {
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

  public String getBpm() {
    return bpm;
  }

  public String toString() {
    return title;
  }
}
