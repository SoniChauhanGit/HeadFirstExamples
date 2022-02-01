package ch10b;

public class SongV4 implements Comparable<SongV4> {
  String title;
  String artist;
  String bpm;

  public int compareTo(SongV4 s) {
    return title.compareTo(s.getTitle());
  }

  SongV4(String t, String a, String b) {
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
    return title + ": " + artist;
  }
}
