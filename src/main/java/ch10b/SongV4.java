package ch10b;

public class SongV4 implements Comparable<SongV4> {
  private String title;
  private String artist;
  private String bpm;

  public boolean equals(Object aSong) {
    SongV4 other = (SongV4) aSong;
    return title.equals(other.getTitle());
  }

  public int hashCode() {
    return title.hashCode();
  }

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
    return title;
  }
}
